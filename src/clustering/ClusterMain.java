package clustering;

import java.io.IOException;

import clustering.ClusterList;
import clustering.Clusterer;
import clustering.DistanceMetric;
import clustering.DataList;
import clustering.EuclideanDistance;
import clustering.KMeansClusterer;
import clustering.OutPutFile;

public class ClusterMain {
	private static final int iter = 500; //set iteration number
    private static final int feature_number = 2; //set feature number
    private static final int k = 3;  //set cluster number

    public static void main(String[] args) throws IOException {
        //file input
    	String fileinput = "data/data.txt";
        //read file,change to vector mode
        DataList documentList = new DataList(fileinput, feature_number);
        //define distance
        DistanceMetric distance = new EuclideanDistance();
        Clusterer clusterer = new KMeansClusterer(distance, iter);
        //Clustering
        ClusterList clusterList = clusterer.runKMeansClustering(documentList, k);
        System.out.println(clusterList);
        //Output result
        OutPutFile.outputClusterAndContent("result/cluster"+k,clusterList);
    }
}
