package controlePonto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO_He_Periodo {

	private Connection connection;

	public DAO_He_Periodo() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void insert(int idHe, int idPeriodo) throws SQLException {
		String querysql = "INSERT INTO \"schemaControlePonto\".he_periodo(id, id_he, id_periodo) VALUES (default, ?, ?);";
		PreparedStatement ps = this.connection.prepareStatement(querysql);
		ps.setInt(1, idHe);
		ps.setInt(2, idPeriodo);
		ps.execute();
		ps.close();
	}
}
