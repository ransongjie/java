package com.xcrj.basic.pass2.limit_resource;

/**
 * 找出所有重复的URL. 100亿个URL，每个URL占64B
 * 
 * 
 * 方法一：hash分流，利用hash的均匀性和唯一性，每个文件的大小差不多，相同URL一定在同一个文件中
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
 * 
 * 方法三：
 * - 100亿个URL》hash函数》100亿个<Int 位置,Int 数量>数组 12.5GB*16*8
 * - 遍历结果数组》数量>1》根据位置定位URL
 * 
 * 方法四：
 * - 100亿个bit的bloom过滤器，12.5GB
 * - 遍历所有URL》getBit，为1则是重复URL》否则setBit
 * 
 * 方法五：
 * - 减少bit数，增多hash函数
 * - 2GB的bloom过滤器，hash函数为8个，降低失误率
 * - 遍历所有URL》getBit，都为1则是重复URL》否则setBit
 */
public class DuplicateURL {
    
}
