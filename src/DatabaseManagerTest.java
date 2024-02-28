import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;

class DatabaseManagerTest {
    // create an object to hold comics
    ArrayList<Comic> comics;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // add comics to the list
        comics = new ArrayList<>();
        try {
            File comicFile = new File("C:\\Users\\repti\\IdeaProjects\\Williams_Ryan_DMS\\src\\myComics.txt");
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
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Add Comics Test")
    void addComicsTest() {
        // check that comics are added to the list
        assertEquals(comics.size(), 4, "Error: Wrong amount of comics added to the list.");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Edit Comics Int Test")
    void editComicsIntTest() {
        // removed all statements that use scanner because no user inputs are allowed in tests
        // make sure user can edit comics (valid comic, valid int attribute)
        boolean loopFlag = true;
        while (loopFlag) {
            String editTitle = "Spider-Man/Deadpool";
            int editedComicIndex = 0;
            while (loopFlag) {
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
                String attribute = "2";

                switch (attribute) {
                    case "1":
                    case "image":
                        System.out.println("Please type the new image link.");
                        break;
                    case "2":
                    case "rating":
                        System.out.println("Please type the new rating.");
                        while (loopFlag) {
                            try {
                                comics.get(editedComicIndex).setRating(8);
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                            }
                        }
                        //loopFlag = true;
                        break;
                    case "3":
                    case "current chapter":
                        System.out.println("Please type the new current chapter.");
                        while (loopFlag) {
                            try {
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                            }
                        }
                        loopFlag = true;
                        break;
                    case "4":
                    case "total chapters":
                        System.out.println("Please type the new chapter total.");
                        while (loopFlag) {
                            try {
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                            }
                        }
                        loopFlag = true;
                        break;
                    case "5":
                    case "completed":
                        System.out.println("Please type the new state of completion (\"true\" or \"false\").");
                        while (loopFlag) {
                            try {
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please type \"true\" or \"false\".");
                            }
                        }
                        loopFlag = true;
                        break;
                    case "6":
                    case "pinned":
                        System.out.println("Please type whether the comic is pinned or not (\"true\" or \"false\").");
                        while (loopFlag) {
                            try {
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please type \"true\" or \"false\".");
                            }
                        }
                        loopFlag = true;
                        break;
                    case "7":
                    case "stop":
                        System.out.println("Moving on...");
                        loopFlag = false;
                        break;
                    default:
                        System.out.println("Invalid selection. Please type the number or text of the attribute.");
                        break;
                }
            }
        }
        assertEquals(8, comics.get(2).getRating());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Edit Comics Boolean Test")
    void EditComicsBooleanTest() {
        // removed all statements that use scanner because no user inputs are allowed in tests
        // make sure user can edit comics (valid comic, valid boolean attribute)
        boolean loopFlag = true;
        while (loopFlag) {
            String editTitle = "Spider-Man/Deadpool";
            int editedComicIndex = 0;
            while (loopFlag) {
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
                String attribute = "5";

                switch (attribute) {
                    case "1":
                    case "image":
                        System.out.println("Please type the new image link.");
                        break;
                    case "2":
                    case "rating":
                        System.out.println("Please type the new rating.");
                        while (loopFlag) {
                            try {
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                            }
                        }
                        //loopFlag = true;
                        break;
                    case "3":
                    case "current chapter":
                        System.out.println("Please type the new current chapter.");
                        while (loopFlag) {
                            try {
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                            }
                        }
                        loopFlag = true;
                        break;
                    case "4":
                    case "total chapters":
                        System.out.println("Please type the new chapter total.");
                        while (loopFlag) {
                            try {
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please input an integer.");
                            }
                        }
                        loopFlag = true;
                        break;
                    case "5":
                    case "completed":
                        System.out.println("Please type the new state of completion (\"true\" or \"false\").");
                        while (loopFlag) {
                            try {
                                comics.get(editedComicIndex).setCompleted(false);
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please type \"true\" or \"false\".");
                            }
                        }
                        //loopFlag = true;
                        break;
                    case "6":
                    case "pinned":
                        System.out.println("Please type whether the comic is pinned or not (\"true\" or \"false\").");
                        while (loopFlag) {
                            try {
                                loopFlag = false;
                            } catch (InputMismatchException ime) {
                                System.out.println("Please type \"true\" or \"false\".");
                            }
                        }
                        loopFlag = true;
                        break;
                    case "7":
                    case "stop":
                        System.out.println("Moving on...");
                        loopFlag = false;
                        break;
                    default:
                        System.out.println("Invalid selection. Please type the number or text of the attribute.");
                        break;
                }
            }
        }
        assertFalse(comics.get(2).getCompleted());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Edit Comics Invalid Comic Test")
    void EditComicsInvalidTest() {
        // make sure user edits something (invalid comic)
        boolean loopFlag = true;
        while (loopFlag) {
            String editTitle = "Spider-Man/Deadpoo";
            int editedComicIndex = 0;
            while (loopFlag) {
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
                }
                loopFlag = false;
                assertFalse(found);
            }
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Print Comics Pinned Test")
    void printComicsPinnedTest() {
        // make sure the first comic in the list is the pinned one
        ArrayList<Comic> comicOrder = new ArrayList<>();
        System.out.println("Printing comics (pinned comic first)...");
        for (Comic comic : comics) {
            if (comic.getPinned()) {
                comic.print();
                comicOrder.add(comic);
            }
        }
        for (Comic comic : comics) {
            if (!comic.getPinned()) {
                comic.print();
                comicOrder.add(comic);
            }
        }
        assertTrue(comicOrder.get(0).getPinned());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Remove Comic By Title Test")
    void removeComicTitleTest() {
        // make sure a user can remove a comic using its title
        String inputType = "title";
        if (inputType.equals("title")) {
            System.out.println("Type the title of a comic to remove.");
            String title = "Spider-Man/Deadpool";

            boolean removed = comics.removeIf(comic -> (comic.getTitle().equals(title)));
            if (!removed) {
                System.out.println("The comic you requested was not found.\n");
            } else {
                System.out.println("Comic deleted.\n");
            }
        } else if (inputType.equals("ID")) {
            boolean loopFlag = true;
            while (loopFlag) {
                try {
                    System.out.println("Type the ID of a comic to remove.");
                    int id = 3;
                    boolean removed = comics.removeIf(comic -> (comic.getId() == id));
                    if (!removed) {
                        System.out.println("The comic you requested was not found.\n");
                    } else {
                        System.out.println("Comic deleted.\n");
                    }
                    loopFlag = false;
                } catch (InputMismatchException ime) {
                    System.out.println("Please input an integer.");
                }
            }
        }
        assertEquals(3, comics.size());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Remove Comic By ID Test")
    void removeComicIdTest() {
        // make sure a user can remove a comic using its title
        String inputType = "ID";
        if (inputType.equals("title")) {
            System.out.println("Type the title of a comic to remove.");
            String title = "Spider-Man/Deadpool";

            boolean removed = comics.removeIf(comic -> (comic.getTitle().equals(title)));
            if (!removed) {
                System.out.println("The comic you requested was not found.\n");
            } else {
                System.out.println("Comic deleted.\n");
            }
        } else if (inputType.equals("ID")) {
            boolean loopFlag = true;
            while (loopFlag) {
                try {
                    System.out.println("Type the ID of a comic to remove.");
                    int id = 3;
                    boolean removed = comics.removeIf(comic -> (comic.getId() == id));
                    if (!removed) {
                        System.out.println("The comic you requested was not found.\n");
                    } else {
                        System.out.println("Comic deleted.\n");
                    }
                    loopFlag = false;
                } catch (InputMismatchException ime) {
                    System.out.println("Please input an integer.");
                }
            }
        }
        assertEquals(3, comics.size());
    }
}