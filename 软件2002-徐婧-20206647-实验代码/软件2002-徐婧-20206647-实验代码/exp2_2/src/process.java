
public class process {
	private String name;
	private int need;
	private int runtime;
	private String state;
	private int r;
	public process() {
		
	}
	public process(String name,int need,int runtime) {
		this.name=name;
		this.need=need;
		this.runtime=runtime;
	}
	public process(String name,int need,int runtime,String state,int r) {
		this.name=name;
		this.need=need;
		this.runtime=runtime;
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
	public int getruntime() {
		return runtime;
	}
	public void setruntime(int runtime) {
		this.runtime = runtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void work() {
		this.runtime=runtime+1;
	}
}