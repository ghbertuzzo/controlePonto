package controlePonto.controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JTextField;
import controlePonto.db.DAOHistorico;
import controlePonto.model.Historico;
import controlePonto.model.Periodo;
import controlePonto.view.JanelaPrincipal;

public class HistoricoController {
	
	private DAOHistorico daoHistorico;
	
	public HistoricoController() throws SQLException {
		this.daoHistorico = new DAOHistorico();
	}

	public boolean hasARegister(List<JTextField> listJTextField) throws SQLException {
		String date = getDateFormatted(listJTextField);
		Historico historico = this.daoHistorico.getHistoricoByDate(date);
		if(historico!=null)
			return true;
		return false;
	}
	
	public Historico returnHistoricoHasARegister(List<JTextField> listJTextField) throws SQLException {
		String date = getDateFormatted(listJTextField);
		Historico historico = this.daoHistorico.getHistoricoByDate(date);
		if(historico!=null)
			return historico;
		return null;
	}

	private String getDateFormatted(List<JTextField> listJTextField) {
		return new String("20"+listJTextField.get(2).getText()+"-"+listJTextField.get(1).getText()+"-"+listJTextField.get(0).getText());
	}
	
	public String getDateFormattedView(List<JTextField> listJTextField) {
		return new String(listJTextField.get(0).getText()+"/"+listJTextField.get(1).getText()+"/20"+listJTextField.get(2).getText());
	}

	public void insert(JanelaPrincipal context) throws SQLException {
		String date = getDateFormatted(context.listTextFieldsData);
		int idHT = context.htController.saveHorarioDeTrabalho(context.horarioDeTrabalhoView);
		int idMF = context.mfController.saveMarcacoesFeitas(context.marcacoesFeitasView);
		int idHE = context.heController.saveHoraExtra(context.horaExtraView);
		int idAT = context.atController.saveAtrasos(context.atrasoView);
		this.daoHistorico.insert(date, idHT, idMF, idHE, idAT);	
	}

	public void update(JanelaPrincipal context) throws SQLException {
		String date = getDateFormatted(context.listTextFieldsData);
		int idAntigo = this.daoHistorico.getIdHistoricoByDate(date);
		this.daoHistorico.delete(idAntigo);
		int idHT = context.htController.saveHorarioDeTrabalho(context.horarioDeTrabalhoView);
		int idMF = context.mfController.saveMarcacoesFeitas(context.marcacoesFeitasView);
		int idHE = context.heController.saveHoraExtra(context.horaExtraView);
		int idAT = context.atController.saveAtrasos(context.atrasoView);
		this.daoHistorico.insert(date, idHT, idMF, idHE, idAT);	
	}

	public void load(Historico historico, JanelaPrincipal context) throws SQLException {
		loadHorarioDeTrabalho(historico, context);
		loadMarcacoesFeitas(historico,context);
		loadHoraExtra(historico,context);
		loadAtrasos(historico,context);				
	}

	private void loadAtrasos(Historico historico, JanelaPrincipal context) throws SQLException {
		context.atrasoView.clearTable();	
		List<Periodo> listPeriodsAT = context.atController.getPeriods(historico.getId_at());;
		for(Periodo periodo: listPeriodsAT) {
			context.atrasoView.getListEntries().add(periodo.getEntrada());
			context.atrasoView.getListExits().add(periodo.getSaida());
		}
		context.atrasoView.renderTable();		
	}

	private void loadHoraExtra(Historico historico, JanelaPrincipal context) throws SQLException {
		context.horaExtraView.clearTable();
		List<Periodo> listPeriodsHE = context.heController.getPeriods(historico.getId_he());;
		for(Periodo periodo: listPeriodsHE) {
			context.horaExtraView.getListEntries().add(periodo.getEntrada());
			context.horaExtraView.getListExits().add(periodo.getSaida());
		}
		context.horaExtraView.renderTable();
	}

	private void loadMarcacoesFeitas(Historico historico, JanelaPrincipal context) throws SQLException {
		context.marcacoesFeitasView.clearTable();
		List<Periodo> listPeriodsMF = context.mfController.getPeriods(historico.getId_mf());;
		for(Periodo periodo: listPeriodsMF) {
			context.marcacoesFeitasView.getListEntries().add(periodo.getEntrada());
			context.marcacoesFeitasView.getListExits().add(periodo.getSaida());
		}
		context.marcacoesFeitasView.renderTable();
	}

	private void loadHorarioDeTrabalho(Historico historico, JanelaPrincipal context) throws SQLException {
		context.horarioDeTrabalhoView.clearTable();
		List<Periodo> listPeriodsHT = context.htController.getPeriods(historico.getId_ht());
		for(Periodo periodo: listPeriodsHT) {
			context.horarioDeTrabalhoView.getListEntries().add(periodo.getEntrada());
			context.horarioDeTrabalhoView.getListExits().add(periodo.getSaida());
		}
		context.horarioDeTrabalhoView.renderTable();
	}


}