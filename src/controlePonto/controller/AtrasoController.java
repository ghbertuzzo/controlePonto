package controlePonto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import controlePonto.db.DAOAtraso;
import controlePonto.db.DAOPeriodo;
import controlePonto.db.DAO_At_Periodo;
import controlePonto.model.Dia;
import controlePonto.model.Periodo;
import controlePonto.view.AtrasoView;
import controlePonto.view.HorarioDeTrabalhoView;
import controlePonto.view.JanelaPrincipal;
import controlePonto.view.MarcacoesFeitasView;
import controlePonto.view.TabelaView;

public class AtrasoController {

	private JanelaPrincipal context;
	private DiaController diaController;
	private PeriodoController periodoController;
	private DAOPeriodo daoPeriodo;
	private DAO_At_Periodo daoat_periodo;
	
	public AtrasoController(JanelaPrincipal context) throws SQLException {
		this.context = context;
		this.diaController = new DiaController();
		this.periodoController = new PeriodoController();
		this.daoPeriodo = new DAOPeriodo();
		this.daoat_periodo = new DAO_At_Periodo();
	}
	
	public void subAtraso() {
		this.context.atrasoView.setTable(calculaAtrasos(this.context.horarioDeTrabalhoView,this.context.marcacoesFeitasView,this.context.atrasoView));
		this.context.atrasoView.renderTable();
	}
	
	private JTable calculaAtrasos(HorarioDeTrabalhoView horarioTrabalhoView, MarcacoesFeitasView marcacoesFeitasView, AtrasoView atrasoView) {
		Dia horarioTrabalhoDia = new Dia();		
		horarioTrabalhoDia = this.diaController.getConvertedDay(horarioTrabalhoView);
		Dia marcacoesFeitasDia = new Dia();
		marcacoesFeitasDia = this.diaController.getConvertedDay(marcacoesFeitasView);
		Dia atrasoDia = this.diaController.getAtrasos(horarioTrabalhoDia, marcacoesFeitasDia);
		List<Periodo> listaPeriodosAtrasados = this.periodoController.getPeriods(atrasoDia);
		TabelaView tableView = atrasoView.newTable(listaPeriodosAtrasados);
		atrasoView.setListEntries(tableView.getListEntries());
		atrasoView.setListExits(tableView.getListExits());
		return atrasoView.getTable();
	}

	public int saveAtrasos(AtrasoView atrasoView) throws SQLException {		
		DAOAtraso daoAtraso = new DAOAtraso();
		//CRIA NOVO REGISTRO DO TIPO ATRASO NO BANCO 
		Integer idAt = daoAtraso.insert();
		//CRIA NOVOS REGISTROS DO TIPO PERIODO (PERIODOS DE ATRASO) NO BANCO
		for(int i = 0; i<atrasoView.getTableModel().getRowCount();i++) {
			int idperiodo = this.daoPeriodo.insert(this.periodoController.localTimeToNumber(atrasoView.getListEntries().get(i)), this.periodoController.localTimeToNumber(atrasoView.getListExits().get(i)));
			this.daoat_periodo.insert(idAt, idperiodo);
		}
		return idAt;
	}
	
	//RECEBE UM ID DE ATRASO E RETORNA TODOS OS PERIODOS (PERIODOS DE ATRASO) DAQUELE DIA
	public List<Periodo> getPeriods(int id_at) throws SQLException {
		List<Integer> list_idPeriods = this.daoat_periodo.getPeriods(id_at);
		ArrayList<Periodo> listPeriods = new ArrayList<Periodo>();
		for(Integer idPeriod: list_idPeriods) {
			Periodo periodo = this.daoPeriodo.getPeriodoByID(idPeriod);
			listPeriods.add(periodo);
		}		
		return listPeriods;
	}
}
