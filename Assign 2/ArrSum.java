import mpi.MPI;

public class ArrSum {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);  // Initialize MPI environment

        int rank = MPI.COMM_WORLD.Rank();  // Get ID of this process
        int size = MPI.COMM_WORLD.Size();  // Total number of processes

        int unitSize = 5;  // Number of elements per process
        int root = 0;

        int[] sendBuffer = null;  // Buffer to store elements at root
        int[] receiveBuffer = new int[unitSize];  // Buffer to receive chunk at each process
        int[] newReceiveBuffer = new int[size];  // Buffer to collect intermediate sums at root

        if (rank == root) {
            sendBuffer = new int[unitSize * size];
            int totalElements = unitSize * size;
            System.out.println("Enter " + totalElements + " elements:");
            for (int i = 0; i < totalElements; i++) {
                sendBuffer[i] = i;  // (You can use Scanner for user input)
                System.out.println("Element " + i + "\t = " + sendBuffer[i]);
            }
        }

        // Distribute chunks of data to each process
        MPI.COMM_WORLD.Scatter(
            sendBuffer, 0, unitSize, MPI.INT,
            receiveBuffer, 0, unitSize, MPI.INT,
            root
        );

        // Compute local sum
        for (int i = 1; i < unitSize; i++) {
            receiveBuffer[0] += receiveBuffer[i];
        }

        System.out.println("Intermediate sum at process " + rank + ": " + receiveBuffer[0]);

        // Gather local sums at the root
        MPI.COMM_WORLD.Gather(
            receiveBuffer, 0, 1, MPI.INT,
            newReceiveBuffer, 0, 1, MPI.INT,
            root
        );

        // Compute final sum at root
        if (rank == root) {
            int totalSum = 0;
            for (int i = 0; i < size; i++) {
                totalSum += newReceiveBuffer[i];
            }
            System.out.println("Final sum: " + totalSum);
        }

        MPI.Finalize();  // Terminate MPI environment
    }
}


/*Compile your code:
 * javac -cp ".:/path/to/mpj/lib/mpj.jar" ArrSum.java

 Run using MPJ Launcher (for 4 processes, for example):
cd /path/to/compiled/class
/path/to/mpj/bin/mpjrun.sh -np 4 ArrSum

Example :- /home/user/mpj/bin/mpjrun.sh -np 4 ArrSum
-np 4 means run with 4 processes.
 */
