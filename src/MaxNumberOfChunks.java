public class MaxNumberOfChunks {
    public static void main(String[] args) {
        MaxNumberOfChunks max = new MaxNumberOfChunks();
        int[] a = new int[]{100,90,200,300,400};
        System.out.println(max.maxNumberOfChunks(a));
    }

    private int maxNumberOfChunks(int[] arr) {
        /*int i = 0;
        int chunkCount = 0;
        int chunkMin = Integer.MAX_VALUE;
        int chunkMax = Integer.MIN_VALUE;
        int startIndex = 0;
        while(i < arr.length){
            int cur = arr[i];
            chunkMin = Math.min(cur, chunkMin);
            chunkMax = Math.max(cur, chunkMax);
            if(chunkMin == startIndex && chunkMax == i){
                chunkCount++;
                startIndex = i + 1;
                chunkMin = Integer.MAX_VALUE;
                chunkMax = Integer.MIN_VALUE;

            }
            i++;

        }return chunkCount;*/
        int chunks = 0;
        int maxJump = arr[0];
        for(int i=0;i<arr.length;i++) {
            maxJump = Math.max(maxJump, arr[i]);
            if (i == maxJump)
                chunks++;
        }
       return chunks;
     }
}
