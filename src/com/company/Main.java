package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: client <hostname> <port>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        MessageSender sender = new MessageSender(hostname, port);
        MessageReceiver receiver = new MessageReceiver(sender.getSocket());
        Thread th = new Thread(receiver);
        th.start();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            sender.sendMessage(msg);
            if(msg.startsWith("@quit")){
                System.exit(1);
            }
        }
    }
}
