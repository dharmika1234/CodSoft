import java.io.*;
import java.util.*;

class Student {
    int rollNo;
    String name;
    int age;
    String grade;
    String email;

    Student(int rollNo, String name, int age, String grade, String email) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.email = email;
    }

    @Override
    public String toString() {
        return rollNo + "," + name + "," + age + "," + grade + "," + email;
    }

    public String display() {
        return "Roll No: " + rollNo + ", Name: " + name + ", Age: " + age +
                ", Grade: " + grade + ", Email: " + email;
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    StudentManagementSystem() {
        loadFromFile();
    }
    boolean exists(int rollNo) {
        for (Student s : students) {
            if (s.rollNo == rollNo) return true;
        }
        return false;
    }

    boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }


    void addStudent(Student s) {
        if (exists(s.rollNo)) {
            System.out.println("Student with Roll No " + s.rollNo + " already exists!");
            return;
        }

        if (!isValidEmail(s.email)) {
            System.out.println("Invalid email format!");
            return;
        }

        s.grade = s.grade.toUpperCase(); // normalize grade
        students.add(s);
        saveToFile();
        System.out.println("Student added.");
    }


    void removeStudent(int rollNo) {
        boolean removed = students.removeIf(s -> s.rollNo == rollNo);
        if (removed) {
            saveToFile();
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    void editStudent(int rollNo, Scanner sc) {
        for (Student s : students) {
            if (s.rollNo == rollNo) {
                System.out.print("Enter new name: ");
                s.name = sc.next();
                System.out.print("Enter new age: ");
                s.age = sc.nextInt();
                System.out.print("Enter new grade: ");
                s.grade = sc.next().toUpperCase(); 
                System.out.print("Enter new email: ");
                String newEmail = sc.next();
                if (!isValidEmail(newEmail)) {
                    System.out.println("Invalid email format!");
                    return;
                }
                s.email = newEmail;
                saveToFile();
                System.out.println("Student updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }


    void searchStudent(int rollNo) {
        for (Student s : students) {
            if (s.rollNo == rollNo) {
                System.out.println("Found: " + s.display());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s.display());
            }
        }
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int rollNo = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String grade = parts[3];
                    String email = parts[4];
                    students.add(new Student(rollNo, name, age, grade, email));
                }
            }
        } catch (IOException e) {
       
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("students.txt"))) {
            for (Student s : students) {
                pw.println(s.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}

public class StudentManagementSystemApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    String grade = sc.next();
                    System.out.print("Enter Email: ");
                    String email = sc.next();
                    sms.addStudent(new Student(roll, name, age, grade, email));
                    break;
                case 2:
                    System.out.print("Enter Roll No to remove: ");
                    sms.removeStudent(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter Roll No to edit: ");
                    sms.editStudent(sc.nextInt(), sc);
                    break;
                case 4:
                    System.out.print("Enter Roll No to search: ");
                    sms.searchStudent(sc.nextInt());
                    break;
                case 5:
                    sms.displayAll();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
        sc.close();
    }
}


