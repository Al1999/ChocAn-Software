package ChocAn;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ChocAnTerminal extends Utils{

    protected ChocAnList ProviderList;
    protected ChocAnList MemberList;
    protected ChocAnList ServiceList;
    protected ChocAnList ServiceRecordList;

    public ChocAnTerminal() {
        loadLists();
    }


    private void loadLists()
    {
        ProviderList = new ChocAnList();
        MemberList = new ChocAnList();
        ServiceList = new ChocAnList();
        ServiceRecordList = new ChocAnList();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("Database/ChocAnProviders.txt"));
            ProviderList.load(reader);
            reader.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while loading providers");
        }

        try {
            reader = new BufferedReader(new FileReader("Database/ChocAnMembers.txt"));
            MemberList.load(reader);
            reader.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while loading members");
        }

        try {
            reader = new BufferedReader(new FileReader("Database/ChocAnServices.txt"));
            ServiceList.load(reader);
            reader.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while loading services");
        }

        try {
            reader = new BufferedReader(new FileReader("Database/ChocAnServiceRecords.txt"));
            ServiceRecordList.load(reader);
            reader.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while loading service records");
        }
    }



    protected void saveLists()
    {
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter("Database/ChocAnProviders.txt"));
            ProviderList.save(writer);
            writer.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while saving providers");
        }

        try {
            writer = new BufferedWriter(new FileWriter("Database/ChocAnMembers.txt"));
            MemberList.save(writer);
            writer.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while saving members");
        }

        try {
            writer = new BufferedWriter(new FileWriter("Database/ChocAnServices.txt"));
            ServiceList.save(writer);
            writer.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while saving services");
        }

        try {
            writer = new BufferedWriter(new FileWriter("Database/ChocAnServiceRecords.txt"));
            ServiceRecordList.save(writer);
            writer.close();
        }
        catch (Exception e){
            System.out.println("ERROR: encountered error while saving service records");
        }
    }


}
