
public class process {
	private String name;
	private int need;
	private int priority;
	private String state;
	private int r;
	public process() {
		
	}
	public process(String name,int need,int priority) {
		this.name=name;
		this.need=need;
		this.priority=priority;
	}
	public process(String name,int need,int priority,String state,int r) {
		this.name=name;
		this.need=need;
		this.priority=priority;
		this.state=state;
		this.r=r;
	}
	public String getName() {
		return name;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNeed() {
		return need;
	}
	public void setNeed(int need) {
		this.need = need;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void work() {
		this.need=need-1;
		this.priority=priority-1;
	}
}