public class UFD {
    public String fileName;
    public int[] protect = new int[3];
    public int fileLength;
    public  UFD(){
        fileName = null;
        protect[0] = 1;
        protect[1] = 1;
        protect[2] = 1;
        fileLength = 0;
    }
    public  UFD(String name, int file_length){
        this.fileName =name;
        this.fileLength  =file_length;
        protect[0] = 1;
        protect[1] = 1;
        protect[2] = 1;
    }
}