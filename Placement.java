import java.nio.Buffer;
import java.util.*;
import java.io.*;

class Student {
    
    class Score {
        Company c;
        int technical_score;

        Score(Company c,int technical_score) {
            this.c = c;
            this.technical_score = technical_score;
        }

        int getTechnical_score() {
            return this.technical_score;
        }
        Company getCompany() {
            return this.c;
        }
    }



    private static LinkedList<Student> students = new LinkedList<Student>();
    private ArrayList<Score> list_companies = new ArrayList<Score>();
    private static int num_of_students = 0;
    final private int roll_number;
    final private float cgpa;
    final private String course;
    private String placement_status;
    private Company company_placed;

    static int student_list() {
        return students.size();
    }

    Student(float cgpa,String course) {
        num_of_students++;
        this.roll_number = num_of_students;
        this.course = course;
        this.cgpa = cgpa;
        this.placement_status = "Not Placed";
        this.company_placed = null;
        students.add(this);
    }

    void setPlacement_status() {
        this.placement_status = "Placed";
    }

    void setCompany_placed(Company c) {
        this.company_placed = c;
    }

    static ArrayList<Student> valid_students(String[] courses) {
        ArrayList<Student> st = new ArrayList<Student>();
        for(Student s:students) {
            String name = s.getCourse();
            for(int i=0;i<courses.length;i++) {
                if(courses[i].equals(name)) {
                    st.add(s);
                }
            }   
        } 
        return st;
    }

    static void display_companies(int roll_number) {
        int flag = 0;
        for(Student s : students) {
            if(s.getRollNumber() == roll_number) {
                flag = 1;
                for(Score st: s.list_companies) {
                    System.out.print(st.getCompany().getCompanyName()+" ");
                    System.out.println(st.getTechnical_score());
                }
            }
        }
        if(flag == 0) {
            System.out.println("No student with the given roll number has an account");
        }
    }

    int getScore(String name) {
        for(Score s : list_companies) {
            if(s.getCompany().equals(name)) {
                return s.getTechnical_score();
            }
        }
        return -1;
    }

    void add_company(Company c, int technical_score) {
        Score s = new Score(c,technical_score);
        this.list_companies.add(s);
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
    
    static void display(int roll_number) {
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
        System.out.println("Placement Status: "+this.getPlacement_status());
        if (this.is_placed()) {
            System.out.println(this.getCompany().getCompanyName());
        }
    }

    static void remove_placed_students() {
        LinkedList<Student> placed = new LinkedList<Student>();
        for(Student s : students) {
            if(s.is_placed()) {
                System.out.println(s.getRollNumber());
                placed.add(s);
            }
        }
        students.removeAll(placed);
    }

    static void number_unplaced_students() {
        int count = 0;
        for(Student s : students) {
            if(!s.is_placed()) {
                count++;
            }
        }
        System.out.println(count+" students left");
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

    private ArrayList<Student> val_students;
    private static LinkedList<Company> companies = new LinkedList<Company>();
    final private String name;
    final private String[] course_criteria;
    private int required_students;
    private String application_status;

    Company(String name,String[] course_criteria,int required_students) throws java.io.IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        this.name = name;
        this.course_criteria = course_criteria;
        this.required_students = required_students;
        this.application_status = "OPEN";
        companies.add(this);
        this.display();
        System.out.println("Enter scores for the technical round.");
        val_students = Student.valid_students(this.getCourses());
        for(Student st : val_students) {
            System.out.println("Enter score for Roll No. "+st.getRollNumber());
            int technical_score = Integer.parseInt(buffer.readLine());
            st.add_company(this,technical_score);
        }
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
        this.required_students = 0; 
    }

    void setApplication_status() {
        if(this.is_open()) {
            this.application_status = "CLOSED";
        }
    }


    private void display() {
        System.out.println(this.getCompanyName());
        String[] courses = this.getCourses();
        System.out.println("Course Criteria");
        for(int i=0;i<courses.length;i++) {
            System.out.println(courses[i]);
        }
        System.out.println("Number Of Required Students = "+this.getRequiredStudents());
        System.out.println("Application Status = "+this.getAppliction_status());
    }

    static void display(String name) {
        for(Company c : companies) {
            if(c.getCompanyName().equals(name)){
                c.display();
                return;
            }
        }
    }

    private boolean is_open() {
        return this.getAppliction_status().equals("OPEN");
    }

    static void display_open_companies() {
        for(Company c : companies) {
            if(c.is_open()){
                System.out.println(c.getCompanyName());
            }
        }
    }

    static void remove_closed_companies() {
        LinkedList<Company> closed = new LinkedList<Company>();
        for(Company c : companies) {
            if(!c.is_open()) {
                System.out.println("Accounts removed for");
                System.out.println(c.getCompanyName());
                closed.add(c);
            }
        }
        companies.removeAll(closed);
    }

    static void select_students(String name) {
        Company company = null;
        for(Company c: companies) {
            if(c.getCompanyName().equals(name)) {
                company = c;
                break;
            }
        }
        Student[] s = new Student[company.val_students.size()];
        for(int i=0;i<s.length;i++) {
            s[i] = company.val_students.get(i);
        }
        for(int i=0;i<s.length-1;i++) {
            int max = i;
            for(int j=i+1;j<s.length;j++) {
                if(s[j].getScore(company.getCompanyName())>s[max].getScore(company.getCompanyName())) {
                    // System.out.println("Max-> "+s[max].getRollNumber());
                    // System.out.println("Min-> "+s[j].getRollNumber());
                    max = j;
                }
            }
            Student temp = s[max];
            s[max] = s[i];
            s[i] = temp;
        }
        System.out.println("Roll Number of Selected Students");
        if(company.getRequiredStudents()<=s.length) {
            for(int i=0;i<company.getRequiredStudents();i++) {
                System.out.println(s[i].getRollNumber());
                s[i].setPlacement_status();
                s[i].setCompany_placed(company);
            }
            company.setRequiredStudents();
            company.setApplication_status();
        }
        else{
            for(int i=0;i<s.length;i++) {
                System.out.println(s[i].getRollNumber());
                s[i].setPlacement_status();
                s[i].setCompany_placed(company);
            }
            company.setRequiredStudents();
            company.setApplication_status();
        }
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
        while(Student.student_list()>0) {
            String[] q = buffer.readLine().split(" ");
            int q1 = Integer.parseInt(q[0]);
            switch(q1) {
                
                case 1 : 
                    String company = buffer.readLine();
                    System.out.print("Number of Eligible Courses = ");
                    int x = Integer.parseInt(buffer.readLine());
                    String[] courses = new String[x];
                    for(int i=0;i<x;i++) {
                        courses[i] = buffer.readLine();
                    }
                    System.out.print("Number of Required Students = ");
                    int required = Integer.parseInt(buffer.readLine());
                    Company c = new Company(company,courses,required);
                    break;
                
                case 2 :
                    System.out.println("Accounts removed for");
                    Student.remove_placed_students();
                    break;

                case 3 : 
                    Company.remove_closed_companies();
                    break;

                case 4 : 
                    Student.number_unplaced_students();
                    break;
                
                case 5 :
                    Company.display_open_companies();
                    break;
                
                case 6 :
                    String company_name = q[1];
                    Company.select_students(company_name);
                    break;
                case 7 :
                    String name = q[1];
                    Company.display(name);
                    break;
                
                case 8 :
                    int roll_number = Integer.parseInt(q[1]);
                    Student.display(roll_number);
                    break;
                
                case 9 :
                    int number = Integer.parseInt(q[1]);
                    Student.display_companies(number);
            }
        }
    }
}
