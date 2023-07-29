package com.xcrj.excelme.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xcrj.excelme.model.Student;

@RestController
@RequestMapping("/excel")
public class SXSSFExcelController {
    /**
     * List<Student> stuList
     * cell style: Date, LocalDateTime, float, double
     * @param response
     * @return void
     * @throws IOException
     */
    @GetMapping("/sxssf/list")
    public void sxssfDownload1(HttpServletResponse response) throws IOException {
        // excel
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
        // sheet
        SXSSFSheet sheet = sxssfWorkbook.createSheet("学生信息表");

        /*header*/
        // sheet row
        String[] headers = { "ID", "年龄", "姓名", "学分", "零花钱", "农历生日", "公历生日", "性别" };
        SXSSFRow headerRow = sheet.createRow(0);

        // sheet row cell
        for (int i = 0; i < headers.length; i++) {
            SXSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
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
        for(int i=0;i<1;i++){
            stus.add(stu1);
        }

        // Attention! XSSFCellStyle
        // cell style
        // data style
        XSSFCellStyle xssfCellStyleDate =(XSSFCellStyle) sxssfWorkbook.createCellStyle();
        XSSFDataFormat xssfDataFormatDate =(XSSFDataFormat) sxssfWorkbook.createDataFormat();
        xssfCellStyleDate.setDataFormat(xssfDataFormatDate.getFormat("yyyy年m月d日"));

        // decimal style
        XSSFCellStyle xssfCellStyleDigit =(XSSFCellStyle) sxssfWorkbook.createCellStyle();
        xssfCellStyleDigit.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

        /*body*/
        // row cell
        int rowNum = 1;
        for (Student stu : stus) {
            SXSSFRow dataRow = sheet.createRow(rowNum);
            SXSSFCell cell0 = dataRow.createCell(0);
            cell0.setCellValue(stu.getId());
            SXSSFCell cell1 = dataRow.createCell(1);
            cell1.setCellValue(stu.getAge());
            SXSSFCell cell2 = dataRow.createCell(2);
            cell2.setCellValue(stu.getName());
            // float
            SXSSFCell cell3 = dataRow.createCell(3);
            cell3.setCellValue(stu.getScore());
            cell3.setCellStyle(xssfCellStyleDigit);
            // double
            SXSSFCell cell4 = dataRow.createCell(4);
            cell4.setCellValue(stu.getPinMoney());
            cell4.setCellStyle(xssfCellStyleDigit);
            // Date
            SXSSFCell cell5 = dataRow.createCell(5);
            cell5.setCellValue(stu.getChineseBirthDay());
            cell5.setCellStyle(xssfCellStyleDate);
            // LocalDateTime
            SXSSFCell cell6 = dataRow.createCell(6);
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
            sxssfWorkbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            // Dispose of temporary files backing this workbook on disk.
            sxssfWorkbook.dispose();
            sxssfWorkbook.close();
        }
    }
}
