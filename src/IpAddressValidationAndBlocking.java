import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IpAddressValidationAndBlocking {
    public static void main(String[] args) {
        System.out.println(findAndMatch(new String[]{".123*"}, "12.123"));
        System.out.println(findAndMatch(new String[]{".123*"}, "12.123.12"));
        System.out.println(findAndMatch(new String[]{".123*"}, "123"));
        System.out.println(findAndMatch(new String[]{".123*"}, "11 2 3"));
        System.out.println(findAndMatch(new String[]{".123*"}, "121.1.23.34"));
        System.out.println(findAndMatch(new String[]{".123*"}, "121.1.1123.34"));
    }

    private static int findAndMatch(String[] blockedIpArray, String ip) {
        for (String ipAddress : blockedIpArray) {
            Pattern pattern = Pattern.compile(ipAddress);
            Matcher matcher = pattern.matcher(ip);
            boolean isBlocked = matcher.find();
            if (isBlocked)
                return 0;
        }
        return 1;
    }

}
