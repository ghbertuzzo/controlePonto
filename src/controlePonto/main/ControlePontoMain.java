package controlePonto.main;

import java.sql.Connection;
import java.sql.SQLException;

import controlePonto.db.ConnectionFactory;
import controlePonto.view.JanelaPrincipal;
import net.sf.jasperreports.engine.JRException;

public class ControlePontoMain {

	public static void main(String[] args) throws SQLException, JRException {
		Connection connection = ConnectionFactory.getConnection();

		/*String reportPath = "C:\\Users\\Admin\\Documents\\Projetos\\Controle Ponto\\controlePonto\\src\\reports\\relatorioHistorico.jrxml";
		JasperReport jr = JasperCompileManager.compileReport(reportPath);
		JasperPrint jp = JasperFillManager.fillReport(jr, null, connection);
		JasperViewer.viewReport(jp);
		JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(new ArrayList<String>());
/*/
		JanelaPrincipal start = new JanelaPrincipal();
		start.setVisible(true);
		connection.close();
	}

}
