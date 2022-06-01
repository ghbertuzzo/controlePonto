package controlePonto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlePonto.db.DAOHorarioDeTrabalho;
import controlePonto.db.DAOPeriodo;
import controlePonto.db.DAO_Ht_Periodo;
import controlePonto.model.Periodo;
import controlePonto.view.HorarioDeTrabalhoView;

public class HorarioDeTrabalhoController {	
	
	private DAOPeriodo daoPeriodo;
	private DAO_Ht_Periodo daoht_periodo;
	
	public HorarioDeTrabalhoController() throws SQLException {
		this.daoPeriodo = new DAOPeriodo();
		this.daoht_periodo = new DAO_Ht_Periodo();
	}

	public int saveHorarioDeTrabalho(HorarioDeTrabalhoView horarioDeTrabalhoView) throws SQLException {
		PeriodoController periodoController = new PeriodoController();
		DAOHorarioDeTrabalho daoHt = new DAOHorarioDeTrabalho();
		//CRIA NOVO REGISTRO DO TIPO HORARIODETRABALHO NO BANCO 
		Integer idHT = daoHt.insert();
		//CRIA NOVOS REGISTROS DO TIPO PERIODO (PERIODOS DE HORARIODETRABALHO) NO BANCO
		for(int i = 0; i<horarioDeTrabalhoView.getTableModel().getRowCount();i++) {
			int idperiodo = this.daoPeriodo.insert(periodoController.localTimeToNumber(horarioDeTrabalhoView.getListEntries().get(i)), periodoController.localTimeToNumber(horarioDeTrabalhoView.getListExits().get(i)));
			this.daoht_periodo.insert(idHT, idperiodo);
		}	
		return idHT;		
	}

	//RECEBE UM ID DE HORARIO DE TRABALHO E RETORNA TODOS OS PERIODOS (PERIODOS DE HORARIO DE TRABALHO) DAQUELE DIA
	public List<Periodo> getPeriods(int id_ht) throws SQLException {	
		List<Integer> list_idPeriods = this.daoht_periodo.getPeriods(id_ht);
		ArrayList<Periodo> listPeriods = new ArrayList<Periodo>();
		for(Integer idPeriod: list_idPeriods) {
			Periodo periodo = this.daoPeriodo.getPeriodoByID(idPeriod);
			listPeriods.add(periodo);
		}		
		return listPeriods;
	}

}
