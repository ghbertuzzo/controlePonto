package controlePonto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOHorarioDeTrabalho {

	public Connection connection;

	public DAOHorarioDeTrabalho() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public Integer insert() throws SQLException {
		String querysql = "INSERT INTO \"schemaControlePonto\".horario_trabalho (id) VALUES (DEFAULT);";
		String generatedColumns[] = { "id" };
		PreparedStatement ps = this.connection.prepareStatement(querysql, generatedColumns);
		int affectedRows = ps.executeUpdate(querysql,generatedColumns);
		long id = -1;
		if (affectedRows > 0) {
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					id = rs.getLong(1);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		ps.close();
		return (int) id;
	}
}
