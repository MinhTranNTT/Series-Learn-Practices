package com.practices.colllections.trying;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

public class Trying01 {

    private static List<StudentScore> getDataStudents() {
        // StudentScore studentScore1 = new StudentScore("Tom", "Java", 20);
        // StudentScore studentScore2 = new StudentScore("Tom", "C#", 18);
        // StudentScore studentScore3 = new StudentScore("Tom", "JS", 15);
        // StudentScore studentScore4 = new StudentScore("Jerry", "Vue", 14);
        // StudentScore studentScore5 = new StudentScore("Jerry", "Angular", 19);
        // StudentScore studentScore6 = new StudentScore("Jerry", "React", 20);
        // StudentScore studentScore7 = new StudentScore("Harry", "Java", 19);
        // return Arrays.asList(studentScore1,studentScore2,studentScore3,studentScore4,studentScore5
        //                     ,studentScore6,studentScore7);
        List<StudentScore> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            if (i > 10 && i < 20) {
                StudentScore student = new StudentScore("Tom", "Java" + i, 20);
                list.add(student);
            }
            if (i > 20 && i < 30) {
                StudentScore student = new StudentScore("Jerry", "C#" + i, 20);
                list.add(student);
            }
        }
        return list;
    }
    public static void main(String[] args) {
        List<StudentScore> dataStudents = getDataStudents();

        Map<String, Integer> studentScoreMap = processOtherWay01(dataStudents);
        System.out.println(studentScoreMap);

        Map<String, Integer> studentScoreMap2 = processOtherWay02(dataStudents);
        System.out.println(studentScoreMap2);

        Map<String, Integer> studentScoreMap3 = processOtherWay03(dataStudents);
        System.out.println(studentScoreMap3);

        // Map<String, Integer> studentScoreMap4 = processOtherWay04(dataStudents);
        // System.out.println(studentScoreMap4);
    }

    private static Map<String, Integer> processOtherWay04(List<StudentScore> dataStudents) {
        Map<String, Integer> studentScoreMap = new HashMap<>();
        studentScoreMap = dataStudents.stream()
                .collect(Collectors.groupingBy(
                        StudentScore::getStudentName,
                        Collectors.summingInt(StudentScore::getScore)));
        return studentScoreMap;
    }

    // wasted costs, n item => n loop + n instance temp Map
    private static Map<String, Integer> processOtherWay03(List<StudentScore> dataStudents) {
        Map<String, Integer> studentScoreMap = new HashMap<>();
        studentScoreMap = dataStudents.stream()
                .collect(Collectors.toMap(
                        StudentScore::getStudentName,
                        StudentScore::getScore,
                        Integer::sum));
        return studentScoreMap;
    }

    private static Map<String, Integer> processOtherWay02(List<StudentScore> dataStudents) {
        // normal : (oldValue, newValue) -> oldValue+newValue)
        // method references: Integer::sum
        Map<String, Integer> studentScoreMap = new HashMap<>();
        dataStudents.forEach(student -> studentScoreMap.merge(
                student.getStudentName(),
                student.getScore(),
                Integer::sum));
        return studentScoreMap;
    }

    private static Map<String, Integer> processOtherWay01(List<StudentScore> dataStudents) {
        Map<String, Integer> studentScoreMap = new HashMap<>();
        dataStudents.forEach(student -> {
            if (studentScoreMap.containsKey(student.getStudentName())) {
                studentScoreMap.put(student.getStudentName(), studentScoreMap.get(student.getStudentName()) + student.getScore());
            } else {
                studentScoreMap.put(student.getStudentName(), student.getScore());
            }
        });
        return studentScoreMap;
    }


}
