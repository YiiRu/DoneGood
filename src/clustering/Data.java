package clustering;

import clustering.Vector;

public class Data implements Comparable<Data> {
    private final String title;
    private final long id;
    private boolean allocated;
    private  Vector vector;
    private int numFeatures;

    public Data(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void clearIsAllocated() {
        allocated = false;
    }

    public int compareTo(Data data) {
        if (id > data.getId()) {
            return 1;
        } else if (id < data.getId()) {
            return -1;
        } else {
            return 0;
        }
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
 
    public int getNumFeatures() {
        return numFeatures;
    }

    public Vector getVector() {
        return vector;
    }

    public boolean isAllocated() {
        return allocated;
    }


    /** 标记该数据点已被分配 */
    public void setIsAllocated() {
        allocated = true;
    }
    
    public void setVector(Vector vector) {
        this.vector = vector;
        this.numFeatures = vector.size();
    }
  
    public String toString() {
        return "Data: " + id + ", Title: " + title;
    }
}
