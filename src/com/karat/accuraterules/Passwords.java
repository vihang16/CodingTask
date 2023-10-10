package com.karat.accuraterules;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/*

You are working on an authentication system and there is a set of rules the users have to follow when picking a new password:

1. It has to be at least 16 characters long.
2. The password cannot contain the word "password". This rule is not case-sensitive.
3. The same character cannot be used more than 4 times. This rule is case-sensitive, "a" and "A" are different characters.
4. The password has to contain at least one uppercase and one lowercase letter.
5. The password has to contain at least one of the following special characters "*","#","@".

Write a function that takes in a password and returns a collection of any rule numbers that are not met.

password_1 = "Strongpwd9999#abc"             ==> []
password_2 = "Less10#"                       ==> [1]
password_3 = "Password@"                     ==> [1,2]
password_4 = "#PassWord011111112222223x"     ==> [2,3]
password_5 = "password#1111111"              ==> [2,3,4]
password_6 = "aaaapassword$$"                ==> [1,2,3,4,5]
password_7 = "LESS10#"                       ==> [1,4]
password_8 = "SsSSSt#passWord"               ==> [1,2]


All test cases:

validate(password_1) ==> []
validate(password_2) ==> [1]
validate(password_3) ==> [1,2]
validate(password_4) ==> [2,3]
validate(password_5) ==> [2,3,4]
validate(password_6) ==> [1,2,3,4,5]
validate(password_7) ==> [1,4]
validate(password_8) ==> [1,2]


Complexity variables:

N = length of the password
 */
public class Passwords {
    public static void main(String[] args) {
        String password_1 = "Strongpwd9999#abc";
        String password_2 = "Less10#";
        String password_3 = "Password@";
        String password_4 = "#PassWord011111112222223x";
        String password_5 = "password#1111111";
        String password_6 = "aaaapassword$$";
        String password_7 = "LESS10#";
        String password_8 = "SsSSSt#passWord";
        System.out.println(validate(password_1));
        System.out.println(validate(password_2));
        System.out.println(validate(password_3));
        System.out.println(validate(password_4));
        System.out.println(validate(password_5));
        System.out.println(validate(password_6));
        System.out.println(validate(password_7));
        System.out.println(validate(password_8));
    }

    private static Set<Integer> validate(String password) {
        Set<Integer> brokenRuleSet = new HashSet<>();
        findFirstRule(brokenRuleSet, password);
        validateSecondRule(brokenRuleSet, password);
        validateThirdRule(brokenRuleSet, password);
        validateFourthRule(brokenRuleSet, password);
        validateFifthRule(brokenRuleSet, password);
        return brokenRuleSet;
    }

    private static void validateFifthRule(Set<Integer> brokenRuleSet, String password) {
        if(!password.contains("*") && !password.contains("#") && !password.contains("@"))
            brokenRuleSet.add(5);
    }

    private static void validateFourthRule(Set<Integer> brokenRuleSet, String password) {
        boolean upperCase = false;
        boolean lowerCase = false;
        for(char c : password.toCharArray()){
            if( c >= 'a' && c<='z')
                lowerCase = true;
            if( c >= 'A' && c <='Z')
                upperCase = true;
            if(lowerCase && upperCase)
                break;
        }
        if(!upperCase || !lowerCase)
            brokenRuleSet.add(4);
    }

    private static void validateThirdRule(Set<Integer> brokenRuleSet, String password) {
        Map<Character, Long> freqMap = password
                .chars()
                .mapToObj(c -> (char) c)
                //.filter( c ->  (c >= 'A' && c<= 'Z') || (c >='a' && c <='z'))
                .collect(groupingBy(Function.identity(), counting()));
       long total = freqMap
                   .values()
                   .stream()
                   .filter( x -> x > 4)
                   .count();
       if(total > 0)
           brokenRuleSet.add(3);
    }

    private static void validateSecondRule(Set<Integer> brokenRuleSet, String password) {
        if(password.toLowerCase().contains("password"))
            brokenRuleSet.add(2);
    }

    private static void findFirstRule(Set<Integer> brokenRuleSet, String password) {
        if(password.length() < 16)
            brokenRuleSet.add(1);
    }
}
