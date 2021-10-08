package com.sapient.asde.batch5.vehicleservice.util;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Shubham Chaudhari shubham.chaudhari@publicissapeint.com
 */

public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Vehicle> vehicles;
    private Row row;
    private Cell cell;
    
     
    public ExcelExporter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Comparison");
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        int rowcount=0;
 
        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("brand");
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("model"); 
        cell.setCellStyle(style); 
        
        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("description");  
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("price");  
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("color");  
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("tankCapacity"); 
        cell.setCellStyle(style);  

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("mediaInterface"); 
        cell.setCellStyle(style);  

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("theftAlarm"); 
        cell.setCellStyle(style); 
        
        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("tripMeter"); 
        cell.setCellStyle(style);  

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("airBagCount"); 
        cell.setCellStyle(style); 
        
        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("horn");  
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("powerTrainTorque");  
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("accelaration"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("nightMode"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("isABS"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("wheelRadius"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("wheelSpeed"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("ignitionTime");
        cell.setCellStyle(style);  

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("odometer"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("washerFluid"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("airConditioning"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("malfunctionIndicator"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("batteryLevel"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("mirror"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("childSafetyLock"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("topSpeedLimit"); 
        cell.setCellStyle(style); 

        row =sheet.createRow(rowcount++);
        cell =row.createCell(0);
        cell.setCellValue("chime"); 
        cell.setCellStyle(style); 

    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int columnCount = 0;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
        int rowCount=0;

        for (Vehicle vehicle : vehicles){

            rowCount=0;
            columnCount++;

            //brand
            if(vehicle.getBrand()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getBrand()); 
                
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
                
            }
            
            // //model
            if(vehicle.getModel()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getModel()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //description
            if(vehicle.getDescription()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getDescription()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //price
            if(vehicle.getPrice()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getPrice()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //color
            if(vehicle.getColor()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getColor()); 
                
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //tankCapacity
            if(vehicle.getTankCapacity()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getTankCapacity()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }
  
       


            // //mediainterface
            if(vehicle.getMediaInterface()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getMediaInterface()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //theftAlarm
            if(vehicle.getTheftAlarm()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getTheftAlarm()); 
                //createCell(sheet.getRow(rowCount++), columnCount, vehicle.getTheftAlarm(), style);
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //tripMeter
            if(vehicle.getTripMeter()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getTripMeter()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }
  
            //airBagCount
            if(vehicle.getAirBagCount()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getAirBagCount()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //horn
            if(vehicle.getHorn()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getHorn()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //powertaintorque
            if(vehicle.getPowerTrainTorque()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getPowerTrainTorque()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //accelaration
            if(vehicle.getAccelaration()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getAccelaration()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //night mode
            if(vehicle.getNightMode()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getNightMode()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }
 
            //abs
            if(vehicle.getIsABS()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getIsABS()); 
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //wheel radius
            if(vehicle.getWheelRadius()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getWheelRadius());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            } 

            //wheel speed
            if(vehicle.getWheelSpeed()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getWheelSpeed());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }


            //ignition time
            if(vehicle.getIgnitionTime()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getIgnitionTime());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //odometer
            if(vehicle.getOdometer()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getOdometer());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //washer fluid
            if(vehicle.getWasherFluid()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getWasherFluid());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //air conditioning
            if(vehicle.getAirConditioning()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getAirConditioning());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //MalfunctionIndicator
            if(vehicle.getMalfunctionIndicator()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getMalfunctionIndicator());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //battery level
            if(vehicle.getBatteryLevel()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getBatteryLevel());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //mirron
            if(vehicle.getMirror()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getMirror());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //child safety lock
            if(vehicle.getChildSafetyLock()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getChildSafetyLock());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }

            //top speed limit
            if(vehicle.getTopSpeedLimit()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getTopSpeedLimit());
            }
            else{
                empltyCell(rowCount++, columnCount);
            }

            //chime
            if(vehicle.getChime()!=null)
            {
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue(vehicle.getChime());
            }
            else{
                row =sheet.getRow(rowCount++);
                cell =row.createCell(columnCount);
                cell.setCellValue("NA"); 
            }
             
        }
    }
    private void empltyCell(int rowCount , int columnCount)
    {
        row =sheet.getRow(rowCount);
        cell =row.createCell(columnCount);
        cell.setCellValue("NA");
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
    }
}
