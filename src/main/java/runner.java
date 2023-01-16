import java.util.*;

public class runner {
    //Entrypoint of program execution
    public static void main(String[] args) throws Exception {

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter command: ");

            Boolean result = parseCommand(sc.nextLine());
            System.out.print(result);


        }

    }

    public static Boolean parseCommand(String command) {
        // 1. Set to lowercase
        // 2. Remove leading/trailing spaces
        // 3. Check if first symbol is correct (letter (capital or lower) and must be in list of known commands)
        // 4. Check if additional argument needed for given command
        // 5. Check if grid has been initialized before proceeding with a command

        return true;
    }
}
