package com.xcrj;

import java.util.*;

/**
 * 本算法
 * - "本算法，内部结点key存在于右子树最左叶子结点的第0个key。下层内部结点不包含上层内部结点的key值"
 * - 因此本算法统一了边界情况和内部情况，而在内部结点和叶子结点的操作上有不同
 *
 * B+树
 * - 内部结点key数量+1=孩子指针数量。叶子结点key数量=记录数量
 * - 上层结点key出现在右侧key下方的子树中
 * - 每一层内部结点的key是其"右子结点"的"最左叶子结点"的"第0个key"
 * - 内部结点key一定出现在叶子结点中，不会出现在下层结点中
 * - 内部结点含有keys和children。叶子结点含有keys和data
 * - 内部结点key个数：[下(m/2),m-1]。孩子数[下(m/2), m]。key数量+1=孩子指针数量
 * - 根节点key个数：[1,m-1]。孩子数[2,m]。key数量=数据索引指针数量
 * -- 有一个指针指向关键字最小的叶子结点，所有叶子结点链接为一个链表
 * -- 叶子结点已经排好序，叶子结点包含全部关键字，叶子结点指向记录
 *
 * 优势对比B-树
 * - IO次数更少：B+树内部结点可以存储更多的关键字，B+树更矮
 * - IO次数稳定：B+树查询所有关键字的磁盘 I/O 次数都一样，查询效率稳定。
 * - 区间查找优势明显：B+树进行区间查找时更加简便实用。
 *
 * 参考
 * - https://blog.csdn.net/qq_36456827/article/details/122192984
 * - https://github.com/Morgan279/MemoryBasedBPlusTree
 */
public class BPlusTree<K extends Comparable<K>,V> {
    Node root;

    final int M;
    final int LB;//下边界
    final int UB;//上边界

    BPlusTree(int m){
        if(m<3) throw new RuntimeException("m>2");
        M=m;
        LB=m/2;
        UB=m-1;
    }

    /**
     * 获取key指向的记录
     * B+树是否存在
     * - 不存在，返回空集合
     * - 存在，查找，先查找内部结点，再查找叶子结点
     * @param key
     * @return
     */
    List<V> select(K key){
        if(root==null) return Collections.emptyList();
        return root.select(key);
    }

    /**
     * 范围查找
     * @param startInclude
     * @param endExclude
     * @return
     */
    public List<V> rangeSelect(K startInclude, K endExclude) {
        if(startInclude.compareTo(endExclude)>=0){
            throw new IllegalArgumentException("Invalid range");
        }
        if(root==null){
            return Collections.emptyList();
        }
        return root.rangeSelect(startInclude,endExclude);
    }

    /**
     *
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     */
    public boolean update(K key, V oldValue, V newValue) {
        if(root==null) return false;
        return root.update(key,oldValue,newValue);
    }

    /**
     * 插入查找，input key >= left ket && input key< right key。走right key下方的child指针
     * B+树是否存在
     * - B+不存在，创建结点为根结点
     * - B+存在，从内部结点走到叶子结点，添加到合适位置
     * - newNode为空，根结点未上溢
     * - newNode不为空，根节点上溢，构建新根结点
     * @param key
     * @param value
     */
    void insert(K key,V value){
        //根节点为null
        if(root==null){
            root=new LeafNode(toList(key), toList(toSet(value)));
            return;
        }

        //newChildNode 是否为null判断根结点是否上溢
        Node newChildNode=root.insert(key, value);
        if(newChildNode==null) return;
        K newRootKey=newChildNode.getClass().equals(LeafNode.class)?
                newChildNode.keys.get(0) :((InnerNode)newChildNode).findLeafNodeKey(newChildNode);
        root=new InnerNode(toList(newRootKey), toList(root,newChildNode));
        return;
    }

    /**
     * 删除key的所有value
     * @param key
     * @return true 有删除操作 false 无删除操作
     */
    public boolean delete(K key){
        //B+是否存在
        if(root==null) return false;
        //开始删除，从根结点一直走到叶子结点，
        //B+树从叶子结点开始删除，从叶子到根判断删除是否造成下溢出
        //B+树的所有key都会出现在叶子结点中，所以从叶子结点开始删除
        DelResult delResult=root.delete(key);
        //是否产生删除
        if(!delResult.isRemoved) return false;
        //根结点是否下溢出
        if(root.keys.isEmpty()){
            //处理下溢出
            root=handleRootUnderFlow();
        }
        return true;
    }

    /**
     * 删除key的指定value
     * @param key
     * @param value
     * @return true 有删除操作 false 无删除操作
     */
    public boolean delete(K key, V value){
        //B+树是否存在
        if(root==null) return false;
        DelResult delResult=root.delete(key,value);
        //是否产生删除
        if(!delResult.isRemoved) return false;
        //根结点是否下溢出
        if(root.keys.isEmpty()){
            root=handleRootUnderFlow();
        }
        return true;
    }

    public void clear(){
        root=null;
    }

    /**
     * 处理根结点的下溢出
     */
    private Node handleRootUnderFlow(){
        //根结点是叶子结点 返回null。根结点是内部结点 返回根结点的第0个孩子，因为只剩下第0个子树
        return root.getClass().equals(LeafNode.class)
                ?null:((InnerNode)root).children.get(0);
    }

    //泛型为T，... 可变参数
    private final <T> List<T> toList(T...t) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, t);
        return list;
    }

    private final <T> Set<T> toSet(T... t){
        Set<T> set=new HashSet<>();
        Collections.addAll(set,t);
        return set;
    }

    //抽象方法，(非)叶子结点利用多态
    abstract class Node{
        //(非)叶子结点 key相同
        List<K> keys;

        /**
         * 内部结点和叶子结点的下溢出
         * 根节点的下溢出不使用这个方法
         * 根节点的下溢出是根节点为空
         * @return
         */
        boolean isUnderFlow(){
            return keys.size()<LB;
        }

        /**
         * 根结点，内部结点，叶子结点的上溢出
         * 都是>m-1，等于m时需要分裂
         * @return
         */
        boolean isOverFlow(){
            return keys.size()>UB;
        }
        int getMidIdx(){
            return M/2;
        }

        /**
         * >=key所在子树
         * - input key >= current key 往右走
         * - input key < current key 往左走
         * 二分法非递归形式, O(logN)
         * @param key
         * @return >=key所在子树的idx
         */
        int biggerKeyIdx(K key){
            int l=0,r=keys.size();
            while (l<r){
                int mid=(l+r)>>1;
                if(keys.get(mid).compareTo(key)<=0){
                    l=mid+1;
                }else {
                    r=mid;//没有mid-1
                }
            }
            return l;
        }
        //将返回添加的结点，若已存在将返回null
        abstract Node insert(K key, V value);
        //删除key，所有value
        abstract DelResult delete(K key);
        //删除key，指定value
        abstract DelResult delete(K key, V value);
        //更新，成功或失败
        abstract boolean update(K key,V oldValue,V newValue);
        //查找
        abstract List<V> select(K key);
        //范围查找[startKInclude, endKExclude)
        abstract List<V> rangeSelect(K startKInclude, K endKExclude);
        //拆分。重写 返回类型可以是派生类
        abstract Node split();
        //借, 向左/右兄弟借
        abstract void borrow(K parentKey,Node sibling,boolean isLeft);
        //合并，左孩子合并右孩子和父key
        abstract void merge(K parentKey,Node rightSibling);
    }

    class InnerNode extends Node{
        List<Node> children;
        InnerNode(List<K> keys,List<Node> children){
            this.keys=keys;
            this.children=children;
        }

        /**
         * !!! 每一层内部结点的key是其"右子结点"的"最左叶子结点"的"第0个key"
         * @param node
         * @return
         */
        K findLeafNodeKey(Node node){
            if(node.getClass().equals(LeafNode.class)){
                return node.keys.get(0);
            }
            return findLeafNodeKey(((InnerNode)node).children.get(0));
        }

        /**
         * 处理下溢出
         * @param parentKeyIdx
         * @param childNode
         * @param childIdx
         */
        private void handleUnderflow(int parentKeyIdx, Node childNode, int childIdx) {
            Node sibling;
            //可否向左兄弟借
            if(childIdx>0
                    &&(sibling=this.children.get(childIdx-1)).keys.size()>LB){
                //借左兄弟最右侧的元素
                childNode.borrow(this.keys.get(parentKeyIdx),sibling,true);
                //使用右孩子结点最左侧叶子结点的第0个key重置父亲结点key
                this.keys.set(parentKeyIdx
                        ,childNode.getClass().equals(LeafNode.class)
                                ?childNode.keys.get(0):findLeafNodeKey(childNode));
                return;
            }
            //可否向右兄弟借
            if(childIdx<this.children.size()-1
                    &&(sibling=this.children.get(childIdx+1)).keys.size()>LB){
                //右兄弟结点的父亲key索引
                parentKeyIdx=childIdx==0?0:Math.min(this.keys.size()-1,parentKeyIdx+1);
                //借右兄弟最左侧的元素
                childNode.borrow(this.keys.get(parentKeyIdx),sibling,false);
                //使用右兄弟结点最左侧叶子结点的第0个key重置父亲结点key
                this.keys.set(parentKeyIdx
                        ,childNode.getClass().equals(LeafNode.class)
                                ?sibling.keys.get(0):findLeafNodeKey(sibling));
                return;
            }
            //可否合并到左兄弟
            if(childIdx>0){
                //获取左孩子
                sibling=this.children.get(childIdx-1);
                //左孩子合并右孩子和父亲key
                sibling.merge(this.keys.get(parentKeyIdx),childNode);
                //父结点删除被合并的key和右孩子
                this.keys.remove(parentKeyIdx);
                this.children.remove(childIdx);
                return;
            }
            //可否合并到右兄弟
            else{
                //获取右孩子
                sibling=this.children.get(1);//childNode在0，自然右兄弟在1
                //左孩子合并右孩子和父亲key
                childNode.merge(this.keys.get(parentKeyIdx),sibling);
                //父结点删除被合并的key和右孩子
                this.keys.remove(parentKeyIdx);
                this.children.remove(1);
            }
        }

        /**
         * 分裂内部结点
         *
         * 获取中间索引
         * 临时保存所有keys和children
         * 保留0~midIdx的keys，0~midIdx+1的children。孩子数量比结点数量多1
         * 新建内部结点，存储midIdx+1~keys.size()的keys，midIdx+1~children.size()
         * @return 新内部结点
         */
        @Override
        InnerNode split(){
            int midIdx=getMidIdx();
            List<K> allKeys=keys;
            List<Node> allChildren=children;

            keys=new ArrayList<>(keys.subList(0,midIdx));
            children=new ArrayList<>(children.subList(0,midIdx+1));
            //内部结点拆分不保留midIdx所在元素，因为midIdx所在元素在叶子结点中
            List<K> rightKeys=new ArrayList<>(allKeys.subList(midIdx+1,allKeys.size()));
            List<Node> rightChildren=new ArrayList<>(allChildren.subList(midIdx+1,allChildren.size()));
            InnerNode rightInnerNode=new InnerNode(rightKeys,rightChildren);
            return rightInnerNode;
        }

        /**
         * 内部结点插入
         *
         * 插入到叶子结点可能上溢出
         * 插入到内部结点可能上溢出
         * 插入到root结点可能上溢出
         *
         * 是否存在新子结点
         * - 不存在，返回null
         * - 存在，父结点添加新子结点，是否上溢出
         * -- 上溢出，返回新子结点
         * -- 不上溢出，返回null
         *
         * @param key
         * @param value
         * @return 不上溢出返回null，上溢出返回新结点
         */
        @Override
        Node insert(K key, V value) {
            Node newChildNode=children.get(biggerKeyIdx(key)).insert(key,value);
            if(newChildNode==null) return null;
            /**
             * 存在，父结点添加新子结点newChildNode，是否上溢出
             *
             * 根据新子结点寻找将插入到父结点中的边界key upKey
             * upKey插入位置upKeyIdx
             * 父结点中插入upKey, 插入新子结点newChildNode
             * 不上溢返回null，上溢返回新子结点
             */
            K upKey=findLeafNodeKey(newChildNode);
            int upKeyIdx= biggerKeyIdx(upKey);
            keys.add(upKeyIdx,upKey);
            children.add(upKeyIdx+1,newChildNode);
            //split()是内部结点的split函数
            return isOverFlow()? split():null;
        }

        /**
         * 删除内部结点
         * @param key
         * @return
         */
        @Override
        DelResult delete(K key) {
            //获取>=结点idx和结点对象
            int biggerKIdx= biggerKeyIdx(key);
            Node biggerNode=this.children.get(biggerKIdx);
            //获取父亲key索引
            int parentKeyIdx=Math.max(0,biggerKIdx-1);
            //>=结点递归删除
            DelResult delResult=biggerNode.delete(key);
            //是否产生删除
            if(!delResult.isRemoved) return delResult;
            //删除是否产生下溢出
            if(!delResult.isUnderFlow) return delResult;
            //处理儿子下溢出
            handleUnderflow(parentKeyIdx,biggerNode,biggerKIdx);
            //判断本结点即父亲结点是否有下溢出
            return new DelResult(true,isUnderFlow());
        }

        /**
         * 删除内部结点
         * @param key
         * @param value
         * @return
         */
        @Override
        DelResult delete(K key, V value) {
            //获取>=结点索引和父亲key索引
            int geIdx= biggerKeyIdx(key);
            int parentKeyIdx=Math.max(0,geIdx-1);
            Node geNode=this.children.get(geIdx);
            //>=结点递归删除
            DelResult delResult=geNode.delete(key,value);
            //是否产生删除
            if(!delResult.isRemoved) return delResult;
            //删除是否产生下溢出
            if(!delResult.isUnderFlow) return delResult;
            //处理下溢出
            handleUnderflow(parentKeyIdx,geNode,geIdx);
            return new DelResult(true,isUnderFlow());
        }

        @Override
        boolean update(K key, V oldValue, V newValue) {
            return children.get(biggerKeyIdx(key)).update(key,oldValue,newValue);
        }

        /**
         * 递归查找内部结点，直到到叶子结点
         * @param key
         * @return 叶子结点中key元素指向的记录
         */
        @Override
        List<V> select(K key) {
            //初始在root结点中查找
            int idx= biggerKeyIdx(key);
            return children.get(idx).select(key);
        }

        @Override
        List<V> rangeSelect(K startKInclude, K endKExclude) {
            return children.get(biggerKeyIdx(startKInclude)).rangeSelect(startKInclude,endKExclude);
        }

        /**
         * 内部结点合并
         * @param parentKey 父亲key
         * @param rightSibling 右孩子
         */
        @Override
        void merge(K parentKey, Node rightSibling) {
            //左孩子 merge 父亲key
            this.keys.add(parentKey);
            //左孩子 merge 右孩子 的keys和children
            InnerNode innerRightSibNode=(InnerNode) rightSibling;
            this.keys.addAll(innerRightSibNode.keys);
            this.children.addAll(innerRightSibNode.children);
        }

        /**
         * 内部结点借
         * 向左兄弟借
         * 向右兄弟借
         * @param parentKey
         * @param sibling
         * @param isLeft
         */
        @Override
        void borrow(K parentKey, Node sibling, boolean isLeft) {
            InnerNode innerSibNode=(InnerNode) sibling;
            int borrowKeyIdx;
            int borrowChildIdx;
            if(isLeft){
                //借用父亲key
                this.keys.add(0,parentKey);
                //借左兄弟的最右侧孩子
                borrowKeyIdx=innerSibNode.keys.size()-1;
                borrowChildIdx=innerSibNode.children.size()-1;
                this.children.add(0,innerSibNode.children.get(borrowChildIdx));
            }else{
                //借用父亲key
                this.keys.add(parentKey);
                //借右兄弟的最左侧孩子
                borrowKeyIdx=0;
                borrowChildIdx=0;
                this.children.add(innerSibNode.children.get(borrowChildIdx));
            }
            //删除被借用兄弟的被借用元素
            innerSibNode.keys.remove(borrowKeyIdx);//叶子结点仍然存在这个key
            innerSibNode.children.remove(borrowChildIdx);
        }
    }

    class LeafNode extends Node{
        List<Set<V>> vals;
        LeafNode next;
        //一个结点多个key，一个key多个value
        LeafNode(List<K> keys,List<Set<V>> vals){
            this.keys=keys;
            this.vals = vals;
        }
        /**
         * 获取key在叶子结点中的idx
         * 是否在叶子结点中找到相等key
         * - 未找到，返回-1
         * - 找到，返回key所在idx
         * @param key
         * @return 不存在返回-1，存在返回idx
         */
        int eqKeyIdx(K key){
            int l=0,r=keys.size()-1;
            while(l<=r){//<=
                int mid=(l+r)>>1;
                int cp=keys.get(mid).compareTo(key);
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
         * 分裂叶子结点
         *
         * 获取中间索引
         * 临时保存所有keys和children
         * 保留0~midIdx的keys，0~midIdx的data
         * 新建叶子结点，存储midIdx~tempKeys.size()的keys，midIdx~tempData.size()
         * 连接next指针
         * @return 新叶子结点
         */
        @Override
        LeafNode split(){
            int midIdx=getMidIdx();
            List<K> allKeys=keys;
            List<Set<V>> allVals= vals;

            keys=new ArrayList<>(allKeys.subList(0,midIdx));
            vals =new ArrayList<>(allVals.subList(0,midIdx));
            //叶子结点拆分 newLeafNode 需要保留midIdx所在元素。
            List<K> rightKeys=new ArrayList<>(allKeys.subList(midIdx,allKeys.size()));
            List<Set<V>> rightData=new ArrayList<>(allVals.subList(midIdx,allVals.size()));
            LeafNode newLeafNode=new LeafNode(rightKeys,rightData);

            newLeafNode.next=this.next;
            this.next=newLeafNode;
            return newLeafNode;
        }

        /**
         * key是否在叶子结点中
         * - 在，添加value，返回null
         * - 不在，添加key和value，是否上溢
         * -- 上溢，返回新叶子结点
         * -- 不上溢，返回null
         * @param key
         * @param value
         * @return
         */
        @Override
        Node insert(K key, V value) {
            int eqKIdx=eqKeyIdx(key);
            if(eqKIdx!=-1){
                vals.get(eqKIdx).add(value);
                return null;
            }

            int biggerKIdx= biggerKeyIdx(key);
            keys.add(biggerKIdx,key);
            vals.add(biggerKIdx, toSet(value));
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
            //删除key和value。叶子结点的key和val的数量相等，key下紧跟val
            this.keys.remove(eqIdx);
            this.vals.remove(eqIdx);
            //返回结果
            return new DelResult(true,isUnderFlow());
        }

        /**
         * 叶子结点删除
         * @param key
         * @param value
         * @return
         */
        @Override
        DelResult delete(K key, V value) {
            //寻找相等key的索引
            int eqIdx=eqKeyIdx(key);
            //key是否存在
            if(eqIdx==-1) return new DelResult(false, false);
            //key存在value是否存在
            if(!this.vals.get(eqIdx).contains(value)) return new DelResult(false, false);
            //删除values中的value
            this.vals.get(eqIdx).remove(value);
            //values是否被删除完了是否为空
            if(this.vals.get(eqIdx).isEmpty()){
                //删除空对象
                this.keys.remove(eqIdx);
                this.vals.remove(eqIdx);
            }
            //返回结果
            return new DelResult(true,isUnderFlow());
        }

        /**
         * 叶子结点更新key的value
         * 叶子结点中是否存在key
         * - 不存在，返回false
         * - 存在，更新key的value
         * @param key
         * @param oldValue
         * @param newValue
         * @return
         */
        @Override
        boolean update(K key, V oldValue, V newValue) {
            int idx=eqKeyIdx(key);
            //key不存在
            if(idx==-1) return false;
            //oldValue不存在
            if(!vals.get(idx).contains(oldValue)) return false;
            vals.get(idx).remove(oldValue);
            vals.get(idx).add(newValue);
            return true;
        }

        /**
         * 获取叶子结点中key元素指向的记录
         * 是否在叶子结点中找到相等key
         * - 未找到，返回空记录，空集合
         * - 找到，返回key所在idx对应的记录
         * @param key
         * @return
         */
        @Override
        List<V> select(K key) {
            int idx=eqKeyIdx(key);
            return idx==-1? Collections.emptyList()
                    :new ArrayList<>(vals.get(idx));
        }

        //todo 优化
        @Override
        List<V> rangeSelect(K startKInclude, K endKExclude) {
            //初始叶子结点（不一定是第一个叶子结点），考虑start和end。
            //s,l,r 0; s=l,r s+1; l,s,r s+1; l,r=s r+1; l,r,s r+1
            //startBiggerIdx=1, s+1, r+1
            int startBiggerIdx=Math.max(1, biggerKeyIdx(startKInclude));
            //e,l,r 0; e=l,r e+1; l,e,r e+1; l,r=e r+1; l,r,e r+1
            //endBiggerIdx=0, e+1, r+1
            int endBiggerIdx= biggerKeyIdx(endKExclude);

            //startIdx=0, s, r
            int startIdx=startBiggerIdx-1;
            //endIdx=-1, e, r
            int endIdx=endBiggerIdx-1;

            if(endIdx==-1) return Collections.emptyList();
            //e<=r，排除endIdx，因为endKExclude
            if(endKExclude==keys.get(endIdx)) endIdx--;

            List<V> list = new ArrayList<>();
            for (int i = startIdx; i <= endIdx; i++) {
                list.addAll(vals.get(i));
            }

            //后续叶子结点，只考虑end。
            LeafNode nextLeaf=next;
            while(nextLeaf!=null){
                for (int i = 0; i < nextLeaf.keys.size(); i++) {
                    if(nextLeaf.keys.get(i).compareTo(endKExclude)<0){
                        list.addAll(nextLeaf.vals.get(i));
                    }else{
                        return list;
                    }
                }
                nextLeaf=nextLeaf.next;
            }

            return list;
        }

        /**
         * 叶子结点合并
         * @param parentKey 无用
         * @param rightSibling 右兄弟
         */
        @Override
        void merge(K parentKey, Node rightSibling) {
            //左孩子 merge 右孩子的keys和vals
            LeafNode leafRightSibNode=(LeafNode) rightSibling;
            this.keys.addAll(leafRightSibNode.keys);
            this.vals.addAll(leafRightSibNode.vals);
            //设置next指针
            this.next=leafRightSibNode.next;
        }

        /**
         * 叶子结点借
         * 向左兄弟借
         * 向右兄弟借
         * @param parentKey 无用
         * @param sibling
         * @param isLeft
         */
        @Override
        void borrow(K parentKey, Node sibling, boolean isLeft) {
            LeafNode leafSibNode=(LeafNode) sibling;
            int borrowIdx;
            if(isLeft){
                //借用左兄弟结点的最右侧的元素 作我的最左侧元素
                borrowIdx=leafSibNode.keys.size()-1;
                this.keys.add(0,leafSibNode.keys.get(borrowIdx));
                this.vals.add(0,leafSibNode.vals.get(borrowIdx));
            }else {
                //借用右兄弟结点的最右侧的元素 作我的最右侧元素
                borrowIdx=0;
                this.keys.add(leafSibNode.keys.get(borrowIdx));
                this.vals.add(leafSibNode.vals.get(borrowIdx));
            }
            //删除被借用兄弟的被借用元素
            leafSibNode.keys.remove(borrowIdx);
            leafSibNode.vals.remove(borrowIdx);
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

    public static void main(String[] args) {
        BPlusTree<Integer,Integer> bplus=new BPlusTree(5);
        int times=100000;
        int maxLen=100;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            int[]as=getAs(maxLen,maxV);
            for (int j = 0; j < as.length; j++) {
                bplus.insert(as[j],j);
            }

            int a=(int)(Math.random()*as.length);
            int key=as[a];
            List<Integer> bs=bplus.select(key);
            if(!bs.contains(a)){
                throw new RuntimeException();
            }

            bplus.update(key,a,200);
            bs=bplus.select(key);
            if(!bs.contains(200)){
                throw new RuntimeException();
            }

            bplus.delete(key);
            if(!bplus.select(key).isEmpty()){
                throw new RuntimeException();
            }

            bplus.insert(key,300);
            bplus.insert(key,400);
            if(!bplus.select(key).contains(300)){
                throw new RuntimeException();
            }
            bplus.delete(key,300);
            if(bplus.select(key).contains(300)){
                throw new RuntimeException();
            }

            bplus.clear();
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
