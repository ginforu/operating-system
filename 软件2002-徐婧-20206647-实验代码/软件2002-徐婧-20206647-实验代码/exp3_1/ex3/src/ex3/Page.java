package ex3;

public class Page {
	    private int pageNum;	//页号
	    private int mark;		//标记,标志位=1，则表示该页已经在主存，标志位=0，则表示该页尚未装入主存。
	    private int blockNum;	//主存块号
	    private int loc;		//磁盘位置
	    private int changeMark=0;	//修改标志

	    public int getPageNum() {
	        return pageNum;
	    }
	    public void setPageNum(int pageNum) {
	        this.pageNum = pageNum;
	    }
	    public int getMark() {
	        return mark;
	    }
	    public void setMark(int mark) {
	        this.mark = mark;
	    }
	    public int getChangeMark() {
	        return changeMark;
	    }
	    public void setChangeMark(int changeMark) {
	        this.changeMark = changeMark;
	    }
	    public int getBlockNum() {
	        return blockNum;
	    }
	    public void setBlockNum(int blockNum) {
	        this.blockNum = blockNum;
	    }
	    public int getLoc() {
	        return loc;
	    }
	    public void setLoc(int loc) {
	        this.loc = loc;
	    }
	    public Page(int pageNum, int mark, int blockNum, int loc) {
	        super();
	        this.pageNum = pageNum;
	        this.mark = mark;
	        this.blockNum = blockNum;
	        this.loc = loc;
	    }
	    public Page(int pageNum, int mark, int loc) {
	        super();
	        this.pageNum = pageNum;
	        this.mark = mark;
	        this.blockNum=-1;
	        this.loc = loc;
	    }
	    @Override
	    public String toString() {
	        return pageNum + "\t" + mark + "\t" + blockNum + "\t" + loc ;
	    }
	}

	


