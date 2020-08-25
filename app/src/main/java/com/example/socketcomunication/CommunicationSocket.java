package com.example.socketcomunication;

import android.os.Build;

import java.io.*;
import java.net.*;

public class CommunicationSocket {
    private Socket socket;
    public DataInputStream  Dis;
    public DataOutputStream Dio;
    public boolean Connection(String host, int port){
        try {
            InetAddress serverAddr = InetAddress.getByName(host);
            socket = new Socket(serverAddr, port);
            Dis = new DataInputStream(socket.getInputStream());
            Dio = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendMessage(String message){
        try {
            Dio.writeUTF(message);
            Dio.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean isIncomingMessage(){
        try {
            if(Dis.available() > 0) return true;
            else return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String  getReply(){
        try {
            String repaly = Dis.readUTF();
            return repaly;
        } catch (IOException e) {
            return "Errpr: "+e.getMessage();
        }

    }
}
