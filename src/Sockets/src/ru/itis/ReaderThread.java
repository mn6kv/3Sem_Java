package ru.itis;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderThread extends Thread {

    private BufferedReader fromClient;
    public String msg;

    public ReaderThread(BufferedReader fromClient) {
        this.fromClient = fromClient;
    }

    @Override
    public void run() {
        try {
            msg = fromClient.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
