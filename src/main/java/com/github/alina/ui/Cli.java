package com.github.alina.ui;

import com.github.alina.models.*;
import com.github.alina.services.*;

import java.util.Scanner;

public class Cli {
    private TeacherService teacherService;
    private CourseService courseService;
    private StudentService studentService;
    private GradingService gradeService;
    private AdminService adminService;

    private User currentUser;
    private Scanner scanner;

    public UserType getUserType() {
        if (currentUser instanceof Teacher) {
            return UserType.TEACHER;
        }
        if (currentUser instanceof Admin) {
            return UserType.ADMIN;
        }
        if (currentUser instanceof Student) {
            return UserType.STUDENT;
        }
        return UserType.NOTLOGIN;
    }


    public Cli() {
        this.teacherService = new TeacherService();
        this.courseService = new CourseService();
        this.studentService = new StudentService();
        this.gradeService = new GradingService();
        this.adminService = new AdminService();
        this.scanner = new Scanner(System.in);
    }

    public void init() {
        teacherService.loadFromFile("teacher.txt");
        courseService.loadFromFile("course.txt");
        studentService.loadFromFile("student.txt");
        adminService.loadFromFile("admin.txt");
        gradeService.loadFromFile("grade.txt");
    }

    public void login() {
        System.out.println("Please choose login type: 1 - teacher, 2 - student, 3 - admin");
        int loginType = scanner.nextInt();
        System.out.println("Type username");
        String userName = scanner.next();
        System.out.println("Type password");
        String password = scanner.next();
        switch (loginType) {
            case 1 -> currentUser = teacherService.login(userName, password);
            case 2 -> currentUser = studentService.login(userName, password);
            default -> currentUser = adminService.login(userName, password);
        }
    }

    public void registerUser() {
        System.out.println("Register a new user: 1 - teacher, 2 - student, 3 - admin");
        int userType = scanner.nextInt();
        System.out.println("Type name");
        String name = scanner.next();
        System.out.println("Type username");
        String userName = scanner.next();
        System.out.println("Type password");
        String password = scanner.next();
        switch (userType) {
            case 1 -> currentUser = teacherService.register(name, userName, password);
            case 2 -> currentUser = studentService.register(name, userName, password);
            default -> currentUser = adminService.register(name, userName, password);
        }
    }

    enum UserType {
        ADMIN,
        TEACHER,
        STUDENT,
        NOTLOGIN

    }

    public void menu() {
        switch (getUserType()) {
            case NOTLOGIN -> {
                System.out.println("You should login or register, 1 for login or 2 for register");
                int option = scanner.nextInt();
                if (option == 1) {
                    login();
                } else {
                    registerUser();
                }
            }
            case TEACHER -> {
                System.out.println("1: List Courses, 2: List My Courses, 3: Grade Student");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> courseService.printAll();
                    case 2 -> ((Teacher) currentUser).getTeachingCourses().forEach(System.out::println);
                    case 3 -> {
                        int studentId = scanner.nextInt();
                        int courseId = scanner.nextInt();
                        Student student = studentService.find(studentId);
                        Course course = courseService.find(courseId);
                        if (((Teacher) currentUser).getTeachingCourses().contains(course)) {
                            int grade = scanner.nextInt();
                            gradeService.createGrade(student, course, grade);
                        }

                    }

                }
            }
            case STUDENT -> {
                System.out.println("1: List Courses, 2: List My Courses, 3: Enroll to Course");
                //TODO add code to list courses, enroll to course etc.
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> courseService.printAll();
                    case 2 -> ((Student) currentUser).getEnrolledCourses().forEach(System.out::println);
                    case 3 -> {
                        int courseId = scanner.nextInt();
                        Course course = courseService.find(courseId);
                        ((Student) currentUser).addCourse(course);
                        studentService.update((Student) currentUser);
                    }

                }

            }
            case ADMIN -> {
                System.out.println("1: List Courses, 2: Create Course, 3: Allocate Teacher to Course");
                //TODO add code to list all courses, create new course, allocate Teachers to courses
            }
        }
    }
}
