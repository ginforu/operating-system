/**
 * @ClassName Consumer
 * @Author lilililpigg
 * @Date 2021/6/9
 * @Description 消费者类
 */
public class Consumer implements  Runnable {
	
    Buffer buffer =null;

    public  Consumer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void  run(){
        while (true)buffer.pop(Thread.currentThread().getName());
    }
}
