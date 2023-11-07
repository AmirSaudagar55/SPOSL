import java.util.Scanner;

public class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Shortest Job First (SJF) Scheduling Algorithm");

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n]; // Process ID
        int at[] = new int[n]; // Arrival Time
        int bt[] = new int[n]; // Burst Time
        int ct[] = new int[n]; // Completion Time
        int ta[] = new int[n]; // Turnaround Time
        int wt[] = new int[n]; // Waiting Time
        int f[] = new int[n];  // Flag to check if process is completed or not
        int k[] = new int[n];  // Stores burst time

        int i, st = 0, tot = 0;
        float avgwt = 0, avgta = 0;

        for (i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter process " + (i + 1) + " arrival time: ");
            at[i] = sc.nextInt();
            System.out.print("Enter process " + (i + 1) + " burst time: ");
            bt[i] = sc.nextInt();
            k[i] = bt[i];
            f[i] = 0;
        }

        while (true) {
            int min = 99, c = n;
            if (tot == n)
                break;

            for (i = 0; i < n; i++) {
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }

            if (c == n)
                st++;
            else {
                bt[c]--;
                st++;
                if (bt[c] == 0) {
                    ct[c] = st;
                    f[c] = 1;
                    tot++;
                }
            }
        }

        for (i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i];
            avgwt += wt[i];
            avgta += ta[i];
        }

        System.out.println("\nPID  Arrival  Burst  Complete  Turnaround  Waiting");
        for (i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + (float) (avgta / n));
        System.out.println("Average Waiting Time: " + (float) (avgwt / n));
        sc.close();
    }
}
