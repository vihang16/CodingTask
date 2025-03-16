public class Evaluate {

    public static void main(String args[]) {
        System.out.println(evaluate("sub(add(238943, 2343), add(1, sub(323, 43)))"));
    }
    public static int evaluate(String input){
        input = input.trim();
        int count = 0;
        int openingParanthesis = -1;
        int closingParanthesis = -1;
        int comma = -1;
        for(int i=0; i<input.length(); i++){
            char ch = input.charAt(i);
            if(ch == '(' ){
                if(count == 0 && openingParanthesis == -1)
                    openingParanthesis = i;
                count++;
            }else if(ch == ')'){
                count--;
                if(count == 0)
                    closingParanthesis = i;
            }
            if(ch == ',' && count ==1){
                comma = i;
            }
        }
        //System.out.println(String.format("parenthesis opening:%d closing:%d comma: %d",openingParanthesis, closingParanthesis, comma ));
        System.out.println(input);
        if(openingParanthesis == -1 && closingParanthesis == -1 && comma == -1 )
            return Integer.parseInt(input);
        //System.out.println(String.format("opening:%d  operand:%s",openingParanthesis, input ));
        String operand =  input.substring(0, openingParanthesis);
        int operand1 = evaluate(input.substring(openingParanthesis+1, comma));
        int operand2 = evaluate(input.substring(comma+1, closingParanthesis));
        if(operand.equals("add")){
            System.out.println(String.format("operands in add:%d %d", operand1, operand2));
            return operand1+operand2;
        }else if(operand.equals("sub")){
            System.out.println(String.format("operands in sub:%d %d", operand1, operand2));
            return operand1-operand2;
        }else{
            throw new IllegalArgumentException("Not valid operation");
        }
    }
}
