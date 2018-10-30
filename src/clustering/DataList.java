package clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import clustering.Vector;

public class DataList implements Iterable<Data> {
    private final List<Data> datas = new ArrayList<Data>();

    public DataList() {
    }
    public DataList(String input,int numFeatures) throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( new File(input)),"gbk"));
        String s = null;
        int i = 0;
        while ((s=reader.readLine())!=null) {
            //title
            String arry[] =s.split("\t");
            //vector content
            String[] vectorString = arry[1].split("\\s+");
            Vector vector = new Vector(numFeatures);
            for (int j = 0; j < vectorString.length; j++) {
                //add to vector
                vector.set(j, Double.parseDouble(vectorString[j]));
            }
            //file title
            String title =arry[0];

            Data data = new Data(i, title);
            data.setVector(vector);
            datas.add(data);
            i++;
        }
        reader.close();

    }

    public void add(Data data) {
        datas.add(data);
    }

    public void clear() {
        datas.clear();
    }

    public void clearIsAllocated() {
        for (Data data : datas) {
            data.clearIsAllocated();
        }
    }

    public Data get(int index) {
        return datas.get(index);
    }

    public boolean isEmpty() {
        return datas.isEmpty();
    }

    public Iterator<Data> iterator() {
        return datas.iterator();
    }

    public int size() {
        return datas.size();
    }

    public void sort() {
        Collections.sort(datas);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Data data : datas) {
            sb.append("  ");
            //获取数据
            sb.append(data.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
