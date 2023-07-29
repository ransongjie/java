package com.xcrj.junit4me.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import com.xcrj.junit4me.generated.dao.entity.UtStudent;
import com.xcrj.junit4me.generated.service.IUtStudentService;

import com.xcrj.junit4me.unified.ErrorCode;
import com.xcrj.junit4me.unified.PageReq;
import com.xcrj.junit4me.unified.PageRes;
import com.xcrj.junit4me.unified.ResultMe;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private IUtStudentService iUtStudentService;

    // 增加一个
    @PostMapping("/one")
    public ResultMe<Object> addOne(@RequestBody UtStudent reqVO) {
        return iUtStudentService.save(reqVO) ? ResultMe.<Object>success()
                : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    // 批量增加
    @PostMapping("/batch")
    public ResultMe<Object> addBatch(@RequestBody List<UtStudent> reqVOList) {
        return iUtStudentService.saveBatch(reqVOList) ? ResultMe.<Object>success()
                : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    // 根据ID删除
    @DeleteMapping("/one/{id}")
    public ResultMe<Object> delById(@PathVariable("id") Long id) {
        return iUtStudentService.removeById(id) ? ResultMe.<Object>success()
                : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    // 根据ID列表批量删除
    @DeleteMapping("/batch/{ids}")
    public ResultMe<Object> delBatch(@PathVariable("ids") List<Long> ids) {
        return iUtStudentService.removeByIds(ids) ? ResultMe.<Object>success()
                : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    // 根据ID修改
    @PutMapping("/one")
    public ResultMe<Object> putOne(@RequestBody UtStudent reqVO) {
        return iUtStudentService.updateById(reqVO) ? ResultMe.<Object>success()
                : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    // 根据ID列表批量修改
    @PutMapping("/batch")
    public ResultMe<Object> putBatch(@RequestBody List<UtStudent> reqVOList) {
        return iUtStudentService.updateBatchById(reqVOList) ? ResultMe.<Object>success()
                : ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001);
    }

    // 根据ID查询
    @GetMapping("/{id}")
    public ResultMe getById(@PathVariable("id") Long id) {
        UtStudent utStudent = iUtStudentService.getById(id);
        return utStudent == null ? ResultMe.<Object>error(ErrorCode.SYSTEM_ERROR_B0001)
                : ResultMe.<UtStudent>success(utStudent);
    }

    // 分页查询
    // pageHelper分页
    @GetMapping("/page/{pageNum}/{pageSize}")
    public PageRes<UtStudent> getPage(@PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize) {
        Page<UtStudent> page = PageHelper.<UtStudent>startPage(pageNum, pageSize);

        List<UtStudent> list = iUtStudentService.list();

        PageInfo<UtStudent> pageInfo = new PageInfo<>(list);
        return PageRes.<UtStudent>success(page.getTotal(), list);
    }

    // mybatis-plus分页
    @PostMapping("/page2")
    public PageRes<UtStudent> page2(@RequestBody PageReq pageReq) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UtStudent> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                pageReq.getPageNum(), pageReq.getPageSize());

        QueryWrapper<UtStudent> queryWrapper = Wrappers.<UtStudent>query().orderBy(pageReq.getOrdered(),
                pageReq.getAsc(), pageReq.getOrderColumn());
        IPage<UtStudent> iPage = iUtStudentService.page(page, queryWrapper);

        return PageRes.<UtStudent>success(iPage.getTotal(), iPage.getRecords());
    }

    // 查询所有
    @GetMapping("/all")
    public ResultMe<List<UtStudent>> getAll() {
        List<UtStudent> list = iUtStudentService.lambdaQuery().list();
        return ResultMe.<List<UtStudent>>success(list);
    }

    // 根据条件查询
    @GetMapping("/list")
    public ResultMe<List<UtStudent>> getList(UtStudent reqVO) {
        List<UtStudent> list = iUtStudentService.lambdaQuery()
                .eq(reqVO.getCode() != null, UtStudent::getCode, reqVO.getCode())
                .eq(reqVO.getGrade() != null, UtStudent::getGrade, reqVO.getGrade())
                .eq(reqVO.getName() != null, UtStudent::getName, reqVO.getName())
                .list();

        return ResultMe.<List<UtStudent>>success(list);
    }
}
