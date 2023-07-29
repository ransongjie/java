package com.xcrj.basic.pass3.window_max;

import java.util.*;

/**
 * 滑动窗口内的最大值
 */
public class DynamicWindowMax {

    /**
     * @param as
     * @param w  窗口大小
     * @return
     */
    public static List<Integer> dynamicWindowMax(int[] as, int w) {
        if (as == null || as.length == 0 || as.length < w) return null;
        List<Integer> ls = new ArrayList<>();
        DynamicWindowMax dwm = new DynamicWindowMax(as);
        for (int i = 0; i < w; i++) {
            dwm.addRight();
        }
        for (int i = w; i < as.length + 1; i++) {//
            ls.add(dwm.getWindowMax());
            dwm.addRight();
            dwm.delLeft();
        }
        return ls;
    }

    int l;
    int r;
    int[] as;
    Deque<Integer> deque;//存储索引

    public DynamicWindowMax(int[] as) {
        this.l = -1;
        this.r = 0;
        this.as = as;
        this.deque = new LinkedList<>();
    }

    /**
     * 从右端加入
     */
    public void addRight() {
        //从右往左严格单调递减
        if (r == this.as.length) return;
        while (!deque.isEmpty() && as[deque.peekLast()] <= as[r]) {
            deque.pollLast();
        }
        this.deque.addLast(r++);
    }

    /**
     * 左端删除
     */
    public void delLeft() {
        //l等于deque左对头才删除
        if (l >= r) return;
        if (++l == deque.peekFirst()) {
            deque.pollFirst();
        }
    }

    /**
     * 获取窗口最大值
     */
    public Integer getWindowMax() {
        if (deque.isEmpty()) return null;
        return as[deque.peekFirst()];//左对头是最大值
    }


    static class Element {
        int[] as;
        int w;
    }

    public static Element getElement(int maxLen, int maxV) {
        int len = (int) (Math.random() * (maxLen - 1) + 1);
        int[] as = new int[len];
        for (int i = 0; i < len; i++) {
            int v = (int) (Math.random() * maxV);
            as[i] = v;
        }

        int w = (int) (Math.random() * (maxLen - 1) + 1);

        Element e = new Element();
        e.as = as;
        e.w = w;
        return e;
    }

    public static List<Integer> cp(int[] as, int w) {
        if (as == null || as.length == 0 || as.length < w) return null;
        List<Integer> ls = new ArrayList<>();
        int l = 0;
        int r = 0;
        while (r <= as.length) {
            if (r < w) {
                r++;
                continue;
            }
            int[] bs = Arrays.copyOfRange(as, l, r);
            int wmax = Arrays.stream(bs).max().getAsInt();
            ls.add(wmax);
            r++;
            l++;
        }
        return ls;
    }

    public static void main(String[] args) {
        int times = 1000000;
        int maxLen = 100;
        int maxV = 100;
        for (int i = 0; i < times; i++) {
            Element e = getElement(maxLen, maxV);
            List<Integer> ls1 = cp(e.as, e.w);
            List<Integer> ls2 = dynamicWindowMax(e.as, e.w);
            if (ls1 == null && ls2 == null) continue;
            Integer[] rs1 = ls1.toArray(new Integer[]{});//
            Integer[] rs2 = ls2.toArray(new Integer[]{});
            if (!Arrays.equals(rs1, rs2)) {
                System.out.println("not good");
                System.out.println(Arrays.toString(rs1));
                System.out.println(Arrays.toString(rs2));
                throw new RuntimeException();
            }
        }
    }
}
