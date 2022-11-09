package de.medieninformatik.util;

import de.medieninformatik.movie.Movie;
import de.medieninformatik.movie.ObjectFactory;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarshallingTransformer {
    private final JAXBContext jaxbContext;
    private final Marshaller marshaller;
    private final ObjectFactory objectFactory;

    public MarshallingTransformer() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Movie.class);
        this.marshaller = jaxbContext.createMarshaller();
        this.objectFactory = new ObjectFactory();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    /**
     * Methode zur Umwandlung eines Resultsets, welches EINEN Film enthält in ein XML-File mit Hilfe des Marshallers
     * @param rs umzuwandelndes Resultset
     * @return File
     * @throws SQLException Datenbankanwendung
     */
    public File rsToXml(ResultSet rs) throws SQLException, IOException, JAXBException {
        Movie movie = objectFactory.createMovie();

        rs.next();
        movie.setId(rs.getInt(1));
        movie.setMovieName(rs.getString(2));
        movie.setYear(rs.getInt(3));
        movie.setGenre(rs.getString(4));
        movie.setRegisseur(rs.getString(5));
        movie.setAppraial(rs.getString(6));

        Path path =  Path.of("../xmlDaten", "/anfrage.xml");
        Writer writer = new FileWriter(path.toFile());
        marshaller.marshal(movie, writer);
        File marshalledMovie = path.toFile();

        return marshalledMovie;
    }



}
