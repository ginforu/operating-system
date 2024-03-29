package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import application.emroll;
import application.login;
import application.mainview;
import bean.User;
import dao.UserDAO;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class emrollController {
	@FXML
	private AnchorPane root;
	@FXML
	private TextField account;
	@FXML
	private TextField password;
	@FXML
	private Button registerBtn;
	@FXML
	private Button backbtn;

	// Event Listener on Button[#registerBtn].onAction
	@FXML
	public void register(ActionEvent event) {
		// TODO Autogenerated
		if(account.getText().isEmpty()==true||password.getText().isEmpty()==true) {
			JOptionPane.showMessageDialog(null, "�˺������벻��Ϊ��", null, JOptionPane.ERROR_MESSAGE);
			return ;
		}
		User u = new User(account.getText(),password.getText(),0);
		boolean ishas = UserDAO.find(account.getText());
		if(ishas==false) {
			UserDAO.savefile(u);
			JOptionPane.showMessageDialog(null, "ע��ɹ�", null, JOptionPane.OK_OPTION);
			Stage stage = (Stage) root.getScene().getWindow();
			stage.close();
			login r = new login();
			r.start(new Stage());
		}else {
			JOptionPane.showMessageDialog(null, "�˺��Ѵ���", null, JOptionPane.ERROR_MESSAGE);
		}
		return ;
	}
	// Event Listener on Button[#backbtn].onAction
	@FXML
	public void back(ActionEvent event) {
		// TODO Autogenerated
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
		login r = new login();
		r.start(new Stage());
	}
}
