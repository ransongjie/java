package com.xcrj.basic.pass2.limit_resource;

/**
 * 找出所有重复的URL. 100亿个URL，每个URL占64B
 * 
 * 方法一：hash分流
 * - m个小文件
 * - URL>hash>%m=i个小文件
 * - 分别统计每个小文件中的重复URL
 * 
 * 方法二：布隆过滤器
 * - 新增时也查询，若已经存在则这条URL是重复的URL，把这个重复URL记录下来
 * - 存在失误率，因为利用了布隆过滤器的 存在判断 布隆过滤器是“可能存在，一定不存在”
 * 
 * 布隆过滤器：
 * - 可能存在(所有hash位都1)，一定不存在(一个hash位为0)
 * - 有新增，有查询，无删除操作
 */
public class DuplicateURL {
    
}
