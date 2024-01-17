package ex3;

public class Job {
	private String Operation;	//²Ù×÷
    private int pageNum;		//Ò³ºÅ
    private int unitNum;		//µ¥ÔªºÅ

    public String getOperation() {
        return Operation;
    }
    public void setOperation(String operation) {
        Operation = operation;
    }
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getUnitNum() {
        return unitNum;
    }
    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }
    public Job(String operation, int pageNum, int unitNum) {
        super();
        Operation = operation;
        this.pageNum = pageNum;
        this.unitNum = unitNum;
    }
    @Override
    public String toString() {
        return this.Operation + "\t" + this.pageNum + "\t" + this.unitNum ;

}
}
