package leo.com.threadpool.utils;

import java.util.Comparator;

import com.hi.baidu.threadpool.task.Task;

public class CustonComparator implements Comparator<Runnable> {
	@Override
	public int compare(Runnable r1, Runnable r2) {

		Task task1 = (Task) r1;
		Task task2 = (Task) r2;
		if (task2.getPriority() > task1.getPriority()) {
			return 1;
		} else if (task2.getPriority() < task1.getPriority()) {
			return -1;
		} else {
			return task2.getTimeStamp() > task1.getTimeStamp() ? -1 : 1;
		}
	}
}
