package com.company;

import java.io.IOException;
import java.net.*;
import java.util.Queue;

public class MessageReceiver implements Runnable{
    private DatagramSocket socket;
    private Queue<String > queue;
    byte[] buf;

    public MessageReceiver(DatagramSocket socket) {
        this.socket = socket;
        buf = new byte[1024];
    }

    public void start(){
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        try {
            socket.receive(dp);
            String str = new String(dp.getData(), 0, dp.getLength());
            System.out.println("From server: " + str);
            if(str.startsWith("@quit")){
                System.exit(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            start();
        }
    }
}
