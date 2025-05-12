package poketgame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Shop {
    private ArrayList<Item> itemsForSale = new ArrayList<>();  // 판매할 아이템 목록
    private Scanner scanner = new Scanner(System.in);          // 사용자 입력을 받기 위한 Scanner 객체
    private Monitor monitor;                                    // 화면 출력용 Monitor 객체
    private Bag bag;                                            // 트레이너의 가방 객체

    // 생성자: Monitor와 Bag 객체를 초기화하고, 판매할 아이템을 추가
    public Shop(Monitor monitor, Bag bag) {
        this.monitor = monitor;
        this.bag = bag;

        itemsForSale.add(new MonsterBall(0));  // 포켓몬볼 추가
        itemsForSale.add(new Potion(0));       // 포션 추가
    }

    // 상점 메뉴를 보여주고, 선택에 따른 동작을 처리하는 메서드
    public void showMenu() {
        while (true) {
            monitor.printBorder();
            monitor.printBoxedMessage("		   포켓몬 샵",   
            		"돈: " + bag.getMoney(),  
            		"1. 아이템 구매 | 2. 아이템 판매 | 0. 나가기");  
            System.out.print("선택: ");
            int choice = 0;
            try{
            	choice = scanner.nextInt();  // 사용자 입력 받기
            	scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력하세요.");  
                scanner.nextLine(); 
                continue;
            }
            
            // 선택된 메뉴에 따른 동작 처리
            if (choice == 1) {
            	monitor.printBorder();  
                buyItem();  // 아이템 구매
            } else if (choice == 2) {
            	monitor.printBorder(); 
                sellItem();  // 아이템 판매
            } else if (choice == 0) {
            	monitor.printBorder(); 
                break;  // 메뉴 종료
            } else {
                System.out.println("잘못된 입력입니다."); 
            }
        }
    }

    // 아이템 구매를 처리하는 메서드
    private void buyItem() {
        monitor.printBoxedMessage("상점 아이템 목록:");
        // 아이템 목록 출력
        for (int i = 0; i < itemsForSale.size(); i++) {
            Item item = itemsForSale.get(i);
            System.out.printf("%d. %s (구매가: %d원)\n", i + 1, item.getName(), item.getBuyPrice());
        }
        System.out.print("구매할 아이템 번호 선택 (0: 취소): ");
        int index = 0;
        try{
        	index = scanner.nextInt() - 1;  // 사용자가 선택한 아이템 번호
        	scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력하세요.");
            scanner.nextLine(); 
            return;
        }
        if (index < 0 || index >= itemsForSale.size()) {
        	System.out.println("잘못된 선택입니다."); 
        	return;
        }

        Item selected = itemsForSale.get(index);  // 선택된 아이템
        System.out.print("몇 개 구매하시겠습니까? ");
        int quantity = 0;
        try{
        	quantity = scanner.nextInt();  // 구매할 개수 입력
        	scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력하세요.");  // 잘못된 입력 처리
            scanner.nextLine(); 
            return;
        }
        
        int totalCost = selected.getBuyPrice() * quantity;  // 총 비용 계산
        if (bag.getMoney() >= totalCost) {  // 충분한 돈이 있는지 확인
            bag.useMoney(totalCost);  // 가방에서 돈 차감
            Item copy = selected.copy();  // 아이템 복제
            copy.setCount(quantity);  // 구매한 개수만큼 아이템의 개수 설정
            bag.addItem(copy);  // 아이템을 가방에 추가
            System.out.println(selected.getName() + " " + quantity + "개를 구매했습니다.");  // 구매 메시지 출력
        } else {
            System.out.println("돈이 부족합니다.");  // 돈이 부족할 때 처리
        }
    }

    // 아이템 판매를 처리하는 메서드
    private void sellItem() {
        bag.printItemList();  // 가방에 있는 아이템 목록 출력
        System.out.print("판매할 아이템 번호 선택 (0: 취소): ");
        int index = 0;
        try{
        	index = scanner.nextInt() - 1;  // 사용자가 선택한 아이템 번호
        	scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력하세요."); 
            scanner.nextLine(); 
            return;
        }
        if (index < 0 || index >= bag.getItems().size()) {
        	System.out.println("잘못된 선택입니다."); 
        	return;
        }

        Item item = bag.getItems().get(index);  // 선택된 아이템
        System.out.print("몇 개 판매하시겠습니까? ");
        int quantity = 0;
        try{
        	quantity = scanner.nextInt();  // 판매할 개수 입력
        	scanner.nextLine();
        }catch (InputMismatchException e) {
            System.out.println("숫자를 입력하세요."); 
            scanner.nextLine(); 
            return;
        }

        // 판매할 수량이 유효한지 확인
        if (quantity <= 0 || quantity > item.getCount()) {
            System.out.println("잘못된 수량입니다.");
            return;
        }

        int totalEarned = item.getSellPrice() * quantity;  // 판매 금액 계산
        bag.addMoney(totalEarned);  // 판매 금액을 가방에 추가
        item.setCount(item.getCount() - quantity);  // 아이템 수량 감소

        // 아이템 수량이 0이 되면 가방에서 삭제
        if (item.getCount() == 0) {
            bag.getItems().remove(index);
        }

        System.out.println(item.getName() + " " + quantity + "개를 판매했습니다. +" + totalEarned + "원"); 
    }
}
