package com.xcrj.interview150.binary_search;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix
 * 搜索二维矩阵
 * 每行递增，下行first>上行last
 * [[1,3,5,7],
 * [10,11,16,20],
 * [23,30,34,60]]
 */
public class Solution01 {
    /**
     * 某列的某个数字，该数之上的数字，都比其小；
     * 某行的某个数字，该数右侧的数字，都比其大；
     * 所以，解题流程如下所示：
     * 以二维数组左下角为原点，建立直角坐标轴。
     * 若当前数字大于了查找数，查找往上移一位。
     * 若当前数字小于了查找数，查找往右移一位。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        //从左下角开始
        int row = matrix.length - 1, column = 0,num;
        while (row >= 0 && column < matrix[0].length) {
            num=matrix[row][column];
            if(num==target){
                return true;
            }else if(target<num){
                row--;
            }else{
                column++;
            }
        }
        return false;
    }
}
