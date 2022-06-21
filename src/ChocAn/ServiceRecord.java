package ChocAn;

import javax.swing.text.DateFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ServiceRecord extends ChocAnDataItem{

    // Private data members
    private LocalDateTime creationDateTime;
    private LocalDateTime serviceDateTime;
    private String providerNumber;
    private String providerName;
    private String memberNumber;
    private String memberName;
    private String serviceCode;
    private String comments;

    // Public getters
    public LocalDateTime CreationDateTime() { return creationDateTime; }
    public LocalDateTime ServiceDateTime() { return serviceDateTime; }
    public String ProviderNumber() { return providerNumber; }
    public String ProviderName() { return providerName; }
    public String MemberNumber() { return memberNumber; }
    public String MemberName() { return memberName; }
    public String ServiceCode() { return serviceCode; }
    public String Comments() { return comments; }

    public ServiceRecord(){
        super();
    }



    public ServiceRecord(LocalDateTime _creationDT, LocalDateTime _serviceDT,
                         String _providerNumber, String _providerName,
                         String _memberNumber, String _memberName,
                         String _serviceCode, String _comments){

        super();

        this.creationDateTime = _creationDT;
        this.serviceDateTime = _serviceDT;
        this.providerNumber = _providerNumber;
        this.providerName = _providerName;
        this.memberNumber = _memberNumber;
        this.memberName = _memberName;
        this.serviceCode = _serviceCode;
        this.comments = _comments;
    }

    //Save outputs all private data members to file passed in, in the form of a BufferedWriter.
    //This replaces any commas in the string with ',/' and then separates the items by ', '
    public boolean save(BufferedWriter save_to) throws IOException {

        //Times need to be converted to strings.
        save_to.write("ServiceRecord, " + this.creationDateTime.toString()
                + ", " + this.serviceDateTime.toString()
                + ", " + this.providerNumber.replaceAll(",", ",/")
                + ", " + this.providerName.replaceAll(",", ",/")
                + ", " + this.memberNumber.replaceAll(",", ",/")
                + ", " + this.memberName.replaceAll(",", ",/")
                + ", " + this.serviceCode.replaceAll(",", ",/")
                + ", " + this.comments.replaceAll(",", ",/") + "\n");
        return true;
    }



    //Loads data from a file, then populates private data members with it.
    //This ingests the data in the format given in the save function.
    //All instances of ',/' will be replaced by ',' and data items are separated by ', '
    public boolean load(BufferedReader load_from) throws IOException {
        String readRaw = load_from.readLine();
        if (readRaw == null){return false;}
        String[] readLine = readRaw.split(", ");

        //Need to convert strings back to time with the parse function.
        this.creationDateTime = LocalDateTime.parse(readLine[1]);
        this.serviceDateTime = LocalDateTime.parse(readLine[2]);

        this.providerNumber = readLine[3].replaceAll(",/", ",");
        this.providerName = readLine[4].replaceAll(",/", ",");
        this.memberNumber = readLine[5].replaceAll(",/", ",");
        this.memberName = readLine[6].replaceAll(",/", ",");
        this.serviceCode = readLine[7].replaceAll(",/", ",");
        this.comments = readLine[8].replaceAll(",/", ",");
        return true;
    }



    //Returns if the service record is for the provider number passed in.
    public boolean match(String to_match) {
        if (to_match == null || this.providerName == null){
            return false;
        }
        return this.providerNumber.equals(to_match);
    }



    //Returns if the service record is for the member number passed in.
    public boolean matchMember(String to_match){
        if (to_match == null || this.memberNumber == null){
            return false;
        }
        return this.memberNumber.equals(to_match);
    }



    public boolean thisWeek(){
        LocalDateTime last_saturday = LocalDateTime.now();

        // Grabbing the enumerated day of the week.
        long day = last_saturday.getDayOfWeek().getValue();

        //Calculating how many days to subtract to get to the last Saturday.
        day = (day + 1) % 7;

        //Subtracting the days, then truncating off any remaining minutes / seconds to get to friday night at midnight.
        last_saturday = last_saturday.minusDays(day).truncatedTo(ChronoUnit.DAYS);

        //Now comparing to see if the current record was submitted this week.
        return (this.creationDateTime.isAfter(last_saturday)) && this.creationDateTime.isBefore(LocalDateTime.now());
    }



    //Take user input and set the values of private data members to user input.
    public boolean read(Provider cur_provider, Member verified_member, ChocAnList services){
        //User input
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        boolean cont = false;

        this.creationDateTime = LocalDateTime.now();

        System.out.println("               Service Record               ");
        System.out.println("Provider: " + this.ProviderName());
        System.out.println("Member: " + verified_member.MemberName());
        System.out.println("____________________________________________");

        do {
            try {
                System.out.print("Service Date (MM-DD-YYYY): ");
                String date = input.nextLine();
                LocalDate serviceDate = LocalDate.parse(date, dateFormat);
                this.serviceDateTime = serviceDate.atStartOfDay();
                cont = true;
            } catch (Exception dateCapture) {
                System.out.println("ERROR: Please enter date in specified format...");
                cont = false;
            }
        }while(cont == false);

        //Set based upon data passed in.
        this.providerNumber = cur_provider.ProviderNumber();
        this.providerName = cur_provider.ProviderName();
        this.memberNumber = verified_member.MemberNumber();
        this.memberName = verified_member.MemberName();

        System.out.print("Service Code:              ");
        this.serviceCode = input.nextLine();
        while(services.match(this.serviceCode) == null){
            System.out.println("Invalid service code. Enter '-1' to abort service billing.");
            System.out.print("Service Code:              ");
            this.serviceCode = input.nextLine();
            if (this.serviceCode.equals("-1")){
                return false;
            }
        }

        System.out.print("Comments:                  ");
        this.comments = input.nextLine();

        return true;
        //Should we make this so it displays info, and optionally lets them re-input?
    }



    public void display(){

        System.out.println("Member Information for " + MemberName());
        System.out.println("______________________________________");
        //Gets creation time and formats to an individual date and time w/ no nanoseconds because it looked gross
        System.out.println("Creation Date:    " + CreationDateTime().format(DateTimeFormatter.ISO_DATE));
        System.out.println("Creation Time:    " + CreationDateTime().withNano(0).format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("Service Date:     " + ServiceDateTime().format(DateTimeFormatter.ISO_DATE));
        System.out.println("Provider Number:  " + ProviderNumber());
        System.out.println("Provider Name:    " + ProviderName());
        System.out.println("Member Number:    " + MemberNumber());
        System.out.println("Member Name:      " + MemberName());
        System.out.println("Service Code:     " + ServiceCode());
        System.out.println("Comments:         " + Comments() + "\n");
    }


    //Setters for when the member / provider names are updated, and the record needs to be updated to match.
    public void setMemberName(String to_set){
        if(to_set != null){this.memberName = to_set;}
        return;
    }
    public void setProviderName(String to_set){
        if(to_set != null){this.providerName = to_set;}
        return;
    }
}
