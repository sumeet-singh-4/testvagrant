package com.testvagrant.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static Object[][] getDataFromExcel(String fileName) {
		File file = new File(fileName);
		Object[][] testDataObject = null;

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet datatypeSheet = workbook.getSheetAt(0);
			int ci = 0;
			Iterator<Row> rowIterator = datatypeSheet.iterator();
			int totalRows = datatypeSheet.getLastRowNum();
			int totalcolumns = getTotalColoumnNumberInExcel(rowIterator.next());
			testDataObject = new Object[totalRows][totalcolumns];

			for (int startRow = 1; startRow <= totalRows; ++ci) {
				int cj = 0;

				for (int startCol = 0; startCol < totalcolumns; ++cj) {
					Cell cell = datatypeSheet.getRow(startRow).getCell(startCol);
					if (cell == null) {
						testDataObject[ci][cj] = null;
					} else {
						switch (cell.getCellType()) {
						case 0:
							testDataObject[ci][cj] = cell.getNumericCellValue();
							break;
						case 1:
							testDataObject[ci][cj] = cell.getStringCellValue().equalsIgnoreCase("null") ? null
									: cell.getStringCellValue();
						case 2:
						default:
							break;
						case 3:
							testDataObject[ci][cj] = "";
							break;
						case 4:
							testDataObject[ci][cj] = cell.getBooleanCellValue();
						}
					}

					++startCol;
				}

				workbook.close();
				++startRow;
			}

			return testDataObject;
		} catch (FileNotFoundException var14) {
			throw new RuntimeException("File " + fileName + " was not found" + var14.getStackTrace().toString());
		} catch (IOException var15) {
			throw new RuntimeException("Can't Read Excel " + fileName + var15.getStackTrace().toString());
		}
	}

	public static int getTotalColoumnNumberInExcel(Row row) {
		Iterator<Cell> cellIterator = row.cellIterator();

		int totalcolumnsInExcel;
		for (totalcolumnsInExcel = 0; cellIterator.hasNext(); ++totalcolumnsInExcel) {
			cellIterator.next();
		}

		return totalcolumnsInExcel;
	}

}
