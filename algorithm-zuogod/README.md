# zuo_god/basic (左神基础)
[zuo_god/basic (左神基础)](https://www.bilibili.com/video/BV13g41157hK)

|Package|Intro|
|---|---|
|bit|位操作|
|sort|排序|
|dichotomy|二分法|
|link|链表|
|tree|二叉树|
|graph|图|
|prefix_tree|前缀树|
|greed|贪心|
|force|暴力递归|
|hash|hash|
|union_set|并查集|
|str|字符串，KMP，Manacher|
|window_max|窗口最大值|
|mono_stack|单调栈|
|limit_resource|资源限制|
|dp|暴力递归改动态规划|
|ordered_table|有序表|

|Intro|Package/Class|More|
|---|---|---|
|交换|bit/Yihuo||
|数组中只有1个元素奇数次其它偶数次|bit/Yihuo1Odd||
|数组中只有2个元素奇数次且两个元素不等其它偶数次|bit/Yihuo2Odd||
|加减乘除|bit2/AddSubMulDiv||
|返回更大的值|bit2/LargeNum||
|2的幂4的幂|bit2/Power24||
|选择排序|sort/SelectSort||
|冒泡排序|sort/BubbleSort||
|插入排序|sort/InsertSort|||
|快速排序|sort/QuickSort1|选择end做轴值，leftBorder变量保存左边界，<=end放到左边界 O(nlogn), recursive O(logn), unstable|
|快速排序|sort/QuickSort2|选择e做轴值，lb内的元素`<e`，rb内的元素`>e`，lb到rb之间的元素等于e|
|快速排序|sort/QuickSort3|选择随机值做轴值，lb内的元素`<e`，rb内的元素`>e`，lb到rb之间的元素等于e|
|归并排序|sort/MergeSort||
|小和问题|sort/MergeLSum|左侧比我小的数的和等价于右侧比我大数的个数|
|逆序对个数|sort/MergeReversePair|右侧比我小的数的个数|
|堆排序|sort/HeapSort|heapInsert, heapfy|
|堆排序|sort/HeapSort2|heapfy|
|桶排序|sort/BucketSort||
|基数排序|sort/RadixSort||
|经典二分查找|dichotomy/Dichotomy||
|>=k最左边的数|dichotomy/GeKLeftmost||
|局部最小值|dichotomy/LocalMin||
|深度拷贝含有随机指针的链表|link/CPRandomP||
|链表环的入口结点|link/Circle||
|返回可能有环链表的1个交点，可能没有交点|link/CrossPoint||
|link/L2Public|两独立有序链表的公共部分||
|link/PalindromeL|回文链表||
|tree/PreOrder|前序遍历，非递归||
|tree/Inorder|中序遍历，非递归||
|tree/PostOrder|后序遍历，非递归||
|tree/WidthOrder|宽度优先||
|tree/PreOrderSerialize PreOrderDeserialize|前序遍历序列化，反序列化||
|tree/InOrderSuccessor|中序遍历后继结点||
|tree/LowestCommonAncestor|最低公共祖先||
|tree/MaxWidth|最大宽度||
|tree/PaperFolding|折纸，从上到下输出折痕方向||
|tree/ABinarySearchTree|是否二叉搜索树||
|tree/IsAVLTree|是否平衡二叉树||
|tree/CompleteBinaryTree|是否完全二叉树||
|tree/FullBinaryTree|是否满二叉树||
|graph/Convert|转换||
|graph/TraDeepFirst|深度优先||
|graph/TraWidthFirst|宽度优先||
|graph/MSTKruskal|最小生成树，简单并查集||
|graph/MSTKruskal2|最小生成树，并查集||
|graph/MSTPrim|最小生成树||
|graph/SPDijkstra|最短路径||
|graph/SPDijkstra2|最短路径，改进自定义堆||
|graph/TopoSort|拓扑排序||
|str/KMP|字符串匹配|无法全部匹配选择部分匹配|
|str/Manacher|最长回文子串|利用历史最大回文信息|
|hash|hash||
|force|暴力递归||
|dp|暴力递归改动态规划||
|union_set/Island|小岛数量||
|union_set/UnionFind (并查集)|两结点是否在同一个集合，合并两个结点|
|prefix_tree/PrefixTree (前缀树)|前缀树，单词树|
|segment_tree/SegmentTree (线段树)||
|binary_idx_tree/BinaryIdxTree (树状数组，二叉索引树)|O(logn)区间和，前缀和|
|window_max/DynamicWindowMax (双端队列)|动态窗口内的最大值|双端队列|
|mono_stack/MonotonicStack (单调栈)|左右最近且大的值|出栈后，栈顶左近且大，待入栈右近且大|
|mono_stack/CumMin (单调栈)|左右最近且小的值|出栈后，栈顶左近且小，待入栈右近且小|
|ordered_table/BST|二叉搜索树|中序遍历递增|
|ordered_table/BST2|二叉搜索树|搜索树|
|ordered_table/AVL|平衡二叉搜索树|搜索树|
|ordered_table/SBTree|Size-Balance Tree|搜索树|
|ordered_table/RBTree|红黑树|搜索树|
|ordered_table/SkipTable|跳表|搜索树|
|b_tree/BTree|B树|索引树|
|b_tree/BPlusTree|B+树|索引树，范围搜索|

# zuo_god/company (左神大厂刷题)
[zuo_god/company (左神大厂刷题)](https://www.bilibili.com/video/BV1ng411n7aT)

|Intro|Class|More|
|---|---|---|
|绳子最多压中几个点|A000MaxPoint||
|分开GB最小相邻交换次数|A001MinStep||
|+-获得target的方法数|A002TargetSum||
|平分司机到AB区域的最高收入|A003MaxIncome||
|O(1)时间put,get,setAll的HashMap|A004SetAll||
|最长无重复字符的子串长度|A005MaxLongestSubStr||
|能力值差k的最大比赛场数|A006MaxGameK||
|每艘船最多坐两人的最少船数量|A007MinBoat||
|连续子数组最大累加和|A008MaxSubArray||
|孩子分糖果所需最少糖果数量|A009SumCandy||
|AB字符串（相对次序不变）能否交错组成字符串C|A011BoolStr12Sum||
|相等子树数量|A012SameTree||
|最短编辑距离(两字符串的相似度)|A013MinEditDistance||
|括号嵌套的四则运算表达式结果|A014Bracket||
|二维能围住的最大水量|A015MaxWater||
|无序括号对变有效的所有方案|A016RemoveInvalidParenthesis||
|递增子序列的最大长度|A017MaxIncSub||
|num是不是某个数的步骤和(stepNum)|A018IsStepSum||
|体力跳台阶的最少次数|A019MinStep||
|逻辑表达式加括号为true或false的方法数|A020WayBoolExpression||
|阈值abc抽牌获胜概率|A021PCard||
|洗衣机相连转移所需最少轮数（一样多衣服）|A022Washer||
|约瑟夫环公式法|A023Joseph||
|行列有序矩阵数组中第k小的数|A024MinK||
|s中t子串的数量|A025WaySubStr1Str2||
|不同非空子序列的个数|A026StrDiffSubStr||
|纵横联通两个岛的桥的最短距离|A028IslandBridge||
|LRU最近最少使用算法|A029LRUCache||
|k个升序数组的最窄包含区间|A030SamlleastRangeCovering||
|一维接雨水|A031D1TrappingRain||
|二维接雨水|A032D2TrappingRain||
|水王数(出现次数>N/2的数)|A033SuperWater||
|水王数(出现次数>N/k的数)|A034SuperWaterK||
|k个石堆相邻合并成一堆的最小代价|A035MinCostMergeStones||
|A包含B所有字符的最短子串|A036MinStr12Window||
|每种字符保留1个且字典序最小的结果串|A037RemoveDuplicateLettersLessLexi||
|根据文化衫问卷获取最少人数|A038MinPeople||
|是否有效的括号对|A039ValidParentheses||
|是否数独(0~9行列3x3小格子不重复)|A040ValidSudoKu||
|求解数独(0~9行列3x3小格子不重复)|A041SudokuSolver||
|x的n次方n非负数|A042QuickPow||
|x的n次方n可负数|A043QuickPow||
|sqrt(x)下取整|A044Sqrt||
|是0则所在行列都变0|A045SetMatrix0||
|子数组最大累乘积|A046CumMultiple||
|n!末尾有几个0|A047Factorial0Num||
|翻转二进制位|A048ReverseBit||
|质数(素数)数量|A049CountPrimes||
|出卷子的方法数(相邻难度限制)|A050ExamMWays||
|房屋偷盗|A051MaxHousePick||
|查找名人|A052FindCelebrity||
|相加等于A的最少完全平方数个数|A053PerfectNum||
|生命游戏细胞死亡存活|A054LifeGame||
|n是不是3的幂|A055Power3||
|最多包含k种字符的候选子串的最大长度|A056MaxSubStrKDiffChar||
|O(1)插入删除和获取随机元素|A057AddDelGetRandom||
|字符串在len字典中的位置|A058DictOrder||
|魔法石头过河(一半蓝一半红)的最小代价|A059MagicStone||
|01字符等比例切分方案|A060Ratio01Split||
|数组拿完所获最大分数(拿a都+a)|A061PickAddMax||
|字典序最大的长度为k的子序列(可不连续)|A062MaxKLenSequence||
|保留/删除数字的最大价值(相同数字递增，不同数字归1)|A063Max01Value||
|能够全消子序列的最大长度(01消除23消除)|A064MaxSubStrLenDisappear||
|数组变有序的最少交换次数|A065MinSwapTimesOrdered||
|使得相邻元素值之差绝对值最小的交换次数|A065MinSwapTimesOrdered||
|下一个全排列|A066NextPermutation||
|最佳聚会地点(二维数组中1代表人)|A067BestMeetingPoint||
|执行两种操作(变0或变相反数)使累加和降到0以下的最小成本|A068MinCostSumNoPositiveNum||
|含有k种不同整数的子数组的个数|A069SubArrContainsKKind||
|魔法积木最少合并堆数|A070SMagicBlock||
|供暖站能够覆盖所有地区的统一最小供暖半径|A071MinRadiusHeaterHouse||
|第k小的数字对差值绝对值|A072MinKthPairDiffAbs||
|障碍球进洞最短路径和走法|A073BallToHoleWay||
|长度>=2的等差子序列的数量|A074Bigger2ArithmeticSequence||
|扫地机器人|A075RoomRobotCleaner||
|吃香蕉的狒狒|A076EatingBananas||
|不相交的线的最大数量(AB的最长公共子序列)|A077MaxUncrossedLines||
|中间有k个未点亮灯泡的最小时刻|A078MinTimeKUnlitBulb||
|避免洪水泛滥(不下雨抽水)|A079AvoidFlood||
