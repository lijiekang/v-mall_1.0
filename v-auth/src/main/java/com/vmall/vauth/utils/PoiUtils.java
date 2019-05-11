package com.vmall.vauth.utils;

import com.vmall.pojo.VUser;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PoiUtils {

    public static ResponseEntity<byte[]> exportEmp2Excel(List<VUser> vUsers){
        HttpHeaders headers=null;
        ByteArrayOutputStream baos=null;
        try{
            //创建Excel文档
            HSSFWorkbook workbook=new HSSFWorkbook();
            //摘要
            workbook.createInformationProperties();
            DocumentSummaryInformation dsi=workbook.getDocumentSummaryInformation();
            dsi.setCategory("用户信息");
            dsi.setManager("chen");
            dsi.setCompany("javashop");
            SummaryInformation si=workbook.getSummaryInformation();
            si.setSubject("用户信息表");
            si.setTitle("用户信息");
            si.setAuthor("chen");
            si.setComments("无");
            //创建Excel表单
            HSSFSheet sheet=workbook.createSheet("javashop用户信息表");
            HSSFCellStyle dataCellStyle=workbook.createCellStyle();
            dataCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            HSSFCellStyle headerStyle=workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            sheet.setColumnWidth(0,5*256);
            sheet.setColumnWidth(5,5*256);
            sheet.setColumnWidth(10,5*256);
            sheet.setColumnWidth(15,5*256);

            HSSFRow headerRow=sheet.createRow(0);

            HSSFCell cell0=headerRow.createCell(0);
            cell0.setCellValue("用户ID");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1=headerRow.createCell(5);
            cell0.setCellValue("用户名");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell2=headerRow.createCell(10);
            cell0.setCellValue("用户密码");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell3=headerRow.createCell(15);
            cell0.setCellValue("用户昵称");
            cell0.setCellStyle(headerStyle);

            for(int i=0;i<vUsers.size();i++){
                HSSFRow row=sheet.createRow(i+1);
                VUser vUser=vUsers.get(i);
                row.createCell(0).setCellValue(vUser.getvUserId());
                row.createCell(5).setCellValue(vUser.getvUsername());
                row.createCell(10).setCellValue(vUser.getvPassword());
                row.createCell(15).setCellValue(vUser.getvUsercode());
            }

            headers=new HttpHeaders();
            headers.setContentDispositionFormData("attachment",new String("用户表.xls".
                    getBytes("UTF-8"),"iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos=new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(),headers, HttpStatus.CREATED);
    }


}
