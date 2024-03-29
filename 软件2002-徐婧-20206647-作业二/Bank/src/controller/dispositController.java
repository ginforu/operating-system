package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import application.disposit;
import application.mainview;
import dao.UserDAO;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class dispositController {
	@FXML
	private AnchorPane root;
	@FXML
	private TextField money;
	@FXML
	private Button confirmbtn;
	@FXML
	private Button backbtn;
	
	// Event Listener on Button[#confirmbtn].onAction
	@FXML
	public void confirm(ActionEvent event) {
		// TODO Autogenerated
		String t = money.getText();
		boolean flag = true;
		for(int i=0;i<t.length();i++) {
			if(t.charAt(i)=='.') {
				flag = false;
				break;
			}
			if((t.charAt(i)>'9'||t.charAt(i)<'0')&&t.charAt(i)!='.') {
				flag = false;
				break;
			}
		}
		if(flag==false) {
			JOptionPane.showMessageDialog(null, "请输入数字", null, JOptionPane.ERROR_MESSAGE);
			return ;
		}
		float m = Float.valueOf(t);
		
		
		//进行存款
		Thread th1 =new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized(this) {
					UserDAO.dispositmoney(m, loginController.ac);	
					JOptionPane.showMessageDialog(null, "存款成功", null, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		th1.setPriority(10);
		th1.start();
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
		mainview r = new mainview();
		r.start(new Stage());
	}
	// Event Listener on Button[#backbtn].onAction
	@FXML
	public void back(ActionEvent event) {
		// TODO Autogenerated
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
		mainview r = new mainview();
		r.start(new Stage());
	}
}
