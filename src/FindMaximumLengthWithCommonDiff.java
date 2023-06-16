import java.util.HashMap;
import java.util.Map;

public class FindMaximumLengthWithCommonDiff {

    public static void main(String[] args) {
        System.out.println(maximumIntegers(new int[]{4,7,1,5,3}));
        System.out.println(maximumIntegers(new int[]{12,12,12,15,10}));
        System.out.println(maximumIntegers(new int[]{18,26,18,24,20,22}));
    }

    private static int maximumIntegers(int[] numbers) {
        Map<Integer, Integer> diffMap =  new HashMap<>();
        for(int i=0; i< numbers.length-1; i++){

            for(int j= i+1; j< numbers.length; j++){
                int diff = Math.abs(numbers[j] - numbers[i]);
                diffMap.put(diff, diffMap.getOrDefault(diff, 0) + 1);

            }
        }
        int max = 0;
        for(Map.Entry<Integer, Integer> map : diffMap.entrySet()){
            max =  Math.max(max, map.getValue());
        }
        return max;
    }
}
