//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.2 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Aenderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2022.02.07 um 05:01:29 PM CET 
//


package de.medieninformatik.movie;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.medieninformatik.de/movie}id"/&gt;
 *         &lt;element ref="{http://www.medieninformatik.de/movie}movie_name"/&gt;
 *         &lt;element ref="{http://www.medieninformatik.de/movie}year"/&gt;
 *         &lt;element ref="{http://www.medieninformatik.de/movie}genre"/&gt;
 *         &lt;element ref="{http://www.medieninformatik.de/movie}regisseur"/&gt;
 *         &lt;element ref="{http://www.medieninformatik.de/movie}appraial"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "movieName",
    "year",
    "genre",
    "regisseur",
    "appraial"
})
@XmlRootElement(name = "movie")
public class Movie {

    protected int id;
    @XmlElement(name = "movie_name", required = true)
    protected String movieName;
    protected int year;
    @XmlElement(required = true)
    protected String genre;
    @XmlElement(required = true)
    protected String regisseur;
    @XmlElement(required = true)
    protected String appraial;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der movieName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Legt den Wert der movieName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovieName(String value) {
        this.movieName = value;
    }

    /**
     * Ruft den Wert der year-Eigenschaft ab.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Legt den Wert der year-Eigenschaft fest.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Ruft den Wert der genre-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Legt den Wert der genre-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenre(String value) {
        this.genre = value;
    }

    /**
     * Ruft den Wert der regisseur-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisseur() {
        return regisseur;
    }

    /**
     * Legt den Wert der regisseur-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisseur(String value) {
        this.regisseur = value;
    }

    /**
     * Ruft den Wert der appraial-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppraial() {
        return appraial;
    }

    /**
     * Legt den Wert der appraial-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppraial(String value) {
        this.appraial = value;
    }

}
