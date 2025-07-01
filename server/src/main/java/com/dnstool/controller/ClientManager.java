package com.dnstool.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientManager {

    private PrintWriter out;
    private Scanner in;

    public ClientManager(Socket client) {
        try {
            out = new PrintWriter(client.getOutputStream(),true);
            in = new Scanner(client.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Hàm xử lý dữ liệu và phản hồi với client. Blocking IO.
    public void start() {

        // Nhận dữ liệu từ client
        String data = in.nextLine();

        System.out.println("Client gửi thông báo : " + data);

        //Gửi phản hồi cho client
        out.println("Xin chào client.");
    }
}
