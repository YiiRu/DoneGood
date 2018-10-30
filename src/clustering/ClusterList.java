package clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ClusterList implements Iterable<Cluster> {
    private final ArrayList<Cluster> clusters = new ArrayList<Cluster>();
    
    public void add(Cluster cluster) {
        clusters.add(cluster);
    }
   
    public void clear() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }
    /**
     * 璁＄畻鏈垎閰嶇殑鏁版嵁绂昏川蹇冪殑璺濈锛岀‘瀹氫竴涓璐ㄥ績鏈�杩滅殑涓�涓偣,杩欓噷閲囩敤鐨勬槸娆у嚑閲屽緱璺濈鍏紡
     * @param DistanceMetric,璺濈 
     * @param DataList,鏁版嵁闆嗗悎
     * @return Data,鏁版嵁鐐�
     */
    public Data findFurthestData(DistanceMetric distance, DataList dataList) {
        double furthestDistance = Double.MIN_VALUE;
        Data furthestData = null;
        for (Data data : dataList) {
            if (!data.isAllocated()) {
                
                double dataDistance = distance.calcDistance(data, this);
                if (dataDistance > furthestDistance) {
                    furthestDistance = dataDistance;
                    furthestData = data;
                }
            }
        }
        return furthestData;
    }
    /**
     * 
     *
     * @param DistanceMetric
     * @param data
     * @return Cluster
     */
    public Cluster findNearestCluster(DistanceMetric distance, Data data) {
        Cluster nearestCluster = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Cluster cluster : clusters) {
            
            double clusterDistance = distance.calcDistance(data, cluster);
            if (clusterDistance < nearestDistance) {
                nearestDistance = clusterDistance;
                nearestCluster = cluster;
            }
        }
        return nearestCluster;
    }

    public Iterator<Cluster> iterator() {
        return clusters.iterator();
    }

   
    public int size() {
        return clusters.size();
    }
   
    private void sort() {
        for (Cluster cluster : this) {
            cluster.sort();
        }
        Collections.sort(clusters);
    }
   
    public String toString() {
        sort();
        StringBuilder sb = new StringBuilder();
        int clusterIndex = 0;
        for (Cluster cluster : clusters) {
            sb.append("Cluster ");
            sb.append(clusterIndex++);
            sb.append("\n");
            sb.append(cluster);
        }
        return sb.toString();
    }

    public void updateCentroids() {
        for (Cluster cluster : clusters) {
            cluster.updateCentroid();
        }
    }

}
