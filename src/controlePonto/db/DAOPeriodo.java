package controlePonto.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOPeriodo {
	
	private Connection connection;
	
	public DAOPeriodo () throws SQLException {		
		this.connection = ConnectionFactory.getConnection();
	}

}
