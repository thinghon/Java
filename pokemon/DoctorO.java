package poketgame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DoctorO {
    private boolean used = false;  // 오박사를 이미 사용했는지 여부
    private Monitor monitor;       // 화면 출력을 담당하는 Monitor 객체
    private Bag bag;               // 트레이너의 가방

    // Constructor: Monitor와 Bag을 초기화
    public DoctorO(Monitor monitor, Bag bag) {
        this.monitor = monitor;
        this.bag = bag;
    }

    // 포켓몬을 선택하는 메서드
    public Pokemon choosePokemon() {
        if(used) {
            System.out.println("이미 오박사를 이용했습니다. 더 이상 사용할 수 없습니다.");
            return null; // 이미 사용한 경우 null 반환
        }
        Scanner scanner = new Scanner(System.in);

        monitor.printBorder();
        monitor.printBoxedMessage(
            "꼬부기, 파이리, 또는 이상해씨 중 하나를 선택하세요.", 
            "1. 꼬부기 | 2. 파이리 | 3. 이상해씨"
        );
        System.out.print("선택: ");
        int choice = 0;
        try{
            choice = scanner.nextInt(); // 사용자가 입력한 선택지
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // 잘못된 입력을 처리하고 다시 입력받기
        }
        
        Pokemon chosenPokemon = null;  // 선택한 포켓몬을 저장할 변수

        // 사용자가 선택한 포켓몬에 따라 객체 생성
        switch (choice) {
            case 1:
                chosenPokemon = new Squirtle(5);  // 꼬부기 생성
                break;
            case 2:
                chosenPokemon = new Charmander(5);  // 파이리 생성
                break;
            case 3:
                chosenPokemon = new Bulbasaur(5);  // 이상해씨 생성
                break;
            default:
                monitor.printBoxedMessage("잘못된 선택입니다.");
                return choosePokemon();  // 재귀 호출로 다시 선택을 받음
        }

        monitor.printBoxedMessage(
            chosenPokemon.getName() + "를 선택했습니다!", 
            chosenPokemon.getName() + "이/가 가방에 추가되었습니다." 
        );
        
        used = true; 
        bag.addPokemon(chosenPokemon);  // 선택된 포켓몬을 가방에 추가
        bag.addItem(new MonsterBall(5));  // 포켓몬볼 추가
        System.out.println("O : 포켓몬볼을 사용해 포켓몬을 잡을수 있어"); 
        
        return chosenPokemon;  // 선택된 포켓몬 반환
    }
}
