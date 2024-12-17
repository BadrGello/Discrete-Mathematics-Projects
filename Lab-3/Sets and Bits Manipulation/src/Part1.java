import java.util.Scanner;


public class Part1 {

    public static void clearConsole() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();   
    } 
    public static void main(String[] args) throws Exception{
        BitsManipulator bitsManipulator = new BitsManipulator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("0. Exit");
            System.out.println("1. Get Bit");
            System.out.println("2. Clear Bit");
            System.out.println("3. Set Bit");
            System.out.println("4. Update Bit");
            System.out.print("Enter your choice: ");

            // Try and scan for the operation choice, if there are error, continue the loop (reloop)
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                clearConsole();
                System.out.println("Invalid Input!");
                continue;
            };

            // Exit choice
            if (choice == 0) {
                
                break;
            }

            // If not a valid choice, contiune the loop (reloop)
            if (!(choice == 1 || choice == 2 || choice == 3 || choice == 4 )){
                clearConsole();
                System.out.println("Invalid Input!");
                continue;
            }


            // Try and scan for the number, if there are errors, continue the loop (reloop)
            System.out.print("Enter the number: ");
            int number = 0;
            try {
                number = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                clearConsole();
                System.out.println("Invalid Input!");
                continue;
            };

            // Try and scan for the position of the bit, if there are errors, continue the loop (reloop)
            System.out.print("Enter the position: ");
            int position = 0;
            try {
                position = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                clearConsole();
                System.out.println("Invalid Input!");
                continue;
            };

            while (true){
                
                switch (choice) {
                    case 1: // Get Bit
                        System.out.println("Bit at position " + position + " is " + bitsManipulator.getBit(number, position));
                        break;

                    case 2: // Clear Bit
                        int clearedNumber = bitsManipulator.clearBit(number, position);
                        System.out.println("Number after clearing bit: " + clearedNumber);
                        break;

                    case 3: // Set Bit
                        int setNumber = bitsManipulator.setBit(number, position);
                        System.out.println("Number after setting bit: " + setNumber);
                        break;

                    case 4: // Update Bit
                        System.out.print("Enter the new value (true for 1, false for 0): ");
                        boolean value = false;
                        try {
                            value = scanner.nextBoolean();
                            scanner.nextLine();
                        } catch (Exception e) {
                            scanner.nextLine();
                            clearConsole();
                            System.out.println("Invalid Input!");
                            continue;
                        };
                        int updatedNumber = bitsManipulator.updateBit(number, position, value);
                        System.out.println("Number after updating bit: " + updatedNumber);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                System.out.println();
                break;
            }
        }

        scanner.close();
    }
}

    
