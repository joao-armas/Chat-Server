package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {

    private static DatagramSocket socket;
    private static boolean running;
    private static int clientID;
    private static ArrayList<ClientInfo> clients = new ArrayList<ClientInfo>();

    public static void start(int port) {
        try {
            socket = new DatagramSocket(port);
            running = true;
            listen();
            System.out.println("Server started on port, " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void broadcast(String message) {
        for (ClientInfo info : clients) {
            send(message, info.getAddress(), info.getPort());
        }
    }

    public static void send(String message, InetAddress address, int port) {
        try {

            message += "\\e";
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
            System.out.println("Sent message to, " + address.getHostAddress() + ":" + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listen() {
        Thread listenThread = new Thread("Chat-Server Listener") {
            public void run() {
                try {
                    while (running) {
                        byte[] data = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        socket.receive(packet);

                        String message = new String(data);
                        message = message.substring(0, message.indexOf("\\e"));

                        //MANAGE MESSAGE
                        if (!isCommand(message, packet))
                            broadcast(message);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        listenThread.start();
    }

    /*
     *SERVER COMMAND LIST
     *\\con[name]: -> Connects Client to server
     *\\dis[id]: -> Disconnects Client from server
     */

    private static boolean isCommand(String message, DatagramPacket packet) {

        if (message.startsWith("\\con:")) {
            // RUN CONNECTION CODE

            String name = message.substring(message.indexOf(":") + 1);
            clients.add(new ClientInfo(name, clientID++, packet.getAddress(), packet.getPort()));


            return true;
        }
        return false;
    }

    public static void stop() {
        running = false;
    }
}
