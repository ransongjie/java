package com.xcrj.junit4me.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.xcrj.junit4me.mapper.MyStudentMapper;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * controller 单元测试, MockMvc
 * jsonpath
 * jackson
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    @Autowired
    private MyStudentMapper myStudentMapper;

    //jsonpath
    private static DocumentContext documentContext;

    //jackson
    private static ObjectMapper objectMapper;

    // 注意, 是 static 方法
    @BeforeClass
    public static void setUp() throws Exception {
        // json数据一次解析，然后多次使用，提升性能
        documentContext = JsonPath.parse(new File("src/test/java/com/xcrj/junit4me/controller/student_test.json"));
        // Jackson
        objectMapper = new ObjectMapper();
    }

    // 在每个测试方法之前运行
    @Before
    public void setUpFunc() throws Exception {
        // 构建mockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // 清空表并重置AUTO_INCREMENT
        myStudentMapper.truncateUtStudent();
    }

    // 测试完后 使用事务回滚数据库
    @Test
    @Transactional
    @Rollback()
    public void testAddOne() throws Exception {
        // JsonPath
        Map<String, Object> req = documentContext.read("$.addOneReq");

        // Jackson map to json string
        String jsonStr = objectMapper.writeValueAsString(req);

        // mockMvc
        mockMvc.perform(
                post("/student/one")
                        .content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback()
    public void testAddBatch() throws Exception {
        // JsonPath
        List<Map<String, Object>> req = documentContext.read("$.addBatchReq");

        // Jackson list to json string
        String jsonStr = objectMapper.writeValueAsString(req);

        // mockMvc
        mockMvc.perform(
                post("/student/batch")
                        .content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback()
    public void testDelById() throws Exception {
        // prepare
        testAddOne();

        // JsonPath
        Integer req = documentContext.read("$.delByIdReq");

        // mockMvc
        mockMvc.perform(
                delete("/student/one/{id}", req))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback()
    public void testDelBatch() throws Exception {
        // prepare
        testAddBatch();

        // JsonPath
        List<Integer> req = documentContext.read("$.delBatchReq");

        ///student/batch/1,2,3 必须传递数组
        Integer[] ids = req.toArray(new Integer[req.size()]);

        // mockMvc
        mockMvc.perform(
                delete("/student/batch/{ids}", ids))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback()
    public void testPutOne() throws Exception {
        // prepare
        testAddOne();

        // JsonPath
        Map<String, Object> req = documentContext.read("$.putOneReq");

        // Jackson map to json string
        String jsonStr = objectMapper.writeValueAsString(req);

        // mockMvc
        mockMvc.perform(
                put("/student/one")
                        .content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    @Rollback()
    public void testPutBatch() throws Exception {
        // prepare
        testAddBatch();

        // JsonPath
        List<Integer> req = documentContext.read("$.putBatchReq");

        // Jackson
        String jsonStr = objectMapper.writeValueAsString(req);

        // mockMvc
        mockMvc.perform(
                put("/student/batch")
                        .content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback()
    public void testGetById() throws Exception {
        // prepare
        testAddOne();

        // JsonPath
        Integer req = documentContext.read("$.getByIdReq");
        Map<String, Object> res = documentContext.read("$.getByIdRes");

        // mockMvc
        mockMvc.perform(
                get("/student/{id}", req))
                .andExpect(status().isOk())
                // JsonPath 选择特定字段
                .andExpect(jsonPath("$.data.['id','code','grade','name']", is(res)));
    }

    @Test
    @Transactional
    @Rollback()
    public void testGetList() throws Exception {
        // prepare
        testAddBatch();

        // JsonPath
        // 注意 表单参数 值必须为String 类型
        Map<String, String> req = documentContext.read("$.getListReq");
        List<Map<String, Object>> res = documentContext.read("$.getListRes");

        // mockMvc
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        req.forEach(params::add);
        mockMvc.perform(
                get("/student/list")
                        .params(params)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                // JsonPath .. 处理list中的每个记录
                .andExpect(jsonPath("$.data..['id','code','grade','name']", is(res)));
    }

    @Test
    @Transactional
    @Rollback()
    public void testGetPage() throws Exception {
        // prepare
        testAddBatch();

        // JsonPath
        // 注意 表单参数 值必须为String 类型
        Integer reqPageNum = documentContext.read("$.getPageReq.pageNum");
        Integer reqPageSize = documentContext.read("$.getPageReq.pageSize");
        List<Map<String, Object>> res = documentContext.read("$.getPageRes");

        // mockMvc
        mockMvc.perform(
                get("/student/page/{pageNum}/{pageSize}", reqPageNum, reqPageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data..['id','code','grade','name']", is(res)));
    }
}
