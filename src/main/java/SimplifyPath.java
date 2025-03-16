import java.util.ArrayList;
import java.util.Stack;

public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
        String result = sp.createSimplifyAPath("/home//foo///");
       // System.out.println(result);
        System.out.println(calculatePair(new int[]{1, 2, 3, -1, -2, 1, 2, 3, -1, -2, 1, 2, 3, -1, -2}));
    }
    public static int calculatePair(int[] arr){
        int count=0;
        ArrayList<Integer> postn = new ArrayList<Integer>();
        for(int  i=0; i<arr.length;i++){
            for(int j = i+1; j<arr.length;j++){
                if(!postn.contains(j)){
                    int temp =  arr[i];
                    int pos = arr[j];
                    int result = temp +(pos);
                    if(result ==0){
                        count++;
                        postn.add(i);

                        postn.add(j);
                    }
                }
            }
        }
        return count;
    }


    private String createSimplifyAPath(String path) {
        String[] paths = path.split("/");
        Stack<String> st=new Stack<>();
        for(String dir:paths){
            if(dir.equals(".") || dir.length()==0) continue;
            else{
                if(!st.isEmpty() && dir.equals(".."))
                    st.pop();
                else if(st.isEmpty() && dir.equals(".."))
                    continue;
                else st.push(dir);
            }
        }
        StringBuilder sb=new StringBuilder();

        if(st.isEmpty()) return "/";
        for(String s:st){
            sb.append("/"); sb.append(s);
        }
        return sb.toString();
    }

}
