package googletests.helpers;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import googletests.apps.Constants;
import googletests.listeners.EventListener;

public class Utils {
	static Workbook workbook;
	static Sheet sheet;
	
	public static String TESTDATA_PATH = Constants.TESTDATA_DIRECTORY + "TestData.xlsx";
	private static Logger log = Log.getLogData(EventListener.class.getName());
	
	public static Object[][] getTestData(String sheetName) {
		Object [][] testData;
		FileInputStream file = null;
				
		try {
			file = new FileInputStream(TESTDATA_PATH);
			workbook = WorkbookFactory.create(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = workbook.getSheet(sheetName);
		
		final int rowNumber = sheet.getLastRowNum();
		final int cellNumber = sheet.getRow(0).getLastCellNum();
		
		testData = new Object[rowNumber][cellNumber];
		
		for (int row = 0; row < rowNumber; row++) {
				testData[row][0] = sheet.getRow(row + 1).getCell(0).toString();
				testData[row][1] = sheet.getRow(row + 1).getCell(1).toString();
		}
		
		return testData;
	}
	
	public static Boolean areTwoImagesEqual(String firstImage, String secondImage) {
		boolean areEqual = false;
		
		Image imageOne = Toolkit.getDefaultToolkit().getImage(firstImage);
		Image imageTwo = Toolkit.getDefaultToolkit().getImage(secondImage);
		
		PixelGrabber grab1 = new PixelGrabber(imageOne, 0, 0, -1, -1, false);
		PixelGrabber grab2 = new PixelGrabber(imageTwo, 0, 0, -1, -1, false);
		       
		try {
			if (grab1.grabPixels() && grab2.grabPixels()) {
				
				int grab1Width = grab1.getWidth();
				int grab1Height = grab1.getHeight();
				
				int grab2Width = grab2.getWidth();
				int grab2Height = grab2.getHeight();
				
				if (grab1Height == grab2Height && grab1Width == grab2Width) {
					int[] dataImageOne = new int[grab1Width * grab1Height];
					int[] dataImageTwo = new int[grab2Width * grab2Height];
					
					dataImageOne = (int[]) grab1.getPixels();
					dataImageTwo = (int[]) grab2.getPixels();
					
					areEqual = Arrays.equals(dataImageOne, dataImageTwo);
					
				} else {
					System.err.println("[Error] - Images are not equal by width or height");
					log.error("Images are not equal by width or height");
				}
			}
		} catch (InterruptedException e) {
			System.err.println("[Error] - Image comparison has failed");
			log.error("Image comparison has failed");
			e.printStackTrace();
		}
		return areEqual;
	}
	
	public static void closeBrowserInstances() {
		Process process = null;
		String [] browsers = new String [] {
				"firefox.exe",
				"opera.exe",
				"chrome.exe",
				"iexplore.exe"
		};
		
		try {
			process = Runtime.getRuntime().exec("tasklist.exe");
			InputStream procOutput = process.getInputStream();
			Scanner scanner = new Scanner(procOutput);
			while (scanner.hasNextLine()) {
				String task = scanner.nextLine();
				if (task.contains("firefox.exe")) {
					Runtime.getRuntime().exec("taskkill.exe /IM firefox.exe /F");
					break;
				}
			}
			scanner.close();
			process.destroy();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}