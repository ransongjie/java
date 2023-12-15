package com.xcrj.basic.pass2.greed;

import java.util.ArrayList;
import java.util.List;

/**
 * 能够安排的最多的日程
 * 按照日程结束时间排序
 * 前一个日程的结束时间>下一个日程的开始时间 不安排进入日程
 */
public class Schedule {
    public static void main(String[] args) {
        int times = 1000;
        int maxL = 100;
        int maxV = 100;
        for (int i = 0; i < times; i++) {
            List<Program> ps = ps(maxL, maxV);

            List<Program> rs = schedule(ps);

            System.out.println(rs.size());
            StringBuilder sb = new StringBuilder();
            rs.forEach(o -> sb.append(o.start + "-" + o.end + ","));
            System.out.println(sb.toString());
        }
    }

    static class Program {
        int start;
        int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static List<Program> schedule(List<Program> ls) {
        if (ls == null || ls.size() == 0) return null;
        // 结果
        List<Program> rs = new ArrayList<>();
        // 按照日程结束时间排序
        ls.sort((o1, o2) -> o1.end - o2.end);
        // 安排第一个日程
        Program pre = ls.get(0);
        rs.add(ls.get(0));
        // 遍历
        for (int i = 1; i < ls.size(); i++) {
            // 前一个日程的结束时间>后一个日程的开始时间，跳过
            if (pre.end > ls.get(i).start) continue;
            rs.add(ls.get(i));
            pre = ls.get(i);
        }

        return rs;
    }

    public static List<Program> ps(int maxL, int maxV) {
        List<Program> ps = new ArrayList<>();
        int len = (int) (Math.random() * (maxL - 1) + 1);
        for (int i = 0; i < len; i++) {
            int start = (int) (Math.random() * (maxV - 1));
            int end = (int) (Math.random() * maxV);
            while (end <= start) {
                end = (int) (Math.random() * maxV);
            }
            Program p = new Program(start, end);
            ps.add(p);
        }
        return ps;
    }
}
