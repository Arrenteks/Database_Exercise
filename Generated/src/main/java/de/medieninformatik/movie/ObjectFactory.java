//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.2 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2022.02.07 um 05:01:29 PM CET 
//


package de.medieninformatik.movie;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.medieninformatik.movie package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Id_QNAME = new QName("http://www.medieninformatik.de/movie", "id");
    private final static QName _MovieName_QNAME = new QName("http://www.medieninformatik.de/movie", "movie_name");
    private final static QName _Year_QNAME = new QName("http://www.medieninformatik.de/movie", "year");
    private final static QName _Genre_QNAME = new QName("http://www.medieninformatik.de/movie", "genre");
    private final static QName _Regisseur_QNAME = new QName("http://www.medieninformatik.de/movie", "regisseur");
    private final static QName _Appraial_QNAME = new QName("http://www.medieninformatik.de/movie", "appraial");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.medieninformatik.movie
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Movie }
     * 
     */
    public Movie createMovie() {
        return new Movie();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.medieninformatik.de/movie", name = "id")
    public JAXBElement<Integer> createId(Integer value) {
        return new JAXBElement<Integer>(_Id_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.medieninformatik.de/movie", name = "movie_name")
    public JAXBElement<String> createMovieName(String value) {
        return new JAXBElement<String>(_MovieName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.medieninformatik.de/movie", name = "year")
    public JAXBElement<Integer> createYear(Integer value) {
        return new JAXBElement<Integer>(_Year_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.medieninformatik.de/movie", name = "genre")
    public JAXBElement<String> createGenre(String value) {
        return new JAXBElement<String>(_Genre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.medieninformatik.de/movie", name = "regisseur")
    public JAXBElement<String> createRegisseur(String value) {
        return new JAXBElement<String>(_Regisseur_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.medieninformatik.de/movie", name = "appraial")
    public JAXBElement<String> createAppraial(String value) {
        return new JAXBElement<String>(_Appraial_QNAME, String.class, null, value);
    }

}
