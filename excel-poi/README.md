# Summary
Use SXSSFWorkbook first
The data row <= 65535, and any method can be used

# HSSFWorkbook
.xls
All data is stored in memory
There are restrictions on the number of rows and columns in this way
row <= 65535

|api|example|
|---|---|
|/excel/hssf/list|`List<Student> stuList`|
|/excel/hssf/map|`List<Map<String, Object>> mapList`|
# XSSFWorkbook
.xlsx
All data is stored in memory
There are restrictions on the number of rows and columns in this way
row <= 1,048,576

|api|example|
|---|---|
|/excel/xssf/list|`List<Student> stuList`|
# SXSSFWorkbook
.xlsx
The latest row is in memory, and the other rows are in disk
row <= 1,048,576

|api|example|
|---|---|
|/excel/sxssf/list|`List<Student> stuList`|
# Reference
[cell style](https://blog.csdn.net/iteye_9300/article/details/82002707)