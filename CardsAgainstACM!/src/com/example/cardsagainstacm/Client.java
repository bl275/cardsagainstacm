package com.example.cardsagainstacm;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Olen on 2/22/14.
 */
public class Client implements Runnable{
    private Socket clientSocket;
    private static final int portNumber = 6969;
    private PrintWriter clientOutput;
    private BufferedReader input;
    private String hostName;

    public Client(String hostName){
        this.hostName = hostName;
    }

    public String getInput(){
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public void sendOutput(String output){
        clientOutput.println(output);
    }

    @Override
    public void run() {
        try {
            InetAddress serverAddr = InetAddress.getByName(hostName);
            clientSocket = new Socket(serverAddr, portNumber);
            //clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);
            //input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
