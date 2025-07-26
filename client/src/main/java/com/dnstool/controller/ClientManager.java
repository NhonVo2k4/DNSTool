package com.dnstool.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ClientManager {

    private Selector selector;
    private final SocketChannel client;

    public ClientManager() {
        int port = 9999;
        String host = "localhost";
        try {

            client = SocketChannel.open(new InetSocketAddress(host, port));

            client.configureBlocking(false);

            Selector selector = Selector.open();
            client.register(selector, SelectionKey.OP_WRITE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();

                handles(iter);

                client.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handles(Iterator<SelectionKey> iter) {
        while (iter.hasNext()) {
            SelectionKey key = iter.next();
            iter.remove();

            if (key.isWritable()) {

                // Nhập yêu cầu
                String request = "";

                //Gửi yêu cầu

            } else if (key.isReadable()) {
                // Nhận phản hổi


                //Xử lý

            }
        }
    }


}
