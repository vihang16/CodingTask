import java.util.Stack;

public class DailyTemperature {
    public static void main(String[] args) {
      DailyTemperature dt = new DailyTemperature();
      System.out.println(dt.dailyTempr(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }
    int[] dailyTempr(int[] temp){
        int n=temp.length;
        if(n==1){
            return temp;
        }

        Stack<Integer> st=new Stack<Integer>();
        int res[]=new int[n];

        st.add(0);
        for(int i=1;i<n;i++){
            while(!st.isEmpty() && temp[st.peek()]<temp[i]){
                int top=st.pop();
                // System.out.print(temp[top] +"=>" + temp[i]);
                res[top]=i-top; //top-i;
            }
            st.add(i);
        }

        return res;
    }
}
