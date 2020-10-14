/*
 * 
 * @author : ABHISHEK BAGCHI
 * 
 */


package com.clearTrip.Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public static FileInputStream file;
	//public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow currentrow;
	//public static XSSFCell cell;
	

	
	public ArrayList<String> getExcelData() throws IOException
	{
		
		ArrayList<String> records = new ArrayList<String>();
		
file= new FileInputStream("C:\\Users\\848829\\git\\clearTrip\\clearTrip\\src\\test\\java\\com\\clearTrip\\testData\\Bookings.xlsx");
workbook=new XSSFWorkbook(file);
sheet=workbook.getSheet("Sheet1");
int rows=sheet.getLastRowNum();



for(int i=1;i<=rows;i++)
{
	 currentrow=sheet.getRow(i);
	
	String from=currentrow.getCell(0).getStringCellValue();
	String to=currentrow.getCell(1).getStringCellValue();
	Date departureDate=currentrow.getCell(2).getDateCellValue();
	Date returnDate=currentrow.getCell(3).getDateCellValue();
	
	
	
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	
	Date date1=departureDate;
	String depDate=df.format(date1);
	
	Date date2=returnDate;
	String retDate=df.format(date2);
	 System.out.println("From "+from+" To "+to+" Where Departure Date is "+depDate+" And Return Date is "+retDate);
		
	 records.add(from);
	 records.add(to);
	 records.add(depDate);
	 records.add(retDate);
	 
	System.out.println(records); 
	 
}
System.out.println();
workbook.close();
return records;

	}
	
	
	
	
	public String[] readExcel() throws IOException
	{
		
		file= new FileInputStream("C:\\Users\\848829\\eclipse-workspace\\clearTrip\\src\\test\\java\\com\\clearTrip\\testData\\Bookings.xlsx");
		workbook=new XSSFWorkbook(file);
		sheet=workbook.getSheet("Sheet1");
		int rows=sheet.getLastRowNum();

		String records[]=new String[10];

		for(int i=1;i<=rows;i++)
		{
			 currentrow=sheet.getRow(i);
			
			String from=currentrow.getCell(0).getStringCellValue();
			String to=currentrow.getCell(1).getStringCellValue();
			Date departureDate=currentrow.getCell(2).getDateCellValue();
			Date returnDate=currentrow.getCell(3).getDateCellValue();
			
			
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			
			Date date1=departureDate;
			String depDate=df.format(date1);
			
			Date date2=returnDate;
			String retDate=df.format(date2);
		
		records= new String[] {from,to,depDate,retDate};
		
		
		
	}
		System.out.println();
		workbook.close();
		
		return records;

}
	
	
	
	
	
	public String readExcelData(int row,int column) throws IOException
	{
		file= new FileInputStream("C:\\Users\\848829\\eclipse-workspace\\clearTrip\\src\\test\\java\\com\\clearTrip\\testData\\Bookings.xlsx");
		workbook=new XSSFWorkbook(file);
		sheet=workbook.getSheet("Sheet1");
		//int rows=sheet.getLastRowNum();
		
		 DataFormatter dataFormatter = new DataFormatter();

		currentrow=sheet.getRow(row);
		
		 String cellValue = dataFormatter.formatCellValue(currentrow.getCell(column));
		
		return cellValue;
		
	}
}