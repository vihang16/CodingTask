import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindingBiggestSizeOfStick {

    public static void main(String[] args) {
        System.out.println(findBiggestBlock(10,21));
        System.out.println(findBiggestBlock(2,1));
        System.out.println(findBiggestBlock(1,8));
        System.out.println(findBiggestBlock(13,11));
        System.out.println(findBiggestBlock(6,21));
    }

    /**
    this is the approach:
     we need to get 4 blocks, this we can either divide highest size block by 4,3,2
     now,we need to take second block and check if we can make 2 blocks from smaller one with similar size which we cut from bigger
     if 2 blocks not available,try for 1, then 0
     */
    private static int findBiggestBlock(int a, int b) {
        int max = Math.max(a,b);
        if(max <3)
            return 0;
        int min = Math.min(a,b);
        int result = 0;
        int total = 4;
        Map<Integer, List<Integer>> sizeBlock = new HashMap<>();//populate hashMap with key as number of partitions correpsonding to length of each block
        sizeBlock.put(0, List.of(max));
        //System.out.println("start index:"+max/4);
        //System.out.println("end index:"+max/2);
        for(int i=max/4; i<= max/2; i++){
            int numberOfBlocks = max/i;
            List<Integer> list = sizeBlock.getOrDefault(numberOfBlocks, new ArrayList<>());
            list.add(i);
            sizeBlock.put(numberOfBlocks, list);
        }
        //System.out.println(sizeBlock);
        int remainder = 2;
        while( remainder >=0){
            int remaining = total - remainder;
            //System.out.println("remaining:"+remaining);
            //System.out.println("remainder:"+remaining);
            List<Integer> sizes = sizeBlock.getOrDefault(remaining, new ArrayList<>());
            //System.out.println("sizes of block:"+sizes);

            for(int i = sizes.size() -1; i>=0; i--){
                //System.out.println("min/sizes.get(i) "+min/sizes.get(i) );
                if(sizes.get(i) <= min && Math.floor(min/(double)sizes.get(i)) >= remainder ){ //check if we can divide smaller sticks into require partitions

                    result =Math.max(sizes.get(i), result);

                }
            }

            remainder--;
        }
        if(result == 0 && sizeBlock.containsKey(4)) // if smaller can't divide to required partition but bigger block can be divide into 4 parts
            return sizeBlock.get(4).get(sizeBlock.get(4).size() -1 );
        return result;
    }
}
