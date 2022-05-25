package controlePonto.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controlePonto.controller.HoraExtraController;

public class HoraExtraView extends TabelaView {

	JanelaPrincipal context;
	HoraExtraController horaExtraController;

	public HoraExtraView(Container container, JanelaPrincipal context) {
		addFourthTable(container);
		activeButtons();
		this.context = context;
		this.horaExtraController = new HoraExtraController(context);
	}

	public void addFourthTable(Container container) {
		JPanel firstPanel = new JPanel(new GridLayout(2, 1));
		JPanel secondPanel = new JPanel(new GridLayout(2, 1));
		JPanel panelTitle = addTitle("HORA EXTRA");
		JPanel panelButtons = addButtons();
		JPanel panelTable = addTable();
		secondPanel.add(panelTitle);
		secondPanel.add(panelButtons);
		firstPanel.add(secondPanel);
		firstPanel.add(panelTable);
		container.add(firstPanel);
	}

	public JPanel addButtons() {
		JPanel panelButtons = new JPanel(new FlowLayout());
		JButton buttonSub = new JButton();
		buttonSub.setText("Subtração");
		JButton buttonClear = new JButton();
		buttonClear.setText("Limpar");
		panelButtons.add(buttonSub);
		panelButtons.add(buttonClear);
		getListButtons().add(buttonSub);
		getListButtons().add(buttonClear);
		return panelButtons;
	}

	public void activeButtons() {
		if (this.getListButtons().size() == 2) {
			this.getListButtons().get(0).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					horaExtraController.subHoraExtra();
				}

			});
			this.getListButtons().get(1).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					clearTable();
				}
			});

		}
	}

}
