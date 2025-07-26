package com.dnstool.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerManager {

    private Selector selector;
    ServerSocketChannel serverSocket;

    public ServerManager() {

        int port = 9999;

        try {
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();

            serverSocket.bind(new InetSocketAddress(port));

            serverSocket.configureBlocking(false);

            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Tiếp nhận kết nối từ client và xử lý.
    public void run() {
        while (true) {

            try {
                selector.select(); // blocking đợi có event
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iter = keys.iterator();

            handles(iter);
        }
    }

    private void handles(Iterator<SelectionKey> iter) {
        while (iter.hasNext()) {
            SelectionKey key = iter.next();
            iter.remove();

            if (key.isAcceptable()) {
                connect();
            } else if (key.isReadable()) {
                new ClientManager(key).handles();
            }
        }
    }

    private void connect() {
        try {
            SocketChannel client = serverSocket.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            System.out.println("Client connected: " + client.getRemoteAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
