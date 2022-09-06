import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static final int MAX_STRING_SIZE = 10;
    static int id=3;
    // "Lex Count" is the short hand for the occurrence of the lexicographically smallest
    // character present.
    public static int findLexCount(String testStr) {
        int id=2;
        char minChar = Character.MAX_VALUE;
        int minCount = 0;
        for (char oneChar : testStr.toCharArray()) {
            if (oneChar < minChar) {
                minChar = oneChar;
                minCount = 1;
            } else if (oneChar == minChar) {
                minCount++;
            }
        }
        return minCount;
    }

    public static List<Integer> compareStrings(List<String> str1, List<String> str2) {
        // This keeps track of the number of strings in str1 has a particular lex index.
        int[] lexCounts = new int[MAX_STRING_SIZE + 1];
        for (String oneStr : str1) {
            lexCounts[findLexCount(oneStr)]++;
        }
        // This keeps track of how many strings in str1 has a lex index less than the key.
        int[] strictlySmallerCount = new int[MAX_STRING_SIZE + 2];
        for (int i = 0; i <= MAX_STRING_SIZE; i++) {
            strictlySmallerCount[i + 1] = strictlySmallerCount[i] + lexCounts[i];
        }
        ArrayList<Integer> returnCount = new ArrayList<>();
        for (String oneStr: str2) {
            returnCount.add(strictlySmallerCount[findLexCount(oneStr)]);
        }
        return returnCount;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
       // Scanner scanner = new Scanner(System.in);
        List<String> str1 = Arrays.asList("abcd","aabc", "bd");//splitWords(scanner.nextLine());
        List<String> str2 = Arrays.asList("aaa", "aa");//splitWords(scanner.nextLine());
        //scanner.close();
        System.out.println(str1);
        System.out.println(str2);
        List<Integer> res = compareStrings(str1, str2);
        //System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        Integer[] a = new Integer[]{1,3,400, 500};
        Integer[] b = new Integer[]{2,4};
        System.out.println(mergeArray(a,b));

    }

    public static List<Integer> mergeArray(Integer[] a, Integer[] b){
        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();
        for(int aD: a)
            aList.add(aD);
        for(int bD: b)
            bList.add(bD);

        List<Integer>  result = new ArrayList<>();
        while(!aList.isEmpty() && !bList.isEmpty()){
            int aFirst = aList.get(0);
            int bFirst = bList.get(0);
            if(aFirst  < bFirst){
                result.add(aFirst);
                aList.remove(0);
            }else{
                result.add(bFirst);
                bList.remove(0);
            }
        }
        for(int aD: aList)
            result.add(aD);


        for(int bD: bList)
            result.add(bD);


        return result;
    }
}