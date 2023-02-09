package com.app;

import java.util.*;

public class runner {
    // Room object
    public static Room room = new Room();

    public static Set<Character> knownCommandsAlpha = new HashSet<Character>();
    public static Set<Character> knownCommandsAlphaNum = new HashSet<Character>();

    // Entrypoint of program execution
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        formatKnownCommands(knownCommandsAlpha, knownCommandsAlphaNum);
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


    public static char getOption(String command) {
        command = command.toLowerCase().trim();
        // Check if command is at least 1 character long
        if (command.length() == 0) {
            return ' ';
        }
        return command.charAt(0);
    }


    public static String getParam(String command) {
        // Get the command's integer parameter if it exists
        if (command.length() <= 1) {
            return null;
        }

        if (command.charAt(1) == ' ') {
            // Check if an integer parameter is included
            if (command.length() == 2) {
                System.out.println("Error: Numerical parameter required for the provided command. Please try again...");
                return null;
            }

            return command.substring(2).strip();
        } else {
            return command.substring(1).strip();
        }
    }


    public static boolean validateSingletonCommand(String command) {
        // Check if the command is a known command
        if (command.length() > 1) {
            System.out.println("Error: Extra arguments included in command. Please try again...");
            return false;
        }

        return true;
    }


    public static boolean checkForExit(char option) {
        if (option == 'q') {
            System.out.println("\nExiting program...");
            return true;
        }

        return false;
    }


    public static boolean checkForEmptyOption(char option) {
        if (option == ' ') {
            System.out.println("Error: No command entered. Please try again...");
            return true;
        }

        return false;
    }


    public static boolean validateParamFormat(String param) {
        // Check if the extra parameter is an integer
        if (!param.matches("[0-9]+")) {
            System.out.println("Error: Positive non-zero numerical parameter required for the provided command. Please try again...");
            return false;
        } else {
            try {
                // Parse the provided parameter to an integer
                if (Integer.parseInt(param) > 100) {
                    System.out.println("Error: Numerical parameter provided is too large. Please try again...");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Unable to parse provided command parameter. Please try again...");
                return false;
            }
        }

        return true;
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

        // Set any alpha characters to lowercase and trim the string
        command = command.toLowerCase().strip();

        char option = getOption(command);

        // Check if the command is empty
        if (checkForEmptyOption(option)) {
            return false;
        }

        // Check if first symbol represents a known command
        if (knownCommandsAlpha.contains(option)) {
            // Check if there's any additional arguments included
            if (!validateSingletonCommand(command)) {
                return false;
            }

            // Check if the command is Q
            if (checkForExit(option)) {
                return true;
            }

            else if (room.isInitialized()) {
                switch (option) {
                    case 'u' -> movePenDown(false);
                    case 'd' -> movePenDown(true);
                    case 'r' -> {
                        room.getRobot().setRobotDirection(1);
                        System.out.printf("Robot is now facing %s%n", room.getRobot().getRobotDirDescription());
                    }
                    case 'l' -> {
                        room.getRobot().setRobotDirection(-1);
                        System.out.printf("Robot is now facing %s%n", room.getRobot().getRobotDirDescription());
                    }
                    case 'p' -> {
                        System.out.println("Floor printout:");
                        room.printFloor();
                    }
                    case 'c' -> room.printRobotState();
                    default -> System.out.println("Error: Command not recognized. Please try again...");
                }
            } else {
                printInitializedError();
                return false;
            }

        } else if (knownCommandsAlphaNum.contains(option) && command.length() > 1) {
            String param = "";
            int parsedParam = -1;

            // Get the command's integer parameter if it exists
            param = getParam(command);

            // Check if the extra parameter is an integer
            if (!validateParamFormat(param)) {
                return false;
            }

            // convert the parameter to an integer
            parsedParam = Integer.parseInt(param);

            switch(option) {
                case 'm' -> {
                    if (room.isInitialized()) {
                        System.out.println("Moving robot...");
                        room.moveRobot(parsedParam);
                    } else {
                        printInitializedError();
                    }
                }
                case 'i' -> {
                    if (room.isInitialized()) {
                        System.out.println("Room is being reinitialized...");
                        room = new Room(parsedParam);
                        room.printRobotState();
                    } else {
                        System.out.println("Initializing floor..." + parsedParam);
                        room = new Room(parsedParam);
                        System.out.println("Initializing floor...");
                    }
                }
                default -> {
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
            System.out.printf("Pen is already in the %s position.%n", room.getRobot().getPenPosition());
        } else {
            // room.getRobot().setIsPenDown(state);
            room.movePen(state);
            System.out.printf("Pen is now in the %s position.%n", room.getRobot().getPenPosition());
        } 
    }


    private static void printInitializedError() {
        System.out.println("Error: Room must be initialized before executing the command provided.");
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
