package controlePonto.main;

import java.sql.SQLException;

import controlePonto.db.ConnectionFactory;
import controlePonto.view.JanelaPrincipal;
import net.sf.jasperreports.engine.JRException;

public class ControlePontoMain {

	public static void main(String[] args) throws SQLException, JRException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		JanelaPrincipal start = new JanelaPrincipal(connectionFactory);
		start.setVisible(true);
	}

}
