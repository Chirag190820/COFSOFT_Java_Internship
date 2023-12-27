package CODSOFT_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
        } else {
            System.out.println("Course is already at full capacity.");
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        } else {
            System.out.println("No students are enrolled in this course.");
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nEnrolled Students: " + enrolledStudents;
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
        } else {
            registeredCourses.add(course);
            course.enrollStudent();
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses;
    }
}


public class Codsoft_TaskNo_5_Student_Course_Registration_System {
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        System.out.println("----------------------------------");
    	System.out.println("STUDENT COURSE REGISTRATION SYSTEM");
    	System.out.println("----------------------------------");

    	
        while (true) {
        	System.out.println("\nMENU:");
        	System.out.println("-----");
            System.out.println("1. Add a Course");
            System.out.println("2. Register a Student");
            System.out.println("3. View Course List");
            System.out.println("4. Register a Student for a Course");
            System.out.println("5. Drop a Course for a Student");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Course Code: ");
                    String courseCode = scanner.next();
                    System.out.print("Enter Course Title: ");
                    String courseTitle = scanner.next();
                    System.out.print("Enter Course Description: ");
                    String courseDescription = scanner.next();
                    System.out.print("Enter Course Capacity: ");
                    int courseCapacity = scanner.nextInt();
                    Course newCourse = new Course(courseCode, courseTitle, courseDescription, courseCapacity);
                    courses.add(newCourse);
                    break;

                case 2:
                    System.out.print("\nEnter Student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.next();
                    Student newStudent = new Student(studentID, studentName);
                    students.add(newStudent);
                    break;

                case 3:
                    System.out.println("\nCourse List:");
                    for (Course course : courses) {
                        System.out.println(course.toString());
                        System.out.println();
                    }
                    break;

                case 4:
                    System.out.print("\nEnter Student ID: ");
                    String stuID = scanner.next();
                    System.out.print("Enter Course Code: ");
                    String code = scanner.next();
                    Student studentToRegister = null;
                    Course courseToRegister = null;
                    for (Student student : students) {
                        if (student.getStudentID().equals(stuID)) {
                            studentToRegister = student;
                            break;
                        }
                    }
                    for (Course course : courses) {
                        if (course.getCode().equals(code)) {
                            courseToRegister = course;
                            break;
                        }
                    }
                    if (studentToRegister != null && courseToRegister != null) {
                        studentToRegister.registerCourse(courseToRegister);
                        System.out.println("Student " + studentToRegister.getName() + " has been registered for course " +
                                courseToRegister.getTitle());
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;

                case 5:
                    System.out.print("\nEnter Student ID: ");
                    String studentIDForDrop = scanner.next();
                    System.out.print("Enter Course Code: ");
                    String courseCodeForDrop = scanner.next();
                    Student studentToDrop = null;
                    Course courseToDrop = null;
                    for (Student student : students) {
                        if (student.getStudentID().equals(studentIDForDrop)) {
                            studentToDrop = student;
                            break;
                        }
                    }
                    for (Course course : courses) {
                        if (course.getCode().equals(courseCodeForDrop)) {
                            courseToDrop = course;
                            break;
                        }
                    }
                    if (studentToDrop != null && courseToDrop != null) {
                        studentToDrop.dropCourse(courseToDrop);
                        System.out.println("Student " + studentToDrop.getName() + " has dropped course " +
                                courseToDrop.getTitle());
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}