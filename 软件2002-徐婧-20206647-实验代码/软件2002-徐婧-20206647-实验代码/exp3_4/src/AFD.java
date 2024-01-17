
public class AFD {
    public String openFilename;
    public int[] openProtect = new int[3];
    public boolean read;
    public boolean write;
    public AFD(){
        openFilename = null;
        openProtect[0] = 1;
        openProtect[1] = 1;
        openProtect[2] = 1;
        read = false;
        write = false;
    }
    public  AFD(String open_filename){
        this.openFilename = open_filename;
        openProtect[0] = 1;
        openProtect[1] = 1;
        openProtect[2] = 1;
        read = false;
        write = false;
    }
}