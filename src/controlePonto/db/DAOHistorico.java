package controlePonto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlePonto.model.Historico;

public class DAOHistorico {

	private Connection connection;

	public DAOHistorico() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public Historico getHistoricoByDate(String date) throws SQLException {
		Historico historico = null;
		String querysql = "SELECT date, id_ht, id_mf, id_he, id_at	FROM \"schemaControlePonto\".historico WHERE date=?;";
		PreparedStatement ps = this.connection.prepareStatement(querysql);
		ps.setDate(1, java.sql.Date.valueOf(date));
		ResultSet rs;
		rs = ps.executeQuery();
		while (rs.next()) {
			historico = new Historico(rs.getDate(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
		}
		return historico;
	}
}
