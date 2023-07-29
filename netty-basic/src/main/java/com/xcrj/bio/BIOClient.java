package com.xcrj.bio;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BIOClient {
    static final String HOST = "localhost";
    static final int PORT = 7000;
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket(HOST, PORT);
        OutputStream os =socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            os.write(msg.getBytes());
            os.flush();
        }
    }
}
