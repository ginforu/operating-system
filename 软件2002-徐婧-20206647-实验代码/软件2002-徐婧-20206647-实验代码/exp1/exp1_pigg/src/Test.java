public class Test {
    /**
     * @ClassName Test
     * @Author lilililpigg
     * @Date 2021/6/9
     * @param args
     */
    public static  void main(String [] args){
        //ʵ����һ�������ߺ������߹��õĻ�����
        Buffer buffer =new Buffer();
        System.out.println("[����������Ϊ2]");
        //ʵ����2��������
        Producer p1=new Producer(buffer);
        Producer p2=new Producer(buffer);
        Producer p3=new Producer(buffer);

        //ʵ��������������
        Consumer c1=new Consumer(buffer);
        Consumer c2=new Consumer(buffer);
        
        //ʵ����4���߳�
        Thread thread1 = new Thread(p1,"[�߳�1]");
        Thread thread2 = new Thread(p2,"[�߳�2]");
        Thread thread3 = new Thread(c1,"[�߳�3]");
        Thread thread4 = new Thread(c2,"[�߳�4]");
        thread2.setPriority(10);

        //3���̹߳���
        thread1.start();
        thread2.start();
        thread3.start();
//        thread4.start();

    }
}
