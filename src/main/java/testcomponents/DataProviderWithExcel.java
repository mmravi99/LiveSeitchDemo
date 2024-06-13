package testcomponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderWithExcel {
	
	@Test(dataProvider="getData")
	public void test1(String browser1,String browser2,String browser3,String browser4,String browser5,String browser6,String browser7) {
		try {
			if(browser7.equals("N")) {
				throw new SkipException("Skipping the test case as the Run mode for data set is NO");
			}
			System.out.println(browser1);
		}
		catch(SkipException e) {
			
		}
		
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		FileInputStream fs = new FileInputStream("D:\\DempApps\\LiveSwichVideo\\src\\test\\resources\\testdata.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sh = wb.getSheet("TC_001");
		int rownum = sh.getLastRowNum();
		int colnum = sh.getRow(0).getLastCellNum();
		Object[][] data=new Object[rownum][colnum];
		for(int i=1;i<=rownum;i++) {
			for( int j=0;j<colnum;j++) {
				data[i-1][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		return data;
	}
}
