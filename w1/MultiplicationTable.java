package w1;

public class MultiplicationTable {

    public static void main(String[] args) {
        // 2단부터 9단까지 반복 (i는 2부터 9까지)
        for (int i = 2; i <= 9; i++) {
            // 현재 단 출력 (i는 2부터 9까지)
            System.out.println(i + "단");

            // 각 단에 대해 1부터 9까지 곱셈을 반복 (j는 1부터 9까지)
            for (int j = 1; j <= 9; j++) {
                // 곱셈 결과 출력: i * j의 결과를 출력
                System.out.println(i + "*" + j + "=" + i * j);
            }

            // 각 단 출력 후 빈 줄 추가 (다음 단으로 구분하기 위함)
            System.out.print("\n");
        }
    }
}
