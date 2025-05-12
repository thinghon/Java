package poketgame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        Monitor monitor = new Monitor(); // 출력 관련 기능 담당
        Map map = new Map(); // 맵 구성 객체
        Bag bag = new Bag(); // 포켓몬 및 아이템 관리
        PokemonCenter pokemoncenter = new PokemonCenter(monitor, bag); // 포켓몬 센터 기능
        Shop shop = new Shop(monitor, bag); // 상점 기능
        DoctorO doctorO = new DoctorO(monitor, bag); // 오박사 관련 이벤트

        // 이동 및 이벤트 처리 담당 객체
        Move move = new Move(map, monitor, pokemoncenter, shop, bag);

        Scanner scanner = new Scanner(System.in);

        // 게임 소개 출력
        monitor.printIntro();
        monitor.printBoxedMessage("확인 했으면 Enter키를 눌러 게임을 시작하세요");
        scanner.nextLine();

        while (true) {
            int n;
            map.printMap(Move.map_num); // 현재 맵 출력

            System.out.print("W, A, S, D (Q: 종료) 입력: ");
            char direction = scanner.next().toLowerCase().charAt(0); // 방향 입력 받기

            if (direction == 'q') {
                // 종료 처리
                monitor.printBorder();
                monitor.printBoxedMessage("게임을 종료합니다.");
                monitor.printBorder();
                break;
            } else if (direction == 'b') {
                // 가방 열기 메뉴
                while (true) {
                    monitor.printBorder();
                    monitor.printBoxedMessage("		    가방 목록",
                            "1.포켓몬 | 2. 아이템 | 0. 취소");
                    System.out.print("선택: ");
                    try {
                        n = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("숫자를 입력하세요.");
                        scanner.nextLine(); // 잘못된 입력 제거
                        continue;
                    }

                    if (n == 1) {
                        // 포켓몬 리스트 출력
                        monitor.printBorder();
                        bag.printPokemonList();
                    } else if (n == 2) {
                        // 아이템 리스트 출력
                        bag.printItemList();

                        if (bag.getItemCount() == 0) {
                            System.out.println("사용 가능한 아이템이 없습니다.");
                        } else {
                            // 아이템 선택
                            System.out.print("사용할 아이템 번호 선택 (0: 취소): ");
                            int itemIndex;
                            try {
                                itemIndex = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("숫자를 입력하세요.");
                                scanner.nextLine(); 
                                continue;
                            }

                            if (itemIndex == 0) {
                                continue; // 취소
                            }

                            itemIndex--; // 인덱스 보정

                            if (itemIndex < 0 || itemIndex >= bag.getItems().size()) {
                                System.out.println("잘못된 번호입니다.");
                            } else {
                                Item selectedItem = bag.getItem(itemIndex);

                                if (selectedItem instanceof MonsterBall) {
                                    // 몬스터볼 사용 제한
                                    System.out.println("몬스터볼은 이 메뉴에서 사용할 수 없습니다.");
                                    continue;
                                }

                                // 사용할 포켓몬 선택
                                monitor.printBorder();
                                for (int i = 0; i < bag.getPokemonCount(); i++) {
                                    Pokemon p = bag.getPokemon(i);
                                    String hpInfo = p.getHp() + "/" + p.getMaxHp();
                                    System.out.println((i+1) + ". " + p.getName() + 
                                            " | Hp: " + hpInfo + " | LV." + p.getLevel());
                                }

                                if (bag.getPokemonCount() == 0) {
                                    System.out.println("포켓몬이 없습니다. 아이템을 사용할 수 없습니다.");
                                    continue;
                                }

                                System.out.print("어떤 포켓몬에게 사용할까요? 번호 선택 (0: 취소): ");
                                int pokeIndex = 999;
                                try {
                                    pokeIndex = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("숫자를 입력하세요.");
                                    scanner.nextLine(); 
                                    continue;
                                }

                                if (pokeIndex == 0) {
                                    continue; // 취소
                                }

                                pokeIndex--; // 인덱스 보정

                                if (pokeIndex < 0 || pokeIndex >= bag.getPokemonCount()) {
                                    System.out.println("잘못된 포켓몬 번호입니다.");
                                } else {
                                    monitor.printBorder();
                                    Pokemon target = bag.getPokemon(pokeIndex);
                                    bag.useItem(itemIndex, target); // 아이템 사용
                                }
                            }
                        } 
                    } else if (n == 0) {
                        monitor.printBorder(); // 가방 나가기
                        break;
                    } else {
                        System.out.println("잘못된 선택입니다. 다시 선택하세요.");
                    }
                }
            }

            // 이동 처리 및 이벤트 발생
            boolean moved = move.move(direction, doctorO, pokemoncenter, bag);
        }
    }
}
