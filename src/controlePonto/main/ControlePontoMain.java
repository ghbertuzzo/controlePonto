package controlePonto.main;

import java.sql.Connection;
import java.sql.SQLException;

import controlePonto.db.ConnectionFactory;
import controlePonto.db.DAOHistorico;
import controlePonto.model.Historico;
import controlePonto.view.JanelaPrincipal;

public class ControlePontoMain {

	public static void main(String[] args) throws SQLException {	
		Connection connection = ConnectionFactory.getConnection();
		
		/*
		//CRIA 2 PERIODOS  (01:00 AS 06:00) E (07:00 AS 11:00) RESERVA IDS
		DAOPeriodo daop = new DAOPeriodo();
		Integer idPeriodo = daop.insert(60, 360);
		Integer idPeriodo2 = daop.insert(420, 660);
		
		//CRIA 1 HT NOVO E RESERVA O ID
		DAOHorarioDeTrabalho daoHt = new DAOHorarioDeTrabalho();
		Integer idHT = daoHt.insert();
		
		//INSERE 2 HT_PERIODO ASSOCIANDO IDHT COM IDPERIODO
		DAO_Ht_Periodo daoht_periodo = new DAO_Ht_Periodo();
		daoht_periodo.insert(idHT, idPeriodo);
		daoht_periodo.insert(idHT, idPeriodo2);*/
		
		
		/*
		//CRIA 2 PERIODOS  (01:10 AS 05:50) E (07:10 AS 11:10) RESERVA IDS
		DAOPeriodo daop = new DAOPeriodo();
		Integer idPeriodo = daop.insert(70, 350);
		Integer idPeriodo2 = daop.insert(430, 670);
		
		//CRIA 1 MF NOVO E RESERVA O ID
		DAOMarcacoesFeitas daoMf = new DAOMarcacoesFeitas();
		Integer idMF = daoMf.insert();
		
		//INSERE 2 MF_PERIODO ASSOCIANDO IDMF COM IDPERIODO
		DAO_Mf_Periodo daomf_periodo = new DAO_Mf_Periodo();
		daomf_periodo.insert(idMF, idPeriodo);
		daomf_periodo.insert(idMF, idPeriodo2);*/
		
		
		/*
		//CRIA 1 PERIODO  (11:00 AS 11:10) RESERVA ID
		DAOPeriodo daop = new DAOPeriodo();
		Integer idPeriodo = daop.insert(660, 670);
		
		//CRIA 1 HE NOVA E RESERVA O ID
		DAOHoraExtra daohe = new DAOHoraExtra();
		int idHE = daohe.insert();
		
		//INSERE HE_PERIODO ASSOCIANDO IDHE COM IDPERIODO
		DAO_He_Periodo daohe_periodo = new DAO_He_Periodo();
		daohe_periodo.insert(idHE, idPeriodo);*/
				
		
		/*
		//CRIA 3 PERIODOS  (01:00 AS 01:10) E (05:50 AS 06:00) E (07:00 AS 07:10) RESERVA IDS
		DAOPeriodo daop = new DAOPeriodo();
		Integer idPeriodo = daop.insert(60, 70);
		Integer idPeriodo2 = daop.insert(350, 360);
		Integer idPeriodo3 = daop.insert(420, 430);
		//CRIA 1 ATRASO E RESERVA O ID
		DAOAtraso daoatraso = new DAOAtraso();
		int idAtraso = daoatraso.insert();
		//INSERE AT_PERIODO ASSOCIANDO IDAT COM IDPERIODO
		DAO_At_Periodo daoat_periodo = new DAO_At_Periodo();
		daoat_periodo.insert(idAtraso, idPeriodo);
		daoat_periodo.insert(idAtraso, idPeriodo2);
		daoat_periodo.insert(idAtraso, idPeriodo3);*/
		
				
		JanelaPrincipal start = new JanelaPrincipal();
		start.setVisible(true);
		connection.close();
	}

}
