package ChocAn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;


//This class holds all data in memory for the individual data types and provides access to it.
public class ChocAnList extends LinkedList<ChocAnDataItem>{

    //Constructor
    public ChocAnList(){
        super();
    }

    //Save function calls the save method for each item in the list
    public boolean save(BufferedWriter save_to) throws IOException {

        //for loop method... going to try the for each though
        //for(int i = 0; i < this.size(); i++){
        // this.get(i).save(save_to);
        //}
       forEach(temp -> {
           try {
               temp.save(save_to);
           } catch (IOException e) {
               e.printStackTrace();
           }
       });

        return true;
    }

    //Attempts to load in data from file passed in as buffered writer.
    //Returns false if action was unsuccessful.
    //Blindly adds all items into list as they are loaded in. Does no verification against duplicates.
    public boolean load(BufferedReader load_from) throws IOException {
        ChocAnDataItem loadedData = null;

        if (!load_from.ready()){
            return false;
        }

        String testRead;
        String[] testSplit;

        //Try to load each object in from file.
        while(load_from.ready()){

            //Mark position in read stream
            load_from.mark(1000);
            //Read in line from file
            testRead = load_from.readLine();
            //Reset to previous position
            load_from.reset();

            //Split up line to determine type.
            testSplit = testRead.split(", ");

            //Creates ChocAnDataItem Sublclass item based on type read in.
            if(testSplit[0].equals("Service")){
                loadedData = new Service();
            }
            else if(testSplit[0].equals("Provider")){
                loadedData = new Provider();
            }
            else if(testSplit[0].equals("Member")){
                loadedData = new Member();
            }
            else if(testSplit[0].equals("ServiceRecord")){
                loadedData = new ServiceRecord();
            }
            else{return false;}

            //Actually read in the data.
            loadedData.load(load_from);

            //Adds object to list.
            this.add(loadedData);

        }
        return true;
    }


    //Looks through list, and compares the String match to the one provided as an argument. If an item's match result is true, return that item.
    public ChocAnDataItem match(String to_match){
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).match(to_match)){
                return this.get(i);
            }
        }
        return null;
    }
}
