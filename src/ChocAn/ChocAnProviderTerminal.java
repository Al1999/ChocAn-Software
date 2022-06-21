package ChocAn;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ChocAnProviderTerminal extends ChocAnTerminal
{
    private String userNumber;


    public ChocAnProviderTerminal() {
        super();
    }


    //attempts to log the user in; lets them try as many times as they want.
    //if they succeed, it runs displayMenu().
    public void run() {
        int tryAgain = 1;
        while (tryAgain == 1) {
            System.out.print("Enter a valid provider number to access the provider terminal: ");
            userNumber = getString();
            if (login(userNumber)) {
                displayMenu();
                tryAgain = 0;       //assume that the user doesn't want to try again after a success
            }
            else {
                System.out.print("ERROR: Invalid provider number. Enter 1 to try again: ");
                tryAgain = getInt();
            }
        }
    }


    //Provider terminal menu display
    protected void displayMenu() {
        int selection;
        Member valid_member = null;

        System.out.println("\n\n--ChocAn Provider Terminal--");

        do {
            System.out.println("1 - Enter Member Number");
            if(valid_member == null) {
                System.out.println("2 - Bill Service - No member validated");
            }
            else {
                System.out.println("2 - Bill Service - For validated member: " + valid_member.MemberName());
            }
            System.out.println("3 - Email Provider Directory");
            System.out.println("4 - Exit");

            selection = getMenuChoice(1,4);

            //enter/validate member number
            if (selection == 1){
                System.out.print("Enter a valid member number: ");
                String memberNumber = getString();
                valid_member = validateMember(memberNumber);
                if(valid_member != null)
                    System.out.println("Validation successful!");
                else
                    System.out.println("Validation failed!");
            }

            //bill a service
            else if (selection == 2) {
                if(valid_member == null){
                    System.out.println("Please validate a member first.");
                }
                else {
                    ServiceRecord record = new ServiceRecord();
                    //Attempt to create service record.
                    if(record.read((Provider) ProviderList.match(userNumber), valid_member, ServiceList)) {
                        ServiceRecordList.add(record);
                        saveLists();
                    }
                    //Upon failure indicate so.
                    else{
                        System.out.println("Billing aborted.");
                    }
                }
            }

            //email provider directory
            else if (selection == 3) {
                Service temp;
                try {
                    File file = new File("Directory_Email/" + userNumber + "_Directory.txt");
                    BufferedWriter directory = new BufferedWriter(new FileWriter(file));
                    directory.write("\tChocoholics Anonymous Service Directory\n" +
                        "\n\nThank you for requesting your covered services directory!\n" +
                        "Below is a list of the service codes, the covered service, and the\n" +
                        "amount you will be compensated for providing the service to our member.\n\n" +
                        "======================================================================\n");
                    for(int i = 0; i < ServiceList.size(); i++){
                        temp = (Service)ServiceList.get(i);
                        directory.write(temp.Code() + ":\t\t" + temp.Name() + "\n" +
                                "Approved fee:\t$" + formatMoney(temp.Price()) + "\n\n");
                    }
                    directory.close();
                    System.out.println("\nYour Service Directory is being emailed now.\n");
                }
                catch(Exception e){
                    System.out.println("Error creating provider directory");
                }
            }

        } while(selection != 4);
    }



    //this function has a confusing flow of logic.
        //the central if-block is the only happy path, and immediately returns the verified member.
        //all other paths are sad paths for some reason or another. all of them continue on until the function end,
        //where they return null.
    protected Member validateMember(String memberNumber){
        Member to_verify = (Member) MemberList.match(memberNumber);
        if (to_verify != null) {                           //if the member number is valid,
            if (to_verify.verify()) {                      //and the member has no unpaid fees,
                System.out.println("Validated!");       //the member is valid.
                return to_verify;
            } else                                      //if the member has unpaid fees...
                System.out.println("ERROR: Member has unpaid fees!");
        }
        else                                            //if the member number is not valid...
            System.out.println("ERROR: Member does not exist!");

        return null;
    }


    protected boolean login(String userNumber) {
        //returns true if it finds a provider that matches the given userNumber,
            //false otherwise
        return ProviderList.match(userNumber) != null;
    }
}