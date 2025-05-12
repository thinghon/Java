package w2;

import java.util.*;

public class Linear_Interporation {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성
		while (true) { // 무한 루프 시작
			// 보드 초기화 (5x5 크기의 2차원 배열 생성)
			char[][] board = new char[5][5];
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					board[i][j] = ('_'); // 보드판의 모든 칸을 '_'로 초기화
				}
			}

			// 메뉴 출력
			System.out.println("그림 그리기 프로그램");
			System.out.println("1. 직선 그리기");
			System.out.println("2. 정사각형 그리기");
			System.out.print("원하는 그림을 선택하세요 (1-2, 종료: 0):");
			int n = sc.nextInt(); // 메뉴 선택

			// 직선 그리기
			if (n == 1) {
				sc.nextLine(); // 버퍼 비우기
				while (true) {
					// 첫 번째 좌표 입력
					System.out.print("첫 번째 점의 좌표: ");
					String num_1 = sc.nextLine();
					String[] coordinate_1 = num_1.split(" "); // 공백 기준으로 좌표 구분
					int x1 = Integer.parseInt(coordinate_1[0]);
					int y1 = Integer.parseInt(coordinate_1[1]);

					// 두 번째 좌표 입력
					System.out.print("두 번째 점의 좌표: ");
					String num_2 = sc.nextLine();
					String[] coordinate_2 = num_2.split(" "); // 공백 기준으로 좌표 구분
					int x2 = Integer.parseInt(coordinate_2[0]);
					int y2 = Integer.parseInt(coordinate_2[1]);

					// 두 좌표 간의 거리 중 더 긴 값을 steps로 설정
					int steps = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
					try {
						// 선형 보간법을 통해 직선 좌표 계산 및 그리기
						for (int i = 0; i <= steps; i++) {
							int x = Math.round(x1 + (x2 - x1) * i / (float) steps);
							int y = Math.round(y1 + (y2 - y1) * i / (float) steps);
							board[y][x] = ('*'); // 보드에 '*' 표시
						}

						// 보드 출력
						for (int i = 0; i < board.length; i++) {
							for (int j = 0; j < board[i].length; j++) {
								System.out.print(board[i][j]);
							}
							System.out.println(); // 한 행 종료 후 줄바꿈
						}
						System.out.println();
						break; // 정상적으로 선을 그린 경우 반복문 종료
					} catch (IndexOutOfBoundsException e) {
						// 보드판을 벗어난 좌표 입력 시 예외 처리
						System.out.println("좌표값이 보드판 크기를 넘었습니다.\n");
					}
				}

			// 정사각형 그리기
			} else if (n == 2) {
				while (true) {
					System.out.println("정사각형을 그리기 시작할 좌표를 입력하세요");
					System.out.println("(좌측상단 꼭짓점 좌표)");
					System.out.print("좌표: ");
					sc.nextLine(); // 버퍼 비우기
					String num_1 = sc.nextLine();
					String[] coordinate_1 = num_1.split(" "); // 공백 기준으로 좌표 구분
					int start_x = Integer.parseInt(coordinate_1[0]);
					int start_y = Integer.parseInt(coordinate_1[1]);

					// 정사각형 크기 입력
					System.out.println("그리고 싶은 사각형의 크기를 입력하세요 (한변의 길이)");
					System.out.print("입력(최대크기 5): ");
					int dis = sc.nextInt(); // 한 변의 길이

					// 우측값, 좌측값 계산
					int right_x = start_x + dis - 1;
					int under_y = start_y + dis - 1;

					try {
						// 정사각형의 네 변 그리기
						for (int i = start_x; i <= right_x; i++) {
							board[start_y][i] = ('*'); // 상단 변
							board[i][right_x] = ('*'); // 우측 변
							board[i][start_x] = ('*'); // 좌측 변
							board[under_y][i] = ('*'); // 하단 변
						}

						// 보드 출력
						for (int i = 0; i < board.length; i++) {
							for (int j = 0; j < board[i].length; j++) {
								System.out.print(board[i][j]);
							}
							System.out.println();
						}
						System.out.println();
						break; // 정상적으로 정사각형을 그린 경우 반복문 종료
					} catch (IndexOutOfBoundsException e) {
						// 보드판을 벗어난 좌표 입력 시 예외 처리
						System.out.println("정사각형이 보드판보다 큽니다\n");
					}
				}

			// 종료
			} else if (n == 0) {
				break; // 프로그램 종료
			}
		}
	}
}
