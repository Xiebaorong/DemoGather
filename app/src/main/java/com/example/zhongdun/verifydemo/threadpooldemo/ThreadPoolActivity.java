package com.example.zhongdun.verifydemo.threadpooldemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a7invensun.verifydemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 核心线程数可以设为CPU数量+1，而最大线程数可以设为CPU的数量*2+1。
 * 获取CPU数量的方法为：Runtime.getRuntime().availableProcessors();
 *
 * 线程池的停止
 *
 * shutdown()方法在终止前允许执行以前提交的任务
 * shutdownNow()方法则是阻止正在任务队列中等待任务的启动并试图停止当前正在执行的任务。
 */
public class ThreadPoolActivity extends AppCompatActivity {
    private static final String TAG = "ThreadPoolActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);

    }

    //创建一个固定线程数量的线程池
    public void onClick_newFixedThreadPool(View view) {
//        int coreNum = Runtime.getRuntime().availableProcessors();

//        Log.e(TAG, "onClick_newFixedThreadPool: " + coreNum);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i <= 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.e(TAG, "newFixedThreadPool_线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //创建一个只有一个线程的线程池
    public void onClick_newSingleThreadExecutor(View view) {
        Log.e(TAG, "onClick_newSingleThreadExecutor: in");
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i <= 10; i++) {
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.e(TAG, "newSingleThreadExecutor_线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public void onClick_newCachedThreadPool(View view) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.e(TAG, "newCachedThreadPool_线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        long time = index * 500;
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            });
        }
    }

    public void onClick_newScheduledThreadPool(View view) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //延迟2秒后执行该任务
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.e(TAG, "newScheduledThreadPool_线程：schedule----" + threadName + ",正在执行");
            }
        }, 2, TimeUnit.SECONDS);

        //延迟1秒后,每两秒执行一次该任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.e(TAG, "newScheduledThreadPool_线程：scheduleAtFixedRate----" + threadName + ",正在执行");
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    public void onClick_newSingleThreadScheduledExecutor(View view) {
        ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduledPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.e(TAG, "newSingleThreadScheduledExecutor_线程：" + threadName + ",正在执行");
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    public void onClick_newCustomThreadPool(View view) {
        ExecutorService priorityThreadPool = new ThreadPoolExecutor(3,
                3, 0L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        for (int i = 0; i <= 10; i++) {
            final int priority = i;
            priorityThreadPool.execute(new PriorityRunnable(priority) {
                @Override
                protected void doSth() {
                    String threadName = Thread.currentThread().getName();

                    Log.e(TAG, "newCustomThreadPool_线程：" + threadName + ",正在执行优先级为：" + priority + "的任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    abstract class PriorityRunnable implements Runnable, Comparable<PriorityRunnable> {
        private int priority;

        public PriorityRunnable(int priority) {
            if (priority < 0)
                throw new IllegalArgumentException();
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(@NonNull PriorityRunnable o) {
            int my = this.getPriority();
            int other = o.getPriority();

            return my < other ? 1 : my > other ? -1 : 0;
        }

        @Override
        public void run() {
            doSth();
        }

        protected abstract void doSth();
    }
}
