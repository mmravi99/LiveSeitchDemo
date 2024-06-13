package testcomponents;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection extends BaseTest{

	public DBConnection() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public static List<Map<String, ?>> getData(String query, Map<String, String> testData) throws SQLException {
		
		List<Map<String, ?>> results = null;
		
		Connection con = DriverManager.getConnection(testData.get("URL"), testData.get("USERNAME"), testData.get("PASSWORD"));

		if (con.isClosed())
			System.out.println("DB Not Connected");
		else {
			System.out.println("DB Connected");
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();	
			results = new ArrayList<Map<String, ?>>();
			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
	            for (int i = 1; i <= columns; i++) {
	                row.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));
	            }
	            results.add(row);
			}
			con.close();
			
		}
		return results;
	}

}
