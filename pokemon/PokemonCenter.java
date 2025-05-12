package poketgame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PokemonCenter {
    private Bag bag;  // 포켓몬 가방
    private Monitor monitor;  // 모니터 객체 (UI 출력 담당)

    public PokemonCenter(Monitor monitor, Bag bag) {
    	this.monitor = monitor;  // 모니터 초기화
        this.bag = bag;  // 가방 초기화
    }

    // 모든 포켓몬을 치료하는 메서드
    public void healAllPokemon() {
        for (int i = 0; i < bag.getPokemonCount(); i++) {
            Pokemon pokemon = bag.getPokemon(i);  // 가방에 있는 포켓몬 가져오기
            pokemon.setHp(pokemon.getMaxHp());  // 포켓몬의 HP를 최대 HP로 설정하여 치료
            System.out.println(pokemon.getName() + "이(가) 치료되었습니다.");  
        }
    }

    // 포켓몬 방생 메서드
    public void releasePokemon() {
        if (bag.getPokemonCount() == 0) {  // 가방에 포켓몬이 없는 경우
            System.out.println("가방에 포켓몬이 없습니다.");
            return;
        }
        if (bag.getPokemonCount() == 1) {  // 가방에 포켓몬이 1마리인 경우
            System.out.println("포켓몬은 최소 1마리는 가지고 있어야 합니다. 방생할 수 없습니다.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < bag.getPokemonCount(); i++) {
            System.out.println((i + 1) + ". " + bag.getPokemon(i).getName());  // 가방에 있는 포켓몬 리스트 출력
        }
        System.out.println("방생할 포켓몬을 선택하세요:");
        int choice = 999; 
        try {
        	choice = scanner.nextInt() - 1;  // 사용자 입력을 받아 방생할 포켓몬 선택
        } catch (InputMismatchException e) {
            scanner.nextLine();  // 예외 발생 시 입력 버퍼 초기화
        }
        if (choice >= 0 && choice < bag.getPokemonCount()) {  // 선택된 포켓몬이 유효한지 확인
            Pokemon releasedPokemon = bag.getPokemon(choice);  // 선택된 포켓몬 가져오기
            System.out.println(releasedPokemon.getName() + "이(가) 방생되었습니다.");  
            bag.removePokemon(choice);  // 가방에서 해당 포켓몬 제거
        } else {
            System.out.println("잘못된 선택입니다.");  
        }
    }

    // 포켓몬 센터 메뉴 출력 및 사용자 입력 처리
    public void showMenu() {
    	monitor.printBorder();
        Scanner scanner = new Scanner(System.in);
        monitor.printBoxedMessage("		   포켓몬 센터", 
        		"1. 포켓몬 전체 치료 | 2. 포켓몬 방생 | 0. 종료");
        System.out.print("선택: ");
        int choice = 999; 
        try {
        	choice = scanner.nextInt();  // 사용자 입력 받기
        	scanner.nextLine();  // 입력 후 버퍼 비우기
        } catch (InputMismatchException e) {
            scanner.nextLine();  // 예외 발생 시 입력 버퍼 초기화
        }
        monitor.printBorder();
        switch (choice) {  // 사용자의 선택에 따른 동작
            case 1:
                healAllPokemon();  // 포켓몬 전체 치료
                break;
            case 2:
                releasePokemon();  // 포켓몬 방생
                break;
            case 0:
                System.out.println("포켓몬 센터를 떠납니다."); 
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }
}
