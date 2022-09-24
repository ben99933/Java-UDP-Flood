package clwhthr.updfloodattacker.util;

public class Timer {
	private long period;
	private boolean repeat;
	private Thread thread;
	private volatile boolean stop = false;
	public Timer(long period,boolean repeat,Task task) {
		this.period = period;
		this.repeat = repeat;
		thread = new Thread(()->{
			if(repeat)while(stop==false) {
				task.doWork();
				long time = System.currentTimeMillis();
				while(System.currentTimeMillis()-time<period);
			}
		});
	}
	public Timer(long period,Task task) {
		this(period, true,task);
	}
	public void start() {
		thread.start();
	}
	public void stop() {
		stop = true;
		thread.interrupt();
	}
	
}
interface Task{
	void doWork();
}
