package w2;

import java.util.Random;

public class Lotto {
    
    // 배열에 중복된 숫자가 있는지 확인하는 메서드
    public static boolean duplication(int[] arr) {
        // 배열의 각 요소를 비교하여 중복 여부 확인
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                // 중복된 숫자가 있으면 true 반환
                if(arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        // 중복된 숫자가 없으면 false 반환
        return false;
    }

    public static void main(String[] args) {
        // 랜덤 숫자를 생성할 Random 객체 생성
        Random random = new Random();

        // 로또 번호를 저장할 배열 (6개의 숫자)
        int[] num = new int[6];
        
        // 로또 번호의 최대값 (1부터 45까지)
        int maxRandomNum = 45;

        // 배열에 랜덤 숫자 생성하여 넣기
        for (int i = 0; i < num.length; i++) {
            // 1부터 maxRandomNum까지의 랜덤 숫자 생성
            int randomNum = random.nextInt(maxRandomNum) + 1;
            // 배열에 생성된 랜덤 숫자 저장
            num[i] = randomNum;
        }

        // 중복된 숫자가 있을 경우, 중복되지 않을 때까지 숫자 재생성
        while(duplication(num)) {
            // 중복이 있을 경우 다시 숫자 생성
            for (int i = 0; i < num.length; i++) {
                // 1부터 maxRandomNum까지의 랜덤 숫자 생성
                int randomNum = random.nextInt(maxRandomNum) + 1;
                // 배열에 새로 생성된 숫자 저장
                num[i] = randomNum;
            }
        }

        // 로또 번호 출력
        System.out.println("로또 번호 생성기");
        // 생성된 로또 번호 배열 출력
        System.out.print("선택된 로또 번호: ");
        for(int value : num) {
            // 배열의 각 숫자를 출력 (공백으로 구분)
            System.out.print(value + " ");
        }
    }
}
