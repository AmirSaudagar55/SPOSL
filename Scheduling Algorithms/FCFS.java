import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("First-Come-First-Serve (FCFS) Scheduling Algorithm\n");

        System.out.print("Enter the number of processes: ");
        int numberOfProcesses = in.nextInt();

        int pid[] = new int[numberOfProcesses];
        int bt[] = new int[numberOfProcesses];
        int ar[] = new int[numberOfProcesses];
        int ct[] = new int[numberOfProcesses];
        int ta[] = new int[numberOfProcesses];
        int wt[] = new int[numberOfProcesses];

        // Input arrival time and burst time for each process
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println("\nEnter details for Process " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            ar[i] = in.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = in.nextInt();
            pid[i] = i + 1;
        }

        // Sort processes based on arrival time
        int temp;
        for (int i = 0; i < numberOfProcesses; i++) {
            for (int j = i + 1; j < numberOfProcesses; j++) {
                if (ar[i] > ar[j]) {
                    // Swap arrival time, burst time, and process ID
                    temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;

                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;

                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                }
            }
        }

        // Calculate completion time, turnaround time, and waiting time
        ct[0] = bt[0] + ar[0];
        for (int i = 1; i < numberOfProcesses; i++) {
            ct[i] = ct[i - 1] + bt[i];
        }
        for (int i = 0; i < numberOfProcesses; i++) {
            ta[i] = ct[i] - ar[i];
            wt[i] = ta[i] - bt[i];
        }

        // Print the results in a tabular format
        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println(pid[i] + "\t\t" + ar[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + ta[i] + "\t\t" + wt[i]);
        }

        // Calculate and display the average turnaround time and average waiting time
        double avgTAT = 0;
        double avgWT = 0;
        for (int i = 0; i < numberOfProcesses; i++) {
            avgTAT += ta[i];
            avgWT += wt[i];
        }
        avgTAT /= numberOfProcesses;
        avgWT /= numberOfProcesses;

        System.out.println("\nAverage Turnaround Time (TAT): " + avgTAT);
        System.out.println("Average Waiting Time (WT): " + avgWT);
    }
}
