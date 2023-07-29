package com.xcrj.excelme.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xcrj.excelme.model.Student;

@RestController
@RequestMapping("/excel")
public class XSSFExcelController {
    /**
     * List<Student> stuList
     * cell style: Date, LocalDateTime, float, double
     * @param response
     * @return void
     * @throws IOException
     */
    @GetMapping("/xssf/list")
    public void xssfDownload1(HttpServletResponse response) throws IOException {
        // excel
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        
        // sheet
        XSSFSheet sheet = xssfWorkbook.createSheet("学生信息表");

        /*header*/ 
        // sheet row
        String[] headers = { "ID", "年龄", "姓名", "学分", "零花钱", "农历生日", "公历生日", "性别" };
        XSSFRow headerRow = sheet.createRow(0);
        // sheet row cell
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // data
        List<Student> stus = new ArrayList<>();
        Student stu1 = new Student();
        stu1.setId(1L);
        stu1.setAge(18);
        stu1.setName("xcrj1");
        stu1.setScore(3.1F);
        stu1.setPinMoney(100.11);
        stu1.setChineseBirthDay(new Date(854021393000L));
        stu1.setBirthDay(LocalDateTime.of(1997, 1, 23, 21, 30));
        stu1.setGender(true);
//        for(int i=0;i<200*10000;i++){
//        for(int i=0;i< 1048576;i++){
        for(int i=0;i<1;i++){
            stus.add(stu1);
        }

        // cell style
        // date
        XSSFCellStyle xssfCellStyleDate = xssfWorkbook.createCellStyle();
        XSSFDataFormat xssfDataFormatDate = xssfWorkbook.createDataFormat();
        xssfCellStyleDate.setDataFormat(xssfDataFormatDate.getFormat("yyyy年m月d日"));

        // decimal
        XSSFCellStyle xssfCellStyleDigit = xssfWorkbook.createCellStyle();
        xssfCellStyleDigit.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

        /*body*/
        // row cell
        int rowNum = 1;
        for (Student stu : stus) {
            XSSFRow dataRow = sheet.createRow(rowNum);
            XSSFCell cell0 = dataRow.createCell(0);
            cell0.setCellValue(stu.getId());
            XSSFCell cell1 = dataRow.createCell(1);
            cell1.setCellValue(stu.getAge());
            XSSFCell cell2 = dataRow.createCell(2);
            cell2.setCellValue(stu.getName());
            // float
            XSSFCell cell3 = dataRow.createCell(3);
            cell3.setCellValue(stu.getScore());
            cell3.setCellStyle(xssfCellStyleDigit);
            // double
            XSSFCell cell4 = dataRow.createCell(4);
            cell4.setCellValue(stu.getPinMoney());
            cell4.setCellStyle(xssfCellStyleDigit);
            // Date
            XSSFCell cell5 = dataRow.createCell(5);
            cell5.setCellValue(stu.getChineseBirthDay());
            cell5.setCellStyle(xssfCellStyleDate);
            // LocalDateTime
            XSSFCell cell6 = dataRow.createCell(6);
            cell6.setCellValue(stu.getBirthDay());
            cell6.setCellStyle(xssfCellStyleDate);

            dataRow.createCell(7).setCellValue(stu.getGender());
            //
            rowNum++;
        }

        // file name
        String fileName = "students" + ".xlsx";

        // http response header
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        // flush write
        try {
            response.flushBuffer();
            xssfWorkbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            xssfWorkbook.close();
        }
    }
}
