package video_club;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class InfoConnection {
    // Standar info connection
    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url             = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it175058" ;
    static Connection dbConnection    = null;
    static String     username        = "it175058";
    static String     passwd          = "nikopoli";
    static Statement  statement       = null;
    static ResultSet  rs              = null;
    //End
    static double xOffset = 0;
    static double yOffset = 0;
}
