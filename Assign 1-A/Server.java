import java.net.*;
import java.io.*;

class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket on port 5000
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server is waiting for client...");

            // Accept the client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Set up input stream to receive data
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine(); // read one line from client
            System.out.println("Received from client: " + message);

            // Set up output stream to send response
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from Server!");

            // Close connections
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
