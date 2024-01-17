import java.util.*;

public class main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		process p1=new process("P1",2,1,"ready",0);
		process p2=new process("P2",3,5,"ready",0);
		process p3=new process("P3",1,3,"ready",0);
		process p4=new process("P4",2,4,"ready",0);
		process p5=new process("P5",4,2,"ready",0);
		process p6=new process("P1",2,1,"ready",0);
		process p7=new process("P2",3,5,"ready",0);
		process p8=new process("P3",1,3,"ready",0);
		process p9=new process("P4",2,4,"ready",0);
		process p0=new process("P5",4,2,"ready",0);
		ArrayList<process> p=new ArrayList<process>();
		p.add(p6);
		p.add(p7);
		p.add(p8);
		p.add(p9);
		p.add(p0);
		int cpu=1;
		System.out.println("INPUT NAME , NEEDTIME AND PRIORITY");
		System.out.println(p1.getName()+"     "+p1.getNeed()+"     "+p1.getPriority());
		System.out.println(p2.getName()+"     "+p2.getNeed()+"     "+p2.getPriority());
		System.out.println(p3.getName()+"     "+p3.getNeed()+"     "+p3.getPriority());
		System.out.println(p4.getName()+"     "+p4.getNeed()+"     "+p4.getPriority());
		System.out.println(p5.getName()+"     "+p5.getNeed()+"     "+p5.getPriority());
		System.out.println("OUTPUT  OF  PRIORITY:");
		System.out.println("CPUTIME:0");
		System.out.println(p1.getName()+"     "+p1.getNeed()+"     "+p1.getPriority()+"     "+p1.getState());
		System.out.println(p2.getName()+"     "+p2.getNeed()+"     "+p2.getPriority()+"     "+p2.getState());
		System.out.println(p3.getName()+"     "+p3.getNeed()+"     "+p3.getPriority()+"     "+p3.getState());
		System.out.println(p4.getName()+"     "+p4.getNeed()+"     "+p4.getPriority()+"     "+p4.getState());
		System.out.println(p5.getName()+"     "+p5.getNeed()+"     "+p5.getPriority()+"     "+p5.getState());

		ArrayList<process> a=new ArrayList<process>();
		ArrayList<Integer> c=new ArrayList<Integer>();
		a.add(p1);
		a.add(p2);
		a.add(p3);
		a.add(p4);
		a.add(p5);
		boolean flag=true;
		while(flag) {
			int i;
			c.clear();
			System.out.println("CPUTIME:"+cpu);
			for(process a1:a) {
				if(a1.getState().equals("ready")) {
					c.add(a1.getPriority());
				}
			}
			i=Collections.max(c);
			for(process a1:a) {
				if(a1.getState().equals("ready")) {
					if(a1.getPriority()==i) {
						a1.work();
						a1.setState("working");
						break;
					}
				}
			}
			for(process a1:a) {
				if(a1.getNeed()==0) {
					a1.setState("finish");
				}
				if(a1.getState().equals("finish")) {
					if(a1.getR()==0) {
						a1.setR(cpu);
					}
				}
			}
			for(process a1:a) {
				System.out.println(a1.getName()+"     "+a1.getNeed()+"     "+a1.getPriority()+"     "+a1.getState());
				
			}
			for(process a1:a) {
				if(a1.getState().equals("working")) {
					a1.setState("ready");
					break;
				}
			}

			for(process a1:a) {
				if(a1.getNeed()!=0) {
					flag=true;
					cpu=cpu+1;
					break;
				}else {
					flag=false;
				}
			}
		}
		System.out.println("NAME   RoundTime   WaitingTime");
		int i=0;
		while(i<5) {
			int j=a.get(i).getR()-p.get(i).getNeed();
			System.out.println(a.get(i).getName()+"     "+a.get(i).getR()+"     "+j);
			i++;
		}
	}
}