package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String in = input.readLine();
                    if (in.contains("?msg=Exit")) {
                        server.close();
                        output.write("Exit".getBytes());
                        System.out.println(in);
                    }
                    if (in.contains("?msg=Hello")) {
                        output.write("Hello".getBytes());
                        System.out.println(in);
                    } else {
                        output.write("What".getBytes());
                        System.out.println(in);
                    }
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
            System.out.println("Server stop");
            System.out.println(server.isClosed());
        }
    }
}