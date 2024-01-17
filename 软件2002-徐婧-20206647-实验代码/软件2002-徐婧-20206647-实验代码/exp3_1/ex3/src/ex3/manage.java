package ex3;

import java.util.ArrayList;
import java.util.Random;

public class manage {
	 private static Random random=new Random();

	    //获取不重复的随机数组
	    public static int[] getInt(int size, int range) {
	        int[] result = new int[size];
	        for(int j=0;j<size;j++) {
	            result[j]=-1;
	        }
	        int count = 0;
	        while(count < result.length){
	            boolean flag = true;
	            int r = random.nextInt(range);
	            for(int i=0;i<result.length;i++){
	                if(r == result[i]){
	                    flag = false;
	                    break;
	                }
	            }
	            if(flag){
	                result[count] = r;
	                count++;
	            }
	        }
	        return result;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//建立作业序列并打印
        ArrayList<Job> jobList=new ArrayList<Job>();
        //获得并设置随机的指令单元
        int[] loc1=getInt(10,128);
        jobList.add(new Job("+",0,loc1[0]));
        jobList.add(new Job("+",1,loc1[1]));
        jobList.add(new Job("*",2,loc1[2]));
        jobList.add(new Job("存",3,loc1[3]));
        jobList.add(new Job("取",0,loc1[4]));
        jobList.add(new Job("-",6,loc1[5]));
        jobList.add(new Job("移位",4,loc1[6]));
        jobList.add(new Job("+",5,loc1[7]));
        jobList.add(new Job("/",0,loc1[8]));
        System.out.println("-------作业序列------------");
        System.out.println("操作"+"\t"+"页号"+"\t"+"单元号");
        for (int i=0; i<jobList.size(); i++) {
            System.out.println(jobList.get(i).toString());
        }

        //创建页表并初始化主存(大小为4块)
        ArrayList<Page> Pages=new ArrayList<Page>();
        int[] a=getInt(4,4);	//页随机存入主存
        int[] loc2=getInt(10,128);
        Pages.add(new Page(0,1,a[0],loc2[0]));
        Pages.add(new Page(1,1,a[1],loc2[1]));
        Pages.add(new Page(2,1,a[2],loc2[2]));
        Pages.add(new Page(3,1,a[3],loc2[3]));
        Pages.add(new Page(4,0,loc2[4]));
        Pages.add(new Page(5,0,loc2[5]));
        Pages.add(new Page(6,0,loc2[6]));
        System.out.println("\n"+"------------主存页表------------");
        System.out.println("页号"+"\t"+"标志号"+"\t"+"主存块号"+"\t"+"磁盘位置");
        for (int j=0; j<Pages.size(); j++) {
            System.out.println(Pages.get(j).toString());
        }

        System.out.println("\n"+"------------指令执行------------");
        Page[] pagetable=new Page[] {Pages.get(0),Pages.get(1),Pages.get(2),Pages.get(3)};	//已存入内存的页
        int k=0;	//页置换指针
        for(Job j:jobList) {
            Page p=Pages.get(j.getPageNum());
            if(p.getMark()==1) {	//该页已存入主存
                if(j.getOperation().equals("存")) {
                    p.setChangeMark(1);	//表示该页已被修改, 如果被覆盖, 则需要调出
                }
                int address=p.getBlockNum()*128+j.getUnitNum();
                System.out.println("指令\""+j.getOperation()+"\", 绝对地址为: "+address);
            }else {	//该页未存入主存，产生缺页中断
                System.out.println("·缺页中断, * "+p.getPageNum());
                Page oldPage=pagetable[k];
                if(oldPage.getChangeMark()==1) {
                    System.out.println("·被覆盖页被修改过, 调出保存, OUT: "+oldPage.getPageNum());
                }
                System.out.println("·IN: "+p.getPageNum());
                p.setBlockNum(oldPage.getBlockNum());
                oldPage.setBlockNum(-1);	//将被替换掉的页的主存块号置为-1
                oldPage.setMark(0);	//移出内存
                pagetable[k]=p;	//将新的页存入
                System.out.println("·页: "+p.getPageNum()+" 替换了页: "+oldPage.getPageNum());
                int address=p.getBlockNum()*128+j.getUnitNum();
                System.out.println("指令\""+j.getOperation()+"\", 绝对地址为: "+address);
                k=(k+1)%4;	//获取下一个被替换的内存块号
            }
        }
    }
		

	}


