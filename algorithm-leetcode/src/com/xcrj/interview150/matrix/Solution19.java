package com.xcrj.interview150.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/spiral-matrix/solutions/7155/cxiang-xi-ti-jie-by-youlookdeliciousc-3
 * 螺旋矩阵
 */
public class Solution19 {

    /**
     * 控制边界，边界相交则退出
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int up = 0, bt = matrix.length - 1, lf = 0, rt = matrix[0].length - 1;
        List<Integer> res = new ArrayList<>();

        while (true) {
            //从左往右
            for (int i = lf; i <= rt; i++) {
                res.add(matrix[up][i]);
            }
            if (++up > bt) break;//第up行已经走过
            //从上往下
            for (int i = up; i <= bt; i++) {
                res.add(matrix[i][rt]);
            }
            if (--rt < lf) break;//第rt列已经走过
            //从右往左
            for (int i = rt; i >= lf; i--) {
                res.add(matrix[bt][i]);
            }
            if (--bt < up) break;//第bt行已经走过
            //从下往上
            for (int i = bt; i >= up; i--) {
                res.add(matrix[i][lf]);
            }
            if (++lf > rt) break;//第lf列已经走过
        }

        return res;
    }
}
