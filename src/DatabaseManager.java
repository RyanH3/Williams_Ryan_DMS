/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 8 February 2024
 * DatabaseManager.java
 * This class will present a menu for the user to navigate to display, add, edit,
 * or remove comics in a list of comics.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        ArrayList<Comic> comics = new ArrayList<>();

        //Ask the user for a file name to add comics to the database.
        System.out.println("Type the file path for your comic submission.");
        String filePath = scanner.next();

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
        }
        catch (IOException e) {
            System.out.println("File not found.");
        }

        //Print the database to the screen
        System.out.println("Printing comics...");
        for (Comic comic : comics) {
            comic.print();
        }

        //Ask the user for the title of a comic to remove it.
        System.out.println("Type the title of a comic to remove.");
        String title = scanner.next();

        comics.removeIf(comic -> (comic.getTitle().equals(title)));
        System.out.println("Comic deleted.\n");
        System.out.println("Printing comics...");
        for (Comic comic : comics) {
            comic.print();
        }

        //Ask the user for the id of a comic to remove it.
        System.out.println("Type the ID of a comic to remove.");
        int id = scanner.nextInt();

        comics.removeIf(comic -> (comic.getId() == id));
        System.out.println("Comic deleted.\n");
        System.out.println("Printing comics...");
        for (Comic comic : comics) {
            comic.print();
        }

        //Allow the user to edit a comic entry
        System.out.println("Type the title of a comic to edit..");
        String editTitle = scanner.next();

        System.out.println(""" 
                Type the number or name of an attribute of the comic to edit. 
                 
               1. image 
                2. rating 
                3. current chapter 
                4. total chapters 
                5. completed 
                6. pinned 
                """);
        String attribute = scanner.next();

        int editedComicIndex = 0;
        for (Comic comic : comics) {
            if (comic.getTitle().equals(editTitle)) {
                editedComicIndex = comics.indexOf(comic);
            }
        }

        System.out.println("Please type the new value.");
        switch (attribute) {
            case "1":
            case "image":
                comics.get(editedComicIndex).setImagePath(scanner.next());
                break;
            case "2":
            case "rating":
                comics.get(editedComicIndex).setRating(scanner.nextInt());
                break;
            case "3":
            case "current chapter":
                comics.get(editedComicIndex).setCurrentChapter(scanner.nextInt());
                break;
            case "4":
            case "total chapters":
                comics.get(editedComicIndex).setTotalChapters(scanner.nextInt());
                break;
            case "5":
            case "completed":
                comics.get(editedComicIndex).setCompleted(scanner.nextBoolean());
                break;
            case "6":
            case "pinned":
                comics.get(editedComicIndex).setPinned(scanner.nextBoolean());
                break;
        }

        System.out.println("Printing comics...");
        for (Comic comic : comics) {
            comic.print();
        }

        //Prints the comics with the pinned comic at the top.
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
    }
}