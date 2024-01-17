package ex3;

import java.util.ArrayList;
import java.util.Random;

public class manage {
	 private static Random random=new Random();

	    //��ȡ���ظ����������
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
		//������ҵ���в���ӡ
        ArrayList<Job> jobList=new ArrayList<Job>();
        //��ò����������ָ�Ԫ
        int[] loc1=getInt(10,128);
        jobList.add(new Job("+",0,loc1[0]));
        jobList.add(new Job("+",1,loc1[1]));
        jobList.add(new Job("*",2,loc1[2]));
        jobList.add(new Job("��",3,loc1[3]));
        jobList.add(new Job("ȡ",0,loc1[4]));
        jobList.add(new Job("-",6,loc1[5]));
        jobList.add(new Job("��λ",4,loc1[6]));
        jobList.add(new Job("+",5,loc1[7]));
        jobList.add(new Job("/",0,loc1[8]));
        System.out.println("-------��ҵ����------------");
        System.out.println("����"+"\t"+"ҳ��"+"\t"+"��Ԫ��");
        for (int i=0; i<jobList.size(); i++) {
            System.out.println(jobList.get(i).toString());
        }

        //����ҳ����ʼ������(��СΪ4��)
        ArrayList<Page> Pages=new ArrayList<Page>();
        int[] a=getInt(4,4);	//ҳ�����������
        int[] loc2=getInt(10,128);
        Pages.add(new Page(0,1,a[0],loc2[0]));
        Pages.add(new Page(1,1,a[1],loc2[1]));
        Pages.add(new Page(2,1,a[2],loc2[2]));
        Pages.add(new Page(3,1,a[3],loc2[3]));
        Pages.add(new Page(4,0,loc2[4]));
        Pages.add(new Page(5,0,loc2[5]));
        Pages.add(new Page(6,0,loc2[6]));
        System.out.println("\n"+"------------����ҳ��------------");
        System.out.println("ҳ��"+"\t"+"��־��"+"\t"+"������"+"\t"+"����λ��");
        for (int j=0; j<Pages.size(); j++) {
            System.out.println(Pages.get(j).toString());
        }

        System.out.println("\n"+"------------ָ��ִ��------------");
        Page[] pagetable=new Page[] {Pages.get(0),Pages.get(1),Pages.get(2),Pages.get(3)};	//�Ѵ����ڴ��ҳ
        int k=0;	//ҳ�û�ָ��
        for(Job j:jobList) {
            Page p=Pages.get(j.getPageNum());
            if(p.getMark()==1) {	//��ҳ�Ѵ�������
                if(j.getOperation().equals("��")) {
                    p.setChangeMark(1);	//��ʾ��ҳ�ѱ��޸�, ���������, ����Ҫ����
                }
                int address=p.getBlockNum()*128+j.getUnitNum();
                System.out.println("ָ��\""+j.getOperation()+"\", ���Ե�ַΪ: "+address);
            }else {	//��ҳδ�������棬����ȱҳ�ж�
                System.out.println("��ȱҳ�ж�, * "+p.getPageNum());
                Page oldPage=pagetable[k];
                if(oldPage.getChangeMark()==1) {
                    System.out.println("��������ҳ���޸Ĺ�, ��������, OUT: "+oldPage.getPageNum());
                }
                System.out.println("��IN: "+p.getPageNum());
                p.setBlockNum(oldPage.getBlockNum());
                oldPage.setBlockNum(-1);	//�����滻����ҳ����������Ϊ-1
                oldPage.setMark(0);	//�Ƴ��ڴ�
                pagetable[k]=p;	//���µ�ҳ����
                System.out.println("��ҳ: "+p.getPageNum()+" �滻��ҳ: "+oldPage.getPageNum());
                int address=p.getBlockNum()*128+j.getUnitNum();
                System.out.println("ָ��\""+j.getOperation()+"\", ���Ե�ַΪ: "+address);
                k=(k+1)%4;	//��ȡ��һ�����滻���ڴ���
            }
        }
    }
		

	}


