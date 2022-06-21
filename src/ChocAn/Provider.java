package ChocAn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Provider extends ChocAnDataItem{

    // Private data members
    private String providerNumber;
    private String providerName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    // Public getters
    public String ProviderNumber() { return providerNumber; }
    public String ProviderName() { return providerName; }
    public String StreetAddress() { return streetAddress; }
    public String City() { return city; }
    public String State() { return state; }
    public String ZipCode() { return zipCode; }



    public Provider(){
        super();
    }



    public Provider(String _providerNumber, String _providerName,
                    String _streetAddress, String _city, String _state, String _zipCode) {

        super();

        this.providerNumber = _providerNumber;
        this.providerName = _providerName;
        this.streetAddress = _streetAddress;
        this.city = _city;
        this.state = _state;
        this.zipCode = _zipCode;
    }



    //Save outputs all private data members to file passed in, in the form of a BufferedWriter.
    //This replaces any commas in the string with ',/' and then separates the items by ', '
    public boolean save(BufferedWriter save_to) throws IOException {
        save_to.write("Provider, " + this.providerNumber.replaceAll(",",",/")
                + ", " + this.providerName.replaceAll(",", ",/")
                + ", " + this.streetAddress.replaceAll(",", ",/")
                + ", " + this.city.replaceAll(",", ",/")
                + ", " + this.state.replaceAll(",", ",/")
                + ", " + this.zipCode.replaceAll(",", ",/") + "\n");
        return true;
    }



    //Loads data from a file, then populates private data members with it.
    //This ingests the data in the format given in the save function.
    //All instances of ',/' will be replaced by ',' and data items are separated by ', '
    public boolean load(BufferedReader load_from) throws IOException {
        String readRaw = load_from.readLine();
        if (readRaw == null){return false;}
        String[] readLine = readRaw.split(", ");
        this.providerNumber = readLine[1].replaceAll(",/", ",");
        this.providerName = readLine[2].replaceAll(",/", ",");
        this.streetAddress = readLine[3].replaceAll(",/", ",");
        this.city = readLine[4].replaceAll(",/", ",");
        this.state = readLine[5].replaceAll(",/", ",");
        this.zipCode = readLine[6].replaceAll(",/", ",");
        return true;
    }



    //Check to see if the provider number matches the one passed in.
    public boolean match(String to_match) {
        if (to_match == null || this.providerName == null){
            return false;
        }
        return this.providerNumber.equals(to_match);
    }



    //Always false. Implemented to satisfy abstract parent class methods
    public boolean matchMember(String to_match){return false;}



    //Always false. Implemented to satisfy abstract parent class methods
    public boolean thisWeek(){
        return false;
    }



    //Take user input and set the values of private data members to user input.
    public void read(){

        Scanner input = new Scanner(System.in);

        System.out.println("       Provider Information       ");
        System.out.println("__________________________________");
        System.out.print("Provider Number:    ");
        this.providerNumber = this.getNumber();
        System.out.print("Provider Name:      ");
        this.providerName = input.nextLine();
        System.out.print("Street Address:     ");
        this.streetAddress = input.nextLine();
        System.out.print("City:               ");
        this.city = input.nextLine();
        System.out.print("State:              ");
        this.state = input.nextLine();
        System.out.print("Zip Code:           ");
        this.zipCode = this.getZip();

    }



    //Display the private data members in human readable format
    public void display(){

        System.out.println("Provide Information for " + ProviderName());
        System.out.println("______________________________________");
        System.out.println("Provider Name:     " + ProviderName());
        System.out.println("Provider Number:   " + ProviderNumber());
        System.out.println("Street Address:    " + StreetAddress());
        System.out.println("City/State:        " + City() + ", " + State());
        System.out.println("Zip Code:          " + ZipCode() + "\n");

    }


    //Update the private data members of the Provider class by allowing users to select an
    //individual field or all fields in need of updating.
    public void update(ChocAnList service_records){
        Scanner input = new Scanner(System.in);
        int menuChoice;
        ServiceRecord to_modify;

        do {
            System.out.println("What information would you like to update?");
            System.out.println("__________________________________________");
            System.out.println("1 - Provider Name:     " + ProviderName());
            System.out.println("2 - Street Address:    " + StreetAddress());
            System.out.println("3 - City:              " + City());
            System.out.println("4 - State:             " + State());
            System.out.println("5 - Zip Code:          " + ZipCode());
            System.out.println("0 - Nothing");
            System.out.print("Choice: ");
            menuChoice = input.nextInt();

            input.nextLine(); //Eat the garbage value
            switch (menuChoice) {
                case 1:
                    System.out.print("Enter new provider name: ");

                    this.providerName = input.nextLine();

                    //Update service records to match the new provider name.
                    for(int i = 0; i < service_records.size(); i++){
                        if(service_records.get(i).match(this.providerNumber)){
                            to_modify = (ServiceRecord) service_records.get(i);
                            to_modify.setProviderName(this.providerName);
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
                case 0:
                    break; //prep for exit of loop editing is done
                default:
                    System.out.println("ERROR: Invalid input please choose a valid menu option.");

            }

        }while(menuChoice != 0);
    }


    //TODO implement getDataCopy()
}
