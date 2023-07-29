package com.xcrj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * B树
 * 性质
 * - 内部结点：key个数 [上(m/2)-1,m-1]，孩子数[上(m/2), m]，key个数+1=孩子数
 * - 根节点：key个数 [1,m-1]，孩子数[2,m]，key个数+1=孩子数
 * - B树的所有叶子结点都位于同一层
 * 对比
 * - 对比B+树：B-的key和value放到了每一个结点中；B+树key在每个结点中，value在叶子结点中
 * - 对比于AVL：B-树是m分支平衡搜索树，AVL是2分支平衡搜索树
 */
public class BTree<K extends Comparable<K>,V> {
    Node root;

    final int M;
    final int LB;//下边界
    final int UB;//上边界

    BTree(int m){
        if(m<3) throw new RuntimeException("M>=3");
        M=m;
        LB=(int)Math.ceil(m/2.0)-1;//一定要用2.0
        UB=m-1;
    }

    //B-树的key和value存在于每个结点中
    class Pair{
        K key;
        V val;
        Pair(K key,V val){
            this.key=key;
            this.val=val;
        }
    }

    abstract class Node{
        List<Pair> kvs;

        boolean isOverFlow(){
            return kvs.size()>UB;
        }

        boolean isUnderFlow(){
            return kvs.size()<LB;
        }

        int getMidIdx(){
            return M/2;
        }

        /**
         * 比当前结点左边界更小 返回0
         * 等于当前结点左边界 返回1
         * 在当前结点左右边界中 返回首个大于key的idx
         * 等于当前结点右边界 返回size
         * 大于当前结点右边界 返回size
         * @param key
         * @return
         */
        int biggerKeyIdx(K key){
            int l=0,r=kvs.size();
            while (l<r){
                int mid=(l+r)>>1;
                if(kvs.get(mid).key.compareTo(key)<=0){
                    l=mid+1;
                }else {
                    r=mid;//没有mid-1
                }
            }
            return l;
        }

        //后继结点，用于删除时交换，入参是父亲key的右孩子
        abstract Pair succesor();
        //将返回添加的结点，若已存在将返回null
        abstract Node insert(K key, V value);
        //删除key，value
        abstract DelResult delete(K key);
        //更新，成功或失败
        abstract boolean update(K key,V value);
        //查找
        abstract V select(K key);
        //拆分
        abstract Node split();
        //借, 向左/右兄弟借
        abstract void borrow(Pair parentKV, Node sibling, boolean isLeft);
        //合并，左孩子合并右孩子和父kv
        abstract void merge(Pair parentKV, Node rightSibling);
    }

    class InnerNode extends Node{
        //内部结点有孩子，叶子结点无孩子
        List<Node> children;
        InnerNode(List<Pair> kvs,List<Node> children){
            this.kvs=kvs;
            this.children=children;
        }

        /**
         * 内部结点
         * @return
         */
        @Override
        Pair succesor() {
            return this.children.get(0).succesor();
        }

        /**
         * 内部结点插入
         * @param key
         * @param value
         * @return
         */
        @Override
        Node insert(K key, V value) {
            //寻找插入位置 直到叶子结点 在叶子结点进行插入
            Node newRightChildNode=children.get(biggerKeyIdx(key)).insert(key,value);
            //newChildNode不为空 插入产生上溢出；为空 插入没有产生上溢出
            if(newRightChildNode==null) return null;
            //处理上溢出
            //新父亲元素插入到当前结点合适位置
            Pair newParentPair=newRightChildNode.kvs.get(0);//newChildNode第0个元素保存了新父亲元素
            newRightChildNode.kvs.remove(0);//删除新父亲元素
            int biggerKIdx=biggerKeyIdx(newParentPair.key);
            kvs.add(biggerKIdx,newParentPair);
            //newChildNode插入到新父亲元素右下。因为 newRightChildNode 是右孩子
            children.add(biggerKIdx+1,newRightChildNode);
            return isOverFlow()?split():null;
        }

        /**
         * 内部结点删除
         * @param key
         * @return
         */
        @Override
        DelResult delete(K key) {
            int biggerKIdx=biggerKeyIdx(key);
            Node biggerChildNode=children.get(biggerKIdx);
            //判断key相等。这种方式比判断biggerKIdx-1>-1简洁
            int parentKIdx=Math.max(0,biggerKIdx-1);
            Pair pair=kvs.get(parentKIdx);
            if(pair.key.compareTo(key)==0){
                //右下child开始调用successor
                Pair leafNodePair=this.children.get(biggerKIdx).succesor();
                swap(pair,leafNodePair);
            }
            //继续删除，直到叶子结点
            DelResult delResult=this.children.get(biggerKIdx).delete(key);
            //没有产生删除
            if(!delResult.isRemoved)return delResult;
            //没有产生下溢出
            if(!delResult.isUnderFlow)return delResult;
            //处理下溢出。从其父结点中处理叶子结点产生的下溢出
            handleUnderflow(parentKIdx,biggerChildNode,biggerKIdx);
            //判断本结点即父亲结点是否有下溢出
            return new DelResult(true,isUnderFlow());
        }

        /**
         * 内部结点更新
         * @param key
         * @param value
         * @return
         */
        @Override
        boolean update(K key, V value) {
            //key小于当前结点中所有key，biggerKIdx=0
            int biggerKIdx=biggerKeyIdx(key);
            //key相等则更新
            if(biggerKIdx-1>-1
                    &&kvs.get(biggerKIdx-1).key.compareTo(key)==0){
                kvs.get(biggerKIdx-1).val=value;
                return true;
            }
            return children.get(biggerKIdx).update(key,value);
        }

        /**
         * 内部结点查找
         * @param key
         * @return
         */
        @Override
        V select(K key) {
            //key小于当前结点中所有key，biggerKIdx=0
            int biggerKIdx=biggerKeyIdx(key);
            if(biggerKIdx-1>-1
                    &&kvs.get(biggerKIdx-1).key.compareTo(key)==0){
                return kvs.get(biggerKIdx-1).val;
            }
            return children.get(biggerKIdx).select(key);
        }

        /**
         * 内部结点拆分
         * @return
         */
        @Override
        Node split() {
            //获取midIdx
            int midIdx=getMidIdx();
            //临时保存所有结点所有元素
            List<Pair> allKvs=kvs;
            List<Node> allChildren=children;
            //复用旧结点
            kvs=new ArrayList<>(allKvs.subList(0,midIdx));
            children=new ArrayList<>(allChildren.subList(0,midIdx+1));
            //创建新结点。先加mid元素（将作父亲元素）保存到新子结点中，方便在父亲结点中处理
            List<Pair> rightKvs=new ArrayList<>(allKvs.subList(midIdx,allKvs.size()));
            List<Node> rightChildren=new ArrayList<>(allChildren.subList(midIdx+1,allChildren.size()));
            InnerNode rightNewLeafNode=new InnerNode(rightKvs,rightChildren);
            return rightNewLeafNode;
        }

        /**
         * 内部结点
         * 处理下溢出
         * @param parentKVIdx
         * @param childNode
         * @param childIdx
         */
        private void handleUnderflow(int parentKVIdx, Node childNode, int childIdx) {
            Node sibling;
            //可否向左兄弟借
            if(childIdx>0
                    &&(sibling=this.children.get(childIdx-1)).kvs.size()>LB){
                //借左兄弟最右侧的元素
                childNode.borrow(this.kvs.get(parentKVIdx),sibling,true);
                //使用左兄弟最右侧的kv去重置父亲kv。已经在borrow内部处理，这里不再处理
//                kvs.set(parentKVIdx,sibling.kvs.get(sibling.kvs.size()-1));
                return;
            }
            //可否向右兄弟借
            if(childIdx<this.children.size()-1
                    &&(sibling=this.children.get(childIdx+1)).kvs.size()>LB){
                //右兄弟结点的父亲key索引
                parentKVIdx=childIdx==0?0:Math.min(this.kvs.size()-1,parentKVIdx+1);
                //借右兄弟最左侧的元素
                childNode.borrow(this.kvs.get(parentKVIdx),sibling,false);
                //使用右兄弟最左侧的kv去重置父亲kv。已经在borrow内部处理，这里不再处理
//                kvs.set(parentKVIdx,sibling.kvs.get(0));
                return;
            }
            //可否合并到左兄弟
            if(childIdx>0){
                //获取左孩子
                sibling=this.children.get(childIdx-1);
                //左孩子合并右孩子和父亲key
                sibling.merge(this.kvs.get(parentKVIdx),childNode);
                //父结点删除被合并的key和右孩子
                this.kvs.remove(parentKVIdx);
                this.children.remove(childIdx);
                return;
            }
            //可否合并到右兄弟
            else{
                //获取右孩子
                sibling=this.children.get(1);//childNode在0，自然右兄弟在1
                //左孩子合并右孩子和父亲key
                childNode.merge(this.kvs.get(parentKVIdx),sibling);
                //父结点删除被合并的key和右孩子
                this.kvs.remove(parentKVIdx);
                this.children.remove(1);
            }
        }

        /**
         * 内部结点
         * @param parentKV
         * @param sibling
         * @param isLeft
         */
        @Override
        void borrow(Pair parentKV, Node sibling, boolean isLeft) {
            InnerNode innerSibNode=(InnerNode) sibling;
            //被借用的kv和child的idx
            int borrowKVIdx;
            int borrowChildIdx;
            if(isLeft){
                //借左兄弟的最右侧孩子
                borrowKVIdx=innerSibNode.kvs.size()-1;
                borrowChildIdx=innerSibNode.children.size()-1;
                //往右流动的借用方法
                kvs.add(0,parentKV);
                children.add(0,innerSibNode.children.get(borrowChildIdx));
                parentKV.key=innerSibNode.kvs.get(borrowKVIdx).key;
                parentKV.val=innerSibNode.kvs.get(borrowKVIdx).val;
            }else{
                //借右兄弟的最左侧孩子
                borrowKVIdx=0;
                borrowChildIdx=0;
                //往左流动的借用方法
                kvs.add(parentKV);
                children.add(innerSibNode.children.get(borrowChildIdx));
                parentKV.key=innerSibNode.kvs.get(borrowKVIdx).key;
                parentKV.val=innerSibNode.kvs.get(borrowKVIdx).val;
            }
            //删除被借用兄弟的被借用元素
            innerSibNode.kvs.remove(borrowKVIdx);
            innerSibNode.children.remove(borrowChildIdx);
        }

        /**
         * 内部结点合并
         * @param parentKV
         * @param rightSibling
         */
        @Override
        void merge(Pair parentKV, Node rightSibling) {
            //左孩子 merge 父亲key
            kvs.add(parentKV);
            //左孩子 merge 右孩子 的keys和children
            InnerNode innerRightSibNode=(InnerNode) rightSibling;
            kvs.addAll(innerRightSibNode.kvs);
            children.addAll(innerRightSibNode.children);
        }
    }

    class LeafNode extends Node{
        LeafNode(List<Pair> kvs){
            this.kvs=kvs;
        }

        /**
         * 相等key索引
         * @param key
         * @return 不存在返回-1，存在返回idx
         */
        int eqKeyIdx(K key){
            int l=0,r=kvs.size()-1;
            while(l<=r){//<=
                int mid=(l+r)>>1;
                int cp=kvs.get(mid).key.compareTo(key);
                if(cp==0){
                    return mid;
                }else if(cp<0){
                    l=mid+1;
                }else{
                    r=mid-1;//mid-1
                }
            }
            return -1;
        }

        /**
         * 叶子结点
         * @return
         */
        @Override
        Pair succesor() {
            return this.kvs.get(0);
        }

        //叶子结点插入
        @Override
        Node insert(K key, V value) {
            int biggerIdx=biggerKeyIdx(key);
            kvs.add(biggerIdx,new Pair(key,value));
            return isOverFlow()?split():null;
        }

        /**
         * 叶子结点删除
         * @param key
         * @return
         */
        @Override
        DelResult delete(K key) {
            //寻找相等key的索引
            int eqIdx=eqKeyIdx(key);
            //key是否存在
            if(eqIdx==-1) return new DelResult(false, false);
            //删除元素
            kvs.remove(eqIdx);
            //返回结果
            return new DelResult(true,isUnderFlow());
        }

        /**
         * 叶子结点更新
         * @param key
         * @param value
         * @return
         */
        @Override
        boolean update(K key, V value) {
            int eqKIdx=eqKeyIdx(key);
            if(eqKIdx==-1) return false;
            kvs.get(eqKIdx).val=value;
            return true;
        }

        /**
         * 叶子结点查找
         * @param key
         * @return
         */
        @Override
        V select(K key) {
            int eqKIdx=eqKeyIdx(key);
            if(eqKIdx==-1) return null;
            return kvs.get(eqKIdx).val;
        }

        /**
         * 叶子结点上溢出
         * @return
         */
        @Override
        Node split() {
            //获取midIdx
            int midIdx=getMidIdx();
            //临时保存所有结点所有元素
            List<Pair> allKvs=kvs;
            //复用旧结点
            kvs=new ArrayList<>(allKvs.subList(0,midIdx));
            //创建新结点。先加mid元素（将作父亲元素）保存到新子结点中，方便在父亲结点中处理
            List<Pair> rightKvs=new ArrayList<>(allKvs.subList(midIdx,allKvs.size()));
            LeafNode rightNewLeafNode=new LeafNode(rightKvs);
            return rightNewLeafNode;
        }

        /**
         * 叶子结点借
         * @param parentKV
         * @param sibling
         * @param isLeft
         */
        @Override
        void borrow(Pair parentKV, Node sibling, boolean isLeft) {
            LeafNode leafSibNode=(LeafNode) sibling;
            int borrowIdx;
            if(isLeft){
                //借用左兄弟结点的最右侧的元素
                borrowIdx=leafSibNode.kvs.size()-1;
                //往右流动的借用方法
                kvs.add(0,parentKV);
                parentKV.key=leafSibNode.kvs.get(borrowIdx).key;
                parentKV.val=leafSibNode.kvs.get(borrowIdx).val;
            }else {
                //借用右兄弟结点的最右侧的元素 作我的最右侧元素
                borrowIdx=0;
                //往左流动的借用方法
                kvs.add(parentKV);
                parentKV.key=leafSibNode.kvs.get(borrowIdx).key;
                parentKV.val=leafSibNode.kvs.get(borrowIdx).val;
            }
            //删除被借用兄弟的被借用元素
            leafSibNode.kvs.remove(borrowIdx);
        }

        /**
         * 叶子结点合并
         * @param parentKV 无用
         * @param rightSibling
         */
        @Override
        void merge(Pair parentKV, Node rightSibling) {
            kvs.add(parentKV);
            //左孩子 merge 右孩子的kvs
            LeafNode leafRightSibNode=(LeafNode) rightSibling;
            kvs.addAll(leafRightSibNode.kvs);
        }
    }


    class DelResult{
        //是否有过删除操作
        boolean isRemoved;
        //是否下溢出
        boolean isUnderFlow;
        DelResult(boolean isRemoved,boolean isUnderFlow){
            this.isRemoved=isRemoved;
            this.isUnderFlow=isUnderFlow;
        }
    }

    /**
     * 根据key查找value
     * @param key
     * @return
     */
    public V select(K key){
        if(root==null) return null;
        return root.select(key);
    }

    /**
     * 根据key使用newValue更新oldValue
     * @param key
     * @param value
     * @return true 更新成功，false 更新失败
     */
    public boolean update(K key, V value) {
        //B-树不存在
        if(root==null) return false;
        //key不存在
        if(select(key)==null) return false;
        return root.update(key,value);
    }

    /**
     * 插入key和value
     * @param key
     * @param value
     */
    void insert(K key,V value){
        //B-树不存在 创建叶子结点作根 返回
        if(root==null){
            root=new LeafNode(toList(new Pair(key,value)));
            return;
        }
        //key已存在 返回
        if(select(key)!=null)return;
        //插入新key-value，newChildNode不为空插入产生上溢出；newRightChildNode 为空插入没有产生上溢出
        Node newRightChildNode=root.insert(key,value);
        if(newRightChildNode==null)return;
        //处理根结点上溢
        Pair newRootPair=newRightChildNode.kvs.get(0);
        newRightChildNode.kvs.remove(0);
        root=new InnerNode(toList(newRootPair),toList(root,newRightChildNode));
        return;
    }

    /**
     * 根据key删除
     * @param key
     * @return
     */
    public boolean delete(K key){
        //B+是否存在
        if(root==null) return false;
        //key不存在
        if(select(key)==null) return false;
        //从根节点开始删除
        DelResult delResult=root.delete(key);
        //是否产生删除
        if(!delResult.isRemoved) return false;
        //根结点是否下溢出
        if(delResult.isUnderFlow){
            handleRootUnderFlow();
        }
        return true;
    }

    private void handleRootUnderFlow(){
        //根节点是叶子结点下溢出，1个元素都没有了，root=null
        //根节点是内部结点下溢出，只剩下第0个孩子了。
        root=root.getClass().equals(LeafNode.class)
                ?null:((InnerNode)root).children.get(0);
    }

    //交换pair的属性值
    private void swap(Pair pair1,Pair pair2){
        K tmpKey=pair1.key;
        V tmpVal=pair1.val;
        pair1.key=pair2.key;
        pair1.val=pair2.val;
        pair2.key=tmpKey;
        pair2.val=tmpVal;
    }

    /**
     * 清空
     */
    public void clear(){
        root=null;
    }

    //泛型为T，... 可变参数
    private final <T> List<T> toList(T...t) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, t);
        return list;
    }

    public static void main(String[] args) {
        BTree<Integer,Integer> bminus=new BTree(5);
        int times=100000;
        int maxLen=100;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[]as=getAs(maxLen,maxV);
            for (int j = 0; j < as.length; j++) {
                bminus.insert(as[j],j);
            }

            int a=(int)(Math.random()*as.length);
            int key=as[a];
            Integer b=bminus.select(key);
            //重复的值只会插入第一个，判断key相等与否即可
            if(as[b]!=key){
                throw new RuntimeException();
            }

            bminus.update(key,200);
            b=bminus.select(key);
            if(!b.equals(200)){
                throw new RuntimeException();
            }

            bminus.delete(key);
            if(bminus.select(key)!=null){
                throw new RuntimeException();
            }

            bminus.insert(key,300);
            if(!bminus.select(key).equals(300)){
                throw new RuntimeException();
            }
            bminus.delete(key);
            if(bminus.select(key)!=null){
                throw new RuntimeException();
            }

            bminus.clear();
        }
    }

    public static int[] getAs(int maxLen,int maxV){
        int len=(int)(Math.random()*maxLen+1);
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            as[i]=(int)(Math.random()*maxV);
        }
        return as;
    }
}