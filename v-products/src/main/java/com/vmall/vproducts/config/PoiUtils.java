/*
package com.vmall.vproducts.config;

import com.vmall.pojo.VProduct;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PoiUtils {
    public static ResponseEntity<byte[]> exportEmp2Excel(List<VProduct> vps){
        HttpHeaders headers=null;
        ByteArrayOutputStream baos=null;
        try{
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            dsi.setCategory("商品信息");
            //3.2设置文档管理员
            dsi.setManager("CC");
            //3.3设置组织机构
            dsi.setCompany("XXX集团");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("商品信息表");
            //4.2.设置文档标题
            si.setTitle("商品信息");
            //4.3 设置文档作者
            si.setAuthor("XXX集团");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            HSSFSheet sheet=workbook.createSheet("XXX集团商品信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 12 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 5 * 256);
            sheet.setColumnWidth(4, 12 * 256);
            sheet.setColumnWidth(5, 20 * 256);
            sheet.setColumnWidth(6, 10 * 256);
            sheet.setColumnWidth(7, 10 * 256);
            sheet.setColumnWidth(8, 16 * 256);
            sheet.setColumnWidth(9, 12 * 256);
            sheet.setColumnWidth(10, 15 * 256);
            sheet.setColumnWidth(11, 20 * 256);
            sheet.setColumnWidth(12, 16 * 256);
            sheet.setColumnWidth(13, 14 * 256);
            sheet.setColumnWidth(14, 14 * 256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("商品编号");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell0.setCellValue("商品名称");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell0.setCellValue("一级分类");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell0.setCellValue("二级分类");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell0.setCellValue("三级分类");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(5);
            cell0.setCellValue("库存");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(6);
            cell0.setCellValue("商品图片路径");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(7);
            cell0.setCellValue("商品积分");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(8);
            cell0.setCellValue("商品上下架");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell9 = headerRow.createCell(9);
            cell0.setCellValue("商品品牌");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell10 = headerRow.createCell(10);
            cell0.setCellValue("创建时间");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell11 = headerRow.createCell(11);
            cell0.setCellValue("修改时间");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell12 = headerRow.createCell(12);
            cell0.setCellValue("交易数量");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell13 = headerRow.createCell(13);
            cell0.setCellValue("交易数量");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell14 = headerRow.createCell(14);
            cell0.setCellValue("评论数量");
            cell0.setCellStyle(headerStyle);
            for (int i=0;i<vps.size();i++){
                HSSFRow row = sheet.createRow(i + 1);
                VProduct vp=vps.get(i);
                row.createCell(0).setCellValue(vp.getvPrice());
                row.createCell(1).setCellValue(vp.getvProductName());
                row.createCell(2).setCellValue(vp.getvCategoryLevel1());
                row.createCell(3).setCellValue(vp.getvCategoryLevel2());
                row.createCell(4).setCellValue(vp.getvCategoryLevel3());
                row.createCell(5).setCellValue(vp.getvStore());
                row.createCell(6).setCellValue(vp.getvPrice());
                row.createCell(7).setCellValue(vp.getvImgUrl());
                row.createCell(8).setCellValue(vp.getvGrade());
                row.createCell(9).setCellValue(vp.getvIsDelete());
                row.createCell(10).setCellValue(vp.getvBrandId());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
*/
