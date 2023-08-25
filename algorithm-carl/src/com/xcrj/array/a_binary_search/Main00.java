package com.xcrj.array.a_binary_search;

public class Main00 {
    public static void main(String[] args) {
        int[] as = {1, 2, 3, 4, 7, 9, 10};
        System.out.println(binarySearch1(as, 2));
        System.out.println(binarySearch2(as, 2));
    }

    /**
     * [left,right] 闭区间
     * while(left <= right), = left=right有意义
     * if(nums[middle] > target), right=middle-1, nums[middle]一定=target
     *
     * @param as
     * @param target
     * @return
     */
    private static int binarySearch1(int[] as, int target) {
        // 闭区间 [left,right]
        int left = 0, right = as.length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (as[middle] == target) {
                return middle;
            } else if (as[middle] > target) {
                right = middle - 1;// 闭区间，middle已经比较过
            } else {
                left = middle + 1;// 闭区间，middle已经比较过
            }
        }
        return -1;
    }

    /**
     * [left,right) 右开区间
     * while(left < right), left == right在区间[left, right)是没有意义的
     * if (nums[middle] > target), right = middle, nums[middle]不等于target，去左区间继续寻找，左区间是左闭右开，将right更新为middle, middle不会比较
     *
     * @param as
     * @param target
     * @return
     */
    private static int binarySearch2(int[] as, int target) {
        // 右开区间 [left,right)
        int left = 0, right = as.length;
        while (left < right) {
            int middle = (left + right) >> 1;
            if (as[middle] == target) {
                return middle;
            } else if (as[middle] > target) {
                right = middle;// 右开区间 right=middle不会比较
            } else {
                left = middle + 1;// 右开区间 middle已经比较过
            }
        }
        return -1;
    }
}
