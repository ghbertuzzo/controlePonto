package controlePonto.controller;

import java.util.List;

import javax.swing.JTable;

import controlePonto.model.Dia;
import controlePonto.model.Periodo;
import controlePonto.view.HoraExtraView;
import controlePonto.view.HorarioDeTrabalhoView;
import controlePonto.view.JanelaPrincipal;
import controlePonto.view.MarcacoesFeitasView;
import controlePonto.view.TabelaView;

public class HoraExtraController {

	JanelaPrincipal context;
	DiaController diaController;
	PeriodoController periodoController;

	public HoraExtraController(JanelaPrincipal context) {
		this.context = context;
		this.diaController = new DiaController();
		this.periodoController = new PeriodoController();
	}

	public void subHoraExtra() {
		this.context.horaExtraView.setTable(calculaHoraExtra(this.context.horarioDeTrabalhoView,
				this.context.marcacoesFeitasView, this.context.horaExtraView));
		this.context.horaExtraView.renderTable();
	}

	private JTable calculaHoraExtra(HorarioDeTrabalhoView horarioDeTrabalho, MarcacoesFeitasView marcacoesFeitasView,
			HoraExtraView horaExtraView) {
		Dia horarioTrabalhoDia = new Dia();
		horarioTrabalhoDia = diaController.getConvertedDay(horarioDeTrabalho);
		Dia marcacoesFeitasDia = new Dia();
		marcacoesFeitasDia = diaController.getConvertedDay(marcacoesFeitasView);
		Dia horaExtraDia = diaController.getHoraExtra(marcacoesFeitasDia, horarioTrabalhoDia);
		List<Periodo> listaPeriodosHoraExtra = periodoController.getPeriods(horaExtraDia);
		TabelaView tableView = horaExtraView.newTable(listaPeriodosHoraExtra);
		horaExtraView.setListEntries(tableView.getListEntries());
		horaExtraView.setListExits(tableView.getListExits());
		return horaExtraView.getTable();
	}

}
