package de.medieninformatik.masterclient;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
/**
 * @author <Daniel Adam> <m29062>
 * date: 2022-02-11
 * Hausarbeit Programmierung 3
 * Beschreibung der Klasse: MasterView
 * Beschreibt den View im Model-View-Controller Entwurfsmuster. Lädt eine fxml-Datei aus den Ressourcen und zeigt diese an.
 */
public class MasterView extends Application {
    /**
     * Erstellt einen neuen View basierend auf den Daten, welche von einer fxml Datei ausgelesen wurden und zeigt diese an.
     * @param primaryStage primaryStage
     * @throws Exception Fehler abfangen
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        final URL fxmlURL = this.getClass().getResource("/masterclient.fxml");
        final FXMLLoader loader = new FXMLLoader(fxmlURL);
        loader.setController(new Controller(primaryStage));
        final Parent root = loader.load();

        primaryStage.setTitle("Masterclient - Daniels dolle Datenbankapplication");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}