package dad.javafx.enviaremail;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertEmail {

	public static void mostrarAlert(AlertType tipoAlerta, String cabecera, String contenido) {
		Alert alert = new Alert(tipoAlerta);
		alert.setTitle("Enviar email");
		alert.setHeaderText(cabecera);
		alert.setContentText(contenido);

		alert.showAndWait();
	}

}
