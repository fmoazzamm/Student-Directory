import java.util.ArrayList;
import java.util.Scanner;

public class StudentDirectory {

//the following need to be initialized outside main so that they can be used by all the functions in the class
static Scanner scan = new Scanner(System.in);
static ArrayList<String[]> directory = new ArrayList<>();
static int studentIDCounter = 100000; // will be used to assign student ID numbers chronologically as they are added

    public static void main(String[] args) {

        //Introduction when program is first run
        System.out.println(" --- Welcome to the Student Directory program, run through the console. --- ");
        System.out.println("\nSince the program is starting just now, there is no saved data (issues of this being a console-only program)! Let's first begin by adding your first student. If you would like to proceed, please press 'Enter'.");
        System.out.print("Press Enter >> ");
        scan.nextLine(); //this is here to prompt the program to continue

        createNewStudent();
        System.out.println("\n\nGreat! Your first student has been added.");

        String nextAction = "";
        while (!nextAction.equals("end")) {
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("What would you like to do next? Please indicate a number from the options below:");
            System.out.println("\t1. Create new student");
            System.out.println("\t2. Edit student data");
            System.out.println("\t3. Delete student");
            System.out.println("\t4. Search for a student");
            System.out.println("\t5. Filter directory"); // gets students based on a common characteristics (ex. grade, graduation year)
            System.out.println("\t6. Sort directory"); // reorders directory ArrayList to be organized in a specific way (ex. alphabetic, student ID)
            System.out.println("\t7. Print the full student directory");
            System.out.println("\t8. Terminate the program");
            System.out.print("\nYour Choice >> ");

            nextAction = scan.nextLine(); //keeping as a String because nextAction will also use keywords like "end" for the while loop

            switch (nextAction) {
                case "1":
                    System.out.println("You selected Option 1: Create a new student\n");
                    createNewStudent();
                    break;
                case "2":
                    System.out.println("You selected Option 2: Edit student data\n");
                    editStudent();
                    break;
                case "3":
                    System.out.println("You selected Option 3: Delete student \n");
                    deleteStudent();
                    break;
                case "4":
                    System.out.println("You selected Option 4: Search for a student\n");
                    searchForStudent();
                    break;
                case "5":
                    System.out.println("You selected Option 5: Filter directory\n");
                    filterDirectory();
                    break;
                case "6":
                    System.out.println("You selected Option 6: Sort directory\n");
                    sortDirectory();
                    break;
                case "7":
                    System.out.println("You selected Option 7: Print the full student directory\n");
                    printFullDirectory();
                    break;
                case "8":
                    System.out.println("You selected Option 8: Terminate the program\n");
                    System.out.println("Are you sure you want to close the program? No data will be saved if the terminal is closed.");
                    System.out.println("\tTo confirm, type 'end'.");
                    System.out.println("\tTo continue using the program, press 'Enter'.");
                    nextAction = scan.nextLine().trim().toLowerCase();

                    if (nextAction.equals("end")) {
                        System.out.println("Goodbye :)");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scan.close(); //must be closed out at the end of the program
    }


    /**
     * Function name: createNewStudent
     * 
     * Inside function:
     *  1. creates a new String[] row to contain all student info
     *  2. ask for student first name (stores into row)
     *  3. ask for student last name (stores into row)
     *  3. creates a student ID (stores into row)
     *  4. asks for student birthday (stores into row)
     *  5. asks for student grade (stores into row)
     *  6. asks for student graduation year (stores into row)
     *  7. adds String[] row into directory Arraylist
     */
    public static void createNewStudent() {
        System.out.println("\n\nLet's create student #" + (directory.size() + 1));

        String[] studentData = new String[6]; // 1. first name, 2. last name, 3. student ID #, 4. birthday, 5. grade, 6. graduation year

        System.out.print("\n- First name: ");
        studentData[0] = scan.nextLine().trim();

        System.out.print("- Last name: ");
        studentData[1] = scan.nextLine().trim();

        System.out.print("- Student ID: autopopulated... ");
        String studentID = createStudentID();
        System.out.print(studentID);
        studentData[2] = studentID;

        System.out.print("\n- Birthday: ");
        studentData[3] = scan.nextLine().trim();

        System.out.print("- Grade: ");
        studentData[4] = scan.nextLine().trim();

        System.out.print("- Graduation year: ");
        studentData[5] = scan.nextLine().trim();

        directory.add(studentData);
    }


    /**
     * Function name: createStudentID
     * @return (String)
     * 
     * Inside function:
     *  1. adds one to static int studentIDCounter then returns new value
     */
    public static String createStudentID() {
        //create the new student ID number
        studentIDCounter++;
        return Integer.toString(studentIDCounter);
    }


    /**
     * Function name: editStudent
     * 
     * Inside function:
     *  1. reuses similar code from searchForStudent() function to pull up student data (reused section indicated in code)
     *  2. asks for what student feature to change (first name, last name, birthday, grade, or graduation year)
     *  3. updates selected feature on directory ArrayList with inputted data
     */
    public static void editStudent() {
        System.out.println("To edit a student's data, please search for them first.");

        int indexOfStudent = 0;
        boolean studentFound = false;

        //NOTE: while loop copied from searchForStudent but adds indexOfStudent int and studentFound boolean when match found
        while (!studentFound) {
            String studentID = confirmStudentIDHELPER();
            switch (studentID) {
                case "retry": continue;
                case "exit": return; // ends function here
                default: break; // there is nothing to happen here because the only case is a student ID attempt
            }

            for (int i = 0; i < directory.size(); i++) {
                String[] row = directory.get(i);

                if (row[2].equals(studentID)) {
                    indexOfStudent = i;
                    studentFound = true; // this will cause the infinite while loop to break
                    printRowHELPER(row); // helper function
                    break;
                }
            }

            //NOTE: special if statement (not in searchForStudent() function) because if statement doesn't return after match found as done in original searchForStudent() function.
            if (!studentFound) {
                System.out.println("ERROR: Could not find a student with the inputted ID number.\n");
            }
        }

        System.out.println("What do you want to edit? See options below and input the number:");
        System.out.println("\t1. First name");
        System.out.println("\t2. Last name");
        System.out.println("\t3. Birthday");
        System.out.println("\t4. Grade");
        System.out.println("\t5. Graduation year");
        System.out.println("\t6. Return to main options");
        System.out.print("Choice >> ");
        String choice2 = scan.nextLine();

        String[] studentArray = directory.get(indexOfStudent);

        switch (choice2) {
            case "1": editStudentOptionsHELPER(1, "First name", studentArray, "first name", 0); break;
            case "2": editStudentOptionsHELPER(2, "Last name", studentArray, "last name", 1); break;
            case "3": editStudentOptionsHELPER(3, "Birthday", studentArray, "birthday", 3); break;
            case "4": editStudentOptionsHELPER(4, "Grade", studentArray, "grade", 4); break;
            case "5": editStudentOptionsHELPER(5, "Graduation year", studentArray, "graduation year", 5); break;
            case "6": System.out.println("You selected Option 6: Return to main options"); return;
            default: System.out.println("Invalid choice. Returning to main options."); return;
        }

        System.out.println("The new updated student profile is:");
        printRowHELPER(studentArray);
    }


    /**
     * Function name: deleteStudent
     * 
     * Inside function:
     *  1. reuses similar code from editStudent() function to ensure correct student selected
     *  2. deletes student after user confirmation
     */
    public static void deleteStudent() {
        System.out.println("To delete a student, please search for them first.");

        boolean studentFound = false;

        //NOTE: while loop copied from editStudent()
        while (!studentFound) {
            String studentID = confirmStudentIDHELPER();
            switch (studentID) {
                case "retry": continue;
                case "exit": return; // ends function here
                default: break; // there is nothing to happen here because the only case is a student ID attempt
            }

            for (int i = 0; i < directory.size(); i++) {
                String[] row = directory.get(i);

                if (row[2].equals(studentID)) {
                    studentFound = true; // this will cause the infinite while loop to break

                    System.out.println("Are you sure you want to delete the following student?\n");
                    printRowHELPER(row);
                    System.out.println("Enter 'yes' if you want to delete " + row[0] + ". If not, press 'enter'.");
                    System.out.print("'yes' or Enter >> ");
                    String choice3 = scan.nextLine().trim();

                    if (choice3.equals("yes")) {
                        directory.remove(i);
                    }

                    break;
                }
            }

            //NOTE: special if statement (not in searchForStudent() function) because if statement doesn't return after match found as done in original searchForStudent() function.
            if (!studentFound) {
                System.out.println("ERROR: Could not find a student with the inputted ID number.\n");
            }
        }
    }


    /**
     * Function name: searchForStudent
     * 
     * Inside function:
     *  1. while loop to prevent recursion and keep function cycling
     *  2. gives options for how to search for student
     *  3. checks the student ID column of directory
     *  4. once a match is made with the inputted student ID, that student ID gets printed 
     */
    public static void searchForStudent() {
        while (true) {
            String studentID = confirmStudentIDHELPER();
            switch (studentID) {
                case "retry": continue;
                case "exit": return; // ends function here
                default: break; // there is nothing to happen here because the only case is a student ID attempt
            }

            for (int i = 0; i < directory.size(); i++) {
                String[] row = directory.get(i);

                if (row[2].equals(studentID)) {
                    printRowHELPER(row); // helper function
                    return; // this ends the function here if the student ID was a match
                }
            }

            System.out.println("ERROR: Could not find a student with the inputted ID number.\n"); // this will only show if the funtion has not already called return; yet. Therefore, with the while loop from the start, it will go back to the start of the function.
        }
    }


    /**
     * Function name: filterDirectory
     * 
     * Inside function:
     *  1. infinite while loop to prevent recursion in the case of user misinput
     *  2. asks user to choose how to filter (first name, last name, grade, graduation year)
     *  3. asks user to input the keyword they are filtering by (ex. Jake --OR-- 2025)
     *  4. searches each student in the directory, only looking at the specific column that the user decided to filter by
     *  5. if match found, prints the full row of student data
     */
    public static void filterDirectory() {
        while (true) {
            System.out.println("\nTo filter the student directory, please choose a filter:");
            System.out.println("\t1. First name");
            System.out.println("\t2. Last name");
            System.out.println("\t3. Grade");
            System.out.println("\t4. Graduation year");
            System.out.print("\nEnter choice >> ");
            String choice = scan.nextLine();

            switch (choice) {
            case "1": filterByColumnHELPER(1, "First name", "first name", 0); return;
            case "2": filterByColumnHELPER(2, "Last name", "last name", 1); return;
            case "3": filterByColumnHELPER(3, "Grade", "grade level", 4); return;
            case "4": filterByColumnHELPER(4, "Graduation year", "graduation year", 5); return;
            default: System.out.println("Invalid choice. Please try again."); break;
        }
        }
    }


    /**
     * Function name: sortDirectory
     * 
     * Inside function:
     *  1. ask how to sort directory
     *  2. take user input to assign which column to sort by (use boolean to note if value of column is String or int)
     *  3. call sortByColumnHELPER(columnIndex, numeric) to reorder directory
     *  4. print full directory
     */
    public static void sortDirectory() {
        System.out.println("\nHow would you like to sort the directory? Please choose an option from below:");
        System.out.println("\t1. First name (A -> Z)");
        System.out.println("\t2. Last name (A -> Z)");
        System.out.println("\t3. Student ID (ascending order)");
        System.out.println("\t4. Grade (ascending order)");
        System.out.println("\t5. Graduation year (ascending order)");
        System.out.println("\t6. Return to main options");
        System.out.print("Enter choice >> ");
        String choice = scan.nextLine();

        int columnIndex;
        boolean numeric;

        switch (choice) {
            case "1": 
                System.out.println("\nYou chose Option 1: ");
                columnIndex = 0;
                numeric = false;
                break;
            case "2": 
                System.out.println("\nYou chose Option 2: ");
                columnIndex = 1;
                numeric = false;
                break;
            case "3": 
                System.out.println("\nYou chose Option 3: ");
                columnIndex = 2;
                numeric = true;
                break;
            case "4": 
                System.out.println("\nYou chose Option 4: ");
                columnIndex = 4;
                numeric = true;
                break;
            case "5": 
                System.out.println("\nYou chose Option 5: ");
                columnIndex = 5;
                numeric = true;
                break;
            case "6":
                System.out.println("\nYou chose Option 6: Return to main options.");
                return;
            default:
                System.out.println("Invalid choice. Returning to main options.");
                return;
        }

        sortByColumnHELPER(columnIndex, numeric);
        System.out.println("\nDirectory after sort:");
        printFullDirectory();
    }


    /**
     * Function name: printFullDirectory
     * 
     * Inside function:
     *  1. prints total student count
     *  2. prints header for table to help understand columns
     *  3. loops through every element of the ArrayList and inner Array. Prints each value
     */
    public static void printFullDirectory() {
        System.out.println("Total student count: " + directory.size());
        String header = "First Name: | Last Name: | Student ID: | Birthday: | Grade: | Graduation Year:\n";
        System.out.println(header);

        for (int i = 0; i < directory.size(); i++) {
            String[] row = directory.get(i);
            printRowHELPER(row);
            System.out.print("\n");
        }
    }

    /**
     * Function name: printRowHELPER
     * @param row (String[])
     * 
     * Inside function:
     *  1. prints given row in table-like format for aesthetics
     */
    public static void printRowHELPER(String[] row) {
        for (int i = 0; i < row.length; i++) {
            if (i == row.length - 1) {
                System.out.println(row[i] + "\n");
            } else {
                System.out.print(row[i] + " | ");
            }
        }
    }

    /**
     * Function name: filterByColumnHELPER
     * @param optionNumber (int)            --> used for header and aesthetics
     * @param optionHeader (String)         --> used for header and aesthetics
     * @param optionEntryLine (String)      --> used for user input line and aesthetics
     * @param columnIndex (int)
     * 
     * Inside function:
     *  1. prints header and user input lines
     *  2. runs through given row of student data and uses user input to decide if student matches filter
     *  3. if student matches filter, entire row is printed
     */
    public static void filterByColumnHELPER(int optionNumber, String optionHeader, String optionEntryLine, int columnIndex) {
        System.out.println("You chose Option " + optionNumber + ": " + optionHeader);
        System.out.print("Enter " + optionEntryLine + " to filter by >> ");
        String response = scan.nextLine().trim();

        int numOfMatches = 0;

        for (int i = 0; i < directory.size(); i++) {
            String[] row = directory.get(i);

            if (row[columnIndex].equalsIgnoreCase(response)) {
                numOfMatches++;
                printRowHELPER(row); // helper function
            }
        }

        if (numOfMatches == 0) {
            System.out.println("No students matched that filter.\n");
        }
    }


    /**
     * Function name: editStudentOptionsHELPER
     * @param optionNumber (int)            --> used for header and aesthetics
     * @param optionHeader (String)         --> used for header and aesthetics
     * @param studentArray (String[])
     * @param optionLowerCase (String)      --> used for prompt + user input line and aesthetics
     * @param editIndex (int)
     * 
     * Inside function:
     *  1. prints header, prompt, and user input lines
     *  2. takes user input and updates column with new student data
     */
    public static void editStudentOptionsHELPER(int optionNumber, String optionHeader, String[] studentArray, String optionLowerCase, int editIndex) {
        System.out.println("You chose Option " + optionNumber + ": " + optionHeader);
            System.out.println("What do you want to change " + studentArray[0] + "'s " + optionLowerCase + " to?");
            System.out.print("New " + optionLowerCase + " >> ");
            String update = scan.nextLine().trim();

            studentArray[editIndex] = update;
    }


    /**
     * Function name: confirmStudentIDHELPER
     * @return (String)
     * 
     * Inside function:
     *  1. print the instructions and takes user input
     *  2. switch statement to sort user input
     *      a. some user inputs cause other function to run
     *      b. returns String of either specific code or user's original input (student ID attempt)
     */
    public static String confirmStudentIDHELPER() {
        System.out.println("\nTo search for a student, their student ID number is required.");
        System.out.println("\t- If you know the student's ID number, please enter it below.");
        System.out.println("\t- If you do not know what the student's ID number is, please enter 'filter', where you will instead be prompted to filter the student directory. Or enter 'full' to print the full directory.");
        System.out.println("\t- If you want to exit to the main options, please enter 'exit'");
        System.out.print("\nEnter student ID number, 'filter', 'full', or 'exit' >> ");
        String choice = scan.nextLine().trim().toLowerCase();

        switch (choice) {
            case "filter":
                System.out.println("\nYou chose to filter the student directory.");
                filterDirectory();
                return "retry";
            case "full":
                System.out.println("\nYou chose to print the full student directory.");
                printFullDirectory();
                return "retry";
            case "exit":
                System.out.println("\nYou chose to exit to the main options");
                return "exit";
            default: return choice; // this should be a student ID (or at least an attempt)
        }
    }


    /**
     * Function name: sortByColumnHELPER
     * @param columnIndex (int)
     * @param numeric (boolean)
     * 
     * Inside function:
     *  1. uses a simple selection sort on the directory list
     *      a. for each position i in the list, finds the "smallest" item from i to the end
     *      b. "smallest" is decided by the compareRows(a, b, columnIndex, numeric) function
     *  2. swaps the student at i with the studen that should go in position i
     *  3. repeats until every student is sorted corrected
     */
    public static void sortByColumnHELPER(int columnIndex, boolean numeric) {
        for (int i = 0; i < (directory.size() - 1); i++) {
            int minIndex = i;
            for (int j = i + 1; j < directory.size(); j++) {
                if (compareRowsHELPER(directory.get(j), directory.get(minIndex), columnIndex, numeric) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                // swaps rows i and minIndex
                String[] temp = directory.get(i);
                directory.set(i, directory.get(minIndex));
                directory.set(minIndex, temp);
            }
        }
    }


    /**
     * Function name: compareRowsHELPER
     * @param a (String[])
     * @param b (String[])
     * @param columnIndex (int)
     * @param numeric (boolean)
     * @return (int)
     * 
     * Inside function:
     *  1. get the two values at columnIndex as Strings
     *  2. if numeric is true:
     *      a. concert both to ints and return intA - intB
     *     otherwise:
     *      b. compare them as text and return that result
     */
    public static int compareRowsHELPER(String[] a, String[] b, int columnIndex, boolean numeric) {
        String s1 = a[columnIndex];
        String s2 = b[columnIndex];

        if (numeric) {
            int v1 = Integer.parseInt(s1);
            int v2 = Integer.parseInt(s2);
            return (v1 - v2);
        }
        else {
            return s1.compareToIgnoreCase(s2);
        }
    }
}