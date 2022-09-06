public class HtmlParser {
    static String input = "This is a paragraph with a soft\n" +
            "line break.\n" +
            "\n" +
            "This is another paragraph that has\n" +
            "> Some text that\n" +
            "> is in a\n" +
            "> block quote.\n" +
            "\n" +
            "This is another paragraph with a ~~strikethrough~~ word.";
    public static void main(String[] args) {
        String word = htmlParser(input);
        System.out.println(word);
    }

    private static String htmlParser(String markdownString){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        boolean isBlockQuote = false;
        for(String s: markdownString.split("\\R")){
            //System.out.println("input string"+s);
            if( s.length() >2 && s.charAt(0) == '>' && s.charAt(1) == ' ' && !isBlockQuote){
                sb.append("<blockquote>");
                addingDelChar(sb, s.substring(2));
                sb.append(s.substring(2));
                isBlockQuote = true;
            }else if(s.length() >2 && s.charAt(0) != '>' &&  s.charAt(1) != ' ' && isBlockQuote){
                sb.append("</blockquote>");
                addingDelChar(sb, s);
                sb.append(s.substring(2));
                isBlockQuote = false;
            }else{
                addingDelChar(sb, s);
            }
        }

        return sb.toString();
    }
    private static void addingDelChar(StringBuilder sb, String s){
        boolean isEndingDel = false;
        int index =0;
        //System.out.println("del char ----:"+s);
        while(index != -1){

            index = s.indexOf("~~", index);
            //System.out.println("index:"+index);
            if( index != -1 && !isEndingDel ){
                s = s.replaceFirst("~~", "<del>");
                //System.out.println("del if char:"+s);
                isEndingDel = true;
            }else if(index != -1  && isEndingDel){
                //System.out.println("del else char:"+s);
                s = s.replaceFirst("~~", "</del>");
                isEndingDel = false;
            }
        }
        sb.append(s);
    }
}
