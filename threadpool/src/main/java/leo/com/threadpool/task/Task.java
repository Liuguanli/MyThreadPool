package leo.com.threadpool.task;

/**
 * Created by liuguanli on 2016/10/29.
 */

public abstract class Task implements Runnable {

    public Task() {
    }

    public Task(int id, long timeStamp, int priority) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.priority = priority;
    }

    public Task(long timeStamp, int priority) {
        this.timeStamp = timeStamp;
        this.priority = priority;
    }

    public Task(int priority) {
        this.priority = priority;
    }

    public Task(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    protected int id;

    protected long timeStamp;

    protected int priority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
