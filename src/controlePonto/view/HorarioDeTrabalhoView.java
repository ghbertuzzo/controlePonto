package controlePonto.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlePonto.db.DAOHistorico;
import controlePonto.model.Historico;

public class HorarioDeTrabalhoView extends TabelaView {
	
	private List<JTextField> listTextFieldsData;

	public HorarioDeTrabalhoView(Container container) {
		this.listTextFieldsData = new ArrayList<JTextField>();
		addFirstTable(container);
		activeButtons();
	}	

	public List<JTextField> getListTextFieldsData() {
		return listTextFieldsData;
	}

	public void setListTextFieldsData(List<JTextField> listTextFieldsData) {
		this.listTextFieldsData = listTextFieldsData;
	}

	public void addFirstTable(Container container) {
		JPanel firstPanel = new JPanel(new GridLayout(2, 1));
		JPanel secondPanel = new JPanel(new GridLayout(4, 1));
		JPanel panelData = addDataFields();
		JPanel panelTitle = addTitle("HORÁRIO DE TRABALHO");
		JPanel panelEditTexts = addEditTexts();
		JPanel panelButtons = addButtons();
		JPanel panelTable = addTable();
		secondPanel.add(panelData);
		secondPanel.add(panelTitle);
		secondPanel.add(panelEditTexts);
		secondPanel.add(panelButtons);
		firstPanel.add(secondPanel);
		firstPanel.add(panelTable);
		container.add(firstPanel);
	}

	private JPanel addDataFields() {
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		firstPanel.add(secondPanel);
		JLabel labelDate = new JLabel("Data");
		JLabel label = new JLabel("/");
		JLabel label2 = new JLabel("/");
		JTextField textFieldData1 = new JTextField(2);
		JTextField textFieldData2 = new JTextField(2);
		JTextField textFieldData3 = new JTextField(2);
		JButton btnSalvar = new JButton();
		btnSalvar.setText("Salvar");
		JButton btnCarregar = new JButton();
		btnCarregar.setText("Carregar");
		getListButtons().add(btnSalvar);
		getListButtons().add(btnCarregar);
		secondPanel.add(labelDate);
		secondPanel.add(textFieldData1);
		secondPanel.add(label);
		secondPanel.add(textFieldData2);
		secondPanel.add(label2);
		secondPanel.add(textFieldData3);
		secondPanel.add(btnSalvar);
		secondPanel.add(btnCarregar);
		getListTextFieldsData().add(textFieldData1);
		getListTextFieldsData().add(textFieldData2);
		getListTextFieldsData().add(textFieldData3);
		return firstPanel;
	}

	public JPanel addEditTexts() {
		String[] labels = { "Entrada", "Saída" };
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		firstPanel.add(secondPanel);
		JLabel labelEntrie = new JLabel(labels[0], JLabel.TRAILING);
		JTextField textField = new JTextField(2);
		JTextField textField2 = new JTextField(2);
		JLabel label2 = new JLabel(":");
		JLabel labelExit = new JLabel(labels[1], JLabel.TRAILING);
		JTextField textField3 = new JTextField(2);
		JTextField textField4 = new JTextField(2);
		JLabel label4 = new JLabel(":");
		secondPanel.add(labelEntrie);
		secondPanel.add(textField);
		secondPanel.add(label2);
		secondPanel.add(textField2);
		secondPanel.add(labelExit);
		secondPanel.add(textField3);
		secondPanel.add(label4);
		secondPanel.add(textField4);
		getListTextFields().add(textField);
		getListTextFields().add(textField2);
		getListTextFields().add(textField3);
		getListTextFields().add(textField4);
		return firstPanel;
	}

	public JPanel addButtons() {
		JPanel panelButtons = new JPanel(new FlowLayout());
		JButton buttonAdd = new JButton();
		buttonAdd.setText("Adicionar");
		JButton buttonRemove = new JButton();
		buttonRemove.setText("Remover");
		JButton buttonClear = new JButton();
		buttonClear.setText("Limpar");
		panelButtons.add(buttonAdd);
		panelButtons.add(buttonRemove);
		panelButtons.add(buttonClear);
		getListButtons().add(buttonAdd);
		getListButtons().add(buttonRemove);
		getListButtons().add(buttonClear);
		return panelButtons;
	}

	public void activeButtons() {
		if (this.getListButtons().size() == 5) {
			this.getListButtons().get(0).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						saveDB();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			this.getListButtons().get(1).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					loadDB();
				}
			});
			this.getListButtons().get(2).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					addEntries();
				}
			});
			this.getListButtons().get(3).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					removeList();
				}
			});
			this.getListButtons().get(4).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					clearTable();
				}
			});
		}
	}
	
	public void saveDB() throws SQLException {
		if(validDataFields()) {
			System.out.println("Passou validação de data!");
			// implementar outras validações!
			
			if(hasARegister()) { // já possui registro nesta data?
				JOptionPane.showConfirmDialog(null,"Este dia já possui registros. Deseja sobrescrever as informações?");
			}
		}
	}
	
	private boolean hasARegister() throws SQLException {
		DAOHistorico daohistorico = new DAOHistorico();
		String date = getDateFormatted();
		Historico historico = daohistorico.getHistoricoByDate(date);
		if(historico!=null)
			return true;
		return false;
	}

	private String getDateFormatted() {
		return new String("20"+this.getListTextFieldsData().get(2).getText()+"-"+this.getListTextFieldsData().get(1).getText()+"-"+this.getListTextFieldsData().get(0).getText());
	}

	public void loadDB() {
		if(validDataFields()) {
			System.out.println("Passou validação de data!");
		}
	}

	public void addEntries() {
		if (validateFields()) {
			addList();
		}
	}
	
	public Boolean validDataFields() {
		for(int i = 0; i < this.getListTextFieldsData().size(); i++) {
			if(this.getListTextFieldsData().get(i).getText().length()!=2) {	
				JOptionPane.showMessageDialog(null, "Entrada inválida: informe 2 digitos!");
				this.getListTextFieldsData().get(i).requestFocus();
				return false;
			}
			if(!this.getListTextFieldsData().get(i).getText().matches("[+-]?\\d*(\\.\\d+)?")) {
				JOptionPane.showMessageDialog(null, "Entrada inválida: Informe 2 digitos numéricos!");
				this.getListTextFieldsData().get(i).requestFocus();
				return false;
			}
			if(i == 0) {
				if(Integer.parseInt(this.getListTextFieldsData().get(i).getText())>31) {
					JOptionPane.showMessageDialog(null, "Entrada inválida: informe um dia válido!");
					this.getListTextFieldsData().get(i).requestFocus();
					return false;
				}
			}
			if(i == 1) {
				if(Integer.parseInt(this.getListTextFieldsData().get(i).getText())>12) {
					JOptionPane.showMessageDialog(null, "Entrada inválida: informe um mês válido!");
					this.getListTextFieldsData().get(i).requestFocus();
					return false;
				}
			}			
			if(i == 2) {
				if(Integer.parseInt(this.getListTextFieldsData().get(i).getText())>22) {
					JOptionPane.showMessageDialog(null, "Entrada inválida: informe um ano válido!");
					this.getListTextFieldsData().get(i).requestFocus();
					return false;
				}
			}
		}
		return true;
	}

	public Boolean validFieldsHoursAndMinutes(int i, JTextField field) {
		if (i % 2 == 0) {
			if (Integer.parseInt(field.getText()) > 23) {
				JOptionPane.showMessageDialog(null, "Entrada inválida: informe a hora corretamente!");
				field.requestFocus();
				return false;
			}
		} else {
			if (Integer.parseInt(field.getText()) > 59) {
				JOptionPane.showMessageDialog(null, "Entrada inválida: informe os minutos corretamente!");
				field.requestFocus();
				return false;
			}
		}
		return true;
	}

	public Boolean validateFields() {
		if (this.getListEntries().size() > 2) {
			JOptionPane.showMessageDialog(null, "Não é possível adicionar mais de 3 entradas!");
			return false;
		}

		for (int i = 0; i < this.getListTextFields().size(); i++) {
			if (this.getListTextFields().get(i).getText().length() != 2) {
				JOptionPane.showMessageDialog(null, "Entrada inválida: informe 2 digitos!");
				this.getListTextFields().get(i).requestFocus();
				return false;
			}
			if (!this.getListTextFields().get(i).getText().matches("[+-]?\\d*(\\.\\d+)?")) {
				JOptionPane.showMessageDialog(null, "Entrada inválida: Informe 2 digitos numéricos!");
				this.getListTextFields().get(i).requestFocus();
				return false;
			}
			if (!validFieldsHoursAndMinutes(i, this.getListTextFields().get(i))) {
				return false;
			}
		}
		return true;
	}

	public void removeList() {
		int index = this.getTable().getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(null, "Selecione a linha que deseja excluir");
		} else {
			this.getListEntries().remove(index);
			this.getListExits().remove(index);
			renderTable();
		}
	}

	public void addList() {
		this.getListEntries().add(LocalTime.of(Integer.parseInt(this.getListTextFields().get(0).getText().trim()),
				Integer.parseInt(this.getListTextFields().get(1).getText().trim())));
		this.getListExits().add(LocalTime.of(Integer.parseInt(this.getListTextFields().get(2).getText().trim()),
				Integer.parseInt(this.getListTextFields().get(3).getText().trim())));
		renderTable();
		clearInputs();
		this.getListTextFields().get(0).requestFocus();
	}

	public void clearInputs() {
		for (JTextField entrada : this.getListTextFields()) {
			entrada.setText("");
		}
	}

}
