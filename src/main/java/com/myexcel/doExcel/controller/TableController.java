package com.myexcel.doExcel.controller;

import com.myexcel.doExcel.po.ExcelData;
import com.myexcel.doExcel.service.TableService;
import com.myexcel.doExcel.util.ExportExcelUtils;
import com.myexcel.doExcel.util.HashDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    TableService tableService;

    /**
     * 根据编码获得实体
     */
    @GetMapping("/{dbName}")
    //@ApiMethodFilter(name = "查询单个实例", code = "get", path = "/{*}", method = "GET", permission = "STRATEGY_EXCEL_GET")
    public String getAllTab(@PathVariable String dbName, HttpServletResponse response) {
        HashDto in = HashDto.init("table_schema",dbName);
        List<HashDto> list = tableService.getTabList(in);

        /***************************************************************************************************/
        ExcelData data = new ExcelData();
        data.setName("表清单");
        List<String> titles = new ArrayList();
        titles.add("序号");
        titles.add("所属数据库");
        titles.add("表名");
        titles.add("注释");
        titles.add("转入表");
        titles.add("备注");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        int i = 1;
        for(HashDto hashDto:list){
            List<Object> row = new ArrayList();
            row.add(i);
            row.add(hashDto.get("TABLE_SCHEMA"));
            row.add(hashDto.get("TABLE_NAME"));
            row.add(hashDto.get("TABLE_COMMENT"));
            row.add("");
            row.add("");
            rows.add(row);
            data.setRows(rows);
            i++;
        }
        // 表清单
        List<ExcelData> dataList = new ArrayList();
        dataList.add(data);


        // 列清单
        for(HashDto hashDto:list){
            String tableNameTmp = hashDto.get("TABLE_NAME").toString();
            String tabComment = hashDto.get("TABLE_COMMENT").toString();
            in.put("table_name",tableNameTmp);
            List<HashDto> columnList = tableService.getColumn(in);
            ExcelData colData = new ExcelData();
            colData.setName(tableNameTmp);
            List<String> colTitle = new ArrayList();
            // String[] headersColumns = { "序号", "表名","列名","数据类型", "注释"};
            colTitle.add("序号");
            colTitle.add("表名");
            colTitle.add("列名");
            colTitle.add("数据类型");
            colTitle.add("注释");
            colTitle.add("对应转换列");
            colTitle.add("备注");
            colData.setTitles(colTitle);
            // 列数据
            i = 1;
            List<List<Object>> colRows = new ArrayList();
            for(HashDto dto:columnList){
                List<Object> row = new ArrayList();
                row.add(i);
                String s = "";
                if(tabComment!=null && tabComment.length()>0){
                    row.add(tableNameTmp+"("+tabComment+")");
                }else{
                    row.add(tableNameTmp);
                }
                row.add(dto.get("COLUMN_NAME"));
                row.add(dto.get("COLUMN_TYPE"));
                row.add(dto.get("COLUMN_COMMENT"));
                row.add("");
                row.add("");
                colRows.add(row);
                colData.setRows(colRows);
                i++;
            }
            dataList.add(colData);
        }

        //生成本地
        /*File f = new File("c:/test.xlsx");
        FileOutputStream out = new FileOutputStream(f);
        ExportExcelUtils.exportExcel(data, out);
        out.close();*/
        try{
            String fileName = dbName+".all.xlsx";
            System.out.println(fileName);
            ExportExcelUtils.exportExcel(response,fileName,dataList);
        }catch (Exception e){
            e.printStackTrace();
        }
        /***************************************************************************************************/
        return dbName;
    }


    /**
     * 根据编码获得实体
     */
    @GetMapping("/{dbName}/{tabName}")
    //@ApiMethodFilter(name = "查询单个实例", code = "get", path = "/{*}", method = "GET", permission = "STRATEGY_EXCEL_GET")
    public String get(@PathVariable String dbName, @PathVariable String tabName, HttpServletResponse response) {
        HashDto in = HashDto.init("table_schema",dbName);
        if(tabName != null && !tabName.equals("")){
            in.put("table_name",tabName);
        }
        List<HashDto> list = tableService.getTabList(in);

        /***************************************************************************************************/
        ExcelData data = new ExcelData();
        data.setName("表清单");
        List<String> titles = new ArrayList();
        titles.add("序号");
        titles.add("所属数据库");
        titles.add("表名");
        titles.add("注释");
        titles.add("转入表");
        titles.add("备注");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        int i = 1;
        for(HashDto hashDto:list){
            List<Object> row = new ArrayList();
            row.add(i);
            row.add(hashDto.get("TABLE_SCHEMA"));
            row.add(hashDto.get("TABLE_NAME"));
            row.add(hashDto.get("TABLE_COMMENT"));
            row.add("");
            row.add("");
            rows.add(row);
            data.setRows(rows);
            i++;
        }
        // 表清单
        List<ExcelData> dataList = new ArrayList();
        dataList.add(data);


        // 列清单
        for(HashDto hashDto:list){
            String tableNameTmp = hashDto.get("TABLE_NAME").toString();
            String tabComment = hashDto.get("TABLE_COMMENT").toString();
            in.put("table_name",tableNameTmp);
            List<HashDto> columnList = tableService.getColumn(in);
            ExcelData colData = new ExcelData();
            colData.setName(tableNameTmp);
            List<String> colTitle = new ArrayList();
            // String[] headersColumns = { "序号", "表名","列名","数据类型", "注释"};
            colTitle.add("序号");
            colTitle.add("表名");
            colTitle.add("列名");
            colTitle.add("数据类型");
            colTitle.add("注释");
            colTitle.add("对应转换列");
            colTitle.add("备注");
            colData.setTitles(colTitle);
            // 列数据
            i = 1;
            List<List<Object>> colRows = new ArrayList();
            for(HashDto dto:columnList){
                List<Object> row = new ArrayList();
                row.add(i);
                String s = "";
                if(tabComment!=null && tabComment.length()>0){
                    row.add(tableNameTmp+"("+tabComment+")");
                }else{
                    row.add(tableNameTmp);
                }
                row.add(dto.get("COLUMN_NAME"));
                row.add(dto.get("COLUMN_TYPE"));
                row.add(dto.get("COLUMN_COMMENT"));
                row.add("");
                row.add("");
                colRows.add(row);
                colData.setRows(colRows);
                i++;
            }
            dataList.add(colData);
        }

        //生成本地
        /*File f = new File("c:/test.xlsx");
        FileOutputStream out = new FileOutputStream(f);
        ExportExcelUtils.exportExcel(data, out);
        out.close();*/
        try{
            String fileName;
            if(tabName!=null){
                fileName = dbName+"."+tabName+".xlsx";
            }else{
                fileName = dbName+".all.xlsx";
            }
            System.out.println(fileName);
            ExportExcelUtils.exportExcel(response,fileName,dataList);
        }catch (Exception e){
            e.printStackTrace();
        }
        /***************************************************************************************************/
/***

 HSSFWorkbook workbook = new HSSFWorkbook();
 HSSFSheet sheet = workbook.createSheet(dbName+"表清单");
 String fileName = dbName  + ".xls";//设置要导出的文件的名字
 //新增数据行，并且设置单元格数据
 int rowNum = 1;
 String[] headers = { "序号","所属数据库", "表名", "注释"};

 //headers表示excel表中第一行的表头
 HSSFRow row = sheet.createRow(0);
 //在excel表中添加表头

 for(int i=0;i<headers.length;i++){
 HSSFCell cell = row.createCell(i);
 HSSFRichTextString text = new HSSFRichTextString(headers[i]);
 cell.setCellValue(text);
 }

 //在表中存放查询到的数据放入对应的列
 for (HashDto tab : list) {
 HSSFRow row1 = sheet.createRow(rowNum);
 row1.createCell(0).setCellValue(rowNum);
 row1.createCell(1).setCellValue(tab.get("TABLE_SCHEMA").toString());
 row1.createCell(2).setCellValue(tab.get("TABLE_NAME").toString());
 row1.createCell(3).setCellValue(tab.get("TABLE_COMMENT").toString());
 rowNum++;
 }

 String[] headersColumns = { "序号", "表名","列名","数据类型", "注释"};
 for(HashDto tab : list){
 String tableName = tab.get("TABLE_NAME").toString();
 HashDto dto = HashDto.init("table_schema",dbName);
 dto.put("table_name",tableName);
 List<HashDto> cols = tableService.getColumn(dto);

 //            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
 String tableComment = tab.get("TABLE_COMMENT").toString();
 //            tableComment = tableComment.replaceAll("/","、");
 //            tableComment = tableName+"("+tableComment+")";
 HSSFSheet hssfSheet = workbook.createSheet(tableName);
 HSSFRow row0 = hssfSheet.createRow(0);

 CellRangeAddress cellAddresses = new CellRangeAddress(0,0,0,4);
 hssfSheet.addMergedRegion(cellAddresses);
 row0.createCell(0).setCellValue(tableName+(tableComment!=null && tableComment.length()>0?"("+tableComment+")":""));
 HSSFRow hssfRow = hssfSheet.createRow(1);
 for(int i=0;i<headersColumns.length;i++){
 HSSFCell cell = hssfRow.createCell(i);
 HSSFRichTextString text = new HSSFRichTextString(headersColumns[i]);
 cell.setCellValue(text);
 }
 //在表中存放查询到的数据放入对应的列
 rowNum = 1;
 for (HashDto hashDto : cols) {
 HSSFRow hssfRow1 = hssfSheet.createRow(rowNum+1);
 hssfRow1.createCell(0).setCellValue(rowNum);
 hssfRow1.createCell(1).setCellValue(tableName);
 hssfRow1.createCell(2).setCellValue(hashDto.get("COLUMN_NAME").toString());
 hssfRow1.createCell(3).setCellValue(hashDto.get("COLUMN_TYPE").toString());
 hssfRow1.createCell(4).setCellValue(hashDto.get("COLUMN_COMMENT").toString());
 rowNum++;
 }
 }


 response.setContentType("application/octet-stream");
 response.setHeader("Content-disposition", "attachment;filename=" + fileName);
 try {
 response.flushBuffer();
 workbook.write(response.getOutputStream());
 }catch (Exception e){
 e.printStackTrace();
 }
 **/
        return dbName;
    }
}
