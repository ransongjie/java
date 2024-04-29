## backtrack 回溯法

```text
解空间树，分支（选择/不选择）
控制了开始和结束条件的两层for循环
纵轴-递归，横轴-for
```

## knapsack_01

```text
01背包问题：每种物品有且只有一个&&每个物品只需要考虑选与不选两种情况
遍历顺序：先物品再背包 或 先背包再物品 都可以
第二层循环：倒序，因为同1个物品只能添加1次
求最大价值/方法状态转移：dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
求最大价值/方法结果：return dp[bagSize];
求总方法数状态转移：dp[j] += dp[j - weight[i]];
求总方法数结果：return dp[bagSize];

int bagSize  //背包大小
int[] weight //物品重量数组
int[] value  //物品价值数组
int n = weight.length  //物品数量
int[] dp = new int[bagSize + 1];  //dp数组
```

## knapsack_complete

```text
complete背包问题：每种物品都有无限件
遍历顺序：先物品再背包（组合问题）；先背包再物品（排列问题）；可以统一将称重放到外层循环
第二层循环：正序，因为同1个物品和添加无限次
求最大价值/方法状态转移：dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
求最大价值/方法结果：int max = 0;for (int i = 0; i < bagSize + 1; i++) {max = Math.max(max, dp[i]);} return max;
求总方法数状态转移：dp[j] += dp[j - weight[i]];
求总方法数方法结果：return dp[bagSize];
```

## knapsack_multiple

```text
multiple背包问题：每个物品都有有限件
转换为01背包去做
```