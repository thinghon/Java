package poketgame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class Battle {
    private int addExp;
    private boolean run;

    private Pokemon player;
    private Bag player_bag;
    private Pokemon enemy;
    private Trainer trainer;
    
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private Monitor monitor;

    private boolean playerChanged = false;

    public Battle(Bag player_bag, Trainer trainer, Monitor monitor) {
        this.player_bag = player_bag;
        this.trainer = trainer;
        this.monitor = monitor;
        run = false;
    }

    public Battle(Bag player_bag, Pokemon enemy, Monitor monitor) {
        this.player_bag = player_bag;
        this.enemy = enemy;
        this.monitor = monitor;
        run = false;
    }

    // 사람 vs 포케몬
    public boolean battleType1() {  // 전투 유형 1 시작
        boolean attack;  // 공격 여부 변수
        monitor.printBoxedMessage("전투 시작!");

        // 플레이어의 첫 번째 살아있는 포켓몬을 찾음
        for (int i = 0; i < player_bag.getPokemonCount(); i++) {
            if (player_bag.getPokemon(i).getHp() > 0) {
                player = player_bag.getPokemon(i);  // 살아있는 포켓몬 설정
                break;
            }
        }

        while (true) {  // 전투 루프 시작
            fightMonitor();  // 전투 상태 모니터링
            playerChanged = false;  // 포켓몬 교체 여부 초기화

            while (true) {  // 전투 메뉴 반복
                System.out.println("1.전투  2.가방  3.도망가기  4.교체");  // 전투 메뉴 출력
                System.out.print("사용할 번호 선택: ");
                int num = 0;

                try {
                    num = scanner.nextInt();  // 사용자 입력 받기
                } catch (InputMismatchException e) {  // 숫자 입력 오류 처리
                    System.out.println("숫자를 입력하세요.");
                    monitor.printBorder(); 
                    scanner.nextLine();  // 버퍼 비우기
                    continue;
                }

                attack = playerTurn(num);  // 플레이어 턴 처리

                if (run || num != 4) break;  // 도망가거나 교체가 아니면 반복 종료

                if (num == 4 && playerChanged) break;  // 교체 후 반복 종료
            }

            if ((attack || playerChanged) && !run && enemy.getHp() > 0) {  // 플레이어 공격 또는 교체 후, 적의 체력이 남아 있으면 적의 턴 진행
                enemyTurn();  // 적 턴 처리
            }

            if (player.getHp() <= 0) {  // 플레이어의 HP가 0 이하일 경우
                fightMonitor();
                System.out.println(player.getName() + "이(가) 쓰러졌습니다!");  // 플레이어 포켓몬 쓰러짐 메시지 출력

                boolean hasAlive = false;  // 살아있는 포켓몬 존재 여부 체크
                for (int i = 0; i < player_bag.getPokemonCount(); i++) {
                    if (player_bag.getPokemon(i).getHp() > 0) {
                        hasAlive = true;  // 살아있는 포켓몬이 있으면 true
                        break;
                    }
                }

                if (hasAlive) {  // 살아있는 포켓몬이 있으면
                    while (changePokemon() != 1) {  // 포켓몬 교체 시도
                        System.out.println("잘못된 선택입니다. 다시 선택해 주세요.");
                        monitor.printBorder();  // 화면 구분선 출력
                    }
                    continue;  // 포켓몬 교체 후 다시 전투 진행
                } else {  // 살아있는 포켓몬이 없으면
                    fightMonitor(); 
                    System.out.println("남은 포켓몬이 없습니다..."); 
                    monitor.printBoxedMessage("패배했습니다..."); 
                    monitor.printBorder(); 
                    return false;  // 패배 처리
                }
            }

            if (enemy.getHp() <= 0) {  // 적의 HP가 0 이하일 경우
                fightMonitor();
                monitor.printBoxedMessage("승리했습니다!");  // 승리 메시지 출력
                monitor.printBorder(); 

                int baseExp = enemy.getExp() / 3;  // 기본 경험치 계산
                int variation = new Random().nextInt(11) - 5;  // 경험치 변동량 계산 (-5에서 5 사이)
                int addExp = Math.max(1, baseExp + variation);  // 최소 경험치 1을 보장하여 경험치 증가
                player.increaseExp(addExp);  // 플레이어 경험치 증가

                monitor.printBorder();
                return true;  // 승리 처리
            }

            if (run) {  // 도망친 경우
                monitor.printBorder();
                return true;  // 도망 성공
            }
        }
    }

    // 사람 vs 사람
    public boolean battleType2() {  
        monitor.printBorder(); 
        trainer.battle();  // 트레이너와의 전투 시작
        monitor.printBoxedMessage("전투 시작!");
        
        // 플레이어의 첫 번째 살아있는 포켓몬을 찾음
        for (int i = 0; i < player_bag.getPokemonCount(); i++) {
            if (player_bag.getPokemon(i).getHp() > 0) {
                player = player_bag.getPokemon(i);  // 살아있는 포켓몬 설정
                break;
            }
        }
        
        int enemyIndex = 0;  // 적 포켓몬 인덱스 초기화
        enemy = trainer.getPokemon(enemyIndex);  // 첫 번째 적 포켓몬 설정
        boolean enemyChange = false;  // 적 포켓몬 교체 여부 초기화

        while (player.getHp() > 0 && enemy != null && enemy.getHp() > 0) {  // 전투 루프
            playerChanged = false;  // 포켓몬 교체 여부 초기화
            fightMonitor();

            while (true) {  // 사용자 메뉴 반복
                playerChanged = false;
                System.out.println("1.전투  2.가방  3.도망가기  4.교체");
                System.out.print("사용할 번호 선택: ");
                int num = 0;
                try {
                    num = scanner.nextInt();  // 사용자 입력 받기
                } catch (InputMismatchException e) {  // 잘못된 입력 처리
                    System.out.println("숫자를 입력하세요.");
                    monitor.printBorder();
                    scanner.nextLine();  // 입력 버퍼 비우기
                    continue;
                }
                if (num == 3) {  // 도망가기 시도 시 메시지 출력
                    System.out.println("사람과의 결투에선 도망갈 수 없어!");
                    monitor.printBorder();
                } else {
                    boolean attack = playerTurn(num);  // 플레이어 턴 처리
                    if (attack || playerChanged || run) {  // 공격하거나 포켓몬 교체한 경우 반복 종료
                        break;
                    } else if (!playerChanged || !attack) {
                        playerChanged = true;
                        continue;  // 포켓몬 교체 시 계속 반복
                    }
                }
            }

            if (enemy.getHp() <= 0) {  // 적 포켓몬이 쓰러졌을 경우
                monitor.printBorder();
                System.out.println(enemy.getName() + " 쓰러졌다!");
                int baseExp = enemy.getExp() / 3;  // 경험치 계산
                int variation = new Random().nextInt(11) - 5;  // 경험치 변동량
                int addExp = Math.max(1, baseExp + variation);  // 최소 경험치 보장
                player.increaseExp(addExp);  // 플레이어 경험치 증가

                enemyIndex++;
                if (enemyIndex < trainer.getPokemonCount()) {  // 교체할 적 포켓몬이 있는 경우
                    enemy = trainer.getPokemon(enemyIndex);
                    System.out.println(trainer.getName() + enemy.getName() + "를(을) 꺼냈다!");
                    enemyChange = true;
                } else {
                    enemy = null;  // 적 포켓몬이 더 이상 없으면 전투 종료
                    break;
                }
            }

            if (!enemyChange) {  // 적 포켓몬 교체가 없으면 적의 턴 처리
                enemyTurn();
                if (player.getHp() <= 0) {  // 플레이어 포켓몬이 쓰러졌을 경우
                    monitor.printBorder();
                    System.out.println(player.getName() + "이(가) 쓰러졌습니다!");

                    boolean hasAlive = false;
                    for (int i = 0; i < player_bag.getPokemonCount(); i++) {
                        if (player_bag.getPokemon(i).getHp() > 0) {  // 살아있는 포켓몬이 있는지 체크
                            hasAlive = true;
                            break;
                        }
                    }

                    if (hasAlive) {  // 살아있는 포켓몬이 있으면 교체
                        System.out.println("교체할 포켓몬을 선택하세요: ");
                        while (changePokemon() != 1) {  // 포켓몬 교체 시도
                            System.out.println("잘못된 선택입니다. 다시 선택해 주세요.");
                        }
                        continue;  // 교체 후 전투 계속
                    } else {  // 살아있는 포켓몬이 없으면 패배 처리
                        System.out.println("남은 포켓몬이 없습니다...");
                        monitor.printBoxedMessage("패배했습니다...");
                        monitor.printBorder();
                        return false;
                    }
                }
            } else {  // 적 포켓몬 교체가 있었다면 플래그 초기화
                enemyChange = false;
            }
        }

        if (player.getHp() > 0 && enemy == null) {  // 플레이어가 승리한 경우
            monitor.printBoxedMessage("승리했습니다!");
            monitor.printBorder();
            int reward = trainer.getMoney();  // 트레이너에게서 받은 보상
            player_bag.addMoney(reward);
            System.out.println(trainer.getName() +"트레이너로부터 " + reward + "원을 획득했습니다!");
            monitor.printBorder();
            return true;
        }

        return false;
    }

    private boolean playerTurn(int num) {  
        // 1번: 스킬 사용
        if(num == 1) {  
            monitor.printBorder();  // 구분선 출력
            System.out.println(player.getName() + "의 스킬 목록:");  
            // 스킬 목록 출력
            for (int i = 0; i < player.getSkillCount(); i++) {
                if(i % 2 == 1) {
                    System.out.println("	" +(i + 1) + ". " + player.getSkills(i).getName());
                }
                else {
                    System.out.print((i + 1) + ". " + player.getSkills(i).getName());
                    if(player.getSkillCount() % 2 == 1) {
                        System.out.println();
                    }
                }
            }
            
            // 스킬 선택
            int skillNum = 999;
            while (true) {
                System.out.print("사용할 스킬 선택 (0: 취소): ");
                try {
                    skillNum = scanner.nextInt() - 1;
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                }
                if(skillNum < player.getSkillCount()) {
                    break;
                }
                System.out.println("잘못된 선택입니다.");
                monitor.printBorder();
            }
            
            // 스킬 취소 처리
            if (skillNum == -1) {
                return false;
            }
            Skill skill = player.getSkills(skillNum);
            monitor.printBorder();
            
            // 스킬 성공/실패 처리
            if (random.nextInt(100) < skill.getAccuracy()) {
                int damage = calculateDamage(skill, player, enemy);
                System.out.println(skill.getName() + " 성공! ");
                enemy.takeDamage(damage);
                return true;
            } else {
                System.out.println(skill.getName() + "은(는) 빗나갔다!");
                return true;
            } 
        }
        
        // 2번: 아이템 사용
        else if(num == 2) {
            monitor.printBorder();
            System.out.println("가방을 열었습니다.");
            player_bag.printItemList();  // 아이템 목록 출력

            System.out.print("사용할 아이템 번호 선택 (0: 취소): ");
            int itemIndex = 999;
            try {
                itemIndex = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
            monitor.printBorder();
            
            // 아이템 취소 처리
            if (itemIndex == 0) {
                System.out.println("아이템 사용을 취소했습니다.");
                return false;
            }
            itemIndex--;

            // 아이템 선택 확인
            Item selectedItem = player_bag.getItem(itemIndex);
            if (selectedItem == null) {
                System.out.println("잘못된 번호입니다.");
                monitor.printBorder();
                return false;
            }

            // 몬스터볼 처리
            if (selectedItem instanceof MonsterBall) {
                if (enemy == null || trainer != null) {
                    System.out.println("트레이너의 포켓몬에게는 몬스터볼을 사용할 수 없습니다!");
                    monitor.printBorder();
                    return false;
                }

                System.out.println("몬스터볼을 던졌다!");
                boolean success = ((MonsterBall) selectedItem).tryCatch(enemy);
                if (success) {
                    player_bag.removeItem(itemIndex);
                    monitor.printBoxedMessage(enemy.getName() + "를 잡았다!");
                    player_bag.addPokemon(enemy);
                    run = true;  // 포켓몬이 잡혔으면 도망 처리
                } else {
                    System.out.println(enemy.getName() + "가(이) 몬스터볼에서 튀어나왔다!");
                    monitor.printBorder();
                    return true;
                }
            }

            // 이상한 사탕 처리
            if (selectedItem instanceof StrangeCandy) {
                System.out.println("전투 중에는 이상한 사탕을 사용할 수 없습니다!");
                monitor.printBorder();
                return false;
            }

            // 포션 사용
            if (selectedItem instanceof Potion) {
                // 포켓몬 리스트 출력
                for (int i = 0; i < player_bag.getPokemonCount(); i++) {
                    Pokemon p = player_bag.getPokemon(i);
                    System.out.println((i + 1) + ". " + p.getName() + " (HP: " + p.getHp() + "/" + p.getMaxHp() + ")");
                }

                // 포켓몬 선택
                System.out.print("아이템을 사용할 포켓몬을 선택하세요 (0: 취소): ");
                int pokeIndex = 999;
                try {
                    pokeIndex = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    monitor.printBorder();
                    scanner.nextLine(); 
                }

                // 포켓몬 선택 취소
                if (pokeIndex == 0) {
                    System.out.println("포켓몬 선택을 취소했습니다.");
                    return false;
                }
                pokeIndex--;
                if (pokeIndex < 0 || pokeIndex >= player_bag.getPokemonCount()) {
                    System.out.println("잘못된 번호입니다.");
                    return false;
                }

                // 포션 사용
                Pokemon targetPokemon = player_bag.getPokemon(pokeIndex);
                if (selectedItem instanceof Potion && targetPokemon.getHp() == targetPokemon.getMaxHp()) {
                    System.out.println(targetPokemon.getName() + "의 HP는 이미 가득 찼습니다. 다른 행동을 선택하세요.");
                    monitor.printBorder();
                    return false;
                }

                System.out.println(targetPokemon.getName() + "에게 " + selectedItem.getName() + "을(를) 사용했습니다.");
                selectedItem.use(targetPokemon);
                player_bag.removeItem(itemIndex);
                return true;
            }
        }
        
        // 3번: 도망가기
        else if(num == 3) {
            System.out.println("도망에 성공했다!");
            run = true;
        }
        
        // 4번: 포켓몬 교체
        else if (num == 4) {
            monitor.printBorder();
            while (true) {
                int changed = changePokemon();
                monitor.printBorder();
                if (changed == 0) { 
                    continue;  // 잘못된 선택 시 계속 반복
                } else if (changed == 1) {
                    playerChanged = true;  // 포켓몬 교체 시 전투 종료
                    return false; 
                } else { 
                    playerChanged = false;  // 포켓몬 교체 취소
                    return false; 
                }
            }
        }
        return playerChanged = true;
    }


 // 적의 턴을 처리하는 메서드
    private void enemyTurn() {
        monitor.printBoxedMessage(enemy.getName() + "의 턴!"); // 적의 턴 시작 메시지 출력
        int skillNum = random.nextInt(enemy.getSkillCount()); // 적의 스킬 목록에서 랜덤으로 스킬 선택
        Skill skill = enemy.getSkills(skillNum);

        // 선택한 스킬이 적중할 확률을 랜덤으로 결정
        if (random.nextInt(100) < skill.getAccuracy()) {
            int damage = calculateDamage(skill, enemy, player); // 데미지 계산
            System.out.println(skill.getName() + " 성공! "); // 스킬 성공 메시지 출력
            player.takeDamage(damage); // 플레이어에게 데미지 적용
        } else {
            System.out.println(skill.getName() + "은(는) 빗나갔다!"); // 스킬 빗나갔을 때 메시지 출력
        }
    }

    // 데미지를 계산하는 메서드
    private int calculateDamage(Skill skill, Pokemon attacker, Pokemon defender) {
        double multiplier = getTypeMultiplier(skill.getType(), defender.getType()); // 스킬과 방어자의 타입에 따른 배율 계산
        return (int)(skill.getDamage() * multiplier); // 데미지 반환
    }

    // 타입 상성에 따른 배율을 계산하는 메서드
    private double getTypeMultiplier(int skillType, int defenderType) {
        // 효과가 좋은 경우
        if ((skillType == 1 && defenderType == 3) || // 불 → 풀
            (skillType == 2 && defenderType == 1) || // 물 → 불
            (skillType == 3 && defenderType == 2) || // 풀 → 물
            (skillType == 4 && defenderType == 2)) { // 전기 → 물
            return 1.2;
        }

        // 효과가 나쁜 경우
        if ((skillType == 3 && defenderType == 1) || // 풀 → 불
            (skillType == 1 && defenderType == 2) || // 불 → 물
            (skillType == 2 && defenderType == 3) || // 물 → 풀
            (skillType == 4 && defenderType == 3)) { // 전기 → 풀
            return 0.8;
        }

        return 1.0; // 기본 배율 (상성 없음)
    }

    // 포켓몬의 체력바를 출력하는 메서드
    private String getHpBar(Pokemon p) {
        int maxBarLength = 10; // 체력바의 최대 길이
        double ratio = (double) p.getHp() / p.getMaxHp(); // 현재 체력과 최대 체력 비율
        int filled = (int) Math.round(ratio * maxBarLength); // 채워진 부분
        int empty = maxBarLength - filled; // 비어 있는 부분
        return "[" + "=".repeat(filled) + " ".repeat(empty) + "] (" + p.getHp() + "/" + p.getMaxHp() + ")"; // 체력바 출력
    }

    // 포켓몬을 교체하는 메서드
    private int changePokemon() {
        // 포켓몬 목록 출력
        for (int i = 0; i < player_bag.getPokemonCount(); i++) {
            Pokemon p = player_bag.getPokemon(i);
            String hpInfo = p.getHp() + "/" + p.getMaxHp(); // 체력 정보
            if (p == player) {
                System.out.println((i + 1) + ". " + p.getName() + " Hp: (" + hpInfo + ") Lv. " + p.getLevel() + "[현재]"); // 현재 전투 중인 포켓몬
            } else {
                System.out.println((i + 1) + ". " + p.getName() + " Hp: (" + hpInfo + ") Lv. " + p.getLevel()); // 다른 포켓몬들
            }
        }
        
        System.out.print("교체할 포켓몬을 선택하세요 (0: 취소): ");
        int choice = 999;
        try {
            choice = scanner.nextInt(); // 선택한 포켓몬 번호
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // 잘못된 입력 처리
        }

        if (choice == 0) {
            return -1; // 취소 선택 시
        }

        choice--; // 배열 인덱스에 맞게 1을 빼서 선택
        if (choice < 0 || choice >= player_bag.getPokemonCount()) {
            System.out.println("잘못된 선택입니다. 유효한 번호를 입력해 주세요.");
            return 0; // 잘못된 선택 처리
        }

        Pokemon selected = player_bag.getPokemon(choice);

        if (selected.getHp() <= 0) {
            System.out.println("쓰러진 포켓몬은 교체할 수 없습니다.");
            return 0; // 쓰러진 포켓몬은 교체할 수 없음
        }

        if (selected == player) {
            System.out.println("현재 전투 중인 포켓몬은 교체할 수 없습니다.");
            return 0; // 현재 전투 중인 포켓몬은 교체할 수 없음
        }

        player = selected; // 포켓몬 교체
        System.out.println("포켓몬을 " + player.getName() + "(으)로 교체했습니다!");
        return 1; // 교체 성공
    }

    // 전투 상태를 출력하는 메서드
    private void fightMonitor() {
        monitor.printBorder();
        System.out.println(player.getName() + " LV: " + player.getLevel()); // 플레이어 정보 출력
        System.out.println(getHpBar(player)); // 플레이어 체력바 출력
        System.out.println(enemy.getName() + " LV: " + enemy.getLevel()); // 적 정보 출력
        System.out.println(getHpBar(enemy)); // 적 체력바 출력
        monitor.printBorder();
    }
}
