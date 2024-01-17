import java.util.LinkedList;
/**
 * @ClassName Buffer
 * @Author lilililpigg
 * @Date 2021/6/9
 * @Description ������
 */
public class Buffer {
    //���������������Ϊ2
    public LinkedList<Product> store = new LinkedList<Product>();
    public  LinkedList<Product> getStore(){
        return  store;
    }
    public  void  setStore(LinkedList<Product> store){
        this.store=store;
    }
    //�����߷���
    public  synchronized  void push(Product product,String threadName) {
        
        while (store.size() == 2) {
            try {
                System.out.println(threadName + " ���������� --> �ȴ�״̬ --> �����߿�ʼ����");
                //����������
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        //�������м����Ʒ
        store.addLast(product);
        System.out.println( threadName + " ��ʼ������Ʒ" + product.getId() + "����������" + store.size() + "����Ʒ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //�����߷���
    public synchronized  void  pop(String threadName){
        
        while(store.size()==0){
            try {
                    System.out.println(threadName + " ������Ϊ�� --> �ȴ�״̬ --> �����߿�ʼ���� ");
                    //���������ˣ��ȴ�����
                    this.wait();
                }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        //�ӻ������д����Ʒ
        System.out.println(threadName+" ��ʼ����"+"��Ʒ"+store.removeFirst().getId()+"����������" + store.size() + "����Ʒ");
        try {
            //�߳�˯��ʱ��1��
            Thread.sleep(1000);
            }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
