package com.dnstool.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {

    private final ServerSocket server;

    public ServerManager() {
        try {
            int PORT = 9999;
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Tiếp nhận kết nối từ client và xử lý.
    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                new ClientManager(client).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
