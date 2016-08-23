package com.zyh.seckill2.test;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/15.
 */
public class TestExcel {
    public static void main(String args[]){
        String[] title={"id","name","age"};
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet();
        HSSFRow row=sheet.createRow(0);
        for(int i=0;i<title.length;i++){
            row.createCell(i).setCellValue(title[i]);
        }
        for(int i=1;i<=10;i++){
            HSSFRow row1=sheet.createRow(i);
            row1.createCell(0).setCellValue(i+"");
            row1.createCell(1).setCellValue(i+"user");
            row1.createCell(2).setCellValue(i*10+"");
        }
        File file=new File("e:/test03.xls");
        FileOutputStream outputStream=null;

        try {
            file.createNewFile();
            outputStream= FileUtils.openOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
