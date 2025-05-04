import java.util.Scanner;

class Tring {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);  // Initialize Scanner for input

        try {
            // Step 1: Get the number of nodes
            System.out.print("Enter the number of nodes: ");
            int n = sc.nextInt();

            // Step 2: Print nodes in the ring
            System.out.print("Nodes in the ring: ");
            for (int i = 0; i < n; i++) {
                System.out.print(" " + i);
            }
            System.out.println(" " + 0);

            // Initialize the token to the first node
            int token = 0;

            // Step 3: Run the token passing loop
            while (true) {
                // Step 4: Get sender, receiver, and data
                System.out.print("Enter sender: ");
                int s = sc.nextInt();
                System.out.print("Enter receiver: ");
                int r = sc.nextInt();
                System.out.print("Enter Data: ");
                String d = sc.next();

                // Step 5: Simulate token passing
                System.out.print("Token passing: ");
                for (int i = token, j = token; (i % n) != s; i++, j = (j + 1) % n) {
                    System.out.print(" " + j + "->");
                }
                System.out.println(" " + s); // Show where token reaches the sender

                System.out.println("Sender " + s + " sending data: " + d);

                // Step 6: Forward data from sender to receiver
                for (int i = (s + 1) % n; i != r; i = (i + 1) % n) {
                    System.out.println("Data " + d + " forwarded by " + i);
                }
                System.out.println("Receiver " + r + " received data: " + d);

                // Step 7: Update token to the sender's position
                token = s;
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            // Ensure to close the scanner to avoid resource leaks
            sc.close();
        }
    }
}
