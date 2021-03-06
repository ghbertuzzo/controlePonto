package controlePonto.model;

import java.util.ArrayList;
import java.util.List;

import controlePonto.view.MarcacoesFeitasView;

public class MarcacoesFeitas {
	List<Periodo> listOfPeriods;

	public MarcacoesFeitas(MarcacoesFeitasView marcacoesFeitas) {
		super();
		this.listOfPeriods = new ArrayList<Periodo>();
		for(int i = 0; i<marcacoesFeitas.getListEntries().size();i++) {
			this.listOfPeriods.add(new Periodo(marcacoesFeitas.getListEntries().get(i), marcacoesFeitas.getListExits().get(i)));
		}
	}
}
