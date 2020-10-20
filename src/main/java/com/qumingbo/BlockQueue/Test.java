package com.qumingbo.BlockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: 曲铭博
 * @Date: Created in 22:14 2020/10/20
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        // test1();
        // test2();
        // test3();
        test4();
    }
    /**
     * 抛出异常
     */
    public static void test1() {
        // 初始化时需指定队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.add(2));
        System.out.println(blockingQueue.add(3));

        // IllegalStateException: Queue full添加超出队列初始化指定数量的元素会抛出异常
        // System.out.println(blockingQueue.add(4))

        // 移除元素 返回被移除的元素
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // NoSuchElementException 没有元素异常
        System.out.println(blockingQueue.remove());

        // 获取队首元素
        System.out.println(blockingQueue.element());
    }

    public static void test2() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));

        // 添加超出初始化大小的数量的元素 会返回false
        System.out.println(blockingQueue.offer(4));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        // 移除更多的元素不会抛出异常  会返回null
        System.out.println(blockingQueue.poll());

        // 获取队首元素
        System.out.println(blockingQueue.peek());
    }

    /**
     * 阻塞等待
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.put(3);

        //发生阻塞
        // blockingQueue.put(4)

        // 取元素
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        //取出多余元素   会发生阻塞
        // System.out.println(blockingQueue.take());
    }

    /**
     * 超时等待
     * @throws InterruptedException 中断异常
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer(1,1, TimeUnit.SECONDS);
        blockingQueue.offer(2,2, TimeUnit.SECONDS);
        blockingQueue.offer(3,2, TimeUnit.SECONDS);

        // 等待2s后放弃
        blockingQueue.offer(4,2, TimeUnit.SECONDS);


        // 取元素
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
    }
}
