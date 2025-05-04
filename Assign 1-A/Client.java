import java.net.*;
import java.io.*;

class Client{
    public static void main(String[] args) {
        try {
            // Connect to server at localhost and port 5000
            Socket socket = new Socket("localhost", 8888);

            // Send message to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from Client!");

            // Receive response from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String reply = in.readLine();
            System.out.println("Received from server: " + reply);

            // Close connections
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
