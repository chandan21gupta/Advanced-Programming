import java.nio.Buffer;
import java.util.*;
import java.io.*;

class Student {
    private static LinkedList<Student> students = new LinkedList<Student>();
    private static int num_of_students = 0;
    final private int roll_number;
    final private float cgpa;
    final private String course;
    private String placement_status;
    private Company company_placed;


    Student(float cgpa,String course) {
        num_of_students++;
        this.roll_number = num_of_students;
        this.course = course;
        this.cgpa = cgpa;
        placement_status = "Not Placed";
        company_placed = null;
        students.add(this);
    }

    int getRollNumber() {
        return this.roll_number;
    }

    float getCgpa() {
        return this.cgpa;
    }

    String getCourse() {
        return this.course;
    }

    Company getCompany() {
        return this.company_placed;
    }

    String getPlacement_status() {
        return this.placement_status;
    }
    
    void display(int roll_number) {
        for(Student s : students) {
            if(s.getRollNumber() == roll_number) {
                s.display();
            }
        }
    }


    private boolean is_placed() {
        if(this.getPlacement_status().equals("Not Placed")) {
            return false;
        }
        return true;
    }

    private void display() {
        System.out.println(this.getRollNumber());
        System.out.println(this.getCgpa());
        System.out.println(this.getCourse());
        System.out.println(this.getPlacement_status());
        if (this.is_placed()) {
            System.out.println(this.getCompany().getCompanyName());
        }
    }

    private void remove_placed_students() {
        LinkedList<Student> placed = new LinkedList<Student>();
        for(Student s : students) {
            if(s.is_placed()) {
                placed.add(s);
            }
        }
        students.removeAll(placed);
    }

    int display_unplaced_students() {
        int count = 0;
        for(Student s : students) {
            if(!s.is_placed()) {
                count++;
            }
        }
        return count;
    }

    void placed_students_rollnumbers(Company c) {
        for(Student s : students) {
            if(s.is_placed() && s.getCompany().getCompanyName().equals(c.getCompanyName())) {
                System.out.println(s.getRollNumber());
            }
        }
    }

}


class Company {

    private static LinkedList<Company> companies = new LinkedList<Company>();
    final private String name;
    final private String[] course_criteria;
    private int required_students;
    private String application_status;

    Company(String name,int x,int required_students) throws java.io.IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        this.name = name;
        this.course_criteria = new String[x];
        for(int i=0;i<x;i++) {
            this.course_criteria[i] = buffer.readLine();
        }
        this.required_students = required_students;
        this.application_status = "Open";
        companies.add(this);
        this.display();
    }

    String getAppliction_status() {
        return this.application_status;
    }

    String[] getCourses() {
        return this.course_criteria;
    }

    String getCompanyName() {
        return this.name;
    }

    int getRequiredStudents() {
        return this.required_students;
    }

    void setRequiredStudents() {
        return;
    }

    void setApplication_status() {
        if(this.is_open()) {
            this.application_status = "Closed";
        }
    }

    private void display() {
        System.out.println(this.getCompanyName());
        String[] courses = this.getCourses();
        for(int i=0;i<courses.length;i++) {
            System.out.println(courses[i]);
        }
        System.out.println(this.getRequiredStudents());
        System.out.println(this.getAppliction_status());
    }

    void display(Company c) {
        c.display();
    }

    private boolean is_open() {
        return this.getAppliction_status().equals("Open");
    }

    void display_open_companies() {
        LinkedList<Company> open = new LinkedList<Company>();
        for(Company c : companies) {
            if(c.is_open()){
                open.add(c);
            }
        }
        for(Company c: open) {
            System.out.println(c.getCompanyName());
        }
    }

    void remove_closed_companies() {
        LinkedList<Company> closed = new LinkedList<Company>();
        for(Company c : companies) {
            if(!c.is_open()) {
                closed.add(c);
            }
        }
        companies.removeAll(closed);
    }
}

public class Placement {
    public static void main(String[] args) throws java.io.IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(buffer.readLine());
        for(int i=0;i<n;i++) {
            String[] st = buffer.readLine().split(" ");
            float cgpa = Float.parseFloat(st[0]);
            String course = st[1];
            Student s = new Student(cgpa,course);
        }
        
    }
}
