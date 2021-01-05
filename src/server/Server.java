package server;

import java.net.DatagramSocket;

public class Server {

    private static DatagramSocket socket;

    public static void start(int port) {
        try {
            socket = new DatagramSocket(port);
            System.out.println("Server started on port, " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void broadcast() {
    }

    public static void send() {
    }

    public static void listen() {
    }

    public static void stop() {
    }
}
