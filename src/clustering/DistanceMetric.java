package clustering;

import clustering.Vector;

//calculate distance
public abstract class DistanceMetric {

    public double calcDistance(Data data, Cluster cluster) {
        return calcDistance(data.getVector(), cluster.getCentroid());
    }

    /**
     * calculate min distance between data and centroid
     * @param Data
     * @param ClusterList
     * @return distance, min distance
     */
    public double calcDistance(Data data, ClusterList clusterList) {
        double distance = Double.MAX_VALUE;
        for (Cluster cluster : clusterList) {
            distance = Math.min(distance, calcDistance(data, cluster));
        }
        return distance;
    }

    protected abstract double calcDistance(Vector vector1, Vector vector2);
}
