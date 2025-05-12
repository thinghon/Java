package poketgame;

import java.util.Random;

// Item 클래스: 아이템을 관리하는 추상 클래스
public abstract class Item {
    private String name;       // 아이템의 이름
    private int buyPrice;      // 아이템의 구입 가격
    private int sellPrice;     // 아이템의 판매 가격
    private int count;         // 아이템의 수량

    // 생성자: 아이템의 이름, 구입 가격, 판매 가격, 수량 초기화
    public Item(String name, int buyPrice, int sellPrice, int count) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.count = count;
    }

    // 기본적인 use 메서드
    public void use(Pokemon target) {
        System.out.println("이 아이템은 이렇게 사용할 수 없습니다.");
    }

    // 아이템의 이름을 반환
    public String getName() {
        return name;
    }

    // 아이템의 구입 가격을 반환
    public int getBuyPrice() {
        return buyPrice;
    }

    // 아이템의 판매 가격을 반환
    public int getSellPrice() {
        return sellPrice;
    }

    // 아이템의 수량을 반환
    public int getCount() {
        return count;
    }

    // 아이템 수량을 증가
    public void addCount(int amount) {
        count += amount;
    }

    // 아이템을 하나 사용할 수 있는지 여부 확인
    public boolean useOne() {
        return count > 0;
    }

    // 아이템의 수량을 설정
    public void setCount(int count) {
        this.count = count;
    }

    // 아이템 정보를 출력
    public void printItemInfo() {
        System.out.printf("이름: %s | 수량: %d ", name, count);
    }

    // 아이템을 복제하여 반환하는 메서드 (추상 메서드)
    public abstract Item copy();
}

class Potion extends Item {
    public Potion(int count) {
        super("상처약", 300, 200, count);
    }

    // 상처약을 사용하여 포켓몬의 HP를 회복
    @Override
    public void use(Pokemon target) {
        if (target.getHp() >= target.getMaxHp()) {
            System.out.println(target.getName() + "의 HP는 이미 가득 찼습니다. 아이템을 사용하지 않았습니다.");
            return; 
        }

        if (useOne()) {
            int healAmount = 50;  // 회복량 설정
            int newHp = Math.min(target.getHp() + healAmount, target.getMaxHp()); // HP가 최대치를 넘지 않도록 처리
            target.setHp(newHp);
            System.out.println(target.getName() + "의 HP가 " + healAmount + "만큼 회복되었습니다! (현재 HP: " + newHp + "/" + target.getMaxHp() + ")");
        } else {
            System.out.println("상처약이 없습니다.");
        }
    }

    @Override
    public Item copy() {
        return new Potion(0);
    }
}

class MonsterBall extends Item {
    public MonsterBall(int count) {
        super("몬스터볼", 200, 150, count);
    }

    @Override
    public void use(Pokemon target) {
        System.out.println("몬스터볼은 야생 포켓몬에게만 사용할 수 없습니다.");
    }

    // 몬스터볼을 던져서 야생 포켓몬을 포획할 확률을 계산
    public boolean tryCatch(Pokemon wild) {
        if (!useOne()) {
            System.out.println("몬스터볼이 없습니다.");
            return false; // 몬스터볼이 없으면 포획 실패
        }

        int hpRatio = (int)((1.0 - (double)wild.getHp() / wild.getMaxHp()) * 100); // HP가 낮을수록 포획 확률 증가
        int catchChance = 30 + hpRatio;  // 포획 확률 계산
        int rand = new Random().nextInt(100);

        System.out.println("몬스터볼을 던졌습니다...");
        if (rand < catchChance) {
            return true;  // 포획 성공
        } else {
            return false; // 포획 실패
        }
    }

    @Override
    public Item copy() {
        return new MonsterBall(0); 
    }
}

class StrangeCandy extends Item {
    public StrangeCandy(int count) {
        super("이상한 사탕", 500, 350, count);
    }

    // 이상한 사탕을 사용하여 포켓몬의 레벨을 올림
    @Override
    public void use(Pokemon target) {
        if (useOne()) {
            target.levelUp();  // 포켓몬의 레벨을 올림
        } else {
            System.out.println("이상한 사탕이 없습니다.");
        }
    }

    @Override
    public Item copy() {
        return new StrangeCandy(0); 
    }
}

class Revive extends Item {
    public Revive(int count) {
        super("기력의 조각", 1500, 1000, count);
    }

    // 기력의 조각을 사용하여 쓰러진 포켓몬을 부활시킴
    @Override
    public void use(Pokemon target) {
        if (target.getHp() > 0) {
            System.out.println(target.getName() + "은(는) 아직 쓰러지지 않았습니다. 기력의 조각을 사용할 수 없습니다.");
            return; // 쓰러지지 않은 포켓몬에게는 아이템을 사용할 수 없음
        }

        if (useOne()) {
            int reviveHp = target.getMaxHp() / 2;  // 부활 후 HP는 최대 HP의 절반
            target.setHp(reviveHp);
            System.out.println(target.getName() + "이(가) 기력의 조각으로 부활했습니다! (HP: " + reviveHp + "/" + target.getMaxHp() + ")");
        } else {
            System.out.println("기력의 조각이 없습니다.");
        }
    }

    @Override
    public Item copy() {
        return new Revive(0);  
    }
}
