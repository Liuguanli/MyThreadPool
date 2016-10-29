package leo.com.threadpool.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadPoolConfiguration {
    /**
     * 是否是公平的，即当优先级相同的时候是否按照先后顺序来执行
     */
    public boolean isFair;

    public int coreSize;
    public int maxSize;
    public long keepAliveTime;
    public TimeUnit timeUnit;
    public BlockingQueue<Runnable> workQueue;
    public ThreadFactory threadFactory;
    public RejectedExecutionHandler handler;

    private ThreadPoolConfiguration(final Builder builder) {
        isFair = builder.isFair;
        coreSize = builder.coreSize;
        maxSize = builder.maxSize;
        keepAliveTime = builder.keepAliveTime;
        timeUnit = builder.timeUnit;
        workQueue = builder.workQueue;
        threadFactory = builder.threadFactory;
        handler = builder.handler;
    }

    public static class Builder {

        private boolean isFair;
        private int coreSize;
        private int maxSize;
        private long keepAliveTime;
        private TimeUnit timeUnit;
        private BlockingQueue<Runnable> workQueue;
        private ThreadFactory threadFactory;
        private RejectedExecutionHandler handler;

        public void setFair(boolean fair) {
            isFair = fair;
        }

        public Builder setCoreSize(int coreSize) {
            this.coreSize = coreSize;
            return this;
        }

        public Builder setMaxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public Builder setAliveTimeAndTimeUnit(long keepAliveTime, TimeUnit timeUnit) {
            this.keepAliveTime = keepAliveTime;
            this.timeUnit = timeUnit;
            return this;
        }

        public Builder setThreadFactory(ThreadFactory threadFactory) {
            this.threadFactory = threadFactory;
            return this;
        }

        public Builder setBlockingQueue(BlockingQueue<Runnable> workQueue) {
            this.workQueue = workQueue;
            return this;
        }

        public Builder setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
            this.handler = rejectedExecutionHandler;
            return this;
        }

        private void initDefaultConfig() {
//            coreSize = 5;
//            maxSize = 10;
            if (timeUnit == null) {
                keepAliveTime = 5;
                timeUnit = TimeUnit.MINUTES;
            }
            if (workQueue == null) {
                workQueue = DefaultConfigurationFactory.createBlockingQueue();
            }
            if (threadFactory == null) {
                threadFactory = DefaultConfigurationFactory.createThreadFactory();
            }
            if (handler == null) {
                handler = DefaultConfigurationFactory.createRejectedExecutionHandler();
            }
        }

        public ThreadPoolConfiguration build() {
            initDefaultConfig();
            return new ThreadPoolConfiguration(this);
        }


    }

}
