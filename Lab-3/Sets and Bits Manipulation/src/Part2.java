import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {
    public static void clearConsole() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();   
    } 

    public static void main(String[] args) throws Exception {


        ArrayList<String> universeList = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);

        // a)
        while(true){

            
            System.out.println("Enter the universe set as a list of words separated by commas ( a, b, c ):");
            String universeIn = scanner.nextLine();

            // Check if input is empty
            if (universeIn.isBlank()) {
                clearConsole();
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }

            // Split the input, trim each word, and remove any empty elements
            universeList = new ArrayList<String>(
                                    Arrays.stream(universeIn.split(","))
                                  .map(String::trim) // Trim spaces
                                  .filter(word -> !word.isEmpty()) // Remove empty strings
                                  .toList() // Convert to a list
                                );

            // Removes all spaces in each string
            for (int i=0; i<universeList.size(); i++){
                universeList.set(i, universeList.get(i).replaceAll("\\s", ""));
            }

            clearConsole();
            break;

        }

        Set sets = new Set(universeList);
        int numSubSets;

        //b)
        while(true){
            System.out.println("Universe: " + sets.getUniverse());
            System.out.println("Enter the number of sets: ");
            

            try {
                numSubSets = scanner.nextInt();
                scanner.nextLine();    
            } catch (Exception e) {
                scanner.nextLine();
                clearConsole();
                System.out.println("Enter a valid number!");
                continue;
            }

            if (numSubSets <= 0){
                clearConsole();
                System.out.println("Enter a number thats > 0 !");
                continue;   
            }

            clearConsole();

            for (int i=1; i<=numSubSets; i++){
                System.out.println("Universe: " + sets.getUniverse());
                System.out.println("Enter elements of set " + i + " separated by commas: ");
                String setString = scanner.nextLine();
                ArrayList<String> setList = new ArrayList<String>();

                // Check if input is empty
                if (setString.isBlank()) {
                    clearConsole();
                    System.out.println("Input cannot be empty. Please try again.");
                    i--;
                    continue;
                }

                // Split the input, trim each word, and remove any empty elements
                setList = new ArrayList<String>(
                                    Arrays.stream(setString.split(","))
                                    .map(String::trim) // Trim spaces
                                    .filter(word -> !word.isEmpty()) // Remove empty strings
                                    .toList() // Convert to a list
                                );

                int error =  0;
                
                for (int j=0; j<setList.size(); j++){
                    // Removes all spaces in each string
                    setList.set(j, setList.get(j).replaceAll("\\s", ""));
                    
                    // Checks if any element in the current entered set is not in the universe
                    // if (universeList.indexOf(setList.get(j)) == -1){
                    if( ! sets.isInUniverse(setList.get(i))){
                        clearConsole();
                        System.out.println("Enter elements that exist in the universe");
                        i--;
                        error = 1;
                        break;

                    }
                }

                if (error == 1){
                    continue;
                }
                
                clearConsole();
                System.out.println("" + setList);
                
                for (int j=0; j<setList.size(); j++){
                    sets.addToSet(""+i, setList.get(j));
                }

            }

            clearConsole();
            break;

        }

        // c)
        String lastResult = "";
        while (true) {
            
            int set1 = 0;
            int set2 = 0;


            System.out.println("Enter the number of operation");
            System.out.println("0. Quit Program");
            System.out.println("1. Union of two sets");
            System.out.println("2. Intersection of two sets");
            System.out.println("3. Complement of a set");
            System.out.println("4. Difference between two sets");
            System.out.println("5. Cardinality of a set");
            System.out.println("6. Print a set");
            System.out.println("Last Reuslt: " + lastResult);

            String operation = scanner.nextLine().trim();
            int operationInt = 0;

            switch (operation) {
                case "0":
                case "0.":
                    operationInt = 0;

                    break;

                case "1":
                case "1.":
                    operationInt = 1;

                    break;
            
                case "2":
                case "2.":
                    operationInt = 2;

                    break;

                case "3":
                case "3.":
                    operationInt = 3;


                    break;

                case "4":
                case "4.":
                    operationInt = 4;

                    break;

                case "5":
                case "5.":
                    operationInt = 5;

                    break;

                case "6":
                case "6.":
                    operationInt = 6;


                    break;

                default:
                    clearConsole();
                    System.out.println("Enter a valid number!");
                    continue;
            }

            clearConsole();

            if (operationInt == 0){
                // exit program
                break;
            }

            // 2 sets operations
            if (operationInt == 1 || operationInt == 2 || operationInt == 4){
                if (numSubSets == 1){
                    clearConsole();
                    System.out.println("There's only 1 set!");
                    continue;
                }

                while (true) {
                    
                    System.out.println("Choose the 2 sets separated by a comma (ex: 1, 3): ");
                    for (int i=1; i<=numSubSets; i++){
                        System.out.println(i+". " + sets.getSetFromString(i+""));
                    }

                    String input = scanner.nextLine().trim();

                    String[] parts = input.split(",");
                    if (parts.length != 2) {
                        clearConsole();
                        System.out.println("Invalid Input!");
                    }

                    try {
                        set1 = Integer.parseInt(parts[0].trim());
                        set2 = Integer.parseInt(parts[1].trim());

                    } catch (Exception e) {
                        clearConsole();
                        System.out.println("Invalid Input!");
                    }
                    
                    // Check if both numbers are within the valid range
                    if (set1 > 0 && set1 <= numSubSets && set2 > 0 && set2 <= numSubSets) {

                        break;
                    } else {
                        clearConsole();
                        System.out.println("Invalid Input!");
                    }


                }

                switch (operationInt) {
                    case 1:
                        lastResult = "Union Answer: " + sets.union(set1+"", set2+""); 
                        break;

                    case 2:
                        lastResult = "Intersection Answer: " + sets.intersection(set1+"", set2+""); 
                        break;

                    case 4:
                        lastResult = "Difference Answer: " + sets.difference(set1+"", set2+""); 
                        break;
                
                    default:
                        System.out.println("You shouldn't be here???");
                        break;
                }

                clearConsole();
                continue;
                            
            }

            // 1 set operations
            else{

                while (true) {
                    
                    System.out.println("Choose a set (ex: 1 ): ");
                    if (operationInt == 6){
                        System.out.println(0+". (Universe) " + sets.getUniverse());
                    }

                    for (int i=1; i<=numSubSets; i++){
                        System.out.println(i+". " + sets.getSetFromString(i+""));
                    }

                    String input = scanner.nextLine().trim();

                    try {
                        set1 = Integer.parseInt(input);
                     
                    } catch (Exception e) {
                        clearConsole();
                        System.out.println("Invalid Input!");
                    }

                    if ((set1 > 0 && set1 <= numSubSets) || (operationInt == 6) && (set1 >= 0 && set1 <= numSubSets) ) {

                        break;
                    } else {
                        clearConsole();
                        System.out.println("Invalid Input!");
                    }
                }


                switch (operationInt) {
                    case 3:
                        lastResult = "Complement Answer: " + sets.complement(set1+"");
                        break;

                    case 5:
                        lastResult = "Cardinality Answer: " + sets.cardinality(set1+""); 
                        break;

                    case 6:
                        if (set1 == 0){
                            lastResult = "Print Answer: " + sets.getUniverse(); 
                        }
                        else lastResult = "Print Answer: " + sets.getSetFromString(set1+""); 
                        break;
                
                    default:
                        System.out.println("You shouldn't be here???");
                        break;
                }

                clearConsole();
                continue;
            }
                
        }
    
        scanner.close();
    }
}
