package tech.nehavinjapuri.superposition;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tech.nehavinjapuri.superposition.R;

public class MainActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button police;
    Button parents;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo = "9255489619";
    String phoneNo2 = "9258562489";
    String message = "Emergency. Please come home immediately. Please do not call Neha.";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        police = (Button) findViewById(R.id.btnSendSMS);
        parents = (Button) findViewById(R.id.btnSendSMS5);


        police.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                policeMess(phoneNo);


            }
        });
        parents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage(phoneNo2,message);



            }
        });
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.ring);
        Button button = (Button) this.findViewById(R.id.btnSendSMS4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

            }
        });

    }
    protected void policeMess(String phone){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, "Neha Vinjapuri is in danger. Please do not call her. ", null, null);
        smsManager.sendTextMessage(phone, null, "Parent's Number: 9258562489", null, null);
        smsManager.sendTextMessage(phone, null, "https://www.google.com/maps/place/37%C2%B048'20.6%22N+122%C2%B016'12.1%22W/@37.805708,-122.2722053,17z/data=!4m5!3m4!1s0x0:0x0!8m2!3d37.8057222!4d-122.2700278", null, null);


    }

    protected void sendSMSMessage(String phone, String mess) {



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, mess, null, null);
            smsManager.sendTextMessage(phoneNo2, null, "https://www.google.com/maps/place/37%C2%B048'20.6%22N+122%C2%B016'12.1%22W/@37.805708,-122.2722053,17z/data=!4m5!3m4!1s0x0:0x0!8m2!3d37.8057222!4d-122.2700278", null, null);

        }
    }





    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo2, null, message, null, null);
                    smsManager.sendTextMessage(phoneNo2, null, "https://www.google.com/maps/place/37%C2%B048'20.6%22N+122%C2%B016'12.1%22W/@37.805708,-122.2722053,17z/data=!4m5!3m4!1s0x0:0x0!8m2!3d37.8057222!4d-122.2700278", null, null);


                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}