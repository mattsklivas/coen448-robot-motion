import java.io.StringBufferInputStream;
import java.util.*;

public class runner {
    // Entrypoint of program execution
    public static Room room = new Room();

    public static void main(String[] args) throws Exception {

        List<Object> commandResult;
        Scanner sc = new Scanner(System.in);

        while(true) {
            // Request new commands from the user
            System.out.print("Enter command: ");
            commandResult = parseCommand(sc.nextLine());

            // Exit the program if the command Q has been entered
            if (commandResult.get(0).equals(true)){
                break;
            }

            // Update the room object with the new command
            room = (Room) commandResult.get(1);
        }
    }

    public static List<Object> parseCommand(String command) {
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

        Set<String> knownCommandsAlpha = new HashSet<String>();
        Set<String> knownCommandsAlphaNum = new HashSet<String>();

        formatKnownCommands(knownCommandsAlpha, knownCommandsAlphaNum);

        // Set any alpha characters to lowercase and trim the string
        command = command.toLowerCase().trim();

        // Check if command is at least 1 character long
        if (command.length() == 0) {
            System.out.println("\nError: No command entered. Please try again...");
            return formatOutput(false, room);
        }

        char option = command.charAt(0);

        // Check if first symbol represents a known command
        if (knownCommandsAlpha.contains(option)) {
            // Check if there's any additional arguments included
            if (command.length() > 1) {
                System.out.println("\nError: Extra arguments included in command. Please try again...");
                return formatOutput(false, room);
            }

            // TODO: Check if room has been initialized before proceeding with any commands other than "q"
//            if (!boardSet && option != 'q') {
//                System.out.println("\nError: System must be initialized before executing the command provided. Please try again...");
//                return false;
//            }
            if (option == 'q') {
                System.out.println("\nExiting program...");
                return formatOutput(true, room);
            }
            else if (room.isInitialized()) {
                switch(option) {
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
                        System.out.println("\nError: Command not recognized. Please try again...");
                }
            } else {
                // todo: add format for initialize command
                System.out.println("\nError: Room must be initialized before executing the command provided. ");
                return formatOutput(false, room);
            }

            // Switch case here
        } else if (knownCommandsAlphaNum.contains(option) && command.length() > 1) {
            String param = "";
            int parsedParam = -1;

            // Get the command's integer parameter if it exists
            if (command.charAt(1) == ' ') {
                // Check if an integer parameter is included
                if (command.length() == 2) {
                    System.out.println("\nError: Numerical parameter required for the provided command. Please try again...");
                    return formatOutput(false, room);
                }

                param = command.substring(2);
            } else {
                param = command.substring(1);
            }

            // Check if the extra parameter is an integer
            if (!param.matches("[0-9]")) {
                System.out.println("\nError: Numerical parameter required for the provided command. Please try again...");
                return formatOutput(false, room);
            } else {
                try {
                    // Parse the provided parameter to an integer
                    parsedParam = Integer.parseInt(param);
                } catch (NumberFormatException e) {
                    System.out.println("\nError: Unable to parse provided command parameter. Please try again...");
                    return formatOutput(false, room);
                }
            }

            switch(option) {
                case 'm':
                    // TODO: Check if room has been initialized before proceeding with any commands other than "i"
                    if (room.isInitialized()) {
                        // do something...
                    } else {
                        System.out.println("\nError: Room must be initialized before executing the command provided. ");
                        return formatOutput(false, room);
                    }
                case 'i':
                    if (room.isInitialized()) {
                        System.out.println("\nError: Room has already been initialized. Please enter a different command...");
                        return formatOutput(false, room);
                    } else {
                        // todo: string format
                        System.out.println("Initializing floor...");
                        return Arrays.asList(false, new Room(parsedParam));
                    }
                default:
                    System.out.println("\nError: Command not recognized. Please try again...");
            }

            return formatOutput(false, room);
        } else {
            System.out.println("\nError: Invalid command entered. Please try again...");
            return formatOutput(false, room);
        }

        return formatOutput(false, room);
    }

    public static List<Object> formatOutput(boolean isExit, Room room) {
        return Arrays.asList(isExit, room);
    }

    public static void formatKnownCommands(Set<String> alpha, Set<String> alphaNum) {
        alpha.add("u");
        alpha.add("d");
        alpha.add("r");
        alpha.add("l");
        alpha.add("p");
        alpha.add("c");
        alpha.add("q");

        alphaNum.add("m");
        alphaNum.add("i");
    }
}
