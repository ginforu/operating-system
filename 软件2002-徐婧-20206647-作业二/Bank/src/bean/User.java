package bean;

public class User {

	String account;
	String password;
	float money = 0;
	
	
	
	public User(String account, String password, float money) {
		super();
		this.account = account;
		this.password = password;
		this.money = money;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Ueser [account=" + account + ", password=" + password + ", money=" + money + "]";
	}
	
	

}
