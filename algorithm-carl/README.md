# 注意

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

## str2
```text
int[][] dp = new int[s.length() + 1][t.length() + 1];
-长度为i的s1的子数组 和 长度为j的s2的子数组
-以s1[i-1]结尾的子数组 和 以s2[j-1]结尾的子数组
```