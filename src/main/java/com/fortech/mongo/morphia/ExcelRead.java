//package com.fortech.mongo.morphia;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelRead {
//	private static final String FILE_NAME = "C:/tmp/translation.xlsx";
//
//	@SuppressWarnings("resource")
//	public static void main(String[] args) {
//		try {
//
//			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
//			Workbook workbook = new XSSFWorkbook(excelFile);
//			Sheet datatypeSheet = workbook.getSheetAt(0);
//			Iterator<Row> iterator = datatypeSheet.iterator();
//			HashMap<String, Integer> counter = new HashMap<>();
//
//			while (iterator.hasNext()) {
//
//				Row currentRow = iterator.next();
//				Cell columnName = currentRow.getCell(0);
//				Cell langCell = currentRow.getCell(7);
//				Cell kurzCell = currentRow.getCell(8);
//
//				if (counter.containsKey(columnName.toString())) {
//					System.out.println(columnName.toString());
//
//				} else {
//					counter.put(columnName.toString(), 1);
//				}
//
//				// System.out.println("reporting." + columnName.toString() + "." + "lang" + "= "
//				// + langCell.toString());
//				// System.out.println("reporting." + columnName.toString() + "." + "kurz" + "= "
//				// + kurzCell.toString());
//				// System.out.println("reporting." + langCell.toString() + "." + "lang");
//				// System.out.println("reporting." + kurzCell.toString() + "." + "kurz");
//
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private String createPreFixAndSuffix(String columnValue, String kurzLang) {
//		String finalValue = "reporting." + columnValue + "." + kurzLang;
//		return finalValue;
//	}
//}