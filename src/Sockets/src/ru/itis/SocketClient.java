package ru.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    private Socket client;
    private PrintWriter toServer;
    private BufferedReader fromServer;

    public SocketClient(String host, int port) {
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            new Thread(receiverMessagesTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        toServer.println(message);
    }

    private Runnable receiverMessagesTask = () -> {
      while (true) {
          String msgFromServer = null;
          try {
              msgFromServer = fromServer.readLine();
          } catch (IOException e) {
              throw new IllegalStateException(e);
          }
          if (msgFromServer != null) {
              System.out.println(msgFromServer);
          }
      }
    };
}
