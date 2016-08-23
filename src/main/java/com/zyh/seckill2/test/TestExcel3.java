package com.zyh.seckill2.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class TestExcel3 {
    public static void main(String[] args){
        File file=new File("E:\\imooc\\src\\main\\resources\\students.xml");
        System.out.println(file.getName());
        SAXBuilder saxBuilder=new SAXBuilder();
        try {
            HSSFWorkbook workbook=new HSSFWorkbook();
            //设置字体
            HSSFFont hssfFont=workbook.createFont();
            HSSFSheet sheet=workbook.createSheet("sheet first");
            Document document= saxBuilder.build(file);
            Element root=document.getRootElement();
            String templateName=root.getAttribute("name").getValue();
            //当前列和宽
            int rowNum=0;
            int cloNum=0;
            //列宽节点
            Element colgroup=root.getChild("colgroup");
            //设置列宽
            setColumnWidth(sheet,colgroup);
            //设置表头
            Element title=root.getChild("title");
            List<Element> trs=title.getChildren("tr");
            System.out.println(rowNum);
            for(int i=0;i<trs.size();i++){
                //设置单元格格式
                HSSFCellStyle cellStyle=workbook.createCellStyle();
                //居中
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                HSSFRow row=sheet.createRow(rowNum);
                rowNum++;
                List<Element> tds=trs.get(i).getChildren();
                for(int j=0;j<tds.size();j++){
                    HSSFFont font=workbook.createFont();
                    font.setFontName("仿宋_GB2312");
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    font.setFontHeightInPoints((short)12);
                    HSSFCell cell=row.createCell(j);
                    Element td=tds.get(i);
                    Attribute rowSpan=td.getAttribute("rowspan");
                    Attribute colspan=td.getAttribute("colspan");
                    Attribute value=td.getAttribute("value");
                    if(value!=null){
                        String val=value.getValue();
                        cell.setCellValue(val);
                        //合并单元格
                       sheet.addMergedRegion(new CellRangeAddress(rowSpan.getIntValue()-1,rowSpan.getIntValue()-1,0,colspan.getIntValue()-1));
                    }
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);
                }
            }
            System.out.println(rowNum);

            HSSFRow titleRow=sheet.createRow(rowNum);
            Element thead=root.getChild("thead");
            List<Element> trList=thead.getChildren();
            for(Element e:trList){
                rowNum++;
                List<Element> tdList=e.getChildren();
                for(int i=0;i<tdList.size();i++){
                    Attribute val=tdList.get(i).getAttribute("value");
                    titleRow.createCell(i).setCellValue(val.getValue());
                }
            }
            System.out.println(rowNum+"--");

            //tbody
            Element tbody=root.getChild("tbody");
            Element tr=tbody.getChild("tr");
            List<Element> tds=tr.getChildren("td");
            Attribute repeat=tr.getAttribute("repeat");
            int repeatNum=repeat.getIntValue();
            for(int i=0;i<repeatNum;i++){
                System.out.println("rowNum + i"+rowNum);
                HSSFRow row=sheet.createRow(rowNum);
                for(int j=0;j<tds.size();j++){
                    HSSFCell cell=row.createCell(j);
                    setCellStyle(cell,tds.get(i),workbook);

                }
                rowNum++;
            }
            //excel导入模板
            File tempFile=new File("e:/"+templateName+".xls");
            if(tempFile.exists()){
                tempFile.delete();
            }
            tempFile.createNewFile();
            FileOutputStream outputStream= FileUtils.openOutputStream(tempFile);
            workbook.write(outputStream);
            outputStream.close();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setColumnWidth(HSSFSheet sheet,Element colgroup){
        List<Element> cols=colgroup.getChildren("col");
        for(int i=0;i<cols.size();i++){
            Element col=cols.get(i);
            Attribute width=col.getAttribute("width");
            String unit=width.getValue().replaceAll("[0-9,\\.]","");
            String value=width.getValue().replaceAll(unit,"");
            int v=0;
            if(StringUtils.isBlank(unit)||"px".endsWith(unit)){
                v=Math.round(Float.parseFloat(value)*37F);
            }else if("em".endsWith(unit)){
                v=Math.round(Float.parseFloat(value)*267.5F);
            }
            sheet.setColumnWidth(i,v);
        }
    }
    public static void setCellStyle(HSSFCell cell,Element td,HSSFWorkbook workbook){
        Attribute typeAttribute=td.getAttribute("type");
        String type=typeAttribute.toString();
        HSSFCellStyle hssfCellStyle=workbook.createCellStyle();
        HSSFDataFormat dataFormat=workbook.createDataFormat();
        if("STRING".equalsIgnoreCase(type)){
            cell.setCellValue("");
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            hssfCellStyle.setDataFormat(dataFormat.getFormat("@"));
        }else if("NUMERIC".equalsIgnoreCase(type)){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            Attribute format=td.getAttribute("format");
            String formatValue=format.getValue();
            formatValue=StringUtils.isNotBlank(formatValue)?formatValue:"#,##0.00";
            hssfCellStyle.setDataFormat(dataFormat.getFormat(formatValue));
        }else if("ENUM".equalsIgnoreCase(type)){
            CellRangeAddressList regions=new CellRangeAddressList(cell.getRowIndex(),cell.getRowIndex(),cell.getColumnIndex(),cell.getColumnIndex());
            Attribute enumAttr=td.getAttribute("format");
            String enumVal=enumAttr.getValue();
            //获取下拉列表框的值
            DVConstraint dvConstraint=DVConstraint.createExplicitListConstraint (enumVal.split(","));
            //数据有效性对象
            HSSFDataValidation dataValidation=new HSSFDataValidation(regions,dvConstraint);
            workbook.getSheetAt(0).addValidationData(dataValidation);
        }else if ("DATE".equalsIgnoreCase(type)){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            hssfCellStyle.setDataFormat(dataFormat.getFormat("yyyy-m-d"));
        }
        cell.setCellStyle(hssfCellStyle);
    }
}
