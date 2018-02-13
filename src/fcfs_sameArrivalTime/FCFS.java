package fcfs_sameArrivalTime;

class Process {
	int pid; // Process ID
	int bt; // Burst Time

	public Process(int pid, int bt) {
		this.pid = pid;
		this.bt = bt;
	}
}

public class FCFS {

	static void findWaitingTime(Process proc[], int n, int wt[]) {

		wt[0] = 0;
		for (int i = 1; i < n; i++)
			wt[i] = proc[i - 1].bt + wt[i - 1];
	}

	static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {
		for (int i = 0; i < n; i++)
			tat[i] = proc[i].bt + wt[i];
	}

	static void findavgTime(Process proc[], int n) {
		int[] wt = new int[n];
		int[] tat = new int[n];
		int total_wt = 0, total_tat = 0;

		findWaitingTime(proc, n, wt);
		findTurnAroundTime(proc, n, wt, tat);

		System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			System.out.println(" " + proc[i].pid + "\t\t" + proc[i].bt + "\t " + wt[i] + "\t\t " + tat[i]);
		}

		System.out.println("Average waiting time = " + (float) total_wt / (float) n);
		System.out.println("Average turn around time = " + (float) total_tat / (float) n);

	}

	public static void main(String[] args) {
		Process proc[] = { new Process(1, 10), new Process(2, 5), new Process(3, 8) };
		findavgTime(proc,proc.length);

	}

}

/*
 Implementation
 
 1-  Input the processes along with their burst time (bt).
2-  Find waiting time (wt) for all processes.
3-  As first process that comes need not to wait so 
    waiting time for process 1 will be 0 i.e. wt[0] = 0.
4-  Find waiting time for all other processes i.e. for
     process i -> 
       wt[i] = bt[i-1] + wt[i-1] .
5-  Find turnaround time = waiting_time + burst_time 
    for all processes.
6-  Find average waiting time = 
                 total_waiting_time / no_of_processes.
7-  Similarly, find average turnaround time = 
                 total_turn_around_time / no_of_processes.
 
 */
