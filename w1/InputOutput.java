package w1;

import java.util.Scanner;  // 사용자 입력을 받기 위한 Scanner 클래스 import

public class InputOutput {
    public static void main(String[] args) {
        // 사용자로부터 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 사용자에게 이름을 입력받기 위해 안내 메시지 출력
        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();  // 사용자가 입력한 이름을 name 변수에 저장

        // 사용자에게 나이를 입력받기 위해 안내 메시지 출력
        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt();  // 사용자가 입력한 나이를 age 변수에 저장

        // 입력 받은 이름과 나이를 출력
        System.out.println("이름: " + name + ", 나이: " + age);
    }
}
