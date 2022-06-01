package controlePonto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import controlePonto.db.DAOMarcacoesFeitas;
import controlePonto.db.DAOPeriodo;
import controlePonto.db.DAO_Mf_Periodo;
import controlePonto.model.Periodo;
import controlePonto.view.MarcacoesFeitasView;

public class MarcacoesFeitasController {	
	
	private DAOPeriodo daoPeriodo;
	private DAO_Mf_Periodo daomf_periodo;
	
	public MarcacoesFeitasController() throws SQLException {
		this.daoPeriodo = new DAOPeriodo();	
		this.daomf_periodo = new DAO_Mf_Periodo();
	}

	public int saveMarcacoesFeitas(MarcacoesFeitasView marcacoesFeitasView) throws SQLException {
		PeriodoController periodoController = new PeriodoController();
		DAOMarcacoesFeitas daoMarcacoesFeitas = new DAOMarcacoesFeitas();
		//CRIA NOVO REGISTRO DO TIPO MARCACOESFEITAS NO BANCO 
		Integer idMF = daoMarcacoesFeitas.insert();
		//CRIA NOVOS REGISTROS DO TIPO PERIODO (PERIODOS DE MARCACOESFEITAS) NO BANCO
		for(int i = 0; i<marcacoesFeitasView.getTableModel().getRowCount();i++) {
			int idperiodo = this.daoPeriodo.insert(periodoController.localTimeToNumber(marcacoesFeitasView.getListEntries().get(i)), periodoController.localTimeToNumber(marcacoesFeitasView.getListExits().get(i)));
			this.daomf_periodo.insert(idMF, idperiodo);
		}
		return idMF;
	}
	
	//RECEBE UM ID DE MARCACOES FEITA E RETORNA TODOS OS PERIODOS (PERIODOS DE MARCACOES FEITA) DAQUELE DIA
	public List<Periodo> getPeriods(int id_mf) throws SQLException {
		List<Integer> list_idPeriods = this.daomf_periodo.getPeriods(id_mf);
		ArrayList<Periodo> listPeriods = new ArrayList<Periodo>();
		for(Integer idPeriod: list_idPeriods) {
			Periodo periodo = this.daoPeriodo.getPeriodoByID(idPeriod);
			listPeriods.add(periodo);
		}		
		return listPeriods;
	}

}
