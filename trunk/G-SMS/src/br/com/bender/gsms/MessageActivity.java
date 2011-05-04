package br.com.bender.gsms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MessageActivity extends Activity {
	
	private Button btEnviar;
	private Button btAddLocAtual;
	private EditText etMessageEditor;
	private EditText etNumeroDestino;
    private LocationManager lm;
    private GPSLocationListener locationListener;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        
        //Recupera campos
        etMessageEditor = (EditText) findViewById(R.id.message_editor);
        etNumeroDestino = (EditText) findViewById(R.id.numeroDestino);
        
        //Botão Adicionar Localização
        btAddLocAtual = (Button) findViewById(R.id.addLocAtual);
        btAddLocAtual.setOnClickListener (
        		new View.OnClickListener () {
            		public void	onClick (View v) {
            			updateMessageEditor();
            		}
            	}
        	);
        
        //Botão Enviar SMS
        btEnviar = (Button) findViewById(R.id.enviar);
        btEnviar.setOnClickListener (
        		new View.OnClickListener () {
            		public void	onClick (View v) {
            			enviarMensagem();
            		}
            	}
        	);
        //---use the LocationManager class to obtain GPS locations---
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);    
        
        locationListener = new GPSLocationListener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
    }
    
    public void updateMessageEditor(){
    	String	s = etMessageEditor.getText().toString();
    	String stringAux = "("+locationListener.getLongetitude() + ";" + locationListener.getLatitude() + ")";
    	etMessageEditor.setText(etMessageEditor.getText()+" "+stringAux);
    };

    public void enviarMensagem(){
        String phoneNo = etNumeroDestino.getText().toString();
        String message = etMessageEditor.getText().toString();                 
        if (phoneNo.length()>0 && message.length()>0)                
            sendSMS(phoneNo, message);                
        else
            Toast.makeText(getBaseContext(), 
                "Please enter both phone number and message.", 
                Toast.LENGTH_SHORT).show();    	
        };
    
    //---sends an SMS message to another device---
    private void sendSMS(String phoneNumber, String message){        
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
 
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
            new Intent(SENT), 0);
 
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
            new Intent(DELIVERED), 0);
 
        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off", 
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));
 
        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {  case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered", 
                                Toast.LENGTH_SHORT).show();
                        break;                        
                }
            }
        }, new IntentFilter(DELIVERED));        
 
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);        
    }

}
