package ChocAn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;


//Abstract class to serve as baseclass for all data members. Each must implement the save, load, and match methods.
public abstract class ChocAnDataItem extends Utils{

    public ChocAnDataItem(){}

    //Abstract methods. Implement these.
    public abstract boolean save(BufferedWriter save_to) throws IOException;
    public abstract boolean load(BufferedReader load_from) throws IOException;
    public abstract boolean match(String to_match);
    public abstract boolean matchMember(String to_match);
    public abstract boolean thisWeek();

}
