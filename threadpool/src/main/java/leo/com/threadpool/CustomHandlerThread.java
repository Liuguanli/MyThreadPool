package leo.com.threadpool;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

/**
 * Created by liuguanli on 16/7/18.
 */
public class CustomHandlerThread extends Thread {


    int mPriority;
    int mTid = -1;
    Looper mLooper;

    public CustomHandlerThread(ThreadGroup group, Runnable runnable, String threadName, long stackSize) {
        super(group, runnable, threadName, stackSize);
    }

    public CustomHandlerThread(String name) {
        super(name);
        mPriority = Process.THREAD_PRIORITY_DEFAULT;
    }

    /**
     * Constructs a HandlerThread.
     * @param name
     * @param priority The priority to run the thread at. The value supplied must be from
     * {@link android.os.Process} and not from java.lang.Thread.
     */
    public CustomHandlerThread(String name, int priority) {
        super(name);
        mPriority = priority;
    }

    /**
     * Call back method that can be explicitly overridden if needed to execute some
     * setup before Looper loops.
     */
    protected void onLooperPrepared() {
    }

    @Override
    public void run() {
        mTid = Process.myTid();
        Looper.prepare();
        synchronized (this) {
            mLooper = Looper.myLooper();
            notifyAll();
        }
        Process.setThreadPriority(mPriority);
        onLooperPrepared();
        Looper.loop();
        mTid = -1;
    }

    /**
     * This method returns the Looper associated with this thread. If this thread not been started
     * or for any reason is isAlive() returns false, this method will return null. If this thread
     * has been started, this method will block until the looper has been initialized.
     * @return The looper.
     */
    public Looper getLooper() {
        if (!isAlive()) {
            return null;
        }

        // If the thread has been started, wait until the looper has been created.
        synchronized (this) {
            while (isAlive() && mLooper == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
        return mLooper;
    }

    /**
     * Quits the handler thread's looper.
     * <p>
     * Causes the handler thread's looper to terminate without processing any
     * more messages in the message queue.
     * </p><p>
     * Any attempt to post messages to the queue after the looper is asked to quit will fail.
     * For example, the {@link Handler#sendMessage(Message)} method will return false.
     * </p><p class="note">
     * Using this method may be unsafe because some messages may not be delivered
     * before the looper terminates.  Consider using {@link #quitSafely} instead to ensure
     * that all pending work is completed in an orderly manner.
     * </p>
     *
     * @return True if the looper looper has been asked to quit or false if the
     * thread had not yet started running.
     *
     * @see #quitSafely
     */
    public boolean quit() {
        Looper looper = getLooper();
        if (looper != null) {
            looper.quit();
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public boolean quitSafely() {
        Looper looper = getLooper();
        if (looper != null) {
            looper.quitSafely();
            return true;
        }
        return false;
    }

    /**
     * Returns the identifier of this thread. See Process.myTid().
     */
    public int getThreadId() {
        return mTid;
    }

}
