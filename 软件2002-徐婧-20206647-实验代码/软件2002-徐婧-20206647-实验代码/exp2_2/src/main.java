import java.util.*;

public class main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		process p1=new process("P1",2,0,"ready",0);
		process p2=new process("P2",3,0,"ready",0);
		process p3=new process("P3",1,0,"ready",0);
		process p4=new process("P4",2,0,"ready",0);
		process p5=new process("P5",4,0,"ready",0);

		int cpu=1;
		System.out.println("INPUT NAME , NEEDTIME AND RUNTIME");
		System.out.println(p1.getName()+"     "+p1.getNeed()+"     "+p1.getruntime());
		System.out.println(p2.getName()+"     "+p2.getNeed()+"     "+p2.getruntime());
		System.out.println(p3.getName()+"     "+p3.getNeed()+"     "+p3.getruntime());
		System.out.println(p4.getName()+"     "+p4.getNeed()+"     "+p4.getruntime());
		System.out.println(p5.getName()+"     "+p5.getNeed()+"     "+p5.getruntime());
		System.out.println("OUTPUT  OF  RUNTIME:");
		System.out.println("CPUTIME:0");
		System.out.println(p1.getName()+"     "+p1.getNeed()+"     "+p1.getruntime()+"     "+p1.getState());
		System.out.println(p2.getName()+"     "+p2.getNeed()+"     "+p2.getruntime()+"     "+p2.getState());
		System.out.println(p3.getName()+"     "+p3.getNeed()+"     "+p3.getruntime()+"     "+p3.getState());
		System.out.println(p4.getName()+"     "+p4.getNeed()+"     "+p4.getruntime()+"     "+p4.getState());
		System.out.println(p5.getName()+"     "+p5.getNeed()+"     "+p5.getruntime()+"     "+p5.getState());

		ArrayList<process> a=new ArrayList<process>();
		a.add(p1);
		a.add(p2);
		a.add(p3);
		a.add(p4);
		a.add(p5);
		boolean flag=true;
		int sign=0;
		process p;
		while(flag) {
			System.out.println("CPUTIME:"+cpu);
			for(int i=sign;i<=5;i++) {
				p=a.get(i);
				if(p.getState().equals("ready")) {
					p.work();
					if(p.getruntime()==p.getNeed()) {
						p.setState("Finish");
						p.setR(cpu);
					}
					sign=i+1;
					if(sign==5) {
						sign=0;
					}
					break;
				}
			}
			

			for(process a1:a) {
				System.out.println(a1.getName()+"     "+a1.getNeed()+"     "+a1.getruntime()+"     "+a1.getState());
				
			}

			for(process a1:a) {
				if(a1.getNeed()!=a1.getruntime()) {
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
			int j=a.get(i).getR()-a.get(i).getNeed();
			System.out.println(a.get(i).getName()+"     "+a.get(i).getR()+"     "+j);
			i++;
		}
	}
}