package de.medieninformatik.server;

import de.medieninformatik.rest.FilmRest;

import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class FilmApplication extends Application {
    //private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> classes = new HashSet<>();

    public FilmApplication() {
        //singletons.add(new StudentRest());
        classes.add(FilmRest.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    //@Override
    //public Set<Object> getSingletons() {
    //    return singletons;
    //}
}