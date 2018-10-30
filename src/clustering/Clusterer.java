
package clustering;


public interface Clusterer {
    public ClusterList runKMeansClustering(DataList documentList, int k);
}
