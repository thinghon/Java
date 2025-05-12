package w2;

import java.util.*; // 모든 유틸리티 클래스를 임포트

public class TodoList {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(); // 할 일을 저장할 리스트 생성
        Scanner sc = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체 생성

        // 무한 루프를 사용하여 메뉴 반복 표시
        while (true) {
            System.out.println("ToDo 리스트");
            System.out.println("1. 할 일 추가");
            System.out.println("2. 완료한 항목 체크");
            System.out.println("3. 목록 보기");
            System.out.println("4. 종료");
            System.out.print("메뉴를 선택하세요: ");
            int i = sc.nextInt(); // 사용자 입력 받기

            // 할 일 추가 기능
            if (i == 1) {
                System.out.print("할 일을 입력하세요: ");
                String todo = sc.nextLine(); // 사용자가 입력한 할 일 추가
                list.add(todo);
                System.out.println("할 일이 추가되었습니다.\n");
            } 
            // 완료 항목 체크 기능
            else if (i == 2) {
                while (true) {
                    int n = 1;
                    System.out.println("\nToDo 리스트 목록:");
                    for (String str : list) { // 리스트의 모든 항목 출력
                        System.out.println(n + ". " + str);
                        n++;
                    }
                    System.out.print("완료할 목록 번호: ");
                    int num = sc.nextInt();
                    try {
                        // 선택한 항목 앞에 [완료] 추가
                        list.set(num - 1, "[완료] " + list.get(num - 1));
                        System.out.println();
                        break; // 성공적으로 완료 체크 시 반복 종료
                    } catch (IndexOutOfBoundsException e) {
                        // 존재하지 않는 목록 번호 입력 시 예외 처리
                        System.out.println("목록에 없는 번호입니다.");
                        System.out.println("목록 번호를 다시 확인해주세요.");
                        sc.nextLine(); // 버퍼 비우기 (무한 루프 방지)
                    }
                }
            } 
            // 목록 보기 기능
            else if (i == 3) {
                int n = 1;
                System.out.println("\nToDo 리스트 목록:");
                for (String str : list) { // 리스트의 모든 항목 출력
                    System.out.println(n + ". " + str);
                    n++;
                }
                System.out.println();
            } 
            // 프로그램 종료 기능
            else if (i == 4) {
                System.out.println("종료합니다.");
                break; // 반복문 종료
            }
        }
    }
}