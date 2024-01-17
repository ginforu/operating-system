package ex3;

public class Page {
	    private int pageNum;	//ҳ��
	    private int mark;		//���,��־λ=1�����ʾ��ҳ�Ѿ������棬��־λ=0�����ʾ��ҳ��δװ�����档
	    private int blockNum;	//������
	    private int loc;		//����λ��
	    private int changeMark=0;	//�޸ı�־

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

	


