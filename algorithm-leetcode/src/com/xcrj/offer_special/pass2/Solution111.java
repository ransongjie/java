package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 111. 计算除法
 * 输入，
 * List<List<String>> equations, 元素是被除数A和除数B
 * double[] values, 元素=A/b
 * List<List<String>> queries, 需要查询的除法结果 A/B
 */
public class Solution111 {

    //广度优先遍历+建图
    //A作左端点，B作为右端点，商作为边权
    public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //变量编号
        //Map<变量,编号>
        int varCount=0;
        Map<String,Integer> varNum=new HashMap<>();
        //有序遍历
        for(List<String> equation:equations){
            String A=equation.get(0);
            String B=equation.get(1);
            if(!varNum.containsKey(A))varNum.put(A,varCount++);
            if(!varNum.containsKey(B))varNum.put(B,varCount++);
        }

        //建立图，邻接表
        List<Pair>[] vertexPairs=new List[varCount];
        for(int i=0;i<varCount;i++)vertexPairs[i]=new ArrayList<>();
        for(int i=0;i<equations.size();i++){
            List<String> equation=equations.get(i);

            String A=equation.get(0);
            String B=equation.get(1);
            int ANum=varNum.get(A);
            int BNum=varNum.get(B);

            vertexPairs[ANum].add(new Pair(BNum, values[i]));
            vertexPairs[BNum].add(new Pair(ANum, 1.0/values[i]));
        }

        //求每个查询的结果
        double[] rs=new double[queries.size()];
        for(int i=0;i<rs.length;i++){
            //查询不到结果时，默认值-1.0
            double r=-1.0;

            //处理1个查询
            String A=queries.get(i).get(0);
            String B=queries.get(i).get(1);
            if(varNum.containsKey(A)&&varNum.containsKey(B)){
                int ANum=varNum.get(A);
                int BNum=varNum.get(B);

                //特殊情况，同一个变量
                if(ANum==BNum) r=1.0;
                //广度优先搜索
                else{
                    Queue<Integer> que=new ArrayDeque<>();
                    que.offer(ANum);

                    double[] AToNRatios=new double[varCount];
                    Arrays.fill(AToNRatios, -1.0);
                    AToNRatios[ANum]=1.0;

                    //xcrj 首次广度优先遍历到的距离才是最短距离
                    while(AToNRatios[BNum]<0.0&&!que.isEmpty()){
                        int vertex=que.poll();

                        for(Pair pair:vertexPairs[vertex]){
                            int vertextAdj=pair.adj;
                            if(AToNRatios[vertextAdj]<0.0){
                                AToNRatios[vertextAdj]=AToNRatios[vertex]*pair.value;
                                que.offer(vertextAdj);
                            }
                        }
                    }
                    r=AToNRatios[BNum];
                }
            }
            rs[i]=r;
        }

        return rs;
    }

    //邻接点和边权
    class Pair{
        int adj;
        double value;
        Pair(int adj,double value){
            this.adj=adj;
            this.value=value;
        }
    }

    //建立树+Floyd预处理
    //Floyd 预先计算两点之间的距离
    //查询次数很多的情形，如果每次查询都广度优先搜索一次，效率会变低
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //变量编号
        //Map<变量,编号>
        int varCount=0;
        Map<String,Integer> varNum=new HashMap<>();
        //有序遍历
        for(List<String> equation:equations){
            String A=equation.get(0);
            String B=equation.get(1);
            if(!varNum.containsKey(A))varNum.put(A,varCount++);
            if(!varNum.containsKey(B))varNum.put(B,varCount++);
        }

        //建立图，邻接表
        double[][] ijs=new double[varCount][varCount];
        for(int i=0;i<ijs.length;i++)Arrays.fill(ijs[i], -1.0);
        for(int i=0;i<equations.size();i++){
            List<String> equation=equations.get(i);

            String A=equation.get(0);
            String B=equation.get(1);
            int ANum=varNum.get(A);
            int BNum=varNum.get(B);

            ijs[ANum][BNum]=values[i];
            ijs[BNum][ANum]=1.0/values[i];
        }

        //Floyd算法, 从点i到点j经过点k，k（中继结点）最后变化
        for(int k=0;k<varCount;k++){
            for(int i=0;i<varCount;i++){
                for(int j=0;j<varCount;j++){
                    if(ijs[i][k]>0&&ijs[k][j]>0){
                        if(ijs[i][j]<0)ijs[i][j]=ijs[i][k]*ijs[k][j];
                        if(ijs[i][j]>ijs[i][k]*ijs[k][j])ijs[i][j]=ijs[i][k]*ijs[k][j];
                    }
                }
            }
        }

        //求每个查询的结果
        double[] rs=new double[queries.size()];
        for(int i=0;i<rs.length;i++){
            //查询不到结果时，默认值-1.0
            double r=-1.0;

            //处理1个查询
            String A=queries.get(i).get(0);
            String B=queries.get(i).get(1);
            if(varNum.containsKey(A)&&varNum.containsKey(B)){
                int ANum=varNum.get(A);
                int BNum=varNum.get(B);
                if(ijs[ANum][BNum]>0)r=ijs[ANum][BNum];
            }
            rs[i]=r;
        }

        return rs;
    }

    //带权并查集
    //a/b >转换为> (a/aRoot)/(b/bRoot), aRoot=bRoot。因此需要合并 a和b的根结点
    public double[] calcEquation3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //变量编号
        //Map<变量,编号>
        int varCount=0;
        Map<String,Integer> varNum=new HashMap<>();
        //有序遍历
        for(List<String> equation:equations){
            String A=equation.get(0);
            String B=equation.get(1);
            if(!varNum.containsKey(A))varNum.put(A,varCount++);
            if(!varNum.containsKey(B))varNum.put(B,varCount++);
        }

        //iRoots[i]=root
        int[] iRoots=new int[varCount];
        //初始化，自己是自己的根节点
        for(int i=0;i<varCount;i++)iRoots[i]=i;
        //iRootWeights[i]=i到其根结点的权值
        double[] iRootWeights=new double[varCount];
        //初始化，自己/自己=1.0
        Arrays.fill(iRootWeights, 1.0);

        //xcrj 合并每个等式两端点的根节点
        for(int i=0;i<equations.size();i++){
            String A=equations.get(i).get(0);
            String B=equations.get(i).get(1);
            int ANum=varNum.get(A);
            int BNum=varNum.get(B);
            //合并
            mergeRoot(iRoots, iRootWeights, ANum, BNum, values[i]);
        }

        //求每个查询的结果
        double[] rs=new double[queries.size()];
        for(int i=0;i<rs.length;i++){
            //查询不到结果时，默认值-1.0
            double r=-1.0;

            //处理1个查询
            String A=queries.get(i).get(0);
            String B=queries.get(i).get(1);
            if(varNum.containsKey(A)&&varNum.containsKey(B)){
                int ANum=varNum.get(A);
                int BNum=varNum.get(B);
                int ARoot=findIRoot(iRoots, iRootWeights, ANum);
                int BRoot=findIRoot(iRoots, iRootWeights, BNum);

                //根节点相同，待查询等式两端点存在与equations中
                //r=a/b=(a/aRoot)/(b/bRoot), aRoot=bRoot
                if(ARoot==BRoot)r=iRootWeights[ANum]/iRootWeights[BNum];
            }
            rs[i]=r;
        }

        return rs;
    }

    //合并a, b的根节点
    private void mergeRoot(int[] iRoots, double[] iRootWeights, int a, int b, double value) {
        int aRoot=findIRoot(iRoots, iRootWeights, a);
        int bRoot=findIRoot(iRoots, iRootWeights, b);
        //合并根节点
        iRoots[aRoot]=bRoot;
        //更新权值
        //(a/b)*(b/bRoot)/(a/aRoot)=aRoot/bRoot
        iRootWeights[aRoot]=value*iRootWeights[b]/iRootWeights[a];
    }

    //寻找结点i的根节点
    private int findIRoot(int[] iRoots, double[] iRootWeights, int i) {
        //下标等于值为根
        int pi=iRoots[i];
        if(i!=pi){
            int ppi=findIRoot(iRoots, iRootWeights,pi);
            //更新i权值 i>ppi
            //点i到父节点pi的父节点ppi的权值=i到pi的权值 * pi到ppi的权值
            iRootWeights[i]=iRootWeights[i]*iRootWeights[pi];
            //更新i根结点 i>ppi
            iRoots[i]=ppi;
        }

        //返回i的根结点
        return iRoots[i];
    }
}
