package controlePonto.main;

import java.sql.Connection;
import java.sql.SQLException;

import controlePonto.db.ConnectionFactory;
import controlePonto.view.JanelaPrincipal;
import net.sf.jasperreports.engine.JRException;

public class ControlePontoMain {

	public static void main(String[] args) throws SQLException, JRException {
		Connection connection = ConnectionFactory.getConnection();
		JanelaPrincipal start = new JanelaPrincipal();
		start.setVisible(true);
		connection.close();
	}

}
