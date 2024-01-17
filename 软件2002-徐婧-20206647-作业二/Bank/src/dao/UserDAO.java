package dao;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import bean.User;



public class UserDAO {

	public static List<User> Ulist = new ArrayList<User>();

	private static String path = "/Users/account.csv";
	
	public static boolean Matchaccount(String account, String password) {
//		System.out.println(Ulist.get(0));
//		System.out.println(account+" "+password);
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			String[] arr;
			String key;
			while((line = br.readLine())!=null){
				arr = line.split(",");
				if (arr[0].equals(account)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		for (int i = 0; i < Ulist.size(); i++) {
//			if (Ulist.get(i).getAccount().equals(account) && Ulist.get(i).getPassword().equals(password) == true) {
//				return true;
//			}
//		}
		return false;
	}
	
	public static void Readfile() {
		Ulist.clear();
		String path;
		path = System.getProperty("user.dir").replace("\\", "/") + "/User.json";
		File fl = new File(path);
		FileInputStream fis;
		InputStreamReader isr;
		BufferedReader br;
		String temp;
		String jsonS = "";
		try {
			fis = new FileInputStream(fl);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while ((temp = br.readLine()) != null) {
				jsonS += temp;
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ×ª»»³Élist
		Ulist = JSON.parseArray(jsonS, User.class);

	}	
	
	public static void savefile(User u) {
		Ulist.add(u);
		try {
			String jas = JSON.toJSONString(Ulist);
			String path;
			path = System.getProperty("user.dir").replace("\\", "/") + "/User.json";
			File fl = new File(path);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fl, false));
			bw.write(jas);
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean find(String account) {
//		System.out.println(t1+" "+t2);
//		for(int i=0;i<Ulist.size();i++) {
//			if(Ulist.get(i).getAccount().equals(t1)) {
//				return true;
//			}
//		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			String[] arr;
			while((line = br.readLine())!=null){
				arr = line.split(",");
				if (arr[0].equals(account)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean withdrawmoney(float m ,String account) {
//		for(int i=0;i<Ulist.size();i++) {
//			if(Ulist.get(i).getAccount().equals(account)) {
//				User u = Ulist.get(i);
//				if(u.getMoney()<-m) {
//					return false;
//				}
//				m+=u.getMoney();
//				u.setMoney(m);
//				Ulist.remove(i);
//				Ulist.add(u);
//			}
//		}
//		try {
//			String jas = JSON.toJSONString(Ulist);
//			String path;
//			path = System.getProperty("user.dir").replace("\\", "/") + "/User.json";
//			File fl = new File(path);
//			BufferedWriter bw = new BufferedWriter(new FileWriter(fl, false));
//			bw.write(jas);
//			bw.flush();
//			bw.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return true;
		try {
			float sum = 0l;
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			String[] arr;
			while((line = br.readLine())!=null){
				arr = line.split(",");
				if (arr[0].equals(account)) {
					sum=Float.parseFloat(arr[1]);
				}
			}
			boolean success = sum-m>0;
			if(success){
				File file = new File(path);
				RandomAccessFile pw = new RandomAccessFile(path,"rw");
				pw.seek(file.length());
				pw.write((account+","+(sum-m)+"\n").getBytes());
				pw.close();
				System.out.println(sum-m);
			}
			return success;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static void dispositmoney(float m,String account) {
//		for(int i=0;i<Ulist.size();i++) {
//			if(Ulist.get(i).getAccount().equals(account)) {
//				User u = Ulist.get(i);
//				m+=u.getMoney();
//				u.setMoney(m);
//				Ulist.remove(i);
//				Ulist.add(u);
//			}
//		}
//		try {
//			String jas = JSON.toJSONString(Ulist);
//			String path;
//			path = System.getProperty("user.dir").replace("\\", "/") + "/User.json";
//			File fl = new File(path);
//			BufferedWriter bw = new BufferedWriter(new FileWriter(fl, false));
//			bw.write(jas);
//			bw.flush();
//			bw.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.print(Ulist);
		try {
			float sum = 0l;
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = null;
			String[] arr;
			while((line = br.readLine())!=null){
				arr = line.split(",");
				if (arr[0].equals(account)) {
					sum=Float.parseFloat(arr[1]);
				}
			}

			File file = new File(path);
			RandomAccessFile pw = new RandomAccessFile(path,"rw");
			pw.seek(file.length());
			pw.write((account+","+(sum+m)+"\n").getBytes());
			pw.close();
			System.out.println(sum+m);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static List<String> findallecp(String account) {
		List<String> l = new ArrayList<String>();
		for(int i=0;i<Ulist.size();i++) {
			User u = Ulist.get(i);
			if(u.getAccount().equals(account))continue ;
			l.add(u.getAccount());
		}
		return l;
	}
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	//²âÊÔ
	public static void main (String[] args) throws SQLException {
		List<String> u1 =new ArrayList<>();
		u1.add("111");
		u1.add("1000");
		u1.add("111");

		List<String> u2 =new ArrayList<>();
		u1.add("222");
		u1.add("2000");
		u1.add("222");

		List<String> u3 =new ArrayList<>();
		u1.add("333");
		u1.add("3000");
		u1.add("333");

		Matchaccount("111","111");
		withdrawmoney(2000,"111");
		dispositmoney(1000,"111");

	}
}