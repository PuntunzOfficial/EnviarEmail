package dad.javafx.enviaremail;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller implements Initializable {

	Model model = new Model();

	public Controller() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/emailView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.messageProperty().bindBidirectional(messageArea.textProperty());
		model.serverProperty().bindBidirectional(serverField.textProperty());
		model.portProperty().bindBidirectional(portField.textProperty());
		model.emailFromProperty().bindBidirectional(emailFromField.textProperty());
		model.passwordProperty().bindBidirectional(passwordField.textProperty());
		model.emailToProperty().bindBidirectional(emailToField.textProperty());
		model.subjectProperty().bindBidirectional(subjectField.textProperty());
		model.connectionProperty().bindBidirectional(connectionCheck.selectedProperty());

	}

	// view
	public HBox getView() {
		return root;
	}

	@FXML
	private HBox root;

	@FXML
	private GridPane fieldsPane;

	@FXML
	private TextArea messageArea;

	@FXML
	private TextField serverField;

	@FXML
	private TextField portField;

	@FXML
	private TextField emailFromField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField emailToField;

	@FXML
	private TextField subjectField;

	@FXML
	private CheckBox connectionCheck;

	@FXML
	private VBox buttonsBox;

	@FXML
	private Button sendButton;

	@FXML
	private Button clearButton;

	@FXML
	private Button closeButton;

	@FXML
	void onSendAction(ActionEvent event) {
		
		Email email = new SimpleEmail();
		email.setHostName(model.getServer());
		email.setSmtpPort(Integer.parseInt(model.getPort()));
		email.setAuthenticator(new DefaultAuthenticator(model.getEmailFrom(), model.getPassword()));
		email.setSSLOnConnect(model.isConnection());
		
		
		Task<String> enviar = new Task<String>() {
			@Override
			protected String call() throws Exception {
				Thread.sleep(5000L);
				
				email.setFrom(model.getEmailFrom());
				email.setSubject(model.getSubject());
				email.setMsg(model.getMessage());
				email.addTo(model.getEmailTo());
				return email.send();
								
			}
		};
		
				
		enviar.setOnSucceeded(event1 -> {
			AlertEmail.mostrarAlert(AlertType.INFORMATION, "Mensaje enviado con éxito a '" + model.getEmailTo() + "'.","");

			emailToField.clear();
			subjectField.clear();
			messageArea.clear();;
		});
		enviar.setOnFailed(event1 -> {
			AlertEmail.mostrarAlert(AlertType.ERROR, "No se pudo enviar el email."+ "'.","");
			
		});
		new Thread(enviar).start();
		
		

	}
	

	@FXML
	void onClearAction(ActionEvent event) {
		serverField.clear();
		portField.clear();
		model.setConnection(false);
		emailFromField.clear();
		passwordField.clear();
		emailToField.clear();
		subjectField.clear();
		messageArea.clear();
	}

	@FXML
	void onCloseAction(ActionEvent event) {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();

	}

}
