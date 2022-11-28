import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ShortestFootPrint {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>( List.of(14, 22, 17, 32, 87, 65, 34, 23) );
        List<Integer> list2 = new ArrayList<>( List.of(55, 34, 55, 33, 65, 34, 65) );
        var resultant = new ArrayList<>( List.of(21,16,52) );
        System.out.println(findFootprint(list1, list2, resultant));
    }

    private static String findFootprint(List<Integer> list1, List<Integer> list2, List<Integer> resultant) {
        Collections.sort(list1);
        Collections.sort(list2);
        //Collections.sort(resultant);
        int diff1 = findDifference(list1, resultant);
        int diff2 = findDifference(list2, resultant);
        if(diff2 > diff1)
            return "first";
        return "second";
    }

    private static int findDifference(List<Integer> list, List<Integer> resultant) {
        int diff = 0;
        int index = 1;
        for(int i=0; i< resultant.size(); i++){
            int min = calcualteDifferenceUsingBinarySearch(list, resultant.get(i));
            System.out.println("min diff:"+min);
            diff+=min;
        }
        System.out.println("diff:"+diff);
        return diff;
    }

    private static int calcualteDifferenceUsingBinarySearch(List<Integer> list, int value){
        int min = Integer.MAX_VALUE;
        int l =0;
        int r = list.size();
        int index = (l+ r)/2;
        while( min > Math.abs(list.get(index) -value) && l <=r){
            min = Math.abs(list.get(index) -value);
            if(list.get(index) > value){
                r = index -1;
            } else if (list.get(index) < value) {
                l = index + 1;
            } else if (list.get(index) == value ) {
                min =0;
                break;
            }
            index = (l+r)/2;
        }
        return min;
    }
}
