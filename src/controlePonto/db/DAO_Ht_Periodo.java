package controlePonto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO_Ht_Periodo {

	private Connection connection;

	public DAO_Ht_Periodo() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void insert(int idHT, int idPeriodo) throws SQLException {
		String querysql = "INSERT INTO \"schemaControlePonto\".ht_periodo(id, id_ht, id_periodo) VALUES (default, ?, ?);";
		PreparedStatement ps = this.connection.prepareStatement(querysql);
		ps.setInt(1, idHT);
		ps.setInt(2, idPeriodo);
		ps.execute();
		ps.close();
	}

}
