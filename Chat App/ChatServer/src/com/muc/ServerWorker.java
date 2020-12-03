package com.muc;

import java.io.*;
import java.net.Socket;
import org.apache.commons.lang3.StringUtils;

public class ServerWorker extends Thread {

    private final Socket clientSocket;

    public ServerWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outPutStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ( ( line = reader.readLine()) != null) {
            String[] tokens = StringUtils.split(line);
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];
                if ("quit".equalsIgnoreCase(cmd)) {
                    break;
                } else {
                    String msg = "unknown command: " + cmd + "\n";
                    outPutStream.write(msg.getBytes());
                }
            }
        }

        clientSocket.close();
    }
}
