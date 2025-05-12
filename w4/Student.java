package w4;

import java.util.Scanner;

public class Student {
	private static int student_count = 0;
	String name;
	String grade;

	public Student(String name,String grade) {
		this.name = name;
		this.grade = grade;
		student_count++;
	}

	public static void display_student_info(Student students[]) {
		int n = 1;
		System.out.println("Student Information:");
		System.out.println("....................");
		System.out.println("Total Students: " + student_count);
		System.out.println();
		for (Student student : students) {
			System.out.printf("Student %d: Name - %s, Grade - %s\n"
					, n++, student.name, student.grade);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name, grade;
		Student students[] = new Student[5];
		
		for(int i =0;i<5;i++) {
			System.out.print("Enter student name: ");
			name = sc.nextLine();
			System.out.print("Enter student grade: ");
			grade = sc.next();
			students[i]= new Student(name, grade);
			sc.nextLine();
		}
		Student.display_student_info(students);
	}
}
