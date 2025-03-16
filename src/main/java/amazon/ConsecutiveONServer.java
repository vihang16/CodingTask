package amazon;

public class ConsecutiveONServer {
    public static void main(String[] args) {
        System.out.println(concseutiveOn("00010", 1));
    }

    private static int concseutiveOn(String s, int k) {
        int left = 0;
        int maxLen = 0;
        int zeroCount =0;
        for(int i =0; i < s.length(); i++){
            if(s.charAt(i) == '0'){
                zeroCount++;
            }
            while(zeroCount > k){
                if(s.charAt(left) == '0'){
                    zeroCount--;
                }

                left++;
            }
            maxLen = Math.max(maxLen,  i - left + 1);
        }
        return maxLen;
    }
}
