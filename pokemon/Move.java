package poketgame;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Move {
    private int playerX = 11;  // 플레이어의 X 좌표 초기값
    private int playerY = 11;  // 플레이어의 Y 좌표 초기값
    static int map_num = 0;  // 맵 번호
    private boolean victory;  // 전투 승리 여부

    private Map map;  // 맵 객체
    private Monitor monitor;  // 모니터 객체
    private PokemonCenter pokemonCenter;  // 포켓몬 센터 객체
    private Shop shop;  // 상점 객체
    private Bag bag;  // 가방 객체

    private List<Class<? extends Pokemon>> wildPokemonClasses = new ArrayList<>();
    private Random random = new Random();

    private Trainer trainer1 = new Trainer("낚시꾼 철수", 800, new Magikarp(5), new Magikarp(6));
    private Trainer trainer2 = new Trainer("등산가 진수", 1200, new Bellsprout(6), new Doduo(7));
    private Trainer trainer3 = new Trainer("배틀걸 지현", 1200, new Bellsprout(6), new Eevee(7));
    private Trainer trainer4 = new Trainer("신입 트레이너 지훈", 1000, new Magnemite(8));
    private Trainer trainer5 = new Trainer("중급 트레이너 수아", 1500, new Eevee(8), new Pikachu(8));
    private Trainer trainer6 = new Trainer("쌍둥이 트레이너 태윤", 1800, new Doduo(9), new Doduo(10));
    private Trainer boss = new Trainer("체육관 관장 유리", 2200, new Meowth(10), new Vulpix(10), new Ponyta(12));

    public Move(Map map, Monitor monitor, PokemonCenter pokemonCenter, Shop shop, Bag bag) {
        this.map = map;
        this.monitor = monitor;
        this.pokemonCenter = pokemonCenter;
        this.shop = shop;
        this.bag = bag;
        map.setMap(map_num, playerY, playerX, '*');  // 맵에 플레이어의 초기 위치를 표시
        
        // 야생 포켓몬 클래스 목록에 포켓몬들 추가
        wildPokemonClasses.add(Magikarp.class);
        wildPokemonClasses.add(Psyduck.class);
        wildPokemonClasses.add(Oddish.class);
        wildPokemonClasses.add(Rattata.class);
        wildPokemonClasses.add(Magnemite.class);
        wildPokemonClasses.add(Bellsprout.class);
        wildPokemonClasses.add(Ponyta.class);
        wildPokemonClasses.add(Vulpix.class);
        wildPokemonClasses.add(Doduo.class);
        wildPokemonClasses.add(Meowth.class);
    }

    // 플레이어가 이동하는 메서드
    public boolean move(char direction, DoctorO doctorO, PokemonCenter pokemonCenter, Bag bag) {
        int tempX = playerX;  // 이동 전 플레이어의 X 좌표
        int tempY = playerY;  // 이동 전 플레이어의 Y 좌표
        victory = false;  // 초기 승리 여부는 false로 설정
        
        // 입력된 방향에 따라 플레이어의 임시 좌표를 갱신
        switch (direction) {
            case 'w': tempY--; break;  // 'w'는 위로 이동
            case 'a': tempX -= 2; break;  // 'a'는 왼쪽으로 이동
            case 's': tempY++; break;  // 's'는 아래로 이동
            case 'd': tempX += 2; break;  // 'd'는 오른쪽으로 이동
            default: return false;  // 유효하지 않은 입력이면 false 반환
        }

        char nextTile = map.getTile(map_num, tempY, tempX);  // 이동 후 해당 위치의 타일을 가져옴
        
        if (nextTile == ' ') { // 만약 이동할 칸이 빈 공간이라면
            if(direction == 'd' && map.getTile(map_num, tempY, tempX-1) == '^') { 
                return false;
            }
            else if(direction == 'a' && map.getTile(map_num, tempY, tempX+1) == '^') {
                return false;
            }

            // 현재 위치의 칸을 비우고
            map.setMap(map_num, playerY, playerX, ' '); 
            // 플레이어의 위치를 새로 업데이트
            playerX = tempX;
            playerY = tempY;
            // 새로운 위치에 '*' 표시
            map.setMap(map_num, playerY, playerX, '*');

            // 특정 위치에서 트레이너1과 싸울 준비가 되었을 때
            if(map_num == 1 && playerY == 2 && playerX == 19) {
                // 트레이너1이 아직 패배하지 않았다면
                if(!trainer1.getDefeated()) {
                    map.setMap(map_num, 2, 21, '!');
                    map.printMap(map_num);
                    map.setMap(map_num, 2, 21, ' ');

                    map.setMap(map_num, playerY, playerX + 1, '!');
                    map.printMap(map_num);

                    // 전투 진행
                    Battle battle = new Battle(bag, trainer1, monitor);
                    victory = battle.battleType2();
                    // 전투 승리 시
                    if(victory) {
                        trainer1.setDefeated(true); // 트레이너1 패배 처리
                        map.setMap(map_num, playerY, playerX + 1, ' ');
                    } else { // 전투 패배 시
                        trainerReset(trainer1); // 트레이너1 리셋
                        map.setMap(map_num, playerY, playerX + 1, ' '); 
                        map.setMap(map_num, 2, 21, 'X'); 
                        goPokeCenter(); 
                    }
                }
            } 
            // 트레이너2와의 전투 이벤트 처리
            else if((map_num == 2 && playerY == 8 && playerX == 9) || 
                    (map_num == 2 && (playerY == 9 || playerY == 10) && playerX == 11)) {
                if(!trainer2.getDefeated()) {
                    map.setMap(map_num, 8, 11, '!');
                    map.printMap(map_num);

                    Battle battle = new Battle(bag, trainer2, monitor);
                    victory = battle.battleType2();
                    if(victory) {
                        trainer2.setDefeated(true);
                        map.setMap(map_num, 8, 11, ' ');
                    } else {
                        trainerReset(trainer2);
                        map.setMap(map_num, playerY, playerX, ' ');
                        map.setMap(map_num, 8, 11, 'X');
                        goPokeCenter();
                    }
                }
            } 
            // 트레이너3와의 전투 이벤트 처리
            else if(map_num == 2 && playerY == 8 && playerX == 17) {
                if(!trainer3.getDefeated()) {
                    map.setMap(map_num, 8, 19, '!');
                    map.printMap(map_num);

                    Battle battle = new Battle(bag, trainer3, monitor);
                    victory = battle.battleType2();
                    if(victory) {
                        trainer3.setDefeated(true);
                        map.setMap(map_num, 8, 19, ' ');
                    } else {
                        trainerReset(trainer3);
                        map.setMap(map_num, playerY, playerX, ' ');
                        map.setMap(map_num, 8, 19, 'X');
                        goPokeCenter();
                    }
                }
            } 
            // 트레이너4와의 전투 이벤트 처리
            else if(map_num == 3 && playerY == 5 && playerX == 9) {
                if(!trainer4.getDefeated()) {
                    map.setMap(map_num, 5, 7, '!');
                    map.printMap(map_num);

                    Battle battle = new Battle(bag, trainer4, monitor);
                    victory = battle.battleType2();
                    if(victory) {
                        trainer4.setDefeated(true);
                        map.setMap(map_num, 5, 7, ' ');
                    } else {
                        trainerReset(trainer4);
                        map.setMap(map_num, playerY, playerX, ' ');
                        map.setMap(map_num, 5, 7, 'X');
                        goPokeCenter();
                    }
                }
            } 
            // 트레이너5와의 전투 이벤트 처리
            else if(map_num == 3 && (playerY == 6 || playerY == 7) && playerX == 13) {
                if(!trainer5.getDefeated()) {
                    map.setMap(map_num, 5, 13, '!');
                    map.printMap(map_num);

                    Battle battle = new Battle(bag, trainer5, monitor);
                    victory = battle.battleType2();
                    if(victory) {
                        trainer5.setDefeated(true);
                        map.setMap(map_num, 5, 13, ' ');
                    } else {
                        trainerReset(trainer5);
                        map.setMap(map_num, playerY, playerX, ' ');
                        map.setMap(map_num, 5, 13, 'X');
                        goPokeCenter();
                    }
                }
            } 
            // 트레이너6과의 전투 이벤트 처리
            else if(map_num == 3 && (playerY == 2 || playerY == 3 || playerY == 9 || playerY == 10) && playerX == 17) {
                if(!trainer6.getDefeated()) {
                    map.setMap(map_num, 6, 17, '!');
                    map.printMap(map_num);

                    Battle battle = new Battle(bag, trainer6, monitor);
                    victory = battle.battleType2();
                    if(victory) {
                        trainer6.setDefeated(true);
                        map.setMap(map_num, 6, 17, ' ');
                    } else {
                        trainerReset(trainer6);
                        map.setMap(map_num, playerY, playerX, ' ');
                        map.setMap(map_num, 6, 17, 'X');
                        goPokeCenter();
                    }
                }
            }
            // 트레이너와의 전투가 아니라 wild Pokemon과의 싸움 처리
            else {
                if (map_num == 1) {
                    double encounterRate = 0.1; 
                    // 야생 포켓몬이 등장할 확률
                    if (Math.random() < encounterRate && !wildPokemonClasses.isEmpty()) {
                        int level = random.nextInt(2) + 3; 
                        int index = random.nextInt(1) + 1; 

                        victory = wildPokemonFight(index, level); 
                        if(!victory) {
                            map.setMap(map_num, playerY, playerX, ' '); 
                            goPokeCenter(); 
                        }
                    }
                } 
                else if(map_num == 2) {
                    double encounterRate = 0.1; 
                    if (Math.random() < encounterRate && !wildPokemonClasses.isEmpty()) {
                        int level = random.nextInt(3) + 4; 
                        int index = random.nextInt(2) + 2;

                        victory = wildPokemonFight(index, level);
                        if(!victory) {
                            map.setMap(map_num, playerY, playerX, ' '); 
                            goPokeCenter(); 
                        }
                    }
                } 
                else if(map_num == 3) {
                    double encounterRate = 0.1; 
                    if (Math.random() < encounterRate && !wildPokemonClasses.isEmpty()) {
                        int level = random.nextInt(3) + 5; 
                        int index = random.nextInt(2) + 2; 

                        victory = wildPokemonFight(index, level);
                        if(!victory) {
                            map.setMap(map_num, playerY, playerX, ' '); 
                            goPokeCenter(); 
                        }
                    }
                }
                return true;
            }
        } else if (nextTile == 'O') {  // 타일이 'O'일 경우
            doctorO.choosePokemon();  // DoctorO 객체에서 포켓몬을 선택하도록 처리
            monitor.printBorder();
            return false;  // 더 이상 진행하지 않고 종료
        } else if (nextTile == 'C') {  // 타일이 'C'일 경우
            pokemonCenter.showMenu();  // PokemonCenter의 메뉴를 표시
            monitor.printBorder();
            return false;  // 더 이상 진행하지 않고 종료
        } else if (nextTile == 'S') {  // 타일이 'S'일 경우
            shop.showMenu();  // 상점의 메뉴를 표시
            return false;  // 더 이상 진행하지 않고 종료
        } else if (nextTile == 'I') {  // 타일이 'I'일 경우
            map.setMap(map_num, playerY, playerX, ' '); 
            playerX = tempX;
            playerY = tempY; 
            map.setMap(map_num, playerY, playerX, '*');

            Item item = null;  // 아이템 변수 선언
            // 맵 번호와 플레이어 위치에 따라 아이템 설정
            if (map_num == 0 && playerY == 8 && playerX == 19) {
                item = new Potion(1);  // 포션 아이템 생성
            } else if (map_num == 1 && playerY == 1 && playerX == 21) {
                item = new StrangeCandy(1);  // 이상한 캔디 아이템 생성
            } else if (map_num == 2 && playerY == 2 && playerX == 11) {
                item = new Revive(1);  // 부활 아이템 생성
            } else {
                return false;  // 해당 위치가 아닌 경우 처리 종료
            }

            // 아이템이 생성된 경우
            if (item != null) {
                monitor.printBorder(); 
                bag.addItem(item);  // 가방에 아이템 추가
                monitor.printBoxedMessage("땅에서 " + item.getName() + "을(를) 주웠다!");  // 아이템 획득 메시지 출력
                monitor.printBorder(); 
            }

            return false;
        } else if (nextTile == 'T') {  // 'T'는 맵 간 이동 포털
            if (bag.getPokemonCount() == 0) {  // 포켓몬이 하나도 없으면 이동 불가
                monitor.printBorder();
                monitor.printBoxedMessage("포켓몬을 보유하고 있지 않습니다. 포켓몬을 얻은 후 다시 시도하세요.",
                		"오박사(O)님 한테 찾아가면 포켓몬을 받을수 있습니다.");
                monitor.printBorder();
                return false;
            }

            // 현재 위치 초기화
            map.setMap(map_num, playerY, playerX, ' ');
            playerX = tempX;
            playerY = tempY;
            map.setMap(map_num, playerY, playerX, '*');  // 새 위치에 플레이어 표시

            // 맵 0에서 맵 1로 이동
            if (map_num == 0 && playerY == 3 && playerX == 1) {
                map.setMap(map_num, playerY, playerX, 'T');
                map_num = 1;
                playerX = 11;
                playerY = 10;
                map.setMap(map_num, playerY, playerX, '*');
                return false;
            } 
            // 맵 0에서 맵 2로 이동
            else if (map_num == 0 && playerY == 3 && playerX == 21) {
                map.setMap(map_num, playerY, playerX, 'T');
                map_num = 2;
                playerX = 3;
                playerY = 10;
                map.setMap(map_num, playerY, playerX, '*');
                return false;
            } 
            // 맵 1에서 맵 0으로 복귀
            else if (map_num == 1 && playerY == 11 && playerX == 11) {
                map.setMap(map_num, playerY, playerX, 'T');
                map_num = 0;
                playerX = 3;
                playerY = 3;
                map.setMap(map_num, playerY, playerX, '*');
                return false;
            } 
            // 맵 2에서 맵 0으로 복귀
            else if (map_num == 2 && playerY == 10 && playerX == 1) {
                map.setMap(map_num, playerY, playerX, 'T');
                map_num = 0;
                playerX = 19;
                playerY = 3;
                map.setMap(map_num, playerY, playerX, '*');
                return false;
            } 
            // 맵 2에서 맵 3으로 이동
            else if (map_num == 2 && playerY == 2 && playerX == 19) {
                map.setMap(map_num, playerY, playerX, 'T');
                map_num = 3;
                playerX = 3;
                playerY = 10;
                map.setMap(map_num, playerY, playerX, '*');
                return false;
            } 
            // 맵 3에서 맵 2로 복귀
            else if (map_num == 3 && playerY == 11 && playerX == 3) {
                map.setMap(map_num, playerY, playerX, 'T');
                map_num = 2;
                playerX = 19;
                playerY = 3;
                map.setMap(map_num, playerY, playerX, '*');
                return false;
            }
            return false;

        } else if(nextTile == 'X') {  // 'X'는 트레이너 전투 타일
            map.setMap(map_num, playerY, playerX, ' ');
            playerX = tempX;
            playerY = tempY;
            map.setMap(map_num, playerY, playerX, '*');

            // 트레이너1과 전투
            if(map_num == 1 && playerY == 2 && playerX == 21) {
                if(!trainer1.getDefeated()) {
                    Battle battle = new Battle(bag, trainer1, monitor);
                    victory = battle.battleType2();  
                    if(victory) {
                        trainer1.setDefeated(true);  // 승리 시 트레이너 상태 갱신
                    } else {
                    	trainerReset(trainer1);  // 패배 시 트레이너 초기화
                        map.setMap(map_num, 2, 21, 'X');
                        goPokeCenter();  
                    }
                }

            // 트레이너2와 전투
            } else if(map_num == 2 && playerY == 8 && playerX == 11) {
            	if(!trainer2.getDefeated()) {
                    Battle battle = new Battle(bag, trainer2, monitor);
                    victory = battle.battleType2();
                    if(victory) {
                        trainer2.setDefeated(true);
                    } else {
                    	trainerReset(trainer2);
                        map.setMap(map_num, 8, 11, 'X');
                        goPokeCenter();
                    }
                }

            // 트레이너3과 전투
            } else if(map_num == 2 && playerY == 8 && playerX == 19) {
            	if(!trainer3.getDefeated()) {
                    Battle battle = new Battle(bag, trainer3, monitor);
                    victory = battle.battleType2();
                    if(victory) {
                        trainer3.setDefeated(true);
                    } else {
                    	trainerReset(trainer3);
                        map.setMap(map_num, 8, 19, 'X');
                        goPokeCenter();
                    }
                }

            // 트레이너4와 전투
            } else if(map_num == 3 && playerY == 5 && playerX == 7) {
            	if(!trainer4.getDefeated()) {
                    Battle battle = new Battle(bag, trainer4, monitor);
                    victory = battle.battleType2();
                    if(victory) {
                        trainer4.setDefeated(true);
                        map.setMap(map_num, 8, 19, ' ');  
                    } else {
                    	trainerReset(trainer4);
                    	map.setMap(map_num, playerY, playerX, ' ');
                        map.setMap(map_num, 5, 7, 'X');
                        goPokeCenter();
                    }
                }
            }
            return false;

        } else if (nextTile == 'B') {  // 'B'는 체육관 관장 타일
        	if(!boss.getDefeated()) {
                Battle battle = new Battle(bag, boss, monitor);
                victory = battle.battleType2();
                if(victory) {
                    boss.setDefeated(true);
                    ending();  // 엔딩 호출
                } else {
                	trainerReset(boss);  // 패배 시 초기화
                }
            } else {
            	monitor.printBorder();
            	System.out.println("이미 체육관 관장과의 승부에서 승리했다.");
            	monitor.printBorder();
            }
            return false;  
        }
        return false;
    }

    public int getCh_x() {
        return playerX;
    }

    public int getCh_y() {
        return playerY;
    }

    public int getMapNum() {
        return map_num;
    }

    // 포켓몬 센터로 이동시켜 포켓몬을 모두 회복시키는 메서드
    private void goPokeCenter() {
        map_num = 0;  
        playerX = 7;  
        playerY = 3;
        map.setMap(map_num, playerY, playerX, '*');  
        pokemonCenter.healAllPokemon();  // 가방에 있는 모든 포켓몬 회복
        monitor.printBorder(); 
    }

    // 야생 포켓몬과 전투를 수행하는 메서드
    private boolean wildPokemonFight(int index, int level) {
        try {
            // 야생 포켓몬 클래스를 가져와 생성자 호출
            Class<? extends Pokemon> clazz = wildPokemonClasses.get(index);
            Constructor<? extends Pokemon> constructor = clazz.getConstructor(int.class);
            Pokemon wildPokemon = constructor.newInstance(level);  // 포켓몬 인스턴스 생성

            monitor.printBorder();
            monitor.printBoxedMessage("야생의 " + wildPokemon.getName() + "이(가) 나타났다! [Lv. " + level + "]");

            // 야생 포켓몬과의 전투 시작
            Battle wildBattle = new Battle(bag, wildPokemon, monitor);
            boolean win = wildBattle.battleType1();

            if (!win) {
                return false; 
            } else {
                return true; 
            }

        } catch (Exception e) {
            e.printStackTrace();  // 예외 출력
        }
        return false;  // 예외 발생 시 false 반환
    }

    // 트레이너 포켓몬을 모두 회복시키는 메서드
    private void trainerReset(Trainer trainer) {
        for(int i = 0 ; i < trainer.getPokemonCount(); i++) {
            trainer.getPokemon(i).healTrainerPokemon();  // 각 포켓몬 회복
        }
    }

    // 엔딩 메시지를 출력하는 메서드
    void ending() {
        String[] messages = {
            "\n===================================",
            "       🎮 GAME CLEAR 🎮",
            "===================================",
            "",
            "모든 포켓몬이 당신의 여정을 기억할 것입니다.",
            "",
            "게임을 플레이해 주셔서 감사합니다.",
            "",
            "제작자: 박소호",
            "ⓒ 2025. Pokemon is a trademark of Nintendo.",
            "",
            "\n====== THE END ======"
        };

        for (String msg : messages) {
            System.out.println(msg);  // 한 줄씩 출력
            try {
                Thread.sleep(1200); // 1.2초 간 지연
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  
            }
        }

        System.out.println("\n게임을 종료합니다. 수고하셨습니다!");  // 종료 메시지 출력
    }
}
