package com.muc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) throws IOException {
        int portNumber = 5675;
        ServerSocket serverSocket = new ServerSocket(portNumber);

        while(true) {
            Socket clientSocket = serverSocket.accept();
            ServerWorker worker = new ServerWorker(clientSocket );
            worker.start();
        }
    }
}
