package com.xcrj.spin_lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
/**
 * 参考
 * https://www.cnblogs.com/scholar-hwg/p/12172154.html
 */
public class Main2 {

}

/**
 * 可重入
 */
class SpinLock extends Lock {
    private AtomicReference<Thread> owner = new AtomicReference<>();

    private int count = 0;// 记录加锁次数

    @Override
    public void lock() {
        Thread current = Thread.currentThread();
        // 可重入
        if (current == owner.get()) {
            ++count;
            return;
        }

        while (owner.compareAndSet(null, current)) {
        }
    }

    @Override
    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (count > 0) {// 可重入锁，加锁几次，解锁几次
                --count;
            } else {
                owner.set(null);// count=0，解锁，设置为null
            }
        }
    }
}

/**
 * 公平锁
 * 服务号 排队号
 */
class TicketLock extends Lock {
    // 服务到第n号
    private AtomicInteger serviceNum = new AtomicInteger(0);
    // 排队到第n号
    private AtomicInteger ticketNum = new AtomicInteger(0);
    // 记录线程自己的排队号
    private final ThreadLocal<Integer> myNum = new ThreadLocal<>();

    @Override
    public void lock() {
        // 先到先得排队号
        myNum.set(ticketNum.getAndIncrement());
        // 服务号==排队号
        while (serviceNum.get() != myNum.get()) {
        }
    }

    @Override
    public void unlock() {
        // 服务下一个线程
        serviceNum.compareAndSet(myNum.get(), myNum.get() + 1);
        // 任务完成移除
        myNum.remove();
    }
}

/**
 * 公平锁
 * CLHLock 轮询前驱结点状态
 * 
 * 基于链表的可扩展、高性能、公平的自旋锁，
 * 申请线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋。
 */
class CLHLock extends ReentrantLock {

    /**
     * 锁等待队列的尾部
     */
    private AtomicReference<QNode> tail;
    private ThreadLocal<QNode> preNode;
    private ThreadLocal<QNode> myNode;

    public CLHLock() {
        tail = new AtomicReference<>(null);
        myNode = ThreadLocal.withInitial(QNode::new);
        preNode = ThreadLocal.withInitial(() -> null);
    }

    @Override
    public void lock() {
        QNode qnode = myNode.get();
        //设置自己的状态为locked=true表示需要获取锁
        qnode.locked = true;
        //链表的尾部设置为本线程的qNode，并将之前的尾部设置为当前线程的preNode
        QNode pre = tail.getAndSet(qnode);
        preNode.set(pre);
        if(pre != null) {
            //当前线程在前驱节点的locked字段上旋转，直到前驱节点释放锁资源
            while (pre.locked) {
            }
        }
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        //释放锁操作时将自己的locked设置为false，可以使得自己的后继节点可以结束自旋
        qnode.locked = false;
        //回收自己这个节点，从虚拟队列中删除
        //将当前节点引用置为自己的preNode，那么下一个节点的preNode就变为了当前节点的preNode，这样就将当前节点移出了队列
        myNode.set(preNode.get());
    }

    private class QNode {
        /**
         * true表示该线程需要获取锁，且不释放锁，为false表示线程释放了锁，且不需要锁
         */
        private volatile boolean locked = false;
    }
}

/**
 * 公平锁
 * MCSLock 前驱结点通知后继结点结束自旋
 * 基于链表的可扩展、高性能、公平的自旋锁，
 * 申请线程只在本地变量上自旋，直接前驱负责通知其结束自旋，
 * 从而极大地减少了不必要的处理器缓存同步的次数，降低了总线和内存的开销。
 */
class MCSLock extends Lock {
    private AtomicReference<QNode> tail;
    private ThreadLocal<QNode> myNode;

    public MCSLock() {
        tail = new AtomicReference<>(null);
        myNode = ThreadLocal.withInitial(QNode::new);
    }

    @Override
    public void lock() {
        QNode qnode = myNode.get();
        QNode preNode = tail.getAndSet(qnode);
        if (preNode != null) {
            qnode.locked = false;
            preNode.next = qnode;
            //wait until predecessor gives up the lock
            while (!qnode.locked) {
            }
        }
        qnode.locked = true;
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        if (qnode.next == null) {
            //后面没有等待线程的情况
            if (tail.compareAndSet(qnode, null)) {
                //真的没有等待线程，则直接返回，不需要通知
                return;
            }
            //wait until predecessor fills in its next field
            // 突然有人排在自己后面了，可能还不知道是谁，下面是等待后续者
            while (qnode.next == null) {
            }
        }
        //后面有等待线程，则通知后面的线程
        qnode.next.locked = true;
        qnode.next = null;
    }

    private class QNode {
        /**
         * 是否被qNode所属线程锁定
         */
        private volatile boolean locked = false;
        /**
         * 与CLHLock相比，多了这个真正的next
         */
        private volatile QNode next = null;
    }
}

