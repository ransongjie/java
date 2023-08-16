package com.xcrj.interview150.graph;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-ladder/solutions/831894/gong-shui-san-xie-ru-he-shi-yong-shuang-magjd/
 * 单词接龙
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，
 * 返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0 。
 * <p>
 * beginWord>wordList>endWord, 每次变化1个字母
 */
public class Solution09 {
    String s, e;//开始点，结束点
    Set<String> set = new HashSet<>();//wordList转储到set

    /**
     * 双向BFS 同时从源点 和 终点 出发进行搜索
     * <p>
     * 对于那些搜索节点随着层数增加呈倍数或指数增长的搜索问题，可以使用「双向 BFS」进行求解
     * <p>
     * - 创建「两个队列」分别用于两个方向的搜索；
     * - 创建「两个哈希表」用于「解决相同节点重复搜索」和「记录转换次数」；
     * - 为了尽可能让两个搜索方向“平均”，每次从队列中取值进行扩展时，先判断哪个队列容量较少；
     * - 如果在搜索过程中「搜索到对方搜索过的节点」，说明找到了最短路径。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        s = beginWord;
        e = endWord;
        for (String word : wordList) set.add(word);
        //wordList中需要包含endWord
        if (!set.contains(e)) return 0;
        //
        int ans = bfs();
        return ans == -1 ? 0 : ans + 1;
    }

    private int bfs() {
        //从s出发，从e出发
        Queue<String> sque = new LinkedList<>(), eque = new LinkedList<>();
        sque.add(s);
        eque.add(e);
        Map<String, Integer> sNum = new HashMap<>(), eNum = new HashMap<>();
        sNum.put(s, 0);
        eNum.put(e, 0);
        // 只要有1个队列为空，表示无法转换成功
        while (!sque.isEmpty() && !eque.isEmpty()) {
            int num = -1;
            //搜索越平均，搜索达到的最后一层的结点数量最少
            if (sque.size() <= eque.size()) {
                num = update(sque, sNum, eNum);
            } else {
                num = update(eque, eNum, sNum);
            }
            if (num != -1) return num;
        }

        return -1;
    }

    /**
     * 从que中取出1个str进行扩展
     *
     * @param que
     * @param curStrNum 当前方向 map<str,num>
     * @param otrStrNum 相反方向 map<str,num>
     */
    private int update(Queue<String> que, Map<String, Integer> curStrNum, Map<String, Integer> otrStrNum) {
        //将队列中所有str处理完，队列中的所有str都是同层的，达到这一层所需的num都相等
        int n = que.size();
        while (n-- > 0) {
            String str = que.poll();
            int len = str.length();
            //枚举str的每个字母，替换成26个字母中的每个字母
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < 26; j++) {
                    //str[i]字母替换为j字母。abcd=a+a+cd
                    String str1 = str.substring(0, i) + String.valueOf((char) ('a' + j)) + str.substring(i + 1);
                    //str1需要在wordList中
                    if (!set.contains(str1)) continue;
                    //curStrNum存在str1，表示已经拓展过，比较长度，从str拓展到str1的长度>=从xxx拓展到str1的长度
                    if (curStrNum.containsKey(str1) && curStrNum.get(str) + 1 >= curStrNum.get(str1)) continue;
                    //str1在另一方向上也被拓展过。curStrNum和otrStrNum都拓展到了str1
                    if (otrStrNum.containsKey(str1)) {
                        return curStrNum.get(str) + 1 + otrStrNum.get(str1);
                    } else {
                        //加入队列que
                        que.offer(str1);
                        curStrNum.put(str1, curStrNum.get(str) + 1);
                    }
                }
            }
        }

        return -1;
    }

    /**
     * A*算法 启发式搜索
     * 估值距离=stra与strb不同字符数量<=真实距离=理论最小替换次数
     * 使用A*算法之前需要确保有解，可以提前排除无效的情况
     * 本题数据较弱，无法提前判断是否一定有解
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        start = beginWord;
        end = endWord;
        for (String s : wordList) wordSet.add(s);
        // endWord一定在wordList中
        if (!wordSet.contains(end)) return 0;
        // A*算法
        int ans = astar();
        return ans == -1 ? 0 : ans + 1;
    }

    class Node {
        String str;
        int val;

        Node(String s, int v) {
            str = s;
            val = v;
        }
    }

    String start, end;//beginWord,endWord;
    int INF = 0x3f3f3f;//最大distance 不用Max 防止相加越界
    Set<String> wordSet = new HashSet<>();//转储wordList

    private int astar() {
        // str与end 不等字符越少 越排在前面
        // (a, b) -> a.val > b.val ? 1 : -1 防止越界写法
        PriorityQueue<Node> pQue = new PriorityQueue<>((a, b) -> a.val > b.val ? 1 : -1);
        pQue.offer(new Node(start, distance(start)));
        // map<str,distance> start>str的不等字符数量
        Map<String, Integer> strDistance = new HashMap<>();
        strDistance.put(start, 0);

        while (!pQue.isEmpty()) {
            Node node = pQue.poll();
            String str = node.str;
            //start到str的距离
            int distance = strDistance.get(str);
            if (str.equals(end)) break;
            //
            int len = str.length();
            //str每个字母 尝试变化到26个字母的每个字母
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < 26; j++) {
                    String str1 = str.substring(0, i) + String.valueOf((char) ('a' + j)) + str.substring(i + 1);
                    // 变化str1必须在wordList中
                    if (!wordSet.contains(str1)) continue;
                    // 之前已经拓展了str1 且 之前的拓展距离更短
                    if (strDistance.containsKey(str1) && distance + 1 >= strDistance.get(str1)) continue;
                    strDistance.put(str1, distance + 1);
                    //start到str1到end的距离
                    pQue.offer(new Node(str1, strDistance.get(str1) + distance(str1)));
                }
            }
        }

        return strDistance.getOrDefault(end, -1);
    }

    /**
     * str 与 end 之间的距离 = 不等字符数量
     *
     * @param str
     * @return
     */
    private int distance(String str) {
        if (str.length() != end.length()) return INF;
        int n = str.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            ans += str.charAt(i) == end.charAt(i) ? 0 : 1;
        }
        return ans;
    }
}
