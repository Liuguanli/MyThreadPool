package leo.com.threadpool.core;

import com.hi.baidu.threadpool.config.ThreadPoolConfiguration;

/**
 * Created by liuguanli on 16/7/18.
 */
public class ThreadPool {

    private static ThreadPool threadPool = new ThreadPool();
    private CustomedThreadPool customedThreadPool;

    private ThreadPool() {

    }

    public static ThreadPool getInstance() {
        return threadPool;
    }

    public void init(ThreadPoolConfiguration configuration) {
        customedThreadPool = new CustomedThreadPool(configuration.coreSize, configuration.maxSize, configuration
                .keepAliveTime, configuration.timeUnit, configuration.workQueue, configuration.threadFactory,
                configuration.handler);
    }

    public void execute(Runnable runnable) {
        if (customedThreadPool == null) {
        } else {
            customedThreadPool.execute(runnable);
        }
    }

}
