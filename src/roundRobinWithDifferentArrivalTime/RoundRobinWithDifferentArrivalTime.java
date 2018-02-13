package roundRobinWithDifferentArrivalTime;

class Process {
	int pid; 
	int bt; 
	int art;

	public Process(int pid, int bt, int art) {
		this.pid = pid;
		this.bt = bt;
		this.art = art;
	}
}

public class RoundRobinWithDifferentArrivalTime {

	static void findavgTime(Process proc[], int n, int quantum) {
		int count, time, remain, flag = 0;
		int wait_time = 0, turnaround_time = 0;
		int[] rt = new int[n];
		remain = n;

		for (count = 0; count < n; count++) {

			rt[count] = proc[count].bt;
		}

		System.out.println("Process\t|Turnaround Time|Waiting Time\n");

		for (time = 0, count = 0; remain != 0;) {
			if (rt[count] <= quantum && rt[count] > 0) {
				time += rt[count];
				rt[count] = 0;
				flag = 1;
			} else if (rt[count] > 0) {
				rt[count] -= quantum;
				time += quantum;
			}
			if (rt[count] == 0 && flag == 1) {
				remain--;
				System.out.println("P[" + (count + 1) + "]" + "\t|\t" + (time - proc[count].art) + "\t|\t"
						+ (time - proc[count].art - proc[count].bt) + "\n");

				wait_time += time - proc[count].art - proc[count].bt;
				turnaround_time += time - proc[count].art;
				flag = 0;
			}
			if (count == n - 1)
				count = 0;
			else if (proc[count + 1].art <= time)
				count++;
			else
				count = 0;
		}
		System.out.println("\nAverage Waiting Time= " + wait_time * 1.0 / n);
		System.out.println("Avg Turnaround Time = " + turnaround_time * 1.0 / n);

	}

	public static void main(String[] args) {
		Process proc[] = { new Process(1, 8, 0), new Process(2, 4, 1), new Process(3, 9, 2), new Process(4, 5, 3) };

		findavgTime(proc, proc.length, 5);

	}

}
