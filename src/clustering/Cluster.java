package clustering;

import clustering.Vector;

public class Cluster implements Comparable<Cluster> {
    private Vector centroid;
    private final DataList datas = new DataList();
    private final int numFeatures;

    public Cluster(Data data) {
        add(data);
        centroid = new Vector(data.getVector());
        numFeatures = data.getNumFeatures();
    }

    public void add(Data data) {
        data.setIsAllocated();
        datas.add(data);
    }

    public void clear() {
        datas.clearIsAllocated();
        datas.clear();
    }
    
    public int compareTo(Cluster cluster) {
        if (datas.isEmpty() || cluster.datas.isEmpty()) {
            return 0;
        }
        return datas.get(0).compareTo(cluster.datas.get(0));
    }

    public Vector getCentroid() {
        return centroid;
    }
  
    public DataList getDatas() {
        return datas;
    }

   
    public int size() {
        return datas.size();
    }

 
    public void sort() {
        datas.sort();
    }
  
    @Override
    public String toString() {
        return datas.toString();
    }

    
    public void updateCentroid() {
        centroid = new Vector(numFeatures);
      
        for (Data data : datas) {
            centroid = centroid.add(data.getVector());
        }
        centroid = centroid.divide(size());
    }
}
