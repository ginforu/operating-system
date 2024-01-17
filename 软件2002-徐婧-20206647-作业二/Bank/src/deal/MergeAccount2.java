package deal;

import java.io.*;
import java.util.*;

public class MergeAccount2 {

    private static List<String> list = new LinkedList<>();
    private static RandomAccessFile[] fileWriters = new RandomAccessFile[100];
    private static File[] files = new File[100];
    private static int[] count = new int[100];
    private static StringBuilder[] sbs = new StringBuilder[100];
    private static String PATH = "/Users/account/";
    private static String SRC_FILE = "/Users/account.csv";
    private static String DST_FILE = "/Users/account/account.csv";
    static{
        File path = new File(PATH);
        path.mkdirs();
        for(int i=0;i<100;i++){
            count[i] = 0;
            sbs[i] = new StringBuilder();
            files[i] = new File(PATH+i+".csv");
            if(!files[i].exists()){
                try {
                    files[i].createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriters[i] = new RandomAccessFile(files[i],"rw");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        splitFile();
        try {
            sortFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            mergeFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void mergeFile() throws FileNotFoundException {
        File writeFile = new File(DST_FILE);
        long start = System.currentTimeMillis();
        System.out.println("合并开始");
        if(!writeFile.exists()){
            try {
                writeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter printWriter = new PrintWriter(writeFile);
        int i=0;
        for(File file:files){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()+".copy"));
                String line = null;
                while((line = br.readLine())!=null){
                    printWriter.println(line);
                    if(i%1000==0){
                        printWriter.flush();
                    }
                }
                i++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printWriter.close();
        long end = System.currentTimeMillis();
        System.out.println("合并结束,耗时:"+(end-start)/1000+"秒");
    }

    private static void sortFile() throws FileNotFoundException {
        for(File file:files){
            Map<String,Float> map = new HashMap<>();
            Map<String,Long> countMap = new HashMap<>();
            if(file.exists()){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = null;
                    String[] arr;
                    while((line = br.readLine())!=null) {
                        arr = line.split(",");
                        if(map.get(arr[0])==null){
                            map.put(arr[0],Float.parseFloat(arr[1]));
                            countMap.put(arr[0],1l);
                        }
                        else{
                            map.put(arr[0],map.get(arr[0])+Float.parseFloat(arr[1]));
                            countMap.put(arr[0],countMap.get(arr[0])+1);
                        }
                    }
                    br.close();

                    StringBuilder stringBuilder = new StringBuilder();
                    PrintWriter printWriter = new PrintWriter(new File(file.getAbsoluteFile()+".copy"));
                    int s = 0;
                    for(String k:countMap.keySet()){
                        if(countMap.get(k)!=null&&countMap.get(k)>1){
                            s++;
                            stringBuilder.append(k+","+map.get(k)+"\n");
                            if(s%1000==0){
                                printWriter.print(stringBuilder.toString());
                                printWriter.flush();
                                stringBuilder = new StringBuilder();
                            }
                        }
                    }
                    printWriter.print(stringBuilder.toString());
                    printWriter.flush();
                    printWriter.close();
                    System.out.println("done:"+file.getName());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void splitFile() {
        long start = System.currentTimeMillis();
        try {
            BufferedReader br = new BufferedReader(new FileReader(SRC_FILE));
            int i = 0;
            String line = null;
            String[] arr;
            String key;
            while((line = br.readLine())!=null){
                arr = line.split(",");
                int account = Integer.parseInt(arr[0]);
                int idx = account%100;
                count[idx] = count[idx]+1;
                sbs[idx].append(line+"\n");
                // 10000行写入一次
                if(count[idx]%10000==0){
                    fileWriters[idx].seek(files[idx].length());
                    fileWriters[idx].write(sbs[idx].toString().getBytes());
                    sbs[idx] = new StringBuilder();
                    fileWriters[idx].close();
                    fileWriters[idx] = new RandomAccessFile(files[idx],"rw");
                }
                i++;
                if(i%10000==0){
                    System.out.println(i);
                }
            }

            for(int k=0;k<sbs.length;k++){
                if(!"".equals(sbs[k].toString())){
                    fileWriters[k].seek(files[k].length());
                    fileWriters[k].write(sbs[k].toString().getBytes());
                    fileWriters[k].close();
                }
                else{
                    if(fileWriters[k]!=null){
                        fileWriters[k].close();
                    }
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("分割文件耗时："+(end-start)/1000+"s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
