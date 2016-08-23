package com.zyh.seckill2.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/15.
 */
public class TestExcell {
    public static void main(String[] args){
        File file=new File("e:/test03.xls");
        try {
            HSSFWorkbook hssfWorkbook=new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet=hssfWorkbook.getSheetAt(0);
            for(int i=sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++){
                HSSFRow row=sheet.getRow(i);
                for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++){
                    HSSFCell cell=row.getCell(j);
                    System.out.print(cell.getStringCellValue()+"\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
