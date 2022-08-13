package com.example.notesapptutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {

    EditText etPhone;
    Button btSend;
    TextView allnotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        getSupportActionBar().hide();
        etPhone=findViewById(R.id.msgeditnote);
        btSend=findViewById(R.id.msgbtn);
        allnotes=findViewById(R.id.allnotes);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MessageActivity.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendMessage();
                }
                else {
                    ActivityCompat.requestPermissions(MessageActivity.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });
        allnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageActivity.this, notesactivity.class));
            }
        });

    }
    private  void sendMessage(){
        String sPhone=etPhone.getText().toString().trim();
        String sMessage= "Welcome To Keep Notes," +
                "\nDownload The App To Keep Your Notes simultaneously on any devices" +
                "\nLink to download:\n https://bit.ly/3Ag4tH5";
        if (!sPhone.equals(""))
        {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(sPhone,null,sMessage,null,null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MessageActivity.this,notesactivity.class));
        }
        else
        {
            Toast.makeText(this, "Enter value first", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            sendMessage();
        }
        else {
            Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }
}