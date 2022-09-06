import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class KClosestPointToOrigin {
    public static void main(String[] args) {
            int[][] points = { {1,3},{-2,2}};
            int k =1;
            points = new int[][]{{6, 10}, {-3, 3}, {-2, 5}, {0, 2}};
            k=3;
            KClosestPointToOrigin k2 = new KClosestPointToOrigin();
            System.out.println(k2.kClosestPointToOrigin(points, k));

    }

    public int[][] kClosestPointToOrigin(int[][] points, int k){
        DecimalFormat df = new DecimalFormat("0.00");
        Cell c = new Cell();
        TreeMap<Double, List<Integer[]>> map = new TreeMap<>();
        int count =0;
        int[][] result = new int[k][2];
        for(int i=0; i< points.length; i++){
            double dist = Math.sqrt(points[i][0]*points[i][0] +points[i][1]*points[i][1]);
            dist = Double.valueOf(df.format(dist));
           // if(map.containsKey(dist)) {
                List<Integer[]> distCoordinates = map.getOrDefault(dist, new ArrayList<>());
                Integer[] val = new Integer[]{points[i][0], points[i][1]};
                distCoordinates.add(val);
                map.put(dist, distCoordinates);

        }
        for(Map.Entry<Double, List<Integer[]>> treeMap : map.entrySet()){
            if(k>0){
                if(k>= treeMap.getValue().size()){
                     storeValue(treeMap.getValue(), treeMap.getValue().size(), result, c);
                    k-= treeMap.getValue().size();
                }else{
                     storeValue(treeMap.getValue(), k, result, c);
                    k=0;
                }
            }else
                break;
        }

        return result;
    }

    private void  storeValue(List<Integer[]> distantArray, int lastIndex, int[][] result, Cell c){
        for(int i=0; i<lastIndex; i++){
            result[c.i][0] = distantArray.get(i)[0];
            result[c.i][1] = distantArray.get(i)[1];
            c.i++;
        }

    }
}
class Cell{
    int i;
}
