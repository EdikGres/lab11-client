package com.company;

import java.io.IOException;
import java.net.*;
import java.util.Queue;

public class MessageSender implements Runnable {
    private DatagramSocket socket;
    private String hostname;
    private int port;
    private InetAddress address;
    private Queue<String > queue;

    public MessageSender(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        try {
            address = InetAddress.getByName(hostname);
            socket = new DatagramSocket();
            socket.connect(address, port);
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    void sendMessage(String str) {

        DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), address, port);
        try {
            socket.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

    }
}
