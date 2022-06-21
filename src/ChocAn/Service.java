package ChocAn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Service extends ChocAnDataItem{

    // Private data members
    private String code;
    private String name;
    private int price;

    // Public getters
    public String Code() { return code; }
    public String Name() { return name; }
    public int Price() { return price; }



    public Service(){
        super();
    }



    public Service(String _code, String _name, int _price) {

        super();

        this.code = _code;
        this.name = _name;
        this.price = _price;

    }



    //Save outputs all private data members to file passed in, in the form of a BufferedWriter.
    //This replaces any commas in the string with ',/' and then separates the items by ', '
    public boolean save(BufferedWriter save_to) throws IOException {
        save_to.write("Service, " + this.code.replaceAll(",",",/")
                + ", " + this.name.replaceAll(",", ",/")
                + ", " + this.price + "\n");
        return true;
    }



    //Loads data from a file, then populates private data members with it.
    //This ingests the data in the format given in the save function.
    //All instances of ',/' will be replaced by ',' and data items are separated by ', '
    public boolean load(BufferedReader load_from) throws IOException {
        String readRaw = load_from.readLine();
        if (readRaw == null){return false;}
        String[] readLine = readRaw.split(", ");
        this.code = readLine[1].replaceAll(",/", ",");
        this.name = readLine[2].replaceAll(",/", ",");
        this.price = Integer.valueOf(readLine[3]);
        return true;
    }



    public boolean match(String to_match) {
        if (to_match == null || this.code == null){
            return false;
        }
        return this.code.equals(to_match);
    }



    //Always false. Implemented to satisfy abstract parent class methods
    public boolean matchMember(String to_match){return false;}



    //Always false. Implemented to satisfy abstract parent class methods
    public boolean thisWeek(){
        return false;
    }



    //Display the private data members in human readable format
    public void display(){
        System.out.println("Service Information for " + Name());
        System.out.println("______________________________________");
        System.out.println("Service Code:     " + Code());
        System.out.println("Service Name:     " + Name());
        System.out.println("Price of Service: " + Price());
    }


    //TODO implement getDataCopy()
}
