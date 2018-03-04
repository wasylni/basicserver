package com.wasiluk.basicserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer {

    private ServerSocket server;
    private static final int SERVER_PORT = 8080;

    private BasicServer(String[] serverArgs) throws Exception {
        if (serverArgs.length==0) {
            this.server = new ServerSocket(SERVER_PORT);
        } else {
            this.server = new ServerSocket(Integer.valueOf(serverArgs[0]));
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
        com.wasiluk.basicserver.BasicServer app = new com.wasiluk.basicserver.BasicServer(args);
        System.out.println("\r\nRunning Server: " +
                "Host=" + app.getSocketAddress().getHostAddress() +
                " Port=" + app.getPort());

        app.listen();
    }
}