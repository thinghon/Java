package w1;

public class Prime {
    public static void main(String[] args) {
        // 검사할 숫자 (여기서는 29를 사용)
        int num = 29;
        
        // 소수 여부를 나타내는 flag 변수, 초기값은 false
        boolean flag = false;
        
        // 2부터 num-1까지 반복하면서 num이 소수인지 검사
        for (int i = 2; i < num; ++i) {
            // num이 i로 나누어떨어지면 (즉, 소수가 아니면)
            if (num % i == 0) {
                flag = true; // flag를 true로 설정								
                break; // 더 이상 검사할 필요가 없으므로 루프 종료
            }
        }
        
        // flag 값이 true이면 소수가 아닌 경우, false이면 소수인 경우
        if (flag) {
            // 소수가 아니면 출력
            System.out.println(num + "은 소수가 아닙니다.");
        } else {
            // 소수면 출력
            System.out.println(num + "은 소수입니다.");
        }
    }
}
