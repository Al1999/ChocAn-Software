package ChocAn;

//utils class. Creates scanner object for IO
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

//Utility class, makes a scanner. Perhaps other things to be added as needed.
public class Utils {

    //Constructor
    public Utils() { }

    //Retrieves user input from the console
    //Takes two integer arguments indicating min/max menu option
    //Displays error message for non-integer input and invalid menu choice
    //Returns user selection
    public int getMenuChoice(int minMenuOption, int maxMenuOption)
    {
        int sel = Integer.MIN_VALUE;

        do {

            try {
                sel = new Scanner(System.in).nextInt();
            }
            //Catch non-integer input
            catch (InputMismatchException e) {
                System.out.println("-Error: Please enter a number-");
            }

            // If user has enter an integer not corresponding to a menu option
            if ((sel < minMenuOption || sel > maxMenuOption) && sel != Integer.MIN_VALUE) {
                System.out.println("-Error: Please select a valid menu option-");
                sel = Integer.MIN_VALUE;
            }
        } while (sel < minMenuOption || sel > maxMenuOption);

        return sel;
    }

    //Simple helper function that retrieves user input integer
    public int getInt()
    {
        int sel = Integer.MIN_VALUE;

        do {

            try {
                sel = new Scanner(System.in).nextInt();
            }
            //Catch non-integer input
            catch (InputMismatchException e) {
                System.out.println("-Error: Please enter a number-");
            }

        } while (sel == Integer.MIN_VALUE);

        return sel;
    }

    //Simple helper function that retrieves user input String
    public String getString()
    {
        String str = new Scanner(System.in).nextLine();
        return str;
    }

    //This function gets input, and ensures it returns a string based entirely of numbers 9 digits long.
    public String getNumber(){
        String user_input;
        Scanner input = new Scanner(System.in);
        //Regex pattern for 9 digits number
        Pattern num_pat = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d\\d");
        user_input = input.nextLine();

        //Verifies pattern match, gets input on fail
        while(!num_pat.matcher(user_input).matches()){
            System.out.println("Invalid input. Number must be 9 digits long and only contain numbers.");
            user_input = input.nextLine();
        }

        return user_input;
    }
    //This function gets input, and ensures it returns a string based entirely of numbers 9 digits long.
    public String getZip(){
        String user_input;
        Scanner input = new Scanner(System.in);
        //Regex pattern for 9 digits number
        Pattern num_pat = Pattern.compile("\\d\\d\\d\\d\\d");
        user_input = input.nextLine();

        //Verifies pattern match, gets input on fail
        while(!num_pat.matcher(user_input).matches()){
            System.out.println("Invalid input. Zip codes must be 5 digits long and only contain numbers.");
            user_input = input.nextLine();
        }

        return user_input;
    }

    public String formatMoney(int pennies){
        String format;
        Number value;
        format = "%.2f";
        value = pennies / 100.0;
        return String.format(Locale.US, format, value);
    }
}
