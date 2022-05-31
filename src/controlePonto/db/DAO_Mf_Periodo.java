package controlePonto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO_Mf_Periodo {

	private Connection connection;

	public DAO_Mf_Periodo() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void insert(int idMF, int idPeriodo) throws SQLException {
		String querysql = "INSERT INTO \"schemaControlePonto\".mf_periodo(id, id_mf, id_periodo) VALUES (default, ?, ?);";
		PreparedStatement ps = this.connection.prepareStatement(querysql);
		ps.setInt(1, idMF);
		ps.setInt(2, idPeriodo);
		ps.execute();
		ps.close();
	}
}
