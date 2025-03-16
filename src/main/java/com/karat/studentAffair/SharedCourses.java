package com.karat.studentAffair;

import java.util.*;

public class SharedCourses {
    public static void main(String[] args) {
        String[][] enrollments1 = {
                {"58", "Linear Algebra"},
                {"94", "Art History"},
                {"94", "Operating Systems"},
                {"17", "Software Design"},
                {"58", "Mechanics"},
                {"58", "Economics"},
                {"17", "Linear Algebra"},
                {"17", "Political Science"},
                {"94", "Economics"},
                {"25", "Economics"},
                {"58", "Software Design"}
        };

        String[][] enrollments2 = {
                {"0", "Advanced Mechanics"},
                {"0", "Art History"},
                {"1", "Course 1"},
                {"1", "Course 2"},
                {"2", "Computer Architecture"},
                {"3", "Course 1"},
                {"3", "Course 2"},
                {"4", "Algorithms"}
        };

        String[][] enrollments3 = {
                {"23", "Software Design"},
                {"3",  "Advanced Mechanics"},
                {"2",  "Art History"},
                {"33", "Another"},
        };
        System.out.println(find_pairs(enrollments1));
        System.out.println(find_pairs(enrollments2));
        System.out.println(find_pairs(enrollments3));
    }

    private static Map<StudentPair, Set<String>> find_pairs(String[][] enrollments) {
        Map<StudentPair, Set<String>> studentCourses = new HashMap<>();
        final Map<String, Set<String>> studentMap = new HashMap<>();
        for(String[] enrolment: enrollments){
            studentMap.compute(enrolment[0], (k, v) ->{
                v = v == null? new HashSet<>(): v;
                v.add(enrolment[1]);
                return v;
            });
        }
        for(String student1 :  studentMap.keySet()){
            for(String student2: studentMap.keySet()){
                if(!student1.equals(student2)){
                    StudentPair st = new StudentPair(student1, student2);
                    Set<String> courses = new HashSet<>();
                    //Set<String> biggestSize = studentMap.get(student1).size() > studentMap.get(student2).size()? studentMap.get(student1) : studentMap.get(student2);
                    for(String course : studentMap.get(student1)){
                        if(studentMap.get(student2).contains(course))
                            courses.add(course);
                    }
                    for(String course : studentMap.get(student2)){
                        if(studentMap.get(student1).contains(course))
                            courses.add(course);
                    }

                    studentCourses.put(st, courses);
                }
            }
        }
        return studentCourses;
    }
}

final class StudentPair{
    String st1;
    String st2;

    public StudentPair(String student1, String student2) {
        st1 = student1;
        st2 = student2;
    }

    @Override
    public String toString() {
        return   st1 + " " + st2 ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentPair that = (StudentPair) o;
        return st1.equals(that.st1) && st2.equals(that.st2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(st1, st2);
    }
}
