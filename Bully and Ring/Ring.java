import java.util.*;

public class Ring {
    int max_processes;
    int coordinator;
    boolean processes[];

    public Ring(int max) {
        max_processes = max;
        processes = new boolean[max];
        for (int i = 0; i < max; i++) {
            processes[i] = true;
            System.out.println("P" + (i + 1) + " created.");
        }
        coordinator = max;
        System.out.println("P" + coordinator + " is the initial coordinator.");
    }

    void displayProcesses() {
        for (int i = 0; i < max_processes; i++) {
            System.out.println("P" + (i + 1) + " is " + (processes[i] ? "up" : "down") + ".");
        }
        System.out.println("Current coordinator is P" + coordinator);
    }

    void upProcess(int process_id) {
        if (!processes[process_id - 1]) {
            processes[process_id - 1] = true;
            System.out.println("P" + process_id + " is now up.");
        } else {
            System.out.println("P" + process_id + " is already up.");
        }
    }

    void downProcess(int process_id) {
        if (processes[process_id - 1]) {
            processes[process_id - 1] = false;
            System.out.println("P" + process_id + " is now down.");
        } else {
            System.out.println("P" + process_id + " is already down.");
        }
    }

    void initElection(int process_id) {
        if (!processes[process_id - 1]) {
            System.out.println("Process P" + process_id + " is down and cannot initiate election.");
            return;
        }

        System.out.println("Process P" + process_id + " has initiated an election.");
        ArrayList<Integer> alive = new ArrayList<>();
        int current = process_id - 1;

        do {
            if (processes[current]) {
                alive.add(current + 1);
                System.out.println("Election message passed to P" + (current + 1));
            }
            current = (current + 1) % max_processes;
        } while (current != process_id - 1);

        int newCoordinator = Collections.max(alive);
        coordinator = newCoordinator;
        System.out.println("P" + coordinator + " is elected as new coordinator.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ring ring = null;

        while (true) {
            System.out.println("\n--- Ring Algorithm ---");
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
                    ring = new Ring(sc.nextInt());
                    break;
                case 2:
                    ring.displayProcesses();
                    break;
                case 3:
                    System.out.print("Enter process to up: ");
                    ring.upProcess(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter process to down: ");
                    ring.downProcess(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter process initiating election: ");
                    ring.initElection(sc.nextInt());
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
}
