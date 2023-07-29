package com.xcrj.excelme.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xcrj.excelme.model.Student;

/**
 * excel>sheet>row>cell, cell style
 */
@RestController
@RequestMapping("/excel")
public class HSSFExcelController {

    /**
     * List<Student> stuList
     * cell style: Date, LocalDateTime, float, double
     * @param response
     * @return void
     * @throws IOException
     */
    @GetMapping("/hssf/list")
    public void hssfDownload1(HttpServletResponse response) throws IOException {
        // excel
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        // sheet
        HSSFSheet sheet = hssfWorkbook.createSheet("学生信息表");

        /* header */
        // sheet row
        String[] headers = { "ID", "年龄", "姓名", "学分", "零花钱", "农历生日", "公历生日", "性别" };
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            // sheet row cell
            HSSFCell cell = headerRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
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
//        for (int i = 0; i < 1000*1000; i++) {
//        for (int i = 0; i < 65535; i++) {
        for (int i = 0; i < 1; i++) {
            stus.add(stu1);
        }

        // cell style
        // date
        HSSFCellStyle hssfCellStyleDate = hssfWorkbook.createCellStyle();
        HSSFDataFormat hssfDataFormatDate = hssfWorkbook.createDataFormat();
        hssfCellStyleDate.setDataFormat(hssfDataFormatDate.getFormat("yyyy年m月d日"));
        // decimal
        HSSFCellStyle hssfCellStyleDecimal = hssfWorkbook.createCellStyle();
        hssfCellStyleDecimal.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

        /* body */
        // row cell
        int rowNum = 1;
        for (Student stu : stus) {
            HSSFRow dataRow = sheet.createRow(rowNum);
            HSSFCell cell0 = dataRow.createCell(0);
            cell0.setCellValue(stu.getId());
            HSSFCell cell1 = dataRow.createCell(1);
            cell1.setCellValue(stu.getAge());
            HSSFCell cell2 = dataRow.createCell(2);
            cell2.setCellValue(stu.getName());
            // float, cell style
            HSSFCell cell3 = dataRow.createCell(3);
            cell3.setCellValue(stu.getScore());
            cell3.setCellStyle(hssfCellStyleDecimal);
            // double, cell style
            HSSFCell cell4 = dataRow.createCell(4);
            cell4.setCellValue(stu.getPinMoney());
            cell4.setCellStyle(hssfCellStyleDecimal);
            // Date, cell style
            HSSFCell cell5 = dataRow.createCell(5);
            cell5.setCellValue(stu.getChineseBirthDay());
            cell5.setCellStyle(hssfCellStyleDate);
            // LocalDateTime, cell style
            HSSFCell cell6 = dataRow.createCell(6);
            cell6.setCellValue(stu.getBirthDay());
            cell6.setCellStyle(hssfCellStyleDate);

            dataRow.createCell(7).setCellValue(stu.getGender());

            rowNum++;
        }

        // file name
        String fileName = "students" + ".xls";

        // http response header
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        // flush write
        try {
            response.flushBuffer();
            hssfWorkbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            hssfWorkbook.close();
        }
    }

    /**
     * List<Map<String, Object>> mapList
     * cell style: Date, LocalDateTime, float, double
     * @param response
     * @return void
     * @throws IOException
     */
    @GetMapping("/hssf/map")
    public void hssfDownload2(HttpServletResponse response) throws IOException {
        // excel
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        // sheet
        HSSFSheet sheet = hssfWorkbook.createSheet("学生信息表");

        /* header */
        // sheet row
        String[] headers = { "ID", "年龄", "姓名", "学分", "零花钱", "期望薪资", "农历生日", "公历生日", "性别", "状态" };
        HSSFRow headerRow = sheet.createRow(0);
        // sheet row cell
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = headerRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // data
        List<Map<String, Object>> stus = new ArrayList<>();
        Map<String, Object> stuMap = new HashMap<>();
        stuMap.put("ID", 1L);
        stuMap.put("年龄", 18);
        stuMap.put("姓名", new String("xcrj1"));
        stuMap.put("学分", 3.1F);
        stuMap.put("零花钱", 100.11);
        stuMap.put("期望薪资", new BigDecimal(20.5));
        stuMap.put("农历生日", new Date(854021393000L));
        stuMap.put("公历生日", LocalDateTime.of(1997, 1, 23, 21, 30));
        stuMap.put("性别", true);
        stuMap.put("状态", 'T');
        stus.add(stuMap);

        /* body */
        // row cell
        int rowNum = 1;
        for (Map<String, Object> stu : stus) {
            HSSFRow dataRow = sheet.createRow(rowNum);

            int columnNum = 0;
            HSSFCell cell0 = dataRow.createCell(0);
            handleCellData(hssfWorkbook, cell0, stu.get(headers[columnNum++]));
            HSSFCell cell1 = dataRow.createCell(1);
            handleCellData(hssfWorkbook, cell1, stu.get(headers[columnNum++]));
            HSSFCell cell2 = dataRow.createCell(2);
            handleCellData(hssfWorkbook, cell2, stu.get(headers[columnNum++]));
            HSSFCell cell3 = dataRow.createCell(3);
            handleCellData(hssfWorkbook, cell3, stu.get(headers[columnNum++]));
            HSSFCell cell4 = dataRow.createCell(4);
            handleCellData(hssfWorkbook, cell4, stu.get(headers[columnNum++]));
            HSSFCell cell5 = dataRow.createCell(5);
            handleCellData(hssfWorkbook, cell5, stu.get(headers[columnNum++]));
            HSSFCell cell6 = dataRow.createCell(6);
            handleCellData(hssfWorkbook, cell6, stu.get(headers[columnNum++]));
            HSSFCell cell7 = dataRow.createCell(7);
            handleCellData(hssfWorkbook, cell7, stu.get(headers[columnNum++]));
            HSSFCell cell8 = dataRow.createCell(8);
            handleCellData(hssfWorkbook, cell8, stu.get(headers[columnNum++]));
            HSSFCell cell9 = dataRow.createCell(9);
            handleCellData(hssfWorkbook, cell9, stu.get(headers[columnNum++]));
            //
            rowNum++;
        }

        // file name
        String fileName = "students" + ".xls";

        // http response header
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        // flush write
        try {
            response.flushBuffer();
            hssfWorkbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            hssfWorkbook.close();
        }
    }

    /**
     * Handle cell data
     * Determine data type using instanceof
     * @param hssfWorkbook
     * @param cell
     * @param cellData
     * @return void
     */
    private static void handleCellData(HSSFWorkbook hssfWorkbook, HSSFCell cell, Object cellData) {
        // cell style
        // date style
        HSSFCellStyle hssfCellStyleDate = hssfWorkbook.createCellStyle();
        HSSFDataFormat hssfDataFormatDate = hssfWorkbook.createDataFormat();
        hssfCellStyleDate.setDataFormat(hssfDataFormatDate.getFormat("yyyy年m月d日"));

        // decimal style
        HSSFCellStyle hssfCellStyleDigit = hssfWorkbook.createCellStyle();
        hssfCellStyleDigit.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

        // integer
        if (cellData instanceof Boolean) {
            cell.setCellValue((Boolean) cellData);
            return;
        }
        if (cellData instanceof Byte) {
            Byte data = (Byte) cellData;
            Integer intData = (int) data;
            cell.setCellValue(intData);
            return;
        }
        if (cellData instanceof Short) {
            Short data = (Short) cellData;
            Integer intData = (int) data;
            cell.setCellValue(intData);
            return;
        }
        // Attention! Integer can also be set to retain several decimal places
        if (cellData instanceof Integer) {
            cell.setCellValue((Integer) cellData);
            cell.setCellStyle(hssfCellStyleDigit);
            return;
        }
        if (cellData instanceof Long) {
            cell.setCellValue((Long) cellData);
            return;
        }
        // char
        if (cellData instanceof Character) {
            Character data = (Character) cellData;
            String strData = String.valueOf(data);
            cell.setCellValue(strData);
            return;
        }
        // decimal
        if (cellData instanceof Float) {
            Float data = (Float) cellData;
            cell.setCellValue(data);
            cell.setCellStyle(hssfCellStyleDigit);
            return;
        }
        if (cellData instanceof Double) {
            Double data = (Double) cellData;
            cell.setCellValue(data);
            cell.setCellStyle(hssfCellStyleDigit);
            return;
        }
        if (cellData instanceof BigDecimal) {
            BigDecimal data = (BigDecimal) cellData;
            Double doubleData = data.doubleValue();
            cell.setCellValue(doubleData);
            cell.setCellStyle(hssfCellStyleDigit);
            return;
        }
        // date
        if (cellData instanceof Date) {
            Date data = (Date) cellData;
            cell.setCellValue(data);
            cell.setCellStyle(hssfCellStyleDate);
            return;
        }
        if (cellData instanceof LocalDateTime) {
            LocalDateTime data = (LocalDateTime) cellData;
            cell.setCellValue(data);
            cell.setCellStyle(hssfCellStyleDate);
            return;
        }
        // string
        if (cellData instanceof String) {
            String data = (String) cellData;
            cell.setCellValue(data);
            return;
        }
    }
}
