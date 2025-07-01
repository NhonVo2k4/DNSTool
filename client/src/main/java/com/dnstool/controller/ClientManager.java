package com.dnstool.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientManager {

    private final PrintWriter out;
    private final Scanner in;

    public ClientManager() {
        int port = 9999;
        String host = "localhost";
        try {

            // Kết nối tới server.
            Socket server = new Socket(host, port);

            //mở cổng gửi và nhận dữ liệu.
            in = new Scanner(server.getInputStream());
            out = new PrintWriter(server.getOutputStream(), true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handles() {
        while (true) {

            Scanner keyboard = new Scanner(System.in);

            System.out.print("Nhập tin nhắn của bạn: ");

            String message = keyboard.nextLine();

            out.println(message);

            System.out.println(in.nextLine());
        }
    }
}
