/**
 * @ClassName Producer
 * @Author lilililpigg
 * @Date 2021/6/9
 * @Description 生产者类
 */
public class Producer implements  Runnable {

    public  static  Integer count=0;
    Buffer buffer =null;
    
    public  Producer(Buffer buffer){
          this.buffer = buffer;
    }

    @Override  
    public void run(){
          while(true){
              synchronized (Producer.class){
                  Product product=new Product(count);
                  count++;
                  buffer.push(product,Thread.currentThread().getName());
              }
          }
      }
}
