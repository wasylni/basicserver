package com.wasiluk.basicserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer {

    private ServerSocket server;

    private BasicServer(String ipAddress) throws Exception {
        if (ipAddress == null || ipAddress.isEmpty()) {
            this.server = new ServerSocket(0, 1, InetAddress.getLocalHost());
        } else {
            this.server = new ServerSocket(0, 1, InetAddress.getByName(ipAddress));
        }
    }

    private void listen() throws Exception {
        String data;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        while ((data = in.readLine()) != null) {
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
        }
    }

    private InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    private int getPort() {
        return this.server.getLocalPort();
    }

    public static void main(String[] args) throws Exception {
        com.wasiluk.basicserver.BasicServer app = new com.wasiluk.basicserver.BasicServer(args==null|| args.length==0?null:args[0]);
        System.out.println("\r\nRunning Server: " +
                "Host=" + app.getSocketAddress().getHostAddress() +
                " Port=" + app.getPort());

        app.listen();
    }
}