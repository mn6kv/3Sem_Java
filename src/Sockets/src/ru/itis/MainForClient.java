package ru.itis;

import java.util.Scanner;

public class MainForClient {
    public static void main(String[] args) {
        SocketClient socketClient = new SocketClient("localhost", 1337);

        Scanner sc = new Scanner(System.in);
        while (true) {
            socketClient.sendMessage(sc.nextLine());
        }
    }
}
