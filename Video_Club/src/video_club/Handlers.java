package video_club;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Handlers {
        //Handlers    
    public static void sqlExceptionHandler(SQLException e){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Logs/SQL_Logs.txt", true));
            writer.write("==============================\n");
            writer.write(LocalDateTime.now() + "\n");
            writer.write("==============================\n");
            writer.write("Message: " + e.getMessage() + "\n");
            writer.write("SQLState: " + e.getSQLState() + "\n");
            writer.write("ErrorCode: " + e.getErrorCode() + "\n");
            writer.write("==============================\n");
            writer.close();
        } catch(IOException io){
            System.out.println(io);
        }
        System.out.println("Message: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("ErrorCode: " + e.getErrorCode());
    }

    public static void fxExceptionHandler(Exception e){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Logs/JavaFX_Logs.txt", true));
            writer.write("==============================\n");
            writer.write(LocalDateTime.now() + "\n");
            writer.write("==============================\n");
            writer.write("Error Message: " + e + "\n");
            writer.write("==============================\n");
            writer.close();
        } catch(IOException io){
            System.out.println(io);
        }
        System.out.println("Error Message: " + e + "\n");
    }
    //End Handlers
}
