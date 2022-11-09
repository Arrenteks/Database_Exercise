package de.medieninformatik.masterclient;


import de.medieninformatik.movie.Movie;
import de.medieninformatik.movie.ObjectFactory;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;
import java.nio.file.Path;
/**
 * @author <Daniel Adam> <m29062>
 * date: 2022-02-11
 * Hausarbeit Programmierung 3
 * Beschreibung der Klasse: FilmClient
 * Stellt die Verbindung zwischen dem Nutzer und dem REST Server dar. Kann Daten holen, löschen und hinzufügen.
 * Liefert darüber hinaus das Model im Model-View-Controller Entwurfsmuster.
 * externe Quellen:
 *         Prof Jürgen K. Singer, PhD/USA, "StudentenREST" - Gradle Projekt
 */
public class FilmClient {
    private final Client client;
    private final String baseURI;
    private final ObjectFactory objectFactory;

    /**
     * Konstruktor für den FilmClient. Zuweisung von grundlegenden Elementen für die Verbindung zum Server und die
     * Datenverarbeitung
     * @param baseURI Basis URI für den REST Server
     */
    public FilmClient(String baseURI) {
        this.baseURI = baseURI;
        this.client = ClientBuilder.newClient();
        this.objectFactory = new ObjectFactory();
    }

    /**
     * Holt eine XML Datei basierend auf einer übergebenen URI vom Server und wandelt diese durch Unmarshalling in einen
     * Film um.
     * @param uri welcher Film soll geholt werden
     * @return movie
     * @throws JAXBException Unmarshalling Exception
     * @throws FileNotFoundException Reader Exception
     */
    public Movie get(String uri) throws JAXBException, FileNotFoundException {
        WebTarget target = getTarget("GET", uri);
        Response response = target.request(MediaType.APPLICATION_XML).get();
        JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File movieFile = response.readEntity(File.class);
        Reader reader = new FileReader(movieFile);
        Movie movie = (Movie) unmarshaller.unmarshal(reader);

        return movie;
    }

    /**
     * Holt die aktuelle Anzahl der Filme als String vom Server und gibt diese als int zurück.
     * @param uri woher solllen die Daten bezogen werden
     * @return Anzahl der Filme
     */
    public int getMovieCount(String uri){
        WebTarget target = getTarget("GET", uri);
        Response response = target.request().get();
        return Integer.parseInt(response.readEntity(String.class));
    }

    /**
     * Erstellt einen neuen Film und sendet diesen an den Server. Er schreibt eine neue xml Datei und marshalled diese.
     * Anschließend wird diese in eine Entity vom Typ File umgewandelt und an den Server geschickt.
     * @param uri Zieladresse
     * @param movie neuer Film
     * @throws JAXBException Marshalling Exception
     * @throws IOException Writer Exception
     */
    public void post(String uri, Movie movie) throws JAXBException, IOException {
        WebTarget target = getTarget("POST", uri);
        JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        Path path = Path.of("../xmlDaten", "anfrage.xml");
        Writer writer = new FileWriter(path.toFile());

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(movie, writer);
        File newMovie = path.toFile();
        Entity<File> entity = Entity.entity(newMovie, MediaType.APPLICATION_XML);
        Response response = target.request().post(entity);
        if(status(response) == 201){
            String location = response.getLocation().toString();
            System.out.println("Location:" + location);
        }
    }

    /**
     * Entfernt einen Film von der Datenbank basierend auf einer uri. Nachdem die Läschungsanfrage verschickt wurde
     * wird der Status ausgegeben.
     * @param uri Ziel der Läschung
     */
    public void delete(String uri){
        WebTarget target = getTarget("DELETE", uri);
        Response response = target.request().delete();
        status(response);
    }

    /**
     * gibt einen Film ohne Daten, aber mit einem Index zurück. Dient der Fehlervermeidung, wenn es in der Datenbank
     * keinen Film mit einem angefragten Index gibt
     * @param id Index des Nullmovies
     * @return nullmovie
     */
    public Movie nullMovie(int id){
        Movie movie = objectFactory.createMovie();
        movie.setId(id);
        movie.setMovieName("");
        movie.setYear(0);
        movie.setGenre("");
        movie.setRegisseur("");
        movie.setAppraial("");
        return movie;
    }
    /**
     * Holt das angezielte Target basierend auf uri und crud vom Server
     * @param crud
     * @param uri angezielte URI
     * @return Target
     */
    private WebTarget getTarget(String crud, String uri) {
        System.out.printf("%n--- %s %s%s%n", crud, baseURI, uri);
        return client.target(baseURI + uri);
    }

    private int status(Response response) {
        int code = response.getStatus();
        String reason = response.getStatusInfo().getReasonPhrase();
        System.out.printf("Status: %d %s%n", code, reason);
        return code;
    }
}