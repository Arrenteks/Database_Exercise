package de.medieninformatik.rest;

import de.medieninformatik.movie.Movie;
import de.medieninformatik.util.Database;
import de.medieninformatik.util.MarshallingTransformer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <Daniel Adam> <m29062>
 * date: 2022-02-11
 * Hausarbeit Programmierung 3
 * Beschreibung der Klasse: FilmRest
 * Stellt die REST Schnittstelle für den Server dar. Liefert Methoden für das Läschen, Erstellen und Ansehen von Filmen.
 * externe Quellen:
 *         Prof Jürgen K. Singer, PhD/USA, "StudentenREST" - Gradle Projekt
 */
@Path("movies")
public class FilmRest {
    Database dbs = Database.getInstance();
    MarshallingTransformer transformer;
    {
        try {
            transformer = new MarshallingTransformer();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Liefert einen Film basierend auf der gelieferten ID zurück. Diese Methode holt sich das ResultSet aus dem Server
     * und wandelt die bestehenden Daten in eine xml Datei um. Diese wird dann in eine Entity vom Typ File umgewanelt und
     * der response als entity angehängt.
     * @param id zu liefernder Film
     * @return response
     * @throws SQLException ResultSet
     * @throws JAXBException Marshaller aus dem transformer
     * @throws IOException
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMovieById(@PathParam("id") int id) throws SQLException, JAXBException, IOException {
        ResultSet rs = dbs.getFilmByID(id);

        File movieFile = transformer.rsToXml(rs);
        Entity<File> entity = Entity.entity(movieFile, MediaType.APPLICATION_XML);
        return Response.ok(entity.getEntity()).build();
    }

    /**
     * liefert die aktuelle Anzahl von Filmen in der Datenbank basierend auf einem ResultSet zurück.
     * @return repsonse
     * @throws SQLException ResultSet
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMovieCount() throws SQLException {
        int moviecount = dbs.getMovieCount();
        return Response.ok(moviecount).build();
    }

    /**
     * Erstellt eine neue URI für einen Film, unmarhalled die Daten in der Anfragedatei in einen Film und gibt diesen
     * an die Datenbank weiter
     * @param newMovie neuer Film
     * @param uriInfo uriinfo
     * @return response
     * @throws SQLException dbs.addMovie
     * @throws JAXBException unmarshaller
     * @throws FileNotFoundException Reader
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response newMovie(File newMovie, @Context UriInfo uriInfo) throws SQLException, JAXBException, FileNotFoundException {
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Reader reader = new FileReader(newMovie);

        Movie unmarshalledMovie = (Movie) unmarshaller.unmarshal(reader);
        uriBuilder.path(String.valueOf(unmarshalledMovie.getId()));
        dbs.addMovie(unmarshalledMovie.getId(), unmarshalledMovie.getMovieName(), unmarshalledMovie.getYear(), unmarshalledMovie.getGenre(), unmarshalledMovie.getRegisseur(), unmarshalledMovie.getAppraial());
        return Response.created(uriBuilder.build()).build();
    }


    /**
     * Löscht einen Film basierend auf der gelieferten ID. Wenn der Film existiert wird der Läschbefehl an die Daten-
     * bank weiter gereicht und ok zurückgegeben. Existiert der Film nicht 
     * @param id
     * @return
     * @throws SQLException
     */
    @DELETE
    @Path("{id}")
    public Response deleteMovie(@PathParam("id") int id) throws SQLException {
        Response response;
        if(dbs.exists(id)){
            dbs.deleteMovie(id);
            response = Response.noContent().status(Response.Status.OK).build();
        }else{
            response = Response.noContent().status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

}