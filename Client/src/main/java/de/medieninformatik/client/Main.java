package de.medieninformatik.client;


import javafx.application.Application;
/**
 * @author <Daniel Adam> <m29062>
 * date: 2022-02-11
 * Hausarbeit Programmierung 3
 * Beschreibung der Klasse: Main
 * Startet die Applikation
 */
public class Main {
    /**
     * Startet die Applikation basierend auf UserView.class
     * @param args Programmargumente
     */
    public static void main(String[] args) {
        Application.launch(UserView.class, args);
    }
}