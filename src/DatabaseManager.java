/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 1 April 2024
 * DatabaseManager.java
 * This class will present a menu for the user to navigate to display, add, edit,
 * or remove comics in a list of comics.
 */

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatabaseManager {
    static ArrayList<Comic> comics = new ArrayList<>();
    public static String url;
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
    }

    /*
     * Method Name: addComics
     * Purpose: Adds comics to the comic list from a user-submitted text file.
     * Parameters: String
     * Returns: nothing
     */
    static void addComics(String filePath) throws IOException {
        boolean loopFlag = true;
        while (loopFlag) {
            try {
                File comicFile = new File(filePath);
                Scanner fileScanner = new Scanner(comicFile);

                while (fileScanner.hasNextLine()) {
                    String comicData = fileScanner.nextLine();
                    String[] comicItems = comicData.split(",");
                    Comic comic = new Comic(comicItems[0], comicItems[1], comicItems[2], Integer.parseInt(comicItems[3]),
                            Integer.parseInt(comicItems[4]), Integer.parseInt(comicItems[5]), Integer.parseInt(comicItems[6]),
                            Boolean.parseBoolean(comicItems[7]), Boolean.parseBoolean(comicItems[8]));
                    comics.add(comic);
                }
                fileScanner.close();
                loopFlag = false;
            } catch (IOException e) {
                throw new IOException("Invalid input");
            }
        }
    }

    /*
     * Method Name: editComics
     * Purpose: Edits any editable attribute of a comic from the list.
     * Parameters: ArrayList<Comic>, String, String, String
     * Returns: nothing
     */
    static void editComics(String editTitle, String attribute,
                           String newValue) throws Exception, InputMismatchException {
        // Make sure the comic is there and mark its place in the list.
        boolean found = false;
        int editedComicIndex = 0;
        for (Comic comic : comics) {
            if (comic.getTitle().equals(editTitle)) {
                editedComicIndex = comics.indexOf(comic);
                found = true;
            }
        }

        if (!found) {
            throw new Exception("Comic not found");
        }
        Connection con = DriverManager.getConnection(url);
        Statement statement = con.createStatement();

        switch (attribute) {
            case "1":
            case "image":
                comics.get(editedComicIndex).setImagePath(newValue);
                statement.executeUpdate("UPDATE Comics SET ImagePath = '" + newValue + "' WHERE Title = '" +
                        editTitle + "';");
                System.out.println("New image path set.");
                break;
            case "2":
            case "rating":
                    try {
                        if (Integer.parseInt(newValue) < 0 || Integer.parseInt(newValue) > 10) {
                            throw new InputMismatchException();
                        }
                        comics.get(editedComicIndex).setRating(Integer.parseInt(newValue));
                        statement.executeUpdate("UPDATE Comics SET Rating = " + Integer.parseInt(newValue) +
                                " WHERE Title = '" + editTitle + "';");
                    } catch (InputMismatchException ime) {
                        throw new InputMismatchException("Rating should be an integer from 0 to 10.");
                    }
                break;
            case "3":
            case "current chapter":
                    try {
                        comics.get(editedComicIndex).setCurrentChapter(Integer.parseInt(newValue));
                        statement.executeUpdate("UPDATE Comics SET CurrentChapter = " + Integer.parseInt(newValue)
                                + " WHERE Title = '" + editTitle + "';");
                        System.out.println("Current chapter set.");
                    } catch (InputMismatchException ime) {
                        throw new InputMismatchException("Chapter should be an integer.");
                    }
                break;
            case "4":
            case "total chapters":
                    try {
                        comics.get(editedComicIndex).setTotalChapters(Integer.parseInt(newValue));
                        statement.executeUpdate("UPDATE Comics SET TotalChapters = " + Integer.parseInt(newValue)
                                + " WHERE Title = '" + editTitle + "';");
                        System.out.println("Total Chapters set.");
                    } catch (InputMismatchException ime) {
                        throw new InputMismatchException("Chapter should be an integer.");
                    }
                break;
            case "5":
            case "completed":
                    try {
                        comics.get(editedComicIndex).setCompleted(Boolean.parseBoolean(newValue));
                        statement.executeUpdate("UPDATE Comics SET Completed = " + Boolean.parseBoolean(newValue)
                                + " WHERE Title = '" + editTitle + "';");
                        System.out.println("Completed set.");
                    } catch (InputMismatchException ime) {
                        throw new InputMismatchException("Completed should be a boolean value.");
                    }
                break;
            case "6":
            case "pinned":
                    try {
                        comics.get(editedComicIndex).setPinned(Boolean.parseBoolean(newValue));
                        statement.executeUpdate("UPDATE Comics SET Pinned = " + Boolean.parseBoolean(newValue)
                                + " WHERE Title = '" + editTitle + "';");
                        System.out.println("Pin set.");
                        // Set any pinned comics to unpinned
                        for (Comic comic : comics) {
                            if (!comic.getTitle().equals(editTitle)) {
                                comic.setPinned(false);
                            }
                        }
                    } catch (InputMismatchException ime) {
                        throw new InputMismatchException("Pinned should be a boolean value.");
                    }
                break;
            default:
                throw new Exception("Invalid selection. Please type the number or text of the attribute.");
        }
    }

    /*
     * Method Name: pause
     * Purpose: Pauses the system until the user presses ENTER
     * Parameters: none
     * Returns: nothing
     */
    static void pause() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Press ENTER to continue...");
        myScanner.nextLine();
    }

    /*
     * Method Name: printComics
     * Purpose: Prints all comics in the comic list.
     * Parameters: ArrayList<Comic>
     * Returns: nothing
     */
    static void printComics(ArrayList<Comic> comics) {
        System.out.println("Printing comics...");
        for (Comic comic : comics) {
            comic.print();
        }
    }

    /*
     * Method Name: printComicsPinned
     * Purpose: Prints all comics in the comic list, with the pinned comic at the top.
     * Parameters: ArrayList<Comic>
     * Returns: nothing
     */
    static void printComicsPinned(ArrayList<Comic> comics) {
        System.out.println("Printing comics (pinned comic first)...");
        for (Comic comic : comics) {
            if (comic.getPinned()) {
                comic.print();
            }
        }
        for (Comic comic : comics) {
            if (!comic.getPinned()) {
                comic.print();
            }
        }
        pause();
    }

    /*
     * Method Name: readComics
     * Purpose: Reads the Comics from the database and puts them in a Comic list.
     * Parameters: none
     * Returns: nothing
     */
    static void readComics() {
        try {

            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Comics;");
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
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    /*
     * Method Name: removeComic
     * Purpose: Removes a comic based on its title or ID.
     * Parameters: ArrayList<Comic>, String, String, int
     * Returns: nothing
     */
    static void removeComic(ArrayList<Comic> comics, String inputType, String title, int id)
            throws Exception {
        if (inputType.equals("title")) {
            boolean removed = comics.removeIf(comic -> (comic.getTitle().equals(title)));
            if (!removed) {
                throw new Exception("The comic requested was not found.");
            } else {
                System.out.println("Comic deleted.");
            }
        } else if (inputType.equals("ID")) {
                try {
                    boolean removed = comics.removeIf(comic -> (comic.getId() == id));
                    if (!removed) {
                        throw new Exception("The comic requested was not found.");
                    } else {
                        System.out.println("Comic deleted.");
                    }
                } catch (InputMismatchException ime) {
                    throw new InputMismatchException();
                }
        }
    }
}
