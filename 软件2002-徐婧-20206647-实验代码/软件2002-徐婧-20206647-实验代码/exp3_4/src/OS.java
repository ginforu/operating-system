import java.util.LinkedList;
import java.util.Scanner;

public class OS {
    public LinkedList<MFD> mfd = new LinkedList<MFD>();
    public LinkedList<AFD> afd = new LinkedList<AFD>();
    int afd_size=0;
    int num=0;
    public void register(String userName){
        LinkedList<UFD> ufd = new LinkedList<UFD>();
        MFD user = new MFD(userName);
        mfd.add(user);
        UFD file = new UFD();
        ufd.add(file);
        mfd.get(num).l = ufd;
        num++;
    }
    public void Login(String userName){
        for(int i =0; i<mfd.size();i++){
            if(mfd.get(i).user.equals(userName)){
                if(mfd.get(i).l.get(0).fileName==null){
                    System.out.println("当前账户文件为空");
                }else{
                    System.out.println("列出文件目录");
                    System.out.println("文件名\t保护码\t文件长度\t");
                    for(int j=0;j<mfd.get(i).l.size();j++){
                        System.out.println(mfd.get(i).l.get(j).fileName+"\t"+mfd.get(i).l.get(j).protect+"\t"+mfd.get(i).l.get(j).fileLength);
                    }
                }
                boolean flag =true;
                while(flag){
                    System.out.println("创建文件----1");
                    System.out.println("打开文件----2");
                    System.out.println("删除文件----3");
                    System.out.println("列出文件目录----4");
                    System.out.println("退出----5");
                    Scanner sc=new Scanner(System.in);
                    int num=sc.nextInt();
                    switch(num){
                        case 1: create_file(userName);break;
                        case 2: open_file(userName);break;
                        case 3: delete_file(userName);break;
                        case 4: dir(userName);break;
                        case 5: flag = false;break;
                        default: System.out.println("您输入的数字不正确");
                    }
                }
                break;

            }
        }
    }

    public void create_file(String userName){
        for(int i =0; i<mfd.size();i++){
            if(mfd.get(i).user.equals(userName)){
                System.out.println("请输入创建的文件名称");
                Scanner sc1=new Scanner(System.in);
                String filename = sc1.next();
                System.out.println("请输入创建的文件长度");
                Scanner sc2=new Scanner(System.in);
                int fileLength = sc2.nextInt();   
                UFD a = new UFD(filename,fileLength);
                int j;
                System.out.println(mfd.get(i).l.size());
                for(j =0;j<mfd.get(i).l.size();j++){
                    if(mfd.get(i).l.get(0).fileName ==null)
                        break;
                    else if(mfd.get(i).l.get(j).fileName .equals(filename) ){
                        System.out.println("文件 "+filename+" 已经存在了");
                        break;
                    }
                }
                if(mfd.get(i).l.get(0).fileName ==null){
                    mfd.get(i).l.removeLast();
                    mfd.get(i).l.add(a);
                    System.out.println("文件"+filename+" 创建成功");
                }
                else if(j==mfd.get(i).l.size()){
                    mfd.get(i).l.add(a);
                    System.out.println("文件 "+filename+" 创建成功");
                }

            }
            break;
        }
    }

    public void dir(String userName){
        for(int i =0; i<mfd.size();i++){
            if(mfd.get(i).user.equals(userName)){
                if(mfd.get(i).l.get(0).fileName==null){
                    System.out.println("当前文件列表为空。");
                }else{
                    System.out.println("文件列表:");
                    System.out.println("文件名\t保护码\t文件长度\t");
                    for(int j=0;j<mfd.get(i).l.size();j++){
                        System.out.println(mfd.get(i).l.get(j).fileName+"\t"+mfd.get(i).l.get(j).protect[0]+mfd.get(i).l.get(j).protect[1]+mfd.get(i).l.get(j).protect[2]+"\t"+mfd.get(i).l.get(j).fileLength);
                    }
                }
            }
            break;
        }

    }
    public void delete_file(String userName){

        for(int i =0; i<mfd.size();i++){
            if(mfd.get(i).user.equals(userName)){
                System.out.println("输入需删除的文件名");
                Scanner sc1=new Scanner(System.in);
                String file_name = sc1.next();
                int j;
                for(j =0;j<mfd.get(i).l.size();j++){
                    if(mfd.get(i).l.get(j).fileName .equals(file_name) ){
                        mfd.get(i).l.remove(j);
                        System.out.println("已经删除文件"+file_name);
                        afd_size--;
                        break;
                    }
                }
                if(j==mfd.get(i).l.size()){

                    System.out.println("没有找到文件 "+file_name);
                }

                for(int m =0;m<afd.size();j++){
                    if(afd.get(m).openFilename .equals(file_name) ){
                        afd.remove(m);
                        break;
                    }
                }


            }
            break;
        }



    }
    public void open_file(String userName){
        for(int i =0; i<mfd.size();i++){
            if(mfd.get(i).user.equals(userName)){
                System.out.println("输入需打开的文件名");
                Scanner sc1=new Scanner(System.in);
                String filename = sc1.next();
                int j;
                for(j =0;j<mfd.get(i).l.size();j++){
                    if(mfd.get(i).l.get(j).fileName.equals(filename)){
                        AFD a = new AFD();
                        a.openFilename = filename;
                        if(afd_size==5){
                            afd.removeFirst();
                            afd_size--;
                        }
                        afd.add(a);
                        afd_size++;
                        System.out.println("打开文件 "+filename);
                        boolean flag = true;
                        while(flag){
                            System.out.println("写文件----1");
                            System.out.println("读文件----2");
                            System.out.println("关文件----3");
                            System.out.println("其他----4");
                            Scanner in=new Scanner(System.in);
                            int num=in.nextInt();
                            switch(num){
                                case 1: writeFile(filename);break;
                                case 2: readFile(filename);break;
                                case 3: closeFile(filename);break;
                                case 4: flag = false;break;
                                default: System.out.println("输入错误");
                            }

                        }
                        break;
                    }

                }
                if(j==mfd.get(i).l.size()){

                    System.out.println("没有找到文件"+filename);
                }
                break;
            }

        }


    }
    public void writeFile(String filename){
        for(int i =0; i<afd.size();i++){
            if(afd.get(i).openFilename.equals(filename)){
                if(afd.get(i).read==true){
                    System.out.println("文件"+filename+"正在读，不能同时写");
                }
                else{
                    afd.get(i).write = true;
                    System.out.println("文件"+filename+"正在读");
                }
            }
            break;
        }
    }
    public void readFile(String filename){
        for(int i =0; i<afd.size();i++){
            if(afd.get(i).openFilename.equals(filename)){
                if(afd.get(i).write==true){
                    System.out.println("文件"+filename+"正在写，不能读");
                }
                else{
                    afd.get(i).read = true;
                    System.out.println("文件"+filename+"正在读");
                }
            }
            break;
        }
    }

    public void closeFile(String filename){
        for(int i =0; i<afd.size();i++){
            if(afd.get(i).openFilename.equals(filename)){
                if(afd.get(i).write==true){
                    System.out.println("文件"+filename+"写完毕");
                    System.out.println("文件"+filename+"完毕");
                    afd.get(i).write=false;
                }
                else if(afd.get(i).read==true){
                    afd.get(i).read = false;
                    System.out.println("文件"+filename+"读完毕");
                    System.out.println("文件"+filename+"完毕");
                }
                else{
                    System.out.println("文件"+filename+"完毕");
                }
            }
            break;
        }
    }
}