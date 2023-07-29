package com.xcrj.junit4me.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xcrj.junit4me.generated.dao.entity.UtStudent;

@Mapper
public interface MyStudentMapper extends BaseMapper<UtStudent> {

    // 清空 ut_student 表
    // 重置 AUTO_INCREMENT
    @Update("truncate table ut_student")
    void truncateUtStudent();
}
