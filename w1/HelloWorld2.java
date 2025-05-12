package w1;

import java.util.Scanner;

public class HelloWorld2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력하세요: ");
		int num = sc.nextInt();  // 정수를 입력받고
		// 입력 버퍼에는 개행문자 '\n'이 남아 있습니다.
		System.out.println("엔터를 눌러 다음 항목을 입력하세요");
		String str = sc.nextLine();  // 여기에선 개행문자만 읽혀서 값이 바로 입력되지 않음
		System.out.println("입력한 문자열: " + str);  // 빈 문자열이 출력됩니다.

	}

}