package test;

import school.School;
import school.Score;
import school.Student;
import school.Subject;
import school.report.GenerateGradeReport;
import utils.Define;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GradeTest {

    School goodSchool =School.getInstance();
    Subject korean;
    Subject math;
    Subject dance;

    GenerateGradeReport gradeReport =new GenerateGradeReport();

    public static void main(String[] args) throws FileNotFoundException {
        GradeTest test =new GradeTest();
        test.createSubject();
        test.createStudent();

        String report =test.gradeReport.getReport();
        System.out.println(report);
    }

    public void createSubject(){
        korean=new Subject("국어", Define.KOREAN);

        math=new Subject("수학", Define.MATH);
        dance=new Subject("방송 댄스", Define.DANCE);

        goodSchool.addSubject(korean);
        goodSchool.addSubject(math);
        goodSchool.addSubject(dance);


    }

    public void createStudent() throws FileNotFoundException{
        Scanner scanner =new Scanner(new FileInputStream("studentinfo.txt"));

        String name;
        int id;
        int koreanScore;
        int mathScore;
        int danceScore;
        int majorCode;

        Student student=null;
        while(scanner.hasNext()) {
            name = scanner.next();
            id = scanner.nextInt();
            koreanScore = scanner.nextInt();
            mathScore = scanner.nextInt();
            majorCode = scanner.nextInt();
            danceScore = scanner.nextInt();

            if (majorCode == Define.KOREAN) {
                student = new Student(id, name, korean);
            } else if (majorCode == Define.MATH) {
                student = new Student(id, name, math);
            } else {
                System.out.println("전공 과목 오류 입니다");
                return;
            }

            goodSchool.addStudent(student);
            korean.register(student);
            math.register(student);
            addScoreForStudent(student, korean, koreanScore);
            addScoreForStudent(student, math, mathScore);
        }
        scanner.close();
        }

        public void addScoreForStudent(Student student, Subject subject, int point) {
        Score score =new Score(student.getStudentId(), subject, point);
        student.addSubjectScore(score);


    }



}
