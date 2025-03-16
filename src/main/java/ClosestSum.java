import java.util.Arrays;

public class ClosestSum {
    public static void main(String[] args) {
        ClosestSum c = new ClosestSum();
        System.out.println(c.closet(new int[]{-1,2,1,-4}, 1));
    }

    private int closet(int[] nums, int target) {
        int res =Integer.MAX_VALUE;
        int dif = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for(int i=0; i<nums.length-2; i++){
            int j=i+1, k=nums.length-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(target == sum)
                    return target;
                if(Math.abs(target - sum) < dif){
                    res = sum;
                    dif = Math.abs(target-sum);
                    k--;
                    j++;
                }else if(target - sum >0){
                    j++;
                }else
                    k--;
            }

        }
        return res;
    }
}
