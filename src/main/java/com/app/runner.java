package com.app;

import java.util.*;

public class runner {
    // Entrypoint of program execution
    public static Room room = new Room();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while(true) {
            // Request new commands from the user
            System.out.print("Enter command: ");
            boolean isExit = parseCommand(sc.nextLine());

            // Exit the program if the command Q has been entered
            if (isExit){
                break;
            }

            System.out.println();
        }

        sc.close();
    }

    private static boolean parseCommand(String command) {
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

        Set<Character> knownCommandsAlpha = new HashSet<Character>();
        Set<Character> knownCommandsAlphaNum = new HashSet<Character>();

        formatKnownCommands(knownCommandsAlpha, knownCommandsAlphaNum);

        // Set any alpha characters to lowercase and trim the string
        command = command.toLowerCase().trim();

        // Check if command is at least 1 character long
        if (command.length() == 0) {
            System.out.println("Error: No command entered. Please try again...");
            return false;
        }

        char option = command.charAt(0);

        // Check if first symbol represents a known command
        if (knownCommandsAlpha.contains(option)) {
            // Check if there's any additional arguments included
            if (command.length() > 1) {
                System.out.println("Error: Extra arguments included in command. Please try again...");
                return false;
            }

            if (option == 'q') {
                System.out.println("\nExiting program...");
                return true;
            }
            else if (room.isInitialized()) {
                switch(option) {
                    case 'u':
                        // move pen up
                        movePenDown(false);
                        break;
                    case 'd':
                        // move pen down
                        movePenDown(true);
                        break;
                    case 'r':
                        // turn robot right
                        room.getRobot().setRobotDirection(1);
                        System.out.println(String.format("Robot is now facing %s", room.getRobot().getRobotDirDescription()));
                        break;
                    case 'l':
                        // turn robot left
                        room.getRobot().setRobotDirection(-1);
                        System.out.println(String.format("Robot is now facing %s", room.getRobot().getRobotDirDescription()));
                        break;
                    case 'p':
                        System.out.println("Floor printout:");
                        room.printFloor();
                        break;
                    case 'c':
                        room.printRobotState();
                        break;
                    default:
                        System.out.println("Error: Command not recognized. Please try again...");
                }
            } else {
                printInitializedError();
                return false;
            }

            // Switch case here
        } else if (knownCommandsAlphaNum.contains(option) && command.length() > 1) {
            String param = "";
            int parsedParam = -1;

            // Get the command's integer parameter if it exists
            if (command.charAt(1) == ' ') {
                // Check if an integer parameter is included
                if (command.length() == 2) {
                    System.out.println("Error: Numerical parameter required for the provided command. Please try again...");
                    return false;
                }

                param = command.substring(2);
            } else {
                param = command.substring(1);
            }

            // Check if the extra parameter is an integer
            if (!param.matches("^[0-9]*[1-9][0-9]*$")) {
                System.out.println("Error: Positive numerical parameter required for the provided command. Please try again...");
                return false;
            } else {
                try {
                    // Parse the provided parameter to an integer
                    parsedParam = Integer.parseInt(param);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Unable to parse provided command parameter. Please try again...");
                    return false;
                }
            }

            switch(option) {
                case 'm':
                    if (room.isInitialized()) {
                        System.out.println("Moving robot...");
                        room.moveRobot(parsedParam);
                    } else {
                        printInitializedError();
                    }
                    break;
                case 'i':
                    if (room.isInitialized()) {
                        System.out.println("com.app.Room is being reinitialized...");
                        room = new Room(parsedParam);
                        room.printRobotState();
                    } else {
                        room = new Room(parsedParam);
                        System.out.println("Initializing floor...");
                    }
                    break;
                default: {
                    System.out.println("Error: Command not recognized. Please try again...");
                }
            }
        } else {
            System.out.println("Error: Invalid command entered. Please try again...");
        }

        return false;
    }

    // State = do we want to move pen down
    // curr = up = false
    // state = down = true
    private static void movePenDown(boolean state) {
        if (room.getRobot().isPenDown() == state) {
            System.out.println( String.format("Pen is already in the %s position.", room.getRobot().getPenPosition()));
        } else {
            room.getRobot().setIsPenDown(state);
            System.out.println(String.format("Pen is now in the %s position.", room.getRobot().getPenPosition()));
        } 
    }

    private static void printInitializedError() {
        System.out.println("Error: com.app.Room must be initialized before executing the command provided.");
        System.out.println("Initialize command: I <n> | i <n>");
        System.out.println("n: size of the room (n x n)");
    }

    private static void formatKnownCommands(Set<Character> alpha, Set<Character> alphaNum) {
        alpha.add('u');
        alpha.add('d');
        alpha.add('r');
        alpha.add('l');
        alpha.add('p');
        alpha.add('c');
        alpha.add('q');

        alphaNum.add('m');
        alphaNum.add('i');
    }
}
