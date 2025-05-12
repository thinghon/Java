package w3;

import java.util.Scanner;

class Date_info {
	private int year;
	private int month;
	private int day;
	private String[] monthNames = { "January", "February", "March", "April", "May", "June", 
			"July", "August", "September", "October", "November", "December" };

	public Date_info(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public void print1() {
		System.out.printf("Printing date in format 1: %d.%d.%d\n", year, month, day);
	}

	public void print2() {
		System.out.printf("Printing date in format 2: %s %d, %d\n", monthNames[month - 1], day, year);
	}
}

public class Date {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int year,month,day;
		while(true) {
			System.out.print("날짜를 입력하시오(YYYY M D): ");
			String date = sc.nextLine();
			String[] date_arr = date.split(" ");
			year = Integer.parseInt(date_arr[0]);
			month = Integer.parseInt(date_arr[1]);
			day = Integer.parseInt(date_arr[2]);
			if ((1 <= month && month <= 12) && (1 <= day && day <= 31)) {
				break;
			}
			System.out.println("해당 날짜는 존재하지 않습니다.");
		}
		Date_info dt = new Date_info(year, month, day);
		dt.print1();
		dt.print2();
	}
}
