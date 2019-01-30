package studentresult.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	
	private static DataSource dataSource=null;
	private static final String JNDI_LOOKUP_SERVICE = "studentResult/jdbcDs";
	static {
	try {
		Context context = new InitialContext();
		Object lookup=context.lookup(JNDI_LOOKUP_SERVICE);
		if(lookup!=null) {
			dataSource=(DataSource)lookup;
		}else {
				new RuntimeException("DataSource not found----");
		}
	}
	
	catch(NamingException e)
	{
		e.printStackTrace();
	}
	}
	
	public static DataSource getDataSource()
	{
		return dataSource;
	}
}
		
	
	