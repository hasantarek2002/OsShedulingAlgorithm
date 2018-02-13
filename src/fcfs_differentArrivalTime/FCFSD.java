package fcfs_differentArrivalTime;



class Process {
	int pid; // Process ID
	int bt; // Burst Time
	int art; // Arrival Time

	public Process(int pid, int bt, int art) {
		this.pid = pid;
		this.bt = bt;
		this.art = art;
	}
}

public class FCFSD {

	static void findWaitingTime(Process proc[], int n, int wt[]) {

		// Service time means amount of time after which a process can start
		// execution. It is summation of burst time of previous processes
		// (Processes that came before)

		int[] service_time = new int[n];
		service_time[0] = 0;
		wt[0] = 0;

		for (int i = 1; i < n; i++) {
			service_time[i] = service_time[i - 1] + proc[i - 1].bt;

			wt[i] = service_time[i] - proc[i].art;

			if (wt[i] < 0)
				wt[i] = 0;
		}
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

		System.out.println("Processes " + " Burst Time " + " Arrival Time " + " Waiting Time " + " Turn-Around Time "
				+ " Completion Time");

		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			int compl_time = tat[i] + proc[i].art;
			System.out.println(" " + proc[i].pid + "\t\t" + proc[i].bt + "\t\t" + proc[i].art + "\t\t" + wt[i] + "\t\t "
					+ tat[i] + "\t\t " + compl_time);
		}

		System.out.println("Average waiting time = " + (float) total_wt / (float) n);
		System.out.println("Average turn around time = " + (float) total_tat / (float) n);
	}

	public static void main(String[] args) {
		Process proc[] = { new Process(1, 5, 3), new Process(2, 9, 3), new Process(3, 6, 6) };
		findavgTime(proc,proc.length);

	}

}


/*
 IMPLEMENTATION
 
 
1- Input the processes along with their burst time(bt)
   and arrival time(at)
2- Find waiting time for all other processes i.e. for
   a given process  i:
       wt[i] = (bt[0] + bt[1] +...... bt[i-1]) - at[i] 
3- Now find turn around time 
          = waiting_time + burst_time for all processes
4- Average waiting time = 
                    total_waiting_time / no_of_processes
5- Average turn around time = 
                 total_turn_around_time / no_of_processes
                 
 */
