import java.util.*;

public class runner {
    // Entrypoint of program execution
    public static void main(String[] args) throws Exception {

        boolean isExit;
        Scanner sc = new Scanner(System.in);

        while(true) {
            // Request new commands from the user
            System.out.print("Enter command: ");
            isExit = parseCommand(sc.nextLine());

            // Exit the program if the command Q has been entered
            if (isExit){
                break;
            }
        }
    }

    public static boolean parseCommand(String command) {
        /*
        AVAILABLE STANDALONE COMMANDS:
        [U|u] Pen up
        [D|d] Pen down
        [R|r] Turn right
        [L|l] Turn left
        [P|p] Print the NxN array + display the indices
        [C|c] Print current position of the pen, whether it is up or down + its facing direction
        [Q|q] Stop program

        AVAILABLE COMMANDS WITH MULTIPLE PARAMETERS:
        [M s|m s] Move forward s spaces (s is non-negative), zero or one space between m and s
        [I n|i n] Initialize the system
        */
        Character[] knownCommandsAlpha = {'u', 'd', 'r', 'l', 'p', 'c', 'q'};
        Character[] knownCommandsAlphaNum = {'m', 'i'};

        // Set any alpha characters to lowercase and trim the string
        command = command.toLowerCase().trim();

        // Check if command is at least 1 character long
        if (command.length() == 0) {
            System.out.print("\nError: No command entered. Please try again...");
            return false;
        }

        char option = command.charAt(0);

        // Check if first symbol represents a known command
        if (Arrays.asList(knownCommandsAlpha).contains(option)) {
            // Check if there's any additional arguments included
            if (command.length() > 1) {
                System.out.print("\nError: Extra arguments included in command. Please try again...");
                return false;
            }

            // TODO: Check if room has been initialized before proceeding with any commands other than "q"
//            if (!boardSet && option != 'q') {
//                System.out.print("\nError: System must be initialized before executing the command provided. Please try again...");
//                return false;
//            }

            switch(option) {
                case 'q':
                    System.out.print("\nExiting program...");
                    break;
                case 'u':
                    break;
                case 'd':
                    break;
                case 'r':
                    break;
                case 'l':
                    break;
                case 'p':
                    break;
                case 'c':
                    break;
                default:
                    System.out.print("\nError: Command not recognized. Please try again...");
            }

            // Switch case here
        } else if (Arrays.asList(knownCommandsAlphaNum).contains(option) && command.length() > 1) {
            String param = "";
            int parsedParam = -1;

            // Get the command's integer parameter if it exists
            if (command.charAt(1) == ' ') {
                // Check if an integer parameter is included
                if (command.length() == 2) {
                    System.out.print("\nError: Numerical parameter required for the provided command. Please try again...");
                    return false;
                }

                param = command.substring(2);
            } else {
                param = command.substring(1);
            }

            // Check if the extra parameter is an integer
            if (!param.matches("[0-9]")) {
                System.out.print("\nError: Numerical parameter required for the provided command. Please try again...");
                return false;
            } else {
                try {
                    // Parse the provided parameter to an integer
                    parsedParam = Integer.parseInt(param);
                } catch (NumberFormatException e) {
                    System.out.print("\nError: Unable to parse provided command parameter. Please try again...");
                    return false;
                }
            }

            switch(option) {
                case 'm':
                    // TODO: Check if room has been initialized before proceeding with any commands other than "i"
//            if (!boardSet) {
//                System.out.print("\nError: System must be initialized before executing the command provided. Please try again...");
//                return false;
//            }
                    break;
                case 'i':
                    break;
                default:
                    System.out.print("\nError: Command not recognized. Please try again...");
            }

            return false;
        } else {
            System.out.print("\nError: Invalid command entered. Please try again...");
            return false;
        }

        return false;
    }
}
