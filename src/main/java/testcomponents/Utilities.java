package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {
	
	@DataProvider(name="exceldp")
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
	
	@DataProvider(name = "jsondp", parallel=true)
	public static Object[][] getJsonTestData() throws StreamReadException, DatabindException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
            List<Map<String, String>> data = objectMapper.readValue(new File("D:\\DempApps\\LiveSwichVideo\\src\\test\\resources\\TC001_CreateContacts.json"), List.class);
            Object[][] testData = new Object[data.size()][2];
            for (int i = 0; i < data.size(); i++) {
                testData[i][0] = data.get(i).get("plotform");
                testData[i][1] = data.get(i).get("runmode");
            }
            return testData;
        
    }

}
