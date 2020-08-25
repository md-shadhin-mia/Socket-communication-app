package com.example.socketcomunication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton bnt;
    Button connectBtn;
    EditText msg;
    ListView list;
   // TextView outs;
   ArrayList<Messages> allege;
   Handler handler = new Handler();
   ListAdapter adapter;
   TextView ipAddress;
   TextView portAddress;
   View Connector;
   View messagers;
   View prograssing;
   CommunicationSocket comSoc = new CommunicationSocket();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_main);
        //working with Connector
        Connector = findViewById(R.id.Connector);
        connectBtn = findViewById(R.id.button_connect);
        messagers = findViewById(R.id.messangers);
        prograssing =findViewById(R.id.progressing);
        bnt = findViewById(R.id.button_send);
        msg = findViewById(R.id.messagebox);
        list = findViewById(R.id.listview);
        ipAddress = findViewById(R.id.ip_address);
        portAddress = findViewById(R.id.port_address);
        connectBtn.setOnClickListener(this);
        //set button lisenet

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_send :
               // outs.setText("Send: "+msg.getText());
                adapter.add(new Messages("Send: "+msg.getText().toString(), false));
                comSoc.sendMessage(msg.getText().toString());
                msg.setText("");
                list.setSelection(list.getCount() -1);
                break;
            case R.id.button_connect:
                String ip = ipAddress.getText().toString();
                int port =Integer.parseInt(portAddress.getText().toString());
                prograssing.setVisibility(View.VISIBLE);
                Boolean connect = comSoc.Connection(ip, port);
                prograssing.setVisibility(View.GONE);
                if(connect){
                    Connector.setVisibility(View.GONE);
                    messagers.setVisibility(View.VISIBLE);
                    bnt.setOnClickListener(this);
                    allege = new ArrayList<>();
                    adapter = new ListAdapter(this, allege);
                    list.setDivider(null);
                    list.setAdapter(adapter);
                    checkMessage.run();
                }else {
                    Toast.makeText(this, "Connect is not posible", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private Runnable checkMessage = new Runnable() {
        @Override
        public void run() {
            if(comSoc.isIncomingMessage()){
                String msg = comSoc.getReply();
                adapter.add(new Messages(msg, true));
                list.setSelection(list.getCount() -1);
            }
            handler.postDelayed(this, 1000);
        }
    };


}