package com.xcrj.basic.pass2.limit_resource;

/**
 * 大文件中 出现次数最多的数字 限制内存
 * 
 * hash
 * - 相同数值一定会到同一个文件中去
 * 
 * 步骤
 * 1. 创建m个文件
 * 2. 遍历大文件中的每个数，value>hash>%m=i文件，value放入第i个文件中
 * 3. 统计每个文件中出现次数最多的数，m个文件，m个出现次数最多的数
 * 4. m个数中次数最大的即整个大文件出现次数最多的数
 */
public class MostNumber {

}
