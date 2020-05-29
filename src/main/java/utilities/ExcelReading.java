package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReading {

	FileInputStream fis = null;
	FileOutputStream fos = null;
	XSSFWorkbook workbook = null;
	XSSFWorkbook workbook1 = null;
	XSSFSheet sheet = null;
	Cell cell = null;
	Row row = null;
	String path;

	public ExcelReading(String path) {
		this.path = path;
		File f = new File(path);
		if (f.exists()) {
			try {
				fis = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				workbook = new XSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) throws IOException {

		/*
		 * ExcelPractice ep = new ExcelPractice( System.getProperty("user.dir") +
		 * "\\src\\test\\resources\\excelworkbooks\\TestDataExcel1.xlsx");
		 * System.out.println(ep.getRowCount("testdata"));
		 * System.out.println(ep.getColCount("testData")); ep.getAllTable();
		 * System.out.println("is run " + ep.getRunMode("testrun", "TC1"));
		 * 
		 * // ep.getAllTable(); if (ep.getRunMode("testrun", "TC1")) {
		 * System.out.println("is test run date set " + ep.setRunDate("testrun",
		 * "TC1")); System.out.println("is test s=result set " +
		 * ep.setTestResult("testrun", "tc1", "pass")); } ep.close();
		 */
	}

	public void close() throws IOException {
		fis.close();
	}

	public String getDesiredData(String sheetname, int rownum, int colnum) {
		int index = workbook.getSheetIndex(sheetname);
		
		sheet = workbook.getSheetAt(index);

		// required row
		int rowCount = sheet.getLastRowNum();
		int colCount = -1;
		if (sheet.getRow(0) != null)
			colCount = sheet.getRow(0).getLastCellNum();
		Row row = sheet.getRow(rownum);

		Cell cell = row.getCell(colnum);
		String cellText = "";
		if (cell.getCellType() == CellType.STRING) {
			cellText = cell.getStringCellValue() + "";
		} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
			double d = cell.getNumericCellValue();
			cellText = Double.toString(d);
			cellText = cellText.substring(0, cellText.length() - 2) + "";
			if (DateUtil.isCellDateFormatted(cell)) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.getJavaDate(d));
				String month = Integer.toString((cal.get(cal.MONTH) + 1));
				String date = Integer.toString(cal.get(cal.DAY_OF_MONTH));
				String year = Integer.toString(cal.get(cal.YEAR));

				cellText = date + "-" + month + "-" + year + "";

			}

		}

		else if (cell.getCellType() == CellType.BOOLEAN) {
			cellText = cell.getBooleanCellValue() + "";
		} else if (cell.getCellType() == CellType.BLANK) {
			cellText = "" + " ";
		}
		
		return cellText;
	}

	public boolean setTestResult(String sheetname, String testcase, String result) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);

		int index = workbook.getSheetIndex(sheetname);
		if (index == -1) {
			return false;
		}
		sheet = workbook.getSheetAt(index);
		// check row
		int rowCount = sheet.getLastRowNum();
		row = sheet.getRow(0);
		if (row == null) {
			return false;
		}
		int colCount = -1;
		colCount = row.getLastCellNum();
		if (colCount == -1) {
			return false;
		}
		int findTC = -1;
		int findTRE = -1;

		for (int j = 0; j < colCount; j++) // col iteration of desired row
		{
			if (row.getCell(j).getStringCellValue().equalsIgnoreCase("Testcases")) {
				findTC = j;
			} else if (row.getCell(j).getStringCellValue().equalsIgnoreCase("Testresult")) {
				findTRE = j;
			}
		}
		if (findTRE == -1) {
			return false;
		}
		Date d = null;
		for (int i = 1; i < rowCount; i++) // row iteration of desired col
		{
			if (sheet.getRow(i).getCell(findTC).getStringCellValue().equalsIgnoreCase(testcase)) {
				d = new Date();
				cell = sheet.getRow(i).getCell(findTRE);
				if (cell == null) {
					cell = sheet.getRow(i).createCell(findTRE);

				}

				cell.setCellValue(result);
				fos = new FileOutputStream(path);

				workbook.write(fos);

				fos.close();
				return true;
			} else
				return false;
		}

		return false;

	}

	public boolean setRunDate(String sheetname, String testcase) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);

		int index = workbook.getSheetIndex(sheetname);
		if (index == -1) {
			return false;
		}
		sheet = workbook.getSheetAt(index);
		// check row
		int rowCount = sheet.getLastRowNum();
		row = sheet.getRow(0);
		if (row == null) {
			return false;
		}
		int colCount = -1;
		colCount = row.getLastCellNum();
		if (colCount == -1) {
			return false;
		}
		int findTC = -1;
		int findTLR = -1;

		for (int j = 0; j < colCount; j++) // col iteration of desired row
		{
			if (row.getCell(j).getStringCellValue().equalsIgnoreCase("Testcases")) {
				findTC = j;
			} else if (row.getCell(j).getStringCellValue().equalsIgnoreCase("Testlastrun")) {
				findTLR = j;
			}
		}
		Date d = null;
		for (int i = 1; i < rowCount; i++) // row iteration of desired col
		{
			if (sheet.getRow(i).getCell(findTC).getStringCellValue().equalsIgnoreCase(testcase)) {
				d = new Date();
				cell = sheet.getRow(i).getCell(findTLR);
				if (cell == null) {
					cell=sheet.getRow(i).createCell(findTLR);

				}

				cell.setCellValue(d);
				fos = new FileOutputStream(path);

				workbook.write(fos);

				fos.close();
				return true;
			} else
				return false;
		}

		return false;

	}

	public boolean getRunMode(String sheetname, String testcase) {
		int index = workbook.getSheetIndex(sheetname);
		if (index == -1) {
			return false;
		}
		sheet = workbook.getSheetAt(index);

		// check row
		int rowCount = sheet.getLastRowNum();
		row = sheet.getRow(0);
		if (row == null) {
			return false;
		}
		int colCount = row.getLastCellNum();
		int findTC = -1;
		int findTR = -1;

		for (int j = 0; j < colCount; j++) {
			if (row.getCell(j).getStringCellValue().equalsIgnoreCase("Testcases")) {
				findTC = j;
			} else if (row.getCell(j).getStringCellValue().equalsIgnoreCase("Testrunmode")) {
				findTR = j;
			}
		}

		boolean isRun = false;
		for (int i = 1; i <=rowCount; i++) {
			if (sheet.getRow(i).getCell(findTC).getStringCellValue().equalsIgnoreCase(testcase)) {
				isRun = sheet.getRow(i).getCell(findTR).getBooleanCellValue();
			}
		}

		return isRun;

	}

	public int getRowCount(String sheetname) {

		int index = workbook.getSheetIndex(sheetname);
		if (index == -1) {
			return -1;
		}
		sheet = workbook.getSheetAt(index);

		// required row
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	public int getColCount(String sheetname) {
		int index = workbook.getSheetIndex(sheetname);
		if (index == -1) {
			return -1;
		}
		sheet = workbook.getSheetAt(index);

		// check row
		row = sheet.getRow(0);
		if (row == null) {
			return -1;
		}
		int colCount = row.getLastCellNum();
		return colCount;

	}

	public void getAllTable() {
		// required sheet
		int index = workbook.getSheetIndex("testdata");
		System.out.println(index);
		sheet = workbook.getSheetAt(index);

		// required row
		int rowCount = sheet.getLastRowNum();
		int colCount = -1;
		if (sheet.getRow(0) != null)
			colCount = sheet.getRow(0).getLastCellNum();
		for (int i = 0; i <= rowCount; i++) // with 0 header also comes
		{
			Row row = sheet.getRow(i);

			for (int j = 0; j < colCount; j++) {
				Cell cell = row.getCell(j);
				String cellText = "";
				if (cell.getCellType() == CellType.STRING) {
					cellText = cell.getStringCellValue() + " ";
				} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
					double d = cell.getNumericCellValue();
					cellText = Double.toString(d);
					cellText = cellText.substring(0, cellText.length() - 2) + " ";
					if (DateUtil.isCellDateFormatted(cell)) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(DateUtil.getJavaDate(d));
						String month = Integer.toString((cal.get(cal.MONTH) + 1));
						String date = Integer.toString(cal.get(cal.DAY_OF_MONTH));
						String year = Integer.toString(cal.get(cal.YEAR));

						cellText = date + "-" + month + "-" + year + " ";

					}

				}

				else if (cell.getCellType() == CellType.BOOLEAN) {
					cellText = cell.getBooleanCellValue() + " ";
				} else if (cell.getCellType() == CellType.BLANK) {
					cellText = "" + " ";
				}

				System.out.print(cellText);

			}

			System.out.println();
		}

	}

	public String getDesiredData(String sheetName, int rowNum, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
