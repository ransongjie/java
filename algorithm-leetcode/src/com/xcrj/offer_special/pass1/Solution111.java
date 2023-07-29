package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 111. 计算除法
 * 给定一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * 注意：输入总是有效的。可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 分析：
 * - List<List<String>> equations，一个元素存储了两个值[Ai,Bi]
 * - double[] values，一个元素存储了两个值[Ai,Bi]对应的比率 Ai / Bi = values[i]
 * - List<List<String>> queries，根据已知求queries中每个query的值
 */
public class Solution111 {

    /**
     * 广度优先遍历+建立图（List[ArrayList()]邻接表）
     * 建立图：
     * - 点是变量
     * - 边权是变量的比值，点的比值
     * <p>
     * 问题转换
     * - 对任意两变量求两个变量的比值 转化为 对任意两点求最短路径上边权值之积
     * - 图中任意两点的比率=两点广度优先路径上的点的比率之积=两点广度优先路径上边权值之积
     */
    public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /*使用hash表记录<变量,变量编号>，求得不同变量的数量*/
        // 不同变量总数量
        int amountVar = 0;
        // <变量,变量编号>
        Map<String, Integer> varNumMap = new HashMap<>();
        // 遍历equations数组，使用hash表记录<变量,变量编号>，求得不同变量的数量
        for (int i = 0; i < equations.size(); i++) {
            String varI0 = equations.get(i).get(0);
            String varI1 = equations.get(i).get(1);
            if (!varNumMap.containsKey(varI0)) varNumMap.put(varI0, amountVar++);
            if (!varNumMap.containsKey(varI1)) varNumMap.put(varI1, amountVar++);
        }

        /*对于每个点，存储邻接点和边权值*/
        // 定义邻接点和边权值的存储结构，List[点编号]=List<Pair>=所有邻接点和边权值
        List<Pair>[] vertexIPairs = new List[amountVar];
        for (int i = 0; i < amountVar; i++) {
            vertexIPairs[i] = new ArrayList<>();
        }
        // 使用equations为vertexIPairs存储结构赋值
        for (int i = 0; i < equations.size(); i++) {
            // 第i个式子的第0个变量的编号
            int numVertexI0 = varNumMap.get(equations.get(i).get(0));
            // 第i个式子的第1个变量的编号
            int numVertexI1 = varNumMap.get(equations.get(i).get(1));
            // 为点numVertexI0添加邻接点和边权值
            vertexIPairs[numVertexI0].add(new Pair(numVertexI1, values[i]));
            // 为点numVertexI1添加邻接点和边权值
            vertexIPairs[numVertexI1].add(new Pair(numVertexI0, 1.0 / values[i]));
        }

        // 处理每个查询
        // 结果
        double[] rs = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            // 记录每个查询的结果
            // 默认结果为-1.0，存在某个无法确定的答案，query中含有equations中没有的变量都返回结果-1.0
            double r = -1.0;
            // 获取第i个查询
            String queryI0 = queries.get(i).get(0);
            String queryI1 = queries.get(i).get(1);

            // varNumMap hash表中是否存在查询值
            if (varNumMap.containsKey(queryI0) && varNumMap.containsKey(queryI1)) {
                // 获取结点编号
                int queryI0Num = varNumMap.get(queryI0);
                int queruI1Num = varNumMap.get(queryI1);
                // 同一个变量，直接赋值结果
                if (queryI0Num == queruI1Num) r = 1.0;
                    // 不是同一个变量，广度优先遍历查找图中点queryI0Num到点queryI1Num的最短路径
                else {
                    // ！广度优先所需队列
                    Queue<Integer> que = new ArrayDeque<>();
                    que.offer(queryI0Num);

                    // ！广度优先求最短路径长度所需记录数组
                    double[] queryI0ToJRatios = new double[amountVar];
                    // 初始赋值全为-1.0
                    Arrays.fill(queryI0ToJRatios, -1.0);
                    // queryI0自己到自己为1.0
                    queryI0ToJRatios[queryI0Num] = 1.0;

                    // 开始广度优先遍历
                    // 防止无向图循环遍历，queryI0ToJRatios[queruI1Num] < 0，无向图，queruI0Num到queruI1Num，queruI1Num也到queruI0Num
                    while (!que.isEmpty() && 0 > queryI0ToJRatios[queruI1Num]) {
                        int vertexI = que.poll();
                        // vertexI所有邻接点
                        for (Pair pair : vertexIPairs[vertexI]) {
                            int vertexJ = pair.adj;
                            // queryI0ToJRatios[vertexJ] < 0，无向图，vertexI到vertexJ，vertexJ也到vertexI
                            if (0 > queryI0ToJRatios[vertexJ]) {
                                // 图中任意两点的比率=两点广度优先路径上的点的比率之积
                                queryI0ToJRatios[vertexJ] = queryI0ToJRatios[vertexI] * pair.value;
                                que.offer(vertexJ);
                            }
                        }
                    }
                    r = queryI0ToJRatios[queruI1Num];
                }
            }
            rs[i] = r;
        }
        return rs;
    }

    /**
     * 邻接点编号
     * 边权值
     */
    class Pair {
        int adj;
        double value;

        Pair(int adj, double value) {
            this.adj = adj;
            this.value = value;
        }
    }

    /**
     * 建立图（graph[][]邻接表）+Floyd预处理
     * 对于查询数量很多的情形，如果为每次查询都独立搜索一次，则效率会变低
     * 预处理，Floyd预处理，预先计算出任意两点之间的距离。
     */
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /*使用hash表记录<变量,变量编号>，求得不同变量的数量*/
        // 不同变量总数量
        int amountVar = 0;
        // <变量,变量编号>
        Map<String, Integer> varNumMap = new HashMap<>();
        // 遍历equations数组，使用hash表记录<变量,变量编号>，求得不同变量的数量
        for (int i = 0; i < equations.size(); i++) {
            String var0 = equations.get(i).get(0);
            String var1 = equations.get(i).get(1);
            if (!varNumMap.containsKey(var0)) varNumMap.put(var0, amountVar++);
            if (!varNumMap.containsKey(var1)) varNumMap.put(var1, amountVar++);
        }

        // graph[i][j]=点i到点j的边权值（比率值）
        double[][] adjGraph = new double[amountVar][amountVar];
        // 初始赋值为-1.0
        for (int i = 0; i < adjGraph.length; i++) Arrays.fill(adjGraph[i], -1.0);
        // 遍历已知equations，存储点i到邻接点j的边权值
        for (int i = 0; i < equations.size(); i++) {
            int varNumI0 = varNumMap.get(equations.get(i).get(0));
            int varNumI1 = varNumMap.get(equations.get(i).get(1));
            adjGraph[varNumI0][varNumI1] = values[i];
            adjGraph[varNumI1][varNumI0] = 1.0 / values[i];
        }

        /**
         * Floyd算法，优先计算出两点之间的距离，从点i到点j经过点k
         * 不是求最短路径，只是预处理先计算出两点之间的距离
         * 点i到点i的边权值也已经被记录
         * ！从点i到点j经过点k，k最后变化，中继结点最后变化
         */
        for (int k = 0; k < amountVar; k++) {
            for (int i = 0; i < amountVar; i++) {
                for (int j = 0; j < amountVar; j++) {
                    if (adjGraph[i][k] > 0 && adjGraph[k][j] > 0) {
                        // 只是利用Floyd预处理，不需要比较 graph[i][j]>graph[i][k] * graph[k][j]
                        // graph[i][j]一定等于graph[i][k] * graph[k][j]
                        // graph[a][c]=a/c graph[a][b]=a/b graph[b][c]=b/c；graph[a][b]*graph[b][c]=a/c
                        adjGraph[i][j] = adjGraph[i][k] * adjGraph[k][j];
                    }
                }
            }
        }

        // 记录每个查询的结果
        double[] rs = new double[queries.size()];
        // 处理每个查询
        for (int i = 0; i < queries.size(); i++) {
            // 记录每个查询的结果
            // 默认结果为-1.0，存在某个无法确定的答案，query中含有equations中没有的变量都返回结果-1.0
            double r = -1.0;
            String queryVar0 = queries.get(i).get(0);
            String queryVar1 = queries.get(i).get(1);
            // query的两个变量都包含在hash表中，不包含r=-1.0
            if (varNumMap.containsKey(queryVar0) && varNumMap.containsKey(queryVar1)) {
                int queryVarNum0 = varNumMap.get(queryVar0);
                int queryvarNum1 = varNumMap.get(queryVar1);
                // >0表示可达
                if (0 < adjGraph[queryVarNum0][queryvarNum1])
                    r = adjGraph[queryVarNum0][queryvarNum1];
            }
            rs[i] = r;
        }

        return rs;
    }

    /**
     * ！！带权并查集
     * - 并查集，记录结点的根结点，存储结点之间的关系
     * - 问题转换，将查询点a到点b的权值v[a]/v[b] 转化为 求{点a到共同根节点f的权值}/{b点到共同根节点f的权值}
     * <p>
     * 数据结构
     * - v[i]代表第i个等式的值，端点a到b的权值
     * - w[a]=a点到其根节点的权值
     * - f[a]=a点的根节点
     *
     * <p>
     * 问题转换
     * - 若点a和点b有共同的根结点p，则v[a]/v[b]=(v[a]/v[root])/(v[b]/v[root])
     * - v[a]/v[root]=m
     * - v[b]/v[root]=n
     * - v[a]/v[b]=m/n
     *
     * 带权并查集/定义并查集f[]
     * - f[a]代表a点的根节点
     *
     * 带权并查集/定义权值数组w[]
     * - w[a]代表a点到其根节点的权值，w[a]=v[a]/v[aRoot]
     *
     * <p>
     * ！！构造带权并查集
     * - 寻找点i的根节点
     * - 合并两端点a和b的根节点，因为问题转换中需要找到相同的根节点f
     * <p>
     * 寻找点i的根节点
     * - 点i到父节点的权值，w[i]=v[i]/v[pi]
     * - 点i到父节点的父节点的权值，w[i]=v[i]/v[ppi]=(v[i]/v[pi])*(v[pi]/v[ppi])=w[i]*w[pi]
     * - 点i到其根节点的权值，w[i]=w[i]*w[pi]*w[ppi]*...*w[iRoot]
     *
     * <p>
     * 合并两端点a和b的根节点，！为了构造共同根节点
     * - 合并点a和点b的根节点，f[aRoot]=bRoot
     * - 更新点a根节点的权值，w[aRoot]=v[aRoot]/v[bRoot]=(v[a]/w[a])/(v[b]/w[b])=(v[a]/v[b])*(w[b]/w[a])
     */
    public double[] calcEquation3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /*使用hash表记录<变量,变量编号>，求得不同变量的数量*/
        // 不同变量总数量
        int amountVar = 0;
        // <变量,变量编号>
        Map<String, Integer> varNumMap = new HashMap<>();
        // 遍历equations数组，使用hash表记录<变量,变量编号>，求得不同变量的数量
        for (int i = 0; i < equations.size(); i++) {
            String var0 = equations.get(i).get(0);
            String var1 = equations.get(i).get(1);
            if (!varNumMap.containsKey(var0)) varNumMap.put(var0, amountVar++);
            if (!varNumMap.containsKey(var1)) varNumMap.put(var1, amountVar++);
        }

        // 构造带权并查集
        // iRoots[i]=结点i的根节点
        int[] iRoots = new int[amountVar];
        // iWeights[i到根节点]=权值
        double[] iWeights = new double[amountVar];
        // 初始化，结点的根结点是自己
        for (int i = 0; i < amountVar; i++)
            iRoots[i] = i;
        // 初始化，结点自己到自己的权值是1.0
        Arrays.fill(iWeights, 1.0);

        // 处理equations
        for (int i = 0; i < equations.size(); i++) {
            int vertexA = varNumMap.get(equations.get(i).get(0));
            int vertexB = varNumMap.get(equations.get(i).get(1));
            // 合并点a和点b的根结点，连接点a和点b的根结点
            this.merge(iRoots, iWeights, vertexA, vertexB, values[i]);
        }

        // 处理queries
        // 结果
        double[] rs = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            // 结果
            double r = -1.0;
            // 取出一个查询的端点
            String queryVertexA = queries.get(i).get(0);
            String queryVertexB = queries.get(i).get(1);
            if (varNumMap.containsKey(queryVertexA) && varNumMap.containsKey(queryVertexB)) {
                int queryVertexANum = varNumMap.get(queryVertexA);
                int queryVertexBNum = varNumMap.get(queryVertexB);
                // 在带权并差集中查找根
                int aRoot = findIRoot(iRoots, iWeights, queryVertexANum);
                int bRoot = findIRoot(iRoots, iWeights, queryVertexBNum);
                // 若根相同则表示当前query存在于equations中
                // 构造的带权并查集已经将equation端点的根节点相连了
                if (aRoot == bRoot)
                    // ！
                    r = iWeights[queryVertexANum] / iWeights[queryVertexBNum];
            }
            rs[i] = r;
        }
        return rs;
    }

    /**
     * 合并结点i和j
     *
     * @param a     结点a，一个等式的左变量的索引
     * @param b     结点b，一个等式的右变量的索引
     * @param value 等式的结果值
     */
    private void merge(int[] iRoots, double[] iWeights, int a, int b, double value) {
        int aRoot = findIRoot(iRoots, iWeights, a);
        int bRoot = findIRoot(iRoots, iWeights, b);
        // 合并两根节点
        iRoots[aRoot] = bRoot;
        // 更新aRoot的权值
        // ！
        iWeights[aRoot] = value * iWeights[b] / iWeights[a];
    }

    /**
     * 寻找结点i的根节点
     *
     * @param iRoots
     * @param iWeights
     * @param i        结点i
     */
    private int findIRoot(int[] iRoots, double[] iWeights, int i) {
        // iRoots的下标等于值，表示找到了i的根
        if (i != iRoots[i]) {
            // 递归寻找结点i的根节点
            int iRoot = findIRoot(iRoots, iWeights, iRoots[i]);
            // 更新结点的权值=结点的权值*父节点的权值
            // ！
            iWeights[i] = iWeights[i] * iWeights[iRoots[i]];
            // 更新结点的根结点
            iRoots[i] = iRoot;
        }

        return iRoots[i];
    }
}
