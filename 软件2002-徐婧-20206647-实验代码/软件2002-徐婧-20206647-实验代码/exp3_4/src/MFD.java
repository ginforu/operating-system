import java.util.LinkedList;

public class MFD {
    public String user;
    public LinkedList<UFD> l;
    public MFD(){
        user = null;
        l = null;
    }
    public MFD(String user_name){
        this.user = user_name;
        this.l = null;
    }
}