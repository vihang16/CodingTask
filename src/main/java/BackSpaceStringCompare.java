public class BackSpaceStringCompare {

    public static void main(String[] args) {
        BackSpaceStringCompare bc = new BackSpaceStringCompare();
        System.out.println(bc.isStringEqual("y#fo##f", "y#f#o##f"));
    }

    private boolean isStringEqual(String s, String t) {
        StringBuilder source = createString(s);
        System.out.println(source);
        StringBuilder target = createString(t);
        System.out.println(target);
        return source.toString().equals(target.toString());
    }

    private StringBuilder createString(String s) {
        StringBuilder source = new StringBuilder();
        for(char c: s.toCharArray()){
            if(c == '#' && source.length() != 0)
                source.deleteCharAt(source.length() -1);
            else if(c != '#')
                source.append(c);
        }
        return source;
    }
}
