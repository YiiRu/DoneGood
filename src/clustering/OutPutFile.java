package clustering;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import clustering.Cluster;
import clustering.ClusterList;
import clustering.Data;


public class OutPutFile {
    /** write the results to a file */

    public static void outputClusterAndContent(String strDir,ClusterList clusterList) throws IOException{
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File(strDir)),"gbk"));
        int i = 0;
        for (Cluster cluster : clusterList) {
            writer.write("Cluster" + i + ":" + cluster.getCentroid() + "\n");
            for (Data doc: cluster.getDatas()) {
                writer.write("\t" + doc.getTitle() + "\t" + doc.getVector() + "\n");
            }
            i++;
        }
        writer.close();
    }
}
