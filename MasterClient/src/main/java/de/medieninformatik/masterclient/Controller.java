package de.medieninformatik.masterclient;


import de.medieninformatik.movie.Movie;
import de.medieninformatik.movie.ObjectFactory;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author <Daniel Adam> <m29062>
 * date: 2022-02-11
 * Hausarbeit Programmierung 3
 * Beschreibung der Klasse: Controller
 * Stellt den Controller im Model-View-Controller Entwurfsmuster des Masterclienten dar. Dient der Verbindung der gewonnenen
 * Daten aus dem Model (FilmClient) und deren visueller Darstellung der Elemente im MasterView
 */
public class Controller implements Initializable {
    private final FilmClient filmClient;
    private final Stage stage;
    private final String BASE_URI = "http://localhost:8080/rest";
    private int curr_seitenzahl = 0;//0 wichtig für die Berechnung und Zuweisung der Filme im Grid
    private int max_seitenzahl;
    private int moviecount;
    private final ObjectFactory objectFactory;

    @FXML private Button forward;
    @FXML private Button backwards;
    @FXML private GridPane filmgrid;
    @FXML private Label id1;
    @FXML private Label title1;
    @FXML private Label year1;
    @FXML private Label genre1;
    @FXML private Label reg1;
    @FXML private Label appr1;
    @FXML private Label id2;
    @FXML private Label title2;
    @FXML private Label year2;
    @FXML private Label genre2;
    @FXML private Label reg2;
    @FXML private Label appr2;
    @FXML private Label id3;
    @FXML private Label title3;
    @FXML private Label year3;
    @FXML private Label genre3;
    @FXML private Label reg3;
    @FXML private Label appr3;
    @FXML private Label id4;
    @FXML private Label title4;
    @FXML private Label year4;
    @FXML private Label genre4;
    @FXML private Label reg4;
    @FXML private Label appr4;
    @FXML private Label id5;
    @FXML private Label title5;
    @FXML private Label year5;
    @FXML private Label genre5;
    @FXML private Label reg5;
    @FXML private Label appr5;
    @FXML private Label id6;
    @FXML private Label title6;
    @FXML private Label year6;
    @FXML private Label genre6;
    @FXML private Label reg6;
    @FXML private Label appr6;
    @FXML private Label id7;
    @FXML private Label title7;
    @FXML private Label year7;
    @FXML private Label genre7;
    @FXML private Label reg7;
    @FXML private Label appr7;
    @FXML private Label id8;
    @FXML private Label title8;
    @FXML private Label year8;
    @FXML private Label genre8;
    @FXML private Label reg8;
    @FXML private Label appr8;
    @FXML private Label id9;
    @FXML private Label title9;
    @FXML private Label year9;
    @FXML private Label genre9;
    @FXML private Label reg9;
    @FXML private Label appr9;
    @FXML private Label sitelabel;
    @FXML private Button createMovie;
    @FXML private Button delete1;
    @FXML private Button delete2;
    @FXML private Button delete3;
    @FXML private Button delete4;
    @FXML private Button delete5;
    @FXML private Button delete6;
    @FXML private Button delete7;
    @FXML private Button delete8;
    @FXML private Button delete9;
    @FXML private TextField namefield;
    @FXML private TextField yearfield;
    @FXML private TextField genfield;
    @FXML private TextField regfield;
    @FXML private TextField apprfield;
    @FXML private Label newID;

    /**
     * Konstruktor für den Controller Stage, Filmclient und eine ObjectFactory werden zugewiesen
     * @param stage Bühne für visuelle Umsetzung
     */
    public Controller(Stage stage){
        this.stage = stage;
        this.filmClient = new FilmClient(BASE_URI);
        this.objectFactory = new ObjectFactory();
    }



    /**
     * Initialisiert Buttons und kleinere Variabeln und lädt die ersten 9 Filme aus dem Clienten und setzt diese visuell um.
     * Wird bei Start der Applikation automatisch aufgerufen
     * @param location Location der Daten für den Server
     * @param resources Ressourcen für den View
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        forward.setOnAction(this::forwardAction);
        backwards.setOnAction(this::backwardsAction);
        delete1.setOnAction(this::deleteAction1);
        delete2.setOnAction(this::deleteAction2);
        delete3.setOnAction(this::deleteAction3);
        delete4.setOnAction(this::deleteAction4);
        delete5.setOnAction(this::deleteAction5);
        delete6.setOnAction(this::deleteAction6);
        delete7.setOnAction(this::deleteAction7);
        delete8.setOnAction(this::deleteAction8);
        delete9.setOnAction(this::deleteAction9);
        createMovie.setOnAction(this::newMovieAction);
        moviecount = filmClient.getMovieCount("/movies");
        max_seitenzahl = (moviecount + 9-1)/9;
        setSitelabel();
        loadMovies();
    }
    /**
     * wird der Button für das nach vorne gehen ausgewählt wird die Seitenzahl um 1 erhöht und sämtliche Labels und
     * Filme im View werden neu geladen. if-Statement verhindert das aufrufen von Seiten oberhalb der maximalen Seitenzahl
     * @param event ActionEvent
     */
    public void forwardAction(final ActionEvent event){
        curr_seitenzahl++;
        if(curr_seitenzahl >= max_seitenzahl)curr_seitenzahl = max_seitenzahl-1;
        setSitelabel();
        loadMovies();
    }

    /**
     * wird der Button für das zurück gehen ausgewählt, wird die Seitenzahl um 1 verringert und sämtliche Labels und
     * Filme werden neu gesetzt. if-Statement verhindert das gehen mit der Seitenzahl unter 0;
     * @param event ActionEvent
     */
    public void backwardsAction(final ActionEvent  event){
        curr_seitenzahl--;
        if(curr_seitenzahl < 1) curr_seitenzahl = 0;
        setSitelabel();
        loadMovies();
    }

    /**
     * abhängig von der aktuellen Anzahl von Filmen in der Datenbank wird entweder ein sog. "Nullmovie" vergeben oder
     * die Daten werden aus der Datenbank geholt. Der Index wird dabei mit Multiplen von 9 berechnet.
     * Ist der Movie im Server besetzt wird anhand des Index des Filmes das Feld berechnet. Ein Switch sorgt für die
     * Zuweisung an die richtige Zuweisungsmethode.
     */
    public void loadMovies(){
        Movie movie;
        try {
            for(int i = 1; i <= 9; i++){
                if(moviecount < i + (curr_seitenzahl * 9)){
                    movie = filmClient.nullMovie(i + (curr_seitenzahl * 9));
                }else{
                    movie = filmClient.get("/movies/" + (i + (curr_seitenzahl * 9)));
                }

                switch (movie.getId() - (curr_seitenzahl * 9)){
                    case 1: setMovie1(movie);
                        break;
                    case 2: setMovie2(movie);
                        break;
                    case 3: setMovie3(movie);
                        break;
                    case 4: setMovie4(movie);
                        break;
                    case 5: setMovie5(movie);
                        break;
                    case 6: setMovie6(movie);
                        break;
                    case 7: setMovie7(movie);
                        break;
                    case 8: setMovie8(movie);
                        break;
                    case 9: setMovie9(movie);
                        break;
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        newID.setText(String.valueOf(moviecount+1));
    }

    /**
     * setzt alle Felder des ersten Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie1(Movie movie){
        if(movie.getMovieName() == ""){
            id1.setText("");
            title1.setText("");
            year1.setText("");
            genre1.setText("");
            reg1.setText("");
            appr1.setText("");
            delete1.setDisable(true);
        }else{
            id1.setText(String.valueOf(movie.getId()));
            title1.setText(movie.getMovieName());
            year1.setText("Jahr: " + movie.getYear());
            genre1.setText("Genre: " + movie.getGenre());
            reg1.setText("Regisseur: " + movie.getRegisseur());
            appr1.setText("Bewertung: " + movie.getAppraial());
            delete1.setDisable(false);
        }
    }

    /**
     * setzt alle Felder des zweiten Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie2(Movie movie){
        if(movie.getMovieName() == ""){
            id2.setText("");
            title2.setText("");
            genre2.setText("");
            year2.setText("");
            reg2.setText("");
            appr2.setText("");
            delete2.setDisable(true);
        }else{
            id2.setText(String.valueOf(movie.getId()));
            title2.setText(movie.getMovieName());
            year2.setText("Jahr: " + movie.getYear());
            genre2.setText("Genre: " + movie.getGenre());
            reg2.setText("Regisseur: " + movie.getRegisseur());
            appr2.setText("Bewertung: " + movie.getAppraial());
            delete2.setDisable(false);
        }
    }

    /**
     * setzt alle Felder des dritten Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie3(Movie movie){
        if(movie.getMovieName() == ""){
            id3.setText("");
            title3.setText("");
            year3.setText("");
            genre3.setText("");
            reg3.setText("");
            appr3.setText("");
            delete3.setDisable(true);
        }else{
            id3.setText(String.valueOf(movie.getId()));
            title3.setText(movie.getMovieName());
            year3.setText("Jahr: " + movie.getYear());
            genre3.setText("Genre: " + movie.getGenre());
            reg3.setText("Regisseur: " + movie.getRegisseur());
            appr3.setText("Bewertung: " + movie.getAppraial());
            delete3.setDisable(false);
        }
    }

    /**
     * setzt alle Felder des vierten Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie4(Movie movie){
        if(movie.getMovieName() == ""){
            id4.setText("");
            title4.setText("");
            year4.setText("");
            genre4.setText("");
            reg4.setText("");
            appr4.setText("");
            delete4.setDisable(true);
        }else{
            id4.setText(String.valueOf(movie.getId()));
            title4.setText(movie.getMovieName());
            year4.setText("Jahr: " + movie.getYear());
            genre4.setText("Genre: " + movie.getGenre());
            reg4.setText("Regisseur: " + movie.getRegisseur());
            appr4.setText("Bewertung: " + movie.getAppraial());
            delete4.setDisable(false);
        }
    }

    /**
     * setzt alle Felder des fünften Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie5(Movie movie){
        if(movie.getMovieName() == ""){
            id5.setText("");
            title5.setText("");
            year5.setText("");
            genre5.setText("");
            reg5.setText("");
            appr5.setText("");
            delete5.setDisable(true);
        }else{
            id5.setText(String.valueOf(movie.getId()));
            title5.setText(movie.getMovieName());
            year5.setText("Jahr: " + movie.getYear());
            genre5.setText("Genre: " + movie.getGenre());
            reg5.setText("Regisseur: " + movie.getRegisseur());
            appr5.setText("Bewertung: " + movie.getAppraial());
            delete5.setDisable(false);
        }
    }

    /**
     * setzt alle Felder des sechsten Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie6(Movie movie){
        if(movie.getMovieName() == ""){
            id6.setText("");
            title6.setText("");
            year6.setText("");
            genre6.setText("");
            reg6.setText("");
            appr6.setText("");
            delete6.setDisable(true);
        }else{
            id6.setText(String.valueOf(movie.getId()));
            title6.setText(movie.getMovieName());
            year6.setText("Jahr: " + movie.getYear());
            genre6.setText("Genre: " + movie.getGenre());
            reg6.setText("Regisseur: " + movie.getRegisseur());
            appr6.setText("Bewertung: " + movie.getAppraial());
            delete6.setDisable(false);
        }
    }

    /**
     * setzt alle Felder des siebten Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie7(Movie movie){
        if(movie.getMovieName() == ""){
            id7.setText("");
            title7.setText("");
            year7.setText("");
            genre7.setText("");
            reg7.setText("");
            appr7.setText("");
            delete7.setDisable(true);
        }else{
            id7.setText(String.valueOf(movie.getId()));
            title7.setText(movie.getMovieName());
            year7.setText("Jahr: " + movie.getYear());
            genre7.setText("Genre: " + movie.getGenre());
            reg7.setText("Regisseur: " + movie.getRegisseur());
            appr7.setText("Bewertung: " + movie.getAppraial());
            delete7.setDisable(false);
        }



    }

    /**
     * setzt alle Felder des achten Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie8(Movie movie){
        if(movie.getMovieName() == ""){
            id8.setText("");
            title8.setText("");
            year8.setText("");
            genre8.setText("");
            reg8.setText("");
            appr8.setText("");
            delete8.setDisable(true);
        }else{
            id8.setText(String.valueOf(movie.getId()));
            title8.setText(movie.getMovieName());
            year8.setText("Jahr: " + movie.getYear());
            genre8.setText("Genre: " + movie.getGenre());
            reg8.setText("Regisseur: " + movie.getRegisseur());
            appr8.setText("Bewertung: " + movie.getAppraial());
            delete8.setDisable(false);
        }


    }

    /**
     * setzt alle Felder des neunten Filmes auf Werte eines übergebenen Filmes
     * Falls dieser ein Nullmovie ist werden die Labels und die Läschungsbuttons abgeschaltet
     * @param movie zu setzender Film
     */
    private void setMovie9(Movie movie){
        if(movie.getMovieName() == ""){
            id9.setText("");
            title9.setText("");
            year9.setText("");
            genre9.setText("");
            reg9.setText("");
            appr9.setText("");
            delete9.setDisable(true);
        }else {

            id9.setText(String.valueOf(movie.getId()));
            title9.setText(movie.getMovieName());
            year9.setText("Jahr: " + movie.getYear());
            genre9.setText("Genre: " + movie.getGenre());
            reg9.setText("Regisseur: " + movie.getRegisseur());
            appr9.setText("Bewertung: " + movie.getAppraial());
            delete9.setDisable(false);
        }

    }

    /**
     * setzt den Wert des Seitenlabels basierend auf der maximalen Seitenzahl und der momentanen Seitenzahl.
     * Da in diesem Client aktiv Löschungen stattfinden, wird zur Sicherheit die maximale Seitenzahl bei jedem Aufruf
     * neu berechnet.
     */
    private void setSitelabel(){
        max_seitenzahl = (moviecount + 9-1)/9;
        sitelabel.setText("Seite " + (curr_seitenzahl+1) + " von " + max_seitenzahl);
    }

    /**
     * setzt die Läschungsaktion für den ersten Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction1(final ActionEvent event){
        filmClient.delete("/movies/" + id1.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();

    }

    /**
     * setzt die Läschungsaktion für den zweiten Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction2(final ActionEvent event){
        filmClient.delete("/movies/" + id2.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

    /**
     * setzt die Läschungsaktion für den dritten Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction3(final ActionEvent event){
        filmClient.delete("/movies/" + id3.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

    /**
     * setzt die Läschungsaktion für den vierten Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction4(final ActionEvent event){
        filmClient.delete("/movies/" + id4.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

    /**
     * setzt die Läschungsaktion für den fünften Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction5(final ActionEvent event){
        filmClient.delete("/movies/" + id5.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

    /**
     * setzt die Läschungsaktion für den sechsten Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction6(final ActionEvent event){
        filmClient.delete("/movies/" + id6.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

    /**
     * setzt die Läschungsaktion für den siebten Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction7(final ActionEvent event){
        filmClient.delete("/movies/" + id7.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

    /**
     * setzt die Läschungsaktion für den achten Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction8(final ActionEvent event){
        filmClient.delete("/movies/" + id8.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

    /**
     * setzt die Läschungsaktion für den neunten Film in der aktuellen Liste. Holt sich den Wert des Indexlabels
     * und gibt diesen an die Clienten zum Löschen weiter. Danach wird der View neu geladen.
     * @param event ActionEvent
     */
    private void deleteAction9(final ActionEvent event){
        filmClient.delete("/movies/" + id9.getText());
        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

    /**
     * setzt die Aktion für das Hinzufügen eines neuen Filmes auf Knopfdruck.
     * Die Daten werden aus einem Label und vom Nutzer beschreibbaren Textfeldern geholt. Anschließend dem Client
     * übergeben und dieser postet sie dann an den Server. Ist dies erfolgreich so wird der View neu geladen und der
     * aktuelle moviecount aktualisiert
     * @param event ActionEvent
     */
    private void newMovieAction(final ActionEvent event){
        Movie movie = objectFactory.createMovie();
        movie.setId(Integer.parseInt(newID.getText()));
        movie.setMovieName(namefield.getText());
        movie.setYear(Integer.parseInt(yearfield.getText()));
        movie.setGenre(genfield.getText());
        movie.setRegisseur(regfield.getText());
        movie.setAppraial(apprfield.getText());

        try {
            filmClient.post("/movies", movie);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        moviecount = filmClient.getMovieCount("/movies");
        setSitelabel();
        loadMovies();
    }

}
