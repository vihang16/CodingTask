import com.sun.source.tree.Tree;

import java.util.*;

public class EmelyOA {

    public static void main(String[] args) {
       /* int[] a = new int[]{2,2,1,2};
        int[] b = new int[]{1,3,4,4};
        int n =5;
        int[] a = new int[]{1};
        int[] b = new int[]{3};
        int n =3;*/
        int[] a = new int[]{1,3};
        int[] b = new int[]{2,4};
        int n =4;
        EmelyOA  e = new EmelyOA();
        int result = e.findMaxValue(n, a,b);
        System.out.println(result);

    }

    private int findMaxValue(int n, int[] a, int[] b) {
        Map<Integer, Integer> countreMap = new HashMap<>();
        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>(Collections.reverseOrder());
        Map<Integer, Integer> edgeValueMap = new HashMap<>();
        int sum = 0;
        int count = n;
        for(int i=0; i<a.length; i++){
            countreMap.put(a[i], countreMap.getOrDefault(a[i], 0)+1);
            countreMap.put(b[i],countreMap.getOrDefault(b[i], 0)+1 );
        }
        for(Map.Entry<Integer, Integer> map: countreMap.entrySet() ){
            freqMap.compute(map.getValue(),(k,v) ->{
                v = v == null ? new ArrayList<>() : v;
                v.add(map.getKey());
                return v;
            });
        }
        for(Map.Entry<Integer, List<Integer>> freq: freqMap.entrySet()){
             for(Integer vertex : freq.getValue()){
                 edgeValueMap.put(vertex, count);
                 count--;
             }
        }
        for(int i=0; i<a.length; i++)
            sum+= edgeValueMap.get(a[i]) + edgeValueMap.get(b[i]);

        return sum;
    }
}
