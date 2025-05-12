package w2;

import java.util.Scanner;

public class Calculate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 연산자 입력
        System.out.print("연산자를 입력하세요: ");
        String sign = sc.next();
        
        // 피연산자 2개 입력
        System.out.print("피연산자 2개를 입력하세요: ");
        sc.nextLine();
        String input = sc.nextLine();
        
        // 입력된 피연산자를 공백을 기준으로 나누어 배열에 저장
        String[] number = input.split(" ");

        // 문자열을 double로 변환
        double num1 = Double.parseDouble(number[0]);
        double num2 = Double.parseDouble(number[1]);
        
        // 연산자에 따른 계산
        if (sign.equals("+")) {  // 문자열 비교 시 equals() 사용
            System.out.println(num1 + "+" + num2 + " = " + (num1 + num2));
        }
        else if(sign.equals("-")) {
            System.out.println(num1 + "-" + num2 + " = " + (num1 - num2));
        }
        else if(sign.equals("*")) {
            System.out.println(num1 + "*" + num2 + " = " + (num1 * num2));
        }
        else if(sign.equals("/")) {
            if (num2 == 0) {  // 0으로 나누는 경우를 처리
                System.out.println("나눗셈은 분모가 0이 될 수 없습니다");
            } else {
                System.out.println(num1 + "/" + num2 + " = " + (num1 / num2));
            }
        }
        else {
            // 유효하지 않은 연산자에 대한 처리
            System.out.println("잘못된 연산자입니다.");
        }
    }
}
