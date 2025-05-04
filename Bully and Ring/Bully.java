import java.util.*;

public class Bully {
    int coordinator;
    int max_processes;
    boolean processes[];

    public Bully(int max) {
        max_processes = max;
        processes = new boolean[max_processes];
        coordinator = max;

        for (int i = 0; i < max; i++) {
            processes[i] = true;
            System.out.println("P" + (i + 1) + " created.");
        }
        System.out.println("Process P" + coordinator + " is the initial coordinator.");
    }

    void displayProcesses() {
        for (int i = 0; i < max_processes; i++) {
            if (processes[i]) {
                System.out.println("P" + (i + 1) + " is up.");
            } else {
                System.out.println("P" + (i + 1) + " is down.");
            }
        }
        System.out.println("Current coordinator is P" + coordinator);
    }

    void upProcess(int process_id) {
        if (!processes[process_id - 1]) {
            processes[process_id - 1] = true;
            System.out.println("Process P" + process_id + " is now up.");
        } else {
            System.out.println("Process P" + process_id + " is already up.");
        }
    }

    void downProcess(int process_id) {
        if (processes[process_id - 1]) {
            processes[process_id - 1] = false;
            System.out.println("Process P" + process_id + " is now down.");
        } else {
            System.out.println("Process P" + process_id + " is already down.");
        }
    }

    void runElection(int process_id) {
        System.out.println("Process P" + process_id + " has initiated an election.");
        int newCoordinator = process_id;

        for (int i = process_id; i < max_processes; i++) {
            if (processes[i]) {
                System.out.println("Election message sent from P" + process_id + " to P" + (i + 1));
                newCoordinator = i + 1;
            }
        }

        coordinator = newCoordinator;
        System.out.println("New coordinator is P" + coordinator);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bully bully = null;

        while (true) {
            System.out.println("\n--- Bully Algorithm ---");
            System.out.println("1. Create processes");
            System.out.println("2. Display processes");
            System.out.println("3. Up a process");
            System.out.println("4. Down a process");
            System.out.println("5. Run election");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter number of processes: ");
                    int n = sc.nextInt();
                    bully = new Bully(n);
                    break;
                case 2:
                    bully.displayProcesses();
                    break;
                case 3:
                    System.out.print("Enter process to up: ");
                    bully.upProcess(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter process to down: ");
                    bully.downProcess(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter process initiating election: ");
                    bully.runElection(sc.nextInt());
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
