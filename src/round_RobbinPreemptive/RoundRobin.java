package round_RobbinPreemptive;

//this algorithm with same arrival Time.

class Process {
	int pid; // Process ID
	int bt; // Burst Time

	public Process(int pid, int bt) {
		this.pid = pid;
		this.bt = bt;
	}
}

public class RoundRobin {

	static void findWaitingTime(Process proc[], int n, int wt[], int quantum) {

		int rem_bt[] = new int[n];
		for (int i = 0; i < n; i++)
			rem_bt[i] = proc[i].bt;

		int t = 0; // Current time

		while (true) {
			boolean done = true;

			for (int i = 0; i < n; i++) {
				if (rem_bt[i] > 0) {
					done = false; // There is a pending process

					if (rem_bt[i] > quantum) {

						t += quantum;
						rem_bt[i] -= quantum;
					} else {
						t = t + rem_bt[i];
						wt[i] = t - proc[i].bt;
						rem_bt[i] = 0;
					}
				}
			}
			if (done == true)
				break;
		}
	}

	static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {

		for (int i = 0; i < n; i++)
			tat[i] = proc[i].bt + wt[i];
	}

	static void findavgTime(Process proc[], int n, int quantum) {
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;

		findWaitingTime(proc, n, wt, quantum);
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
		int quantum = 2;
		findavgTime(proc, proc.length, quantum);

	}

}

/*
 Implementation
 
 1- Create an array rem_bt[] to keep track of remaining
   burst time of processes. This array is initially a 
   copy of bt[] (burst times array)
2- Create another array wt[] to store waiting times
   of processes. Initialize this array as 0.
3- Initialize time : t = 0
4- Keep traversing the all processes while all processes
   are not done. Do following for i'th process if it is
   not done yet.
    a- If rem_bt[i] > quantum
       (i)  t = t + quantum
       (ii) bt_rem[i] -= quantum;
    c- Else // Last cycle for this process
       (i)  t = t + bt_rem[i];
       (ii) wt[i] = t - bt[i]
       (ii) bt_rem[i] = 0; // This process is over
       
*/
