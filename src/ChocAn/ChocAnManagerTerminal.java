package ChocAn;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ChocAnManagerTerminal extends ChocAnTerminal
{
    public ChocAnManagerTerminal() {

    }

    public void run() {
        displayMenu();
    }

    //Manager terminal menu display
    private void displayMenu() {
        int selection, selection2;
        //temp variable for getting input.
        String num;

        //Main menu
        do {

            String dummyString;
            Member dummyMember;
            Provider dummyProvider;

            System.out.println();
            System.out.println("\n\n--ChocAn Manager Terminal--");
            System.out.println("1 - Member Management");
            System.out.println("2 - Provider Management");
            System.out.println("3 - Generate Reports");
            System.out.println("4 - Exit");

            selection = getMenuChoice(1,4);

            //Member management sub-menu
            if (selection == 1) {

                System.out.println();
                System.out.println("--Member Management--");
                do{
                    System.out.println("1 - Add New Member");
                    System.out.println("2 - Edit Member");
                    System.out.println("3 - Delete Member");
                    System.out.println("4 - Return");

                    selection2 = getMenuChoice(1,4);

                    //add new member
                    if (selection2 == 1) {
                        dummyMember = new Member();
                        dummyMember.read();

                        //check to see if it is a duplicate.
                        if(MemberList.match(dummyMember.MemberNumber()) == null) {
                            MemberList.add(dummyMember);
                        }
                        else{
                            System.out.println("Duplicate member number found. Failed to add!");
                        }
                    }

                    //edit preexisting member
                    else if (selection2 == 2) {
                        System.out.print("Enter the member number: ");
                        dummyString = getString();
                        editMember(dummyString);
                    }

                    //delete member
                    else if (selection2 == 3){
                        System.out.print("Enter the member number: ");
                        dummyString = getString();
                        removeMember(dummyString);
                    }

                    saveLists();

                } while (selection2 != 4);

            }

            //Provider management sub-menu
            if (selection == 2) {

                System.out.println();
                System.out.println("--Provider Management--");
                do{
                    System.out.println("1 - Add New Provider");
                    System.out.println("2 - Edit Provider");
                    System.out.println("3 - Delete Provider");
                    System.out.println("4 - Return");

                    selection2 = getMenuChoice(1,4);

                    //add new provider
                    if (selection2 == 1){
                        dummyProvider = new Provider();
                        dummyProvider.read();
                        if(ProviderList.match(dummyProvider.ProviderNumber()) == null) {
                            ProviderList.add(dummyProvider);
                        }
                        else{
                            System.out.println("Duplicate provider number found. Failed to add!");
                        }
                }

                    //edit existing provider
                    else if (selection2 == 2) {
                        System.out.print("Enter the provider number: ");
                        dummyString = getString();
                        editProvider(dummyString);
                    }

                    //delete provider
                    else if (selection2 == 3) {
                        System.out.print("Enter the provider number: ");
                        dummyString = getString();
                        removeProvider(dummyString);
                    }

                    saveLists();

                } while (selection2 != 4);

            }

            //Reporting sub-menu
            if (selection == 3) {
                System.out.println("--Generate Reports--");

                do{
                    System.out.println("1 - All Weekly Reports");
                    System.out.println("2 - Member Report");
                    System.out.println("3 - Provider Report");
                    System.out.println("4 - EFT Report");
                    System.out.println("5 - Weekly Summary Report");
                    System.out.println("6 - Return");

                    selection2 = getMenuChoice(1,6);

                    if (selection2 == 1) {
                        GenerateWeeklyReport();
                        GenerateEFTReport();
                        ProviderList.forEach(prov -> ProviderReport(((Provider)prov).ProviderNumber()));
                        MemberList.forEach(mem -> MemberReport(((Member)mem).MemberNumber()));
                    }else if (selection2 == 2) {
                        System.out.println("\n\nPlease enter the 9 digit number for the member whose report you wish to generate.\n");
                        num = new Scanner(System.in).nextLine();
                        if(MemberReport(num)) {
                            System.out.println("Success. Report saved to Member_Reports/");
                        }
                        else
                            System.out.println("Failure. Either member doesn't exist, or no services billed this week for that member.");
                    }else if (selection2 == 3) {
                        System.out.println("\n\nPlease enter the 9 digit number for the provider whose report you wish to generate.\n");
                        num = new Scanner(System.in).nextLine();
                        if (ProviderReport(num)) {
                            System.out.println("Success. Report saved to Provider_Reports/");
                        } else
                            System.out.println("Failure. Either provider number doesn't exist, or they billed no services this week.");
                    }else if (selection2 == 4) {
                        GenerateEFTReport();
                        System.out.println("\nSuccess. Report saved to EFT_Reports/\n");
                    }
                    else if (selection2 == 5) {
                        GenerateWeeklyReport();
                        System.out.println("\nSuccess. Report saved to Weekly_Summary_Reports/\n");
                    }

                } while (selection2 != 6);
            }

        } while (selection != 4);

        this.saveLists();

    }


    //returns false if no member with the number memberNumber can be found
    protected boolean editMember(String memberNumber){
        Member member = (Member) MemberList.match(memberNumber);
        if (member != null) {
            MemberList.remove(member);
            member.update(ServiceRecordList);
            MemberList.add(member);
            return true;
        }
        else {
            System.out.println("Member not found, cannot edit. Verify Number.");
            return false;
        }
    }


    protected boolean editProvider(String providerNumber){
        Provider provider = (Provider) ProviderList.match(providerNumber);
        if (provider != null) {
            ProviderList.remove(provider);
            provider.update(ServiceRecordList);
            ProviderList.add(provider);
            return true;
        }
        else{
            System.out.println("Provider not found, cannot edit. Verify Number.");
            return false;
        }
    }


    protected boolean removeMember(String memberNumber){
        Member member = (Member) MemberList.match(memberNumber);
        if (member != null) {
            MemberList.remove(member);
            return true;
        }
        else {
            System.out.println("Member not found, cannot delete. Verify Number.");
            return false;
        }
    }


    protected boolean removeProvider(String providerNumber) {
        Provider provider = (Provider) ProviderList.match(providerNumber);
        if (provider != null) {
            ProviderList.remove(provider);
            return true;
        } else {
            System.out.println("Provider not found, cannot delete. Verify Number.");
            return false;
        }
    }


    //Weekly Report - Creates a list of each provider that billed a service in the week
    public void GenerateWeeklyReport() {
        try {
            //Make date and time format, get today for report naming.
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDateTime today = LocalDateTime.now();
            String date = today.format(dateFormat);

            //Create File to save data to
            File file = new File("Weekly_Summary_Reports/Weekly_Summary_" + date + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            ServiceRecord record;
            Service service;
            Provider provider;
            int num = 0, consultation = 0, total = 0, weeklyConsulation = 0, weeklyTotal = 0, totalProviders = 0;
            //Goes through each Provider
            for (int x = 0; x < ProviderList.size(); ++x) {
                provider = (Provider) ProviderList.get(x);
                //Goes through each ServiceRecord
                for (int y = 0; y < ServiceRecordList.size(); ++y) {
                    record = (ServiceRecord) ServiceRecordList.get(y);
                    service = (Service) ServiceList.match(record.ServiceCode());
                    //Checks if the service was provided this week and if it matches the provider
                    if (record.thisWeek() && provider.match(record.ProviderNumber()) && num == 0) {
                        writer.write("-------------------------"
                                + "\n" + "Provider: " + provider.ProviderName()
                                + "\n" + "Provider Number: " + provider.ProviderNumber()
                                + "\n");
                        total += service.Price();
                        consultation += 1;
                        totalProviders += 1;
                        num = 1;
                    } else if (record.thisWeek() && provider.match(record.ProviderNumber()) && num == 1) {
                        total += service.Price();
                        consultation += 1;
                    }
                }
                if (num == 1) {
                    writer.write("Consultations: " + consultation
                            + "\n" + "Total weekly fee: $" + formatMoney(total)
                            + "\n");
                    weeklyTotal += total;
                    weeklyConsulation += consultation;
                }
                num = 0;
                total = 0;
            }
            writer.write("---------------------------"
                    + "\n" + "Total Providers Billed: " + totalProviders
                    + "\n" + "Total Overall Consultations: " + weeklyConsulation
                    + "\n" + "Overall Fees for the Week: $" + formatMoney(weeklyTotal));
            writer.close();
        } catch (Exception e) {
            System.out.println("ERROR: encountered error while saving providers");
        }
    }

    //Generates provider report for the number passed in.
    public boolean ProviderReport(String providerNum) {
        try{
            //Checks to see if the provider exists, gets their data.
            Provider provider = (Provider) ProviderList.match(providerNum);
            if (provider == null) {
                return false;
            }

            //Temp service, record.
            ServiceRecord record;
            Service service;

            Boolean exists = false;

            //Checks to see if there is at least one record for this person.
            int j = 0;
            while(!exists && j < ServiceRecordList.size()){
                record = (ServiceRecord) ServiceRecordList.get(j);
                if(record.match(providerNum) && record.thisWeek()){
                    exists = true;
                }
                j += 1;
            }
            if (exists == false){
                return false;
            }

            //Make date and time format, get today for report naming.
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
            LocalDateTime today = LocalDateTime.now();
            String date = today.format(dateFormat);

            //Create File to save data to
            File file = new File("Provider_Reports/" + providerNum + "_" + date + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));


            //Integers for keeping track of data
            int consultations = 0, weeklyTotal = 0;

            //Prints header into file.
            writer.write("-------------------------"
                    + "\n" + "Provider Name: " + provider.ProviderName()
                    + "\n" + "Provider Number: " + provider.ProviderNumber()
                    + "\n" + "Provider Street Address: " + provider.StreetAddress()
                    + "\n" + "Provider City: " + provider.City()
                    + "\n" + "Provider State: " + provider.State()
                    + "\n" + "Provider Zip Code: " + provider.ZipCode());

            //Goes through each service record
            for(int y = 0; y < ServiceRecordList.size(); ++y) {
                record = (ServiceRecord) ServiceRecordList.get(y);
                service = (Service) ServiceList.match(record.ServiceCode());

                //Checks if the service was provided this week and it matches the provider
                if (record.thisWeek() && record.match(providerNum)) {
                    writer.write("\n\nServices Provided: "
                            + "\n\tDate of Service: " + record.ServiceDateTime().format(dateFormat)
                            + "\n\tDate Received by Comp.:" + record.CreationDateTime().format(dateTimeFormat)
                            + "\n\tMember Name: " + record.MemberName()
                            + "\n\tMember Number: " + record.MemberNumber()
                            + "\n\tService Code: " + record.ServiceCode()
                            + "\n\tFee to be Paid: $" + formatMoney(service.Price()));
                    consultations += 1;
                    weeklyTotal += service.Price();
                }
            }
            writer.write("\n-------------------------"
            + "\n" + "Total Consultations: " + consultations
            + "\n" + "Total Fee for the Week: $" + formatMoney(weeklyTotal)
            + "\n" );
            writer.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while saving provider report");
        }
        return true;
    }

    //Generates Member report for the number passed in.
    public boolean MemberReport(String memberNum) {
        try{
            //Checks to see if the member exists, gets their data.
            Member member = (Member) MemberList.match(memberNum);
            if(member == null){
                return false;
            }

            Boolean exists = false;

            //Temp service, record.
            ServiceRecord record;
            Service service;

            //Checks to see if there is at least one record for this person.
            int j = 0;
            while(!exists && j < ServiceRecordList.size()){
                record = (ServiceRecord) ServiceRecordList.get(j);
                if(record.matchMember(memberNum) && record.thisWeek()){
                    exists = true;
                }
                j += 1;
            }
            if (exists == false){
                return false;
            }



            //Make date and time format, get today for report naming.
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDateTime today = LocalDateTime.now();
            String date = today.format(dateFormat);

            //Create File to save data to
            File file = new File("Member_Reports/" + memberNum + "_" + date + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));


            //Integers for keeping track of data
            int consultations = 0, weeklyTotal = 0;

            //Prints header into file.
            writer.write("-------------------------"
                + "\nMember Name: " + member.MemberName()
                + "\nMember Number: " + member.MemberNumber()
                + "\nMember Street Address: " + member.StreetAddress()
                + "\nMember City: " + member.City()
                + "\nMember State: " + member.State()
                + "\nMember Zip Code: " + member.ZipCode()
                + "\n\nConsultations billed this week (May include older records if charge came in this week):\n");

            //Goes through each service record
            for(int y = 0; y < ServiceRecordList.size(); ++y) {
                record = (ServiceRecord) ServiceRecordList.get(y);
                service = (Service) ServiceList.match(record.ServiceCode());

                //Checks if the service was provided this week and it matches the provider
                if (record.thisWeek() && record.matchMember(memberNum)) {
                    writer.write("\n\tDate of Service: " + record.ServiceDateTime().format(dateFormat)
                        + "\n\tProvider Name: " + record.ProviderName()
                        + "\n\tService Name: " + service.Name()
                        + "\n");

                    consultations += 1;
                    weeklyTotal += service.Price();
                }
            }
            writer.write("\n-------------------------"
                    + "\n" + "Total Consultations: " + consultations
                    + "\n" + "Total Fee for the Week: $" + formatMoney(weeklyTotal)
                    + "\n" );
            writer.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while saving member report");
        }
        return true;
    }

    public void GenerateEFTReport() {
        try {
            //Make date and time format, get today for report naming.
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDateTime today = LocalDateTime.now();
            String date = today.format(dateFormat);

            //Create File to save data to
            File file = new File("EFT_Reports/" + "EFT_" + date + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            //Temp Services, Providers, Records for gathering data.
            Service service;
            ServiceRecord record;
            Provider provider;

            //Integers for data collection
            int num = 0, weeklyTotal = 0;
            //Goes through each provider
            for (int x = 0; x < ProviderList.size(); ++x) {
                provider = (Provider) ProviderList.get(x);
                //Goes through each service
                for (int y = 0; y < ServiceRecordList.size(); ++y) {
                    record = (ServiceRecord) ServiceRecordList.get(y);
                    service = (Service) ServiceList.match(record.ServiceCode());
                    //Checks if the service was provided this week and it matches the provider
                    if (record.thisWeek() && provider.match(record.ProviderNumber()) && num == 0) {
                        writer.write("------------------------"
                                + "\n" + record.ProviderName()
                                + "\n" + record.ProviderNumber()
                                + "\n");
                        weeklyTotal += service.Price();
                        num = 1;
                    } else if (record.thisWeek() && provider.match(record.ProviderNumber()) && num == 1) {
                        weeklyTotal += service.Price();
                    }
                }
                if (num == 1) {
                    writer.write("Total Fee For The Week: $" + formatMoney(weeklyTotal)
                            + "\n");
                }
                num = 0;
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("ERROR: encountered error while saving providers");
        }
    }
}