public class Test {
    /**
     * @ClassName Test
     * @Author lilililpigg
     * @Date 2021/6/9
     * @param args
     */
    public static  void main(String [] args){
        //实例化一个生产者和消费者共用的缓冲区
        Buffer buffer =new Buffer();
        System.out.println("[缓冲区容量为2]");
        //实例化2个生产者
        Producer p1=new Producer(buffer);
        Producer p2=new Producer(buffer);
        Producer p3=new Producer(buffer);

        //实例化两个消费者
        Consumer c1=new Consumer(buffer);
        Consumer c2=new Consumer(buffer);
        
        //实例化4个线程
        Thread thread1 = new Thread(p1,"[线程1]");
        Thread thread2 = new Thread(p2,"[线程2]");
        Thread thread3 = new Thread(c1,"[线程3]");
        Thread thread4 = new Thread(c2,"[线程4]");
        thread2.setPriority(10);

        //3个线程工作
        thread1.start();
        thread2.start();
        thread3.start();
//        thread4.start();

    }
}
