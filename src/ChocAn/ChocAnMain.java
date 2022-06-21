package ChocAn;

import jdk.jshell.execution.Util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChocAnMain extends Utils
{
    public ChocAnMain(){ }

    public static void main (String[] args)
    {
        ChocAnTerminal terminal;
        int selection;


        do {
            System.out.println("Welcome to the ChocAn Simulator");
            System.out.println("1 - Provider Terminal");
            System.out.println("2 - Manager Terminal");
            System.out.println("3 - Exit");

            selection = new Utils().getMenuChoice(1,3);

            //Create a provider Terminal and run
            if (selection == 1) {
                terminal = new ChocAnProviderTerminal();
                ((ChocAnProviderTerminal) terminal).run();
                if(confirmExit())
                    selection = 3;
            }

            //Create a Manager Terminal and run
            else if (selection == 2) {
                terminal = new ChocAnManagerTerminal();
                ((ChocAnManagerTerminal) terminal).run();
                if(confirmExit())
                    selection = 3;
            }

        } while (selection != 3);
    }

    //Runs when user closes a terminal
    //Gives option to launch another terminal or exit simulator completely
    private static Boolean confirmExit()
    {
        int sel;

        do {
            System.out.println("\nWould you like to launch another terminal?");
            System.out.println("1 - Yes");
            System.out.println("2 - No, exit the simulator");

            sel = new Utils().getMenuChoice(1,2);

        } while(sel != 1 && sel != 2);

        return sel == 2;
    }
}