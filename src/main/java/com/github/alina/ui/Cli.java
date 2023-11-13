package com.github.alina.ui;

import com.github.alina.models.User;
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
}
