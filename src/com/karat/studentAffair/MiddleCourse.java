//https://central.karat.io/questions/103/guide#3-1
package com.karat.studentAffair;

import java.sql.Array;
import java.util.*;

public class MiddleCourse {
    public static void main(String[] args) {
        String[][] pairs1 = {
                {"Logic", "COBOL"},
                {"Data Structures", "Algorithms"},
                {"Creative Writing", "Data Structures"},
                {"Algorithms", "COBOL"},
                {"Intro to Computer Science", "Data Structures"},
                {"Logic", "Compilers"},
                {"Data Structures", "Logic"},
                {"Graphics", "Networking"},
                {"Networking", "Algorithms"},
                {"Creative Writing", "System Administration"},
                {"Databases", "System Administration"},
                {"Creative Writing", "Databases"},
                {"Intro to Computer Science", "Graphics"},
        };

        String[][] pairs2 = {
                {"Course_3", "Course_7"},
                {"Course_0", "Course_1"},
                {"Course_1", "Course_2"},
                {"Course_2", "Course_3"},
                {"Course_3", "Course_4"},
                {"Course_4", "Course_5"},
                {"Course_5", "Course_6"},
        };
        System.out.println(halfwayCourse(pairs1));
    }

    private static List<String> halfwayCourse(String[][] pairs) {
        Map<String, List<String>> courseMap = new HashMap<>();
        Map<String, List<List<String>>> allRootToCourseMaMap = new HashMap<>();
        for(String[] pair: pairs){
            courseMap.compute(pair[0], (k,v) ->{
                v =  v == null? new ArrayList<>():v;
                v.add(pair[1]);
                return v;
            });
        }
        System.out.println(courseMap);
        for(String course: courseMap.keySet()){
            for(String c: courseMap.get(course)) {
                List<String> courseList = new ArrayList<>();
                //courseSet.add(c);
                dfs(c, courseMap, courseList, allRootToCourseMaMap, course);
//                List<List<String>> courses = allRootToCourseMaMap.getOrDefault(course, new ArrayList<>());
//                courses.add(courseList);
//                allRootToCourseMaMap.put(course, courses);
            }
        }
       // System.out.println(allRootToCourseMaMap.get("Creative Writing"));
        System.out.println(allRootToCourseMaMap.get("Intro to Computer Science"));
        return null;
    }

    private static void dfs(String course, Map<String, List<String>> courseMap, List<String> courseSet, Map<String, List<List<String>>> allRootToCourseMaMap, String originalCourse) {
        if(!courseMap.containsKey(course)){
            courseSet.add(course);
            List<List<String>> courses = allRootToCourseMaMap.getOrDefault(originalCourse, new ArrayList<>());
            courses.add(new ArrayList<>(courseSet));
            if(originalCourse.equals("Intro to Computer Science"))
                System.out.println(courses);
            allRootToCourseMaMap.put(originalCourse, courses);
            return;
        }
        if(courseSet.contains(course))
            return;
        courseSet.add(course);
        for(String courses : courseMap.get(course)){
            dfs(courses, courseMap, courseSet, allRootToCourseMaMap, originalCourse);
            courseSet.remove(courseSet.size() - 1);
        }
    }
}
