package w1;

import java.util.Scanner;  // Scanner 클래스를 사용하기 위한 import

public class BMI {

    public static void main(String[] args) {
        // 사용자 입력을 받기 위해 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        // 체중(kg)을 입력받음
        System.out.print("체중(kg)을 입력하세요: ");
        double weight = sc.nextDouble();  // 사용자 입력을 weight 변수에 저장

        // 키(m)를 입력받음
        System.out.print("키(m)를 입력하세요: ");
        double height = sc.nextDouble();  // 사용자 입력을 height 변수에 저장
        
        // BMI 계산: BMI = 체중(kg) / (키(m) * 키(m))
        System.out.println("BMI지수: " + weight / (height * height));  // 계산된 BMI를 출력
    }
}
