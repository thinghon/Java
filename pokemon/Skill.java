package poketgame;

public class Skill {
    private String name;       // 스킬 이름
    private int damage;        // 스킬 데미지
    private double accuracy;   // 스킬 명중률
    private int type;          // 스킬 속성 타입

    // 스킬 생성자
    public Skill(String name, int damage, double accuracy, int type) {
        this.name = name;
        this.damage = damage;
        this.accuracy = accuracy;
        this.type = type;
    }

    // 스킬 이름을 반환하는 메서드
    public String getName() {
        return name;
    }

    // 스킬 데미지를 반환하는 메서드
    public int getDamage() {
        return damage;
    }

    // 스킬 명중률을 반환하는 메서드
    public double getAccuracy() {
        return accuracy;
    }

    // 스킬 속성 타입을 반환하는 메서드
    public int getType() {
        return type;
    }

    // 스킬 사용 시 대상에게 데미지를 주는 메서드
    public void useSkill(Pokemon target) {
        System.out.println(name + " 스킬을 사용합니다!");
        if (Math.random() <= accuracy) { // 명중률에 따라 성공 여부 결정
            System.out.println(target.getName() + "에게 " + damage + "만큼 피해를 입혔습니다.");
            target.takeDamage(damage); // 대상에게 데미지 적용
        } else {
            System.out.println("스킬이 빗나갔습니다!");
        }
    }
}

class FlameThrower extends Skill {
    public FlameThrower() {
        super("불꽃세례", 40, 95.0, 1);
    }
}

class WaterGun extends Skill {
    public WaterGun() {
        super("물대포", 35, 100.0, 2); 
    }
}

class VineWhip extends Skill {
    public VineWhip() {
        super("덩쿨채찍", 30, 100.0, 3); 
    }
}

class Tackle extends Skill {
    public Tackle() {
        super("몸통박치기", 20, 100, 0);
    }
}

class ThunderShock extends Skill {
    public ThunderShock() {
        super("전기쇼크", 30, 100.0, 4); 
    }
}

class Splash extends Skill {
    public Splash() {
        super("튀어오르기", 0, 100.0, 0);
    }

    @Override
    public void useSkill(Pokemon target) {
        System.out.println("아무 일도 일어나지 않았다..."); 
    }
}

class DoublePeck extends Skill {
    public DoublePeck() {
        super("두번찍기", 25, 95.0, 0); 
    }
}

class QuickAttack extends Skill {
    public QuickAttack() {
        super("전광석화", 30, 100.0, 0); 
    }
}
