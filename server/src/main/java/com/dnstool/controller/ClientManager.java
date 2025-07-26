package com.dnstool.controller;

import com.dnstool.service.ServiceManager;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ClientManager {

    private final SocketChannel client;
    private final ByteBuffer buffer;
    private final int read;

    public ClientManager(SelectionKey key) {
        this.client = (SocketChannel) key.channel();
        buffer = ByteBuffer.allocate(1024);

        try {
            read = client.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Hàm xử lý dữ liệu và phản hồi với client. Blocking IO.
    public void handles() {
        try {
            if (read == -1) {
                client.close();
                System.out.println("Client disconnected");
            } else {

                // Nhận dữ liệu
                String request = receiveData();

                // Xử lý
                ServiceManager serviceManager = new ServiceManager(request);

                //Trả kết quả.
                sendData();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendData() {

    }

    private String receiveData() {
        return "";
    }

}
