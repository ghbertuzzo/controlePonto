package controlePonto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO_At_Periodo {

	private Connection connection;

	public DAO_At_Periodo() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void insert(int idAt, int idPeriodo) throws SQLException {
		String querysql = "INSERT INTO \"schemaControlePonto\".at_periodo(id, id_at, id_periodo) VALUES (default, ?, ?);";
		PreparedStatement ps = this.connection.prepareStatement(querysql);
		ps.setInt(1, idAt);
		ps.setInt(2, idPeriodo);
		ps.execute();
		ps.close();
	}
}
