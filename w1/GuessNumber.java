package w1;

import java.util.Random;  // 랜덤 숫자를 생성하기 위한 클래스 import
import java.util.Scanner;  // 사용자 입력을 받기 위한 클래스 import

public class GuessNumber {

    public static void main(String[] args) {
        // 사용자 입력을 받기 위한 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);
        
        // 랜덤 숫자를 생성하기 위한 Random 객체 생성
        Random random = new Random();

        // 최대 숫자 범위 설정
        int maxNumber = 100;
        
        // 1부터 maxNumber까지의 랜덤 숫자 생성 (1부터 100까지)
        int randomNumber = random.nextInt(maxNumber) + 1;

        // 게임을 반복해서 진행하기 위한 무한 루프
        while(true) {
            // 사용자에게 숫자를 입력하도록 안내
            System.out.println("1부터 " + maxNumber + "까지의 숫자 중 하나를 선택하세요.");
            
            // 사용자가 입력한 숫자를 guess 변수에 저장
            int guess = scanner.nextInt();
            
            // 사용자가 입력한 숫자가 랜덤 숫자와 일치하는지 확인
            if (guess == randomNumber) {
                // 정답을 맞춘 경우
                System.out.println("정답!");
                break;  // 반복문 종료
            } else if(guess > randomNumber) {
                // 정답보다 높은 경우
                System.out.println("down!");
            }else if(guess < randomNumber) {
                // 정답보다 낮은 경우
                System.out.println("up!");
            }
        }
    }
}
