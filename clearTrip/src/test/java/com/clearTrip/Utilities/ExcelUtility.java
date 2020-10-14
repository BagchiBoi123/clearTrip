package com.clearTrip.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	static Workbook book;
	static Sheet sheet;
	
	
	public static String TESTDATA_SHEET_PATH="C:\\Users\\848829\\eclipse-workspace\\clearTrip\\src\\test\\java\\com\\clearTrip\\testData\\Bookings.xlsx";
	 
	public static Object[][] getTestData(String sheetName)
	{
		FileInputStream file=null;
		try 
		{
			
			file=new FileInputStream(TESTDATA_SHEET_PATH);
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try 
		{
			book=WorkbookFactory.create(file);
			
		} 
		catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet=book.getSheet(sheetName);
		
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		int i,j;
		for(i=0;i<sheet.getLastRowNum();i++)
		{
			for(j=0;j<sheet.getRow(0).getLastCellNum();j++)
			{
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
}
