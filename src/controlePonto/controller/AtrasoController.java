package controlePonto.controller;

import java.util.List;

import javax.swing.JTable;

import controlePonto.model.Dia;
import controlePonto.model.Periodo;
import controlePonto.view.AtrasoView;
import controlePonto.view.HorarioDeTrabalhoView;
import controlePonto.view.JanelaPrincipal;
import controlePonto.view.MarcacoesFeitasView;
import controlePonto.view.TabelaView;

public class AtrasoController {

	JanelaPrincipal context;
	DiaController diaController;
	PeriodoController periodoController;
	
	public AtrasoController(JanelaPrincipal context) {
		this.context = context;
		this.diaController = new DiaController();
		this.periodoController = new PeriodoController();
	}
	
	public void subAtraso() {
		this.context.atravoView.setTable(calculaAtrasos(this.context.horarioDeTrabalhoView,this.context.marcacoesFeitasView,this.context.atravoView));
		this.context.atravoView.renderTable();
	}
	
	private JTable calculaAtrasos(HorarioDeTrabalhoView horarioTrabalhoView, MarcacoesFeitasView marcacoesFeitasView, AtrasoView atrasoView) {
		Dia horarioTrabalhoDia = new Dia();		
		horarioTrabalhoDia = diaController.getConvertedDay(horarioTrabalhoView);
		Dia marcacoesFeitasDia = new Dia();
		marcacoesFeitasDia = diaController.getConvertedDay(marcacoesFeitasView);
		Dia atrasoDia = diaController.getAtrasos(horarioTrabalhoDia, marcacoesFeitasDia);
		List<Periodo> listaPeriodosAtrasados = periodoController.getPeriods(atrasoDia);
		TabelaView tableView = atrasoView.newTable(listaPeriodosAtrasados);
		atrasoView.setListEntries(tableView.getListEntries());
		atrasoView.setListExits(tableView.getListExits());
		return atrasoView.getTable();
	}
}
