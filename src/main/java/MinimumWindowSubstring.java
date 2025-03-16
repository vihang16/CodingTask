import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring min = new MinimumWindowSubstring();
        System.out.println(min.minWindow("ADOBECODEBANC", "ABC"));
        //System.out.println(min.minWindow("aa", "aa"));
       // System.out.println(min.minWindow("bdab", "ab"));
    }

    private String minWindow(String s, String t) {
        if(s.length() < t.length())
            return "";
        if(s.equals(t))
            return s;
        if(t.length() ==1 && s.contains(t))
            return t;
        int min = Integer.MAX_VALUE;
        int count = 0;
        String result = "";
        Map<Character, Integer> tMap = generateCountMap(t); new HashMap<>();
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> tMapCopy = new HashMap<>(tMap);
        int i =0;
        int j = 1;
        sb.append(s.charAt(i));
        if(tMap.containsKey(s.charAt(i)))
            tMap.put(s.charAt(i), tMap.getOrDefault(s.charAt(i),0) - 1);

        while(i< s.length()){
            if(j < s.length()) {
                sb.append(s.charAt(j));
                if (tMap.containsKey(s.charAt(j)))
                    tMap.put(s.charAt(j), tMap.getOrDefault(s.charAt(j), 0) - 1);
                if (allIncluded(tMap)) {
                    while (sb.length() > 0) {
                        char c = sb.charAt(0);
                        if (!tMap.containsKey(c)) {
                            sb.deleteCharAt(0);
                            i++;
                        } else if (tMap.containsKey(c)) {
                            tMap.put(c, tMap.get(c) + 1);
                            if (tMap.get(c) > 0 && sb.length() < min) {
                                result = sb.toString();
                                min = sb.length();
                            }

                            sb.deleteCharAt(0);
                            i++;

                            if (tMap.get(c) > 0)
                                break;
                        }

                    }
                    //count =  removeUnnecessary(sb, count, tMap);

                }
                j++;

            }
            if (j >= s.length())
                i++;

        }
        
        return result;
    }

    private int removeUnnecessary(StringBuilder sb, int count, Map<Character, Integer> tMap) {

        while(sb.length() > 0){
            char c = sb.charAt(0);
            if(!tMap.containsKey(c))
                sb.deleteCharAt(0);

            else if(tMap.containsKey(c) && tMap.get(c) <0){
                tMap.put(c, tMap.get(c) + 1);
                sb.deleteCharAt(0);
            }else
                return sb.length();

        }

        return sb.length();
    }

    private boolean allIncluded(Map<Character, Integer> tMap){
        for(Map.Entry<Character, Integer> map: tMap.entrySet()){
            if(map.getValue() > 0)
                return false;
        }
        return true;
    }
    private Map<Character, Integer> generateCountMap(String t){
        Map<Character, Integer> tMap = new HashMap<>();
        for(char c: t.toCharArray())
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        return tMap;
    }
}
