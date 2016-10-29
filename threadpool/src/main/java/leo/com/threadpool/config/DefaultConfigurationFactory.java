package leo.com.threadpool.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class DefaultConfigurationFactory {

    public static BlockingQueue<Runnable> createBlockingQueue() {
        return new LinkedBlockingDeque<>(100);
    }

    public static ThreadFactory createThreadFactory() {
        return Executors.defaultThreadFactory();
    }

    public static RejectedExecutionHandler createRejectedExecutionHandler() {
        return new ThreadPoolExecutor.CallerRunsPolicy();
    }

}
