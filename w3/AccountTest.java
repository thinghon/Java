package w3;

import java.util.Scanner;

class Account {
	private String ownerName;
	private double balance;
	
	public Account(String ownerName) {this.ownerName = ownerName;}
	public String get_ownerName() {return ownerName;}
	public double get_balance() {return balance;}
	public void sum_balance(double balance) {this.balance = this.balance + balance;}
	public void sub_balance(double balance) {this.balance = this.balance - balance;}
}
public class AccountTest{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i;
		double money;
		System.out.print("예금주명을 입력하세요: ");
		String name = sc.next();
		Account ac = new Account(name);
		while(true) {
			System.out.printf("==== %s님 은행 계좌 시스템 ====\n",ac.get_ownerName());
			System.out.println("1. 입금");
			System.out.println("2. 출금");
			System.out.println("3. 잔액 조회");
			System.out.println("4. 종료");
			System.out.print("기능을 선택하세요: ");
			i = sc.nextInt();
			
			if(i==1) {
				System.out.print("얼마를 입금하겠습니까?: ");
				money = sc.nextDouble();
				ac.sum_balance(money);
			}
			else if(i==2) {
				System.out.print("얼마를 출금하겠습니까?: ");
				money = sc.nextDouble();
				ac.sub_balance(money);
			}
			else if(i==3) {
				System.out.println("잔액: "+ ac.get_balance());
			}
			else if(i==4) {
				break;
			}
		}
	}
}

