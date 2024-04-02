/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 1 April 2024
 * DatabaseManagerGUI.java
 * This class will start the GUI.
 */

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManagerGUI {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:\\Users\\repti\\IdeaProjects\\Williams_Ryan_DMS\\sqlite-tools-win-x64-3450200\\comics.db";
        String query = "SELECT * FROM Comics;";
        try {
            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                String comicData = "";
                for (int i = 1; i <= 9; i++) {
                    comicData += result.getString(i) + ",";
                }
                String[] comicItems = comicData.split(",");
                Comic comic = new Comic(comicItems[0], comicItems[1], comicItems[2], Integer.parseInt(comicItems[3]),
                        Integer.parseInt(comicItems[4]), Integer.parseInt(comicItems[5]), Integer.parseInt(comicItems[6]),
                        Boolean.parseBoolean(comicItems[7]), Boolean.parseBoolean(comicItems[8]));
                DatabaseManager.comics.add(comic);
                System.out.println(comicData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MainFrame frame = new MainFrame();
    }
}