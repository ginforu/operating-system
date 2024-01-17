import java.util.LinkedList;
/**
 * @ClassName Buffer
 * @Author lilililpigg
 * @Date 2021/6/9
 * @Description 缓冲区
 */
public class Buffer {
    //缓冲区的最大容量为2
    public LinkedList<Product> store = new LinkedList<Product>();
    public  LinkedList<Product> getStore(){
        return  store;
    }
    public  void  setStore(LinkedList<Product> store){
        this.store=store;
    }
    //生产者方法
    public  synchronized  void push(Product product,String threadName) {
        
        while (store.size() == 2) {
            try {
                System.out.println(threadName + " 缓冲区已满 --> 等待状态 --> 消费者开始清理");
                //缓冲区空了
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        //缓冲区中加入产品
        store.addLast(product);
        System.out.println( threadName + " 开始生产产品" + product.getId() + "，缓冲区有" + store.size() + "个产品");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //消费者方法
    public synchronized  void  pop(String threadName){
        
        while(store.size()==0){
            try {
                    System.out.println(threadName + " 缓冲区为空 --> 等待状态 --> 生产者开始生产 ");
                    //缓冲区空了，等待生产
                    this.wait();
                }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        //从缓冲区中处理产品
        System.out.println(threadName+" 开始清理"+"产品"+store.removeFirst().getId()+"，缓冲区有" + store.size() + "个产品");
        try {
            //线程睡眠时间1秒
            Thread.sleep(1000);
            }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
