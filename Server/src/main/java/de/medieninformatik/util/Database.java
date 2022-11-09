package de.medieninformatik.util;
import java.util.ResourceBundle;
import java.sql.*;

public class Database {

    private static Database dbs = new Database();
    private Connection connection;

    private Database(){
        try{
            ResourceBundle bundle = ResourceBundle.getBundle("Connection");
            String driver = bundle.getString("Driver");
            String dbase = bundle.getString("URL");
            String dbuser = bundle.getString("User");
            String dbpw = bundle.getString("Passwd");
            Class.forName(driver);

            this.connection = DriverManager.getConnection(dbase, dbuser, dbpw);
            setupTable(connection);

        }catch(Exception e){
            System.err.println(e);
        }
    }


    public static Database getInstance(){
        return dbs;
    }


    private void setupTable(Connection connection){
        try {
            String table = """
                    create table Movies(
                    id integer UNIQUE,
                    movie_name varchar(64),
                    year integer,
                    genre varchar(32),
                    regisseur varchar(32),
                    appraisel varchar(32),
                    primary key(movie_name)
                    )""";
            Statement statement = connection.createStatement();
            statement.executeUpdate("drop table if exists Movies");
            statement.executeUpdate(table);

            //sämtliche Bewertungen basieren auf den persönlichen Meinungen von mir
            statement.executeUpdate("INSERT into Movies values (1, 'Star Wars Episode IV: A New Hope', 1977, 'Science-Fiction', 'George Lucas', '10/10 - Masterpiece')");
            statement.executeUpdate("INSERT into Movies values (2, 'Star Wars Episode V: The Empire Strikes Back', 1980, 'Science-Fiction', 'George Lucas', '10/10 - Masterpiece')");
            statement.executeUpdate("INSERT into Movies values (3, 'Star Wars Episode VI: Return of the Jedi', 1983, 'Science-Fiction', 'George Lucas', '10/10 - Masterpiece')");
            statement.executeUpdate("INSERT into Movies values (4, 'Star Wars Episode I: The Phantom Menace', 1999, 'Science-Fiction', 'George Lucas', '6/10 - pretty good')");
            statement.executeUpdate("INSERT into Movies values (5, 'Star Wars Episode II: Attack of the Clones', 2002, 'Science-Fiction', 'George Lucas', '8/10 - good')");
            statement.executeUpdate("INSERT into Movies values (6, 'Star Wars Episode III: Revenge of the Sith', 2005, 'Science-Fiction', 'George Lucas', '7/10 - alright')");
            statement.executeUpdate("INSERT into Movies values (7, 'Star Wars Episode VII: The Force Awakening', 2015, 'Science-Fiction', 'J.J. Abrams', '4/10 - not that good')");
            statement.executeUpdate("INSERT into Movies values (8, 'Star Wars Episode VIII: The Last Jedi', 2017, 'Science-Fiction', 'Rian Johnson', '0/10 - do NOT watch')");
            statement.executeUpdate("INSERT into Movies values (9, 'Star Wars Episode IX: The Rise of Skywalker', 2019,'Science-Fiction', 'J.J. Abrams', '0/10 - do NOT watch')");

            statement.executeUpdate("INSERT into Movies values (10, 'Lord of the Rings: The Fellowship of the Ring', 2001, 'Fantasy', 'Peter Jackson', '8/10 - good')");
            statement.executeUpdate("INSERT into Movies values (11, 'Lord of the Rings: The Two Towers', 2002, 'Fantasy', 'Peter Jackson', '9/10 - almost there')");
            statement.executeUpdate("INSERT into Movies values (12, 'Lord of the Rings: The Return of the King', 2003, 'Fantasy', 'Peter Jackson', '10/10 - Masterpiece')");

            statement.executeUpdate("INSERT into Movies values (13, 'The Hobbit: An Unexpected Journey', 2012, 'Fantasy', 'Peter Jackson', '6/10 - pretty good')");
            statement.executeUpdate("INSERT into Movies values (14, 'The Hobbit: The Desaulation of Smaug', 2013, 'Fantasy', 'Peter Jackson', '7/10 - alright')");
            statement.executeUpdate("INSERT into Movies values (15, 'The Hobbit: The Battle of the Five Armies', 2014, 'Fantasy', 'Peter Jackson', '8/10 - good')");

            statement.executeUpdate("INSERT into Movies values (16, 'Batman: Begins', 2005, 'Action', 'Christopher Nolan', '7/10 - alright')");
            statement.executeUpdate("INSERT into Movies values (17, 'Batman: The Dark Knight', 2008, 'Action', 'Christopher Nolan', '8/10 - good')");
            statement.executeUpdate("INSERT into Movies values (18, 'Batman: The Dark Knight Rises', 2012, 'Action', 'Christopher Nolan', '9/10 - almost there')");

            statement.executeUpdate("INSERT into Movies values (19, 'Persona 3 The Movie #1: Spring of Birth', 2013,'Animation', 'Noriaki Akitaya', '6/10 - pretty good')");
            statement.executeUpdate("INSERT into Movies values (20, 'Persona 3 the Movie: #2: Midsummer Knights Dream', 2014,'Animation', 'Tomohisa Taguchi', '7/10 - alright')");
        }catch (Exception e){
            System.err.println(e);
        }
    }

    public ResultSet getFilmbyName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from Movies where movie_name=" + name);
        return resultSet;
    }

    public ResultSet getFilmByID(int id) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from Movies where id=" + id);
        return resultSet;
    }

    public ResultSet getAllFilms() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from Movies");
        return resultSet;
    }

    public void addMovie(int id, String movie_name, int year, String genre, String regisseur, String appraisal) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate
                (
                        "Insert into Movies values(" + id + ",'" + movie_name + "'," + year + ",'" + genre + "',"
                                + "'" + regisseur + "'," + "'" + appraisal + "'" + ");"
                );
    }

    public void deleteMovie(int id) throws SQLException {
        int moviecount = getMovieCount();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE from Movies where id=" + id);

        for(int i = id + 1; i < moviecount+1 ; i++){
            ResultSet rs = getFilmByID(i);
            rs.next();
            statement.executeUpdate("UPDATE Movies SET id=" + (rs.getInt(1)-1) +  " where id =" + rs.getInt(1));
        }

    }

    public boolean exists(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT (*) from Movies where id=" + id);
        resultSet.next();
        if(resultSet.getInt(1) == 1) return true;

        return false;
    }

    public int getMovieCount() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT COUNT (*) from Movies");
        set.next();
        int id = set.getInt(1);
        return id;
    }






}
