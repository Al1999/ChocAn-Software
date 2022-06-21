package ChocAn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Member extends ChocAnDataItem {

    // Private data members
    private String memberNumber;
    private String memberName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private int feesDue;

    // Public getters
    public String MemberNumber() { return memberNumber; }
    public String MemberName() { return memberName; }
    public String StreetAddress() { return streetAddress; }
    public String City() { return city; }
    public String State() { return state; }
    public String ZipCode() { return zipCode; }
    public int FeesDue() { return  feesDue; }

    public Member(){
        super();
    }

    public Member(String _memberNumber, String _memberName,
                  String _streetAddress, String _city, String _state, String _zipCode, int _feesDue){

        super();

        this.memberNumber = _memberNumber;
        this.memberName = _memberName;
        this.streetAddress = _streetAddress;
        this.city = _city;
        this.state = _state;
        this.zipCode = _zipCode;
        this.feesDue = _feesDue;
    }



    //Save outputs all private data members to file passed in, in the form of a BufferedWriter.
    //This replaces any commas in the string with ',/' and then separates the items by ', '
    public boolean save(BufferedWriter save_to) throws IOException {
        save_to.write("Member, " + this.memberNumber.replaceAll(",",",/")
                + ", " + this.memberName.replaceAll(",", ",/")
                + ", " + this.streetAddress.replaceAll(",", ",/")
                + ", " + this.city.replaceAll(",", ",/")
                + ", " + this.state.replaceAll(",", ",/")
                + ", " + this.zipCode.replaceAll(",", ",/")
                + ", " + feesDue + "\n");
        return true;
    }



    //Loads data from a file, then populates private data members with it.
    //This ingests the data in the format given in the save function.
    //All instances of ',/' will be replaced by ',' and data items are separated by ', '
    public boolean load(BufferedReader load_from) throws IOException {
        String readRaw = load_from.readLine();
        if (readRaw == null){return false;}
        String[] readLine = readRaw.split(", ");
        this.memberNumber = readLine[1].replaceAll(",/", ",");
        this.memberName = readLine[2].replaceAll(",/", ",");
        this.streetAddress = readLine[3].replaceAll(",/", ",");
        this.city = readLine[4].replaceAll(",/", ",");
        this.state = readLine[5].replaceAll(",/", ",");
        this.zipCode = readLine[6].replaceAll(",/", ",");
        this.feesDue = Integer.valueOf(readLine[7]);
        return true;
    }



    //Returns if the member number passed in matches current objects number.
    public boolean match(String to_match) {
        if (to_match == null || this.memberNumber == null){
            return false;
        }
        return this.memberNumber.equals(to_match);
    }



    //Same as above
    public boolean matchMember(String to_match) {
        return this.memberNumber.equals(to_match);
    }



    //Always false. Implemented to satisfy abstract parent class methods
    public boolean thisWeek(){
        return false;
    }



    //Take user input and set the values of private data members to user input.
    public void read(){
        //User input
        Scanner input = new Scanner(System.in);

        System.out.println("      Member Information      ");
        System.out.println("______________________________");
        System.out.print("Member Number:     ");
        this.memberNumber = this.getNumber();
        System.out.print("Member Name:       ");
        this.memberName = input.nextLine();
        System.out.print("Street Address:    ");
        this.streetAddress = input.nextLine();
        System.out.print("City:              ");
        this.city = input.nextLine();
        System.out.print("State:             ");
        this.state = input.nextLine();
        System.out.print("Zip Code:          ");
        this.zipCode = this.getZip();
        System.out.print("Fees Due:          ");
        while (!input.hasNextInt()) {
            System.out.println("ERROR: Numbers only");
            System.out.print("Fees due: ");
            input.next();
        }
        this.feesDue = input.nextInt();

    }



    //Display the private data members in human readable format
    public void display(){
        System.out.println("Member Information for " + MemberName());
        System.out.println("______________________________________");
        System.out.println("Member Name:       " + MemberName());
        System.out.println("Member Number:     " + MemberNumber());
        System.out.println("Street Address:    " + StreetAddress());
        System.out.println("City/State:        " + City() + ", " + State());
        System.out.println("Zip Code:          " + ZipCode());
        System.out.println("Fees Due:          " + FeesDue() + "\n");

    }


    //Update the private data members of the Member class by allowing users to select an
    //individual field or all fields in need of updating.
    public void update(ChocAnList service_records){

        Scanner input = new Scanner(System.in);
        int menuChoice;
        ServiceRecord to_modify;

        do {
            System.out.println("What information would you like to update?");
            System.out.println("__________________________________________");
            System.out.println("1 - Member Name:       " + MemberName());
            System.out.println("2 - Street Address:    " + StreetAddress());
            System.out.println("3 - City:              " + City());
            System.out.println("4 - State:             " + State());
            System.out.println("5 - Zip Code:          " + ZipCode());
            System.out.println("6 - Fees Due:          " + FeesDue());
            System.out.println("0 - Nothing");
            System.out.print("Choice: ");
            menuChoice = input.nextInt();

            input.nextLine(); //Eat the garbage value
            switch (menuChoice) {
                case 1:
                    System.out.print("Enter new member name: ");
                    this.memberName = input.nextLine();

                    //Update service records to match the new member name.
                    for(int i = 0; i < service_records.size(); i++){
                        if(service_records.get(i).matchMember(this.memberNumber)){
                            to_modify = (ServiceRecord) service_records.get(i);
                            to_modify.setMemberName(this.memberName);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter new street address: ");
                    this.streetAddress = input.nextLine();
                    break;
                case 3:
                    System.out.print("Enter new city: ");
                    this.city = input.nextLine();
                    break;
                case 4:
                    System.out.print("Enter new state: ");
                    this.state = input.nextLine();
                    break;
                case 5:
                    System.out.print("Enter new zip code: ");
                    this.zipCode = input.nextLine();
                    break;
                case 6:
                    System.out.println("Enter new fees due: ");
                    while (!input.hasNextInt()) {
                        System.out.println("ERROR: Numbers only");
                        System.out.print("Enter new fees due: ");
                        input.next();
                    }
                    this.feesDue = input.nextInt();
                    break;
                case 0:
                    break; //prep for exit of loop editing is done
                default:
                    System.out.println("ERROR: Invalid input. Please choose a valid menu option.");

            }

        }while(menuChoice != 0);
    }


    public boolean verify(){
        if (feesDue > 0)
            return false;
        else
            return true;
    }

}
