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
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatabaseManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        ArrayList<Comic> comics = new ArrayList<>();
        boolean loopFlag = true;

        //Ask the user for a file name to add comics to the database.
        while (loopFlag) {
            try {
                System.out.println("Type the file path for your comic submission.");
                String filePath = scanner.next();
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
                System.out.println("File not found.");
            }
        }

        //Print the database to the screen
        System.out.println("Printing comics...");
        for (Comic comic : comics) {
            comic.print();
        }

        //Ask the user for the title of a comic to remove it.
        System.out.println("Type the title of a comic to remove.");
        String title = scanner.next();

        boolean removed = comics.removeIf(comic -> (comic.getTitle().equals(title)));
        if (!removed) {
            System.out.println("The comic you requested was not found.\n");
        } else {
            System.out.println("Comic deleted.\n");
        }

        System.out.println("Printing comics...");
        for (Comic comic : comics) {
            comic.print();
        }

        //Ask the user for the ID of a comic to remove it.
        loopFlag = true;
        removed = true;
        while (loopFlag) {
            try {
                System.out.println("Type the ID of a comic to remove.");
                int id = scanner.nextInt();
                removed = comics.removeIf(comic -> (comic.getId() == id));
                loopFlag = false;
            } catch (InputMismatchException ime) {
                System.out.println("Please input an integer.");
                scanner.nextLine();
            }
        }

        if (!removed) {
            System.out.println("The comic you requested was not found.\n");
        } else {
            System.out.println("Comic deleted.\n");
        }

        System.out.println("Printing comics...");
        for (Comic comic : comics) {
            comic.print();
        }

        //Allow the user to edit a comic entry
        loopFlag = true;
        while (loopFlag) {
            String editTitle = "";
            int editedComicIndex = 0;
            while (loopFlag) {
                System.out.println("Type the title of a comic to edit. Type \"stop\" to move on.");
                editTitle = scanner.next();

                // Make sure the comic is there and mark its place in the list.
                boolean found = false;
                for (Comic comic : comics) {
                    if (comic.getTitle().equals(editTitle)) {
                        editedComicIndex = comics.indexOf(comic);
                        found = true;
                        loopFlag = false;
                        comic.print();
                    }
                }

                if (editTitle.equals("stop")) {
                    System.out.println("Moving on...");
                    loopFlag = false;
                    continue;
                }

                if (!found) {
                    System.out.println("That comic was not found in the list.");
                    scanner.nextLine();
                }
            }

            loopFlag = true;
            while (loopFlag) {
                if (editTitle.equals("stop")) {
                    loopFlag = false;
                    continue;
                }
                System.out.println(""" 
                Type the number or name of an attribute of the comic to edit. Type "7" or "stop" to move on.
                 
                1. image
                2. rating
                3. current chapter
                4. total chapters
                5. completed
                6. pinned
                7. stop
                """);
                String attribute = scanner.next();

                switch (attribute) {
                    case "1":
                    case "image":
                        System.out.println("Please type the new image link.");
                        comics.get(editedComicIndex).setImagePath(scanner.next());
                        break;
                    case "2":
                    case "rating":
                        System.out.println("Please type the new rating.");
                        while (loopFlag) {
                            try {
                                comics.get(editedComicIndex).setRating(scanner.nextInt());
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                                scanner.nextLine();
                            }
                        }
                        loopFlag = true;
                        break;
                    case "3":
                    case "current chapter":
                        System.out.println("Please type the new current chapter.");
                        while (loopFlag) {
                            try {
                                comics.get(editedComicIndex).setCurrentChapter(scanner.nextInt());
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                                scanner.nextLine();
                            }
                        }
                        loopFlag = true;
                        break;
                    case "4":
                    case "total chapters":
                        System.out.println("Please type the new chapter total.");
                        while (loopFlag) {
                            try {
                                comics.get(editedComicIndex).setTotalChapters(scanner.nextInt());
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                                scanner.nextLine();
                            }
                        }
                        loopFlag = true;
                        break;
                    case "5":
                    case "completed":
                        System.out.println("Please type the new state of completion.");
                        while (loopFlag) {
                            try {
                                comics.get(editedComicIndex).setCompleted(scanner.nextBoolean());
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please type \"true\" or \"false\".");
                                scanner.nextLine();
                            }
                        }
                        loopFlag = true;
                        break;
                    case "6":
                    case "pinned":
                        System.out.println("Please type whether the comic is pinned or not.");
                        while (loopFlag) {
                            try {
                                comics.get(editedComicIndex).setPinned(scanner.nextBoolean());
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please type \"true\" or \"false\".");
                                scanner.nextLine();
                            }
                        }
                        loopFlag = true;
                        break;
                    case "7":
                    case "stop":
                        System.out.println("Moving on...");
                        loopFlag = false;
                    default:
                        System.out.println("Invalid selection. Please type the number or text of the attribute.");
                        break;
                }
            }
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