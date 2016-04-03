package com.example.root.com.example.root.ExtraActivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.giveblood.R;

public class Contact_Us extends AppCompatActivity {
    private Button send;
    private EditText name,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        send = (Button) findViewById(R.id.btnsend);


        name = (EditText) findViewById(R.id.etname);
        message = (EditText) findViewById(R.id.etmessage);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("+923239549789","Name = "+name.getText().toString()+" Message = "+message.getText().toString());
            }
        });
    }
    protected void sendMessage(String theNum, String myMsg) {
        final ProgressDialog progressDialog = new ProgressDialog(Contact_Us.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        String sent= "Message Send";
        String Delivered= "Message Delivered";
        PendingIntent sentPI= PendingIntent.getBroadcast(this, 0, new Intent(sent), 0);
        PendingIntent deliverPI=PendingIntent.getBroadcast(this, 0, new Intent(Delivered), 0);
        registerReceiver(new BroadcastReceiver() {

            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(Contact_Us.this, "SMS Sent", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        //  notification();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic Failure", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        break;


                }
            }
        }, new IntentFilter(sent));

        registerReceiver(new BroadcastReceiver() {

            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(Contact_Us.this, "SMS Delivered", Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS Not Delivered", Toast.LENGTH_LONG).show();
                        break;


                }
            }
        }, new IntentFilter(Delivered));

        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage(theNum, null, myMsg, sentPI, deliverPI);
        // Toast.makeText(getBaseContext(), "Message sended ", Toast.LENGTH_SHORT).show();

    }

}
