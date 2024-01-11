|Pkg|Intro|
|---|---|
|dp|动态规划|
|greed|贪心算法|

- 子序列，可以不连续
- 子数组=连续子序列
# dp
## max_array
```text
dp[i], 以nums[i]结尾
dp[i], 长度为i，以nums[i-1]结尾
结果 return dp[n-1] 
结果 return max
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
# stock_sell
```text
状态机转换
Main1, 1支股票只买卖1次: dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
Main2, 1支股票买卖多次: dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
Main3, 1支股票买卖至多2次: 
Main4, 1支股票买卖至多k次: 
Main5, 1支股票买卖多次，卖出之后有冷冻期: dp[i][2] 卖出股票 ！
Main6, 1支股票买卖多次，手续费: dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
```
## str2
```text
int[][] dp = new int[s.length() + 1][t.length() + 1];
-长度为i的s1的子数组 和 长度为j的s2的子数组
-以s1[i-1]结尾的子数组 和 以s2[j-1]结尾的子数组
```
# greed
跳跃游戏：能否到达最后1个位置
跳跃游戏II：使用最少的跳跃次数到达最后1个位置

区间重叠：
- 用最少数量的箭引爆气球
- 无重叠区间
- 合并重叠区间