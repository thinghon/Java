package poketgame;

public abstract class Pokemon {
    private String name;  // 포켓몬의 이름
    private int level;  // 포켓몬의 레벨
    private int exp;  // 포켓몬의 경험치
    private int currentExp;  // 현재 포켓몬의 경험치
    private int hp;  // 포켓몬의 현재 HP
    private int maxHp;  // 포켓몬의 최대 HP
    private int type;  // 포켓몬의 타입
    private int hpPerLevel;  // 레벨업마다 증가하는 HP
    private int expPerLevel;  // 레벨업마다 증가하는 EXP
    private Skill[] skills;  // 포켓몬의 스킬 목록
    private int skillCount;  // 현재 보유한 스킬의 수

    public Pokemon(String name, int level, int baseHp, int hpPerLevel, int baseExp, int expPerLevel, int type) {
        this.name = name;
        this.level = level;
        this.exp = baseExp + (level - 5) * expPerLevel;  // 초기 경험치 설정
        this.currentExp = 0;  // 현재 경험치는 0으로 초기화
        this.hp = baseHp + (level - 5) * hpPerLevel;  // 초기 HP 설정
        this.maxHp = hp;  // 최대 HP 설정
        this.type = type;  // 포켓몬 타입 설정
        this.skills = new Skill[4];  // 스킬은 최대 4개까지 보유
        this.skillCount = 0;  // 보유한 스킬의 수는 0으로 초기화
        this.hpPerLevel = hpPerLevel;  // 레벨업 시 증가하는 HP
        this.expPerLevel = expPerLevel;  // 레벨업 시 증가하는 EXP
    }

    // 스킬 추가
    public void addSkill(Skill skill) {
        if (skillCount < skills.length) {
            skills[skillCount++] = skill;  // 스킬을 추가하고 스킬 개수 증가
        } else {
            System.out.println("스킬을 더 이상 추가할 수 없습니다.");  // 스킬 추가 불가 메시지
        }
    }

    // 스킬 사용
    public void useSkill(int index, Pokemon p) {
        if (index >= 0 && index < skillCount) {
            skills[index].useSkill(p);  // 해당 스킬을 사용
        } else {
            System.out.println("잘못된 스킬 번호입니다.");  // 유효하지 않은 스킬 번호 메시지
        }
    }

    // 포켓몬 정보 출력
    public void printPokemonInfo() {
        System.out.println(name + " (Lv. " + level + ")");  // 포켓몬 이름과 레벨 출력
        System.out.println("HP: " + hp + "/" + maxHp);  // 현재 HP와 최대 HP 출력
        System.out.println("타입: " + getTypeAsString());  // 포켓몬 타입 출력
        System.out.println("스킬 목록:");  // 스킬 목록 출력
        for (int i = 0; i < skillCount; i++) {
            System.out.println((i + 1) + ". " + skills[i].getName());  // 각 스킬 이름 출력
        }
    }

    // 포켓몬 타입을 문자열로 반환
    private String getTypeAsString() {
        switch (type) {
        	case 0: return "노말";  // 타입 0은 노말
            case 1: return "불속성";  // 타입 1은 불속성
            case 2: return "물속성";  // 타입 2는 물속성
            case 3: return "풀속성";  // 타입 3은 풀속성
            case 4: return "전기";  // 타입 4는 전기
            default: return "알 수 없음";  // 알 수 없는 타입
        }
    }
    
    // 데미지 받기
    public void takeDamage(int damage) {
        hp -= damage;  // 받은 데미지만큼 HP 감소
        if (hp < 0) hp = 0;  // HP가 0 미만으로 떨어지지 않도록 설정
        System.out.println(name + "이(가) " + damage + "의 데미지를 입었습니다. (남은 HP: " + hp + ")");
    }

    // HP 회복
    public void heal() {
        this.hp = maxHp;  // HP를 최대 HP로 설정
        System.out.println(name + "이(가) 완전히 회복되었습니다. (HP: " + hp + "/" + maxHp + ")");
    }

    // 트레이너의 포켓몬 회복 (특수 기능)
    public void healTrainerPokemon() {
    	this.hp = maxHp;  // 트레이너의 포켓몬도 HP 회복
    }

    // 경험치 증가 및 레벨업
    public void increaseExp(int addExp) {
    	currentExp += addExp;  // 경험치 추가
    	if(currentExp >= exp) {  // 경험치가 필요 경험치 이상일 경우 레벨업
    		levelUp();
    	}
    	System.out.println(getExpBar());  // 경험치 바 출력
    }
    
    // 레벨업 처리
    public void levelUp() {
    	if(currentExp >= exp) {
    		currentExp = currentExp - exp;  // 레벨업 후 남은 경험치 설정
    	}
        level++;  // 레벨 증가
        exp += expPerLevel;  // 새로운 레벨에 필요한 경험치 증가
        maxHp += hpPerLevel;  // 레벨업 시 HP 증가
        hp = maxHp;  // 새로운 최대 HP로 HP 설정
        getExpBar();  // 경험치 바 업데이트
        System.out.println(name + "이(가) 레벨업 했습니다! Lv." + level + ", HP: " + hp + ", 다음 레벨까지 EXP: " + currentExp);
    }
    
    // 경험치 바 출력
    public String getExpBar() {
        int maxBarLength = 10;  // 경험치 바 길이
        double ratio = (double) currentExp / exp;  // 경험치 비율
        int filled = (int) Math.round(ratio * maxBarLength); 
        int empty = maxBarLength - filled;

        return getName() + " exp [" + "-".repeat(filled) + " ".repeat(empty) + "] (" + currentExp + "/" + exp + ")";
    }
    
    // 포켓몬 이름 반환
    public String getName() {
    	return name;
    }

	// 포켓몬 레벨 반환
	public int getLevel() {
		return level;
	}

	// 포켓몬의 현재 HP 반환
	public int getHp() {
		return hp;
	}
	
	// 포켓몬의 HP 설정
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	// 포켓몬의 최대 HP 반환
	public int getMaxHp() {
		return maxHp;
	}

	// 포켓몬의 최대 HP 설정
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	// 포켓몬의 타입 반환
	public int getType() {
		return type;
	}

	// 특정 스킬 번호에 해당하는 스킬 반환
	public Skill getSkills(int num) {
		return skills[num];
	}

	// 보유한 스킬의 수 반환
	public int getSkillCount() {
		return skillCount;
	}

	// 포켓몬의 경험치 반환
	public int getExp() {
		return exp;
	}
}


class Squirtle extends Pokemon {
    public Squirtle(int i) {
        super("꼬부기", i, 95, 7, 50, 10, 2); // 물속성
        addSkill(new Tackle());
        addSkill(new WaterGun());
    }
}

class Charmander extends Pokemon {
    public Charmander(int i) {
        super("파이리", i, 90, 6, 50, 12, 1); // 불속성
        addSkill(new Tackle());
        addSkill(new FlameThrower());
    }

}

class Bulbasaur extends Pokemon {
    public Bulbasaur(int i) {
        super("이상해씨", i, 100, 8, 50, 10, 3); // 풀속성
        addSkill(new Tackle());
        addSkill(new VineWhip());
    }

}

class Magikarp extends Pokemon {
    public Magikarp(int i) {
        super("잉어킹", i, 50, 4, 40, 8, 2); // 물속성, 낮은 스펙
        addSkill(new Splash());
    }
}

class Pikachu extends Pokemon {
    public Pikachu(int i) {
        super("피카츄", i, 90, 6, 60, 11, 4); // 전기
        addSkill(new Tackle());
        addSkill(new ThunderShock());
    }
}

class Vulpix extends Pokemon {
    public Vulpix(int i) {
        super("식스테일", i, 85, 6, 55, 10, 1); // 불속성
        addSkill(new Tackle());
        addSkill(new FlameThrower());
    }
}

class Psyduck extends Pokemon {
    public Psyduck(int i) {
        super("고라파덕", i, 80, 7, 40, 10, 2); // 물속성
        addSkill(new Tackle());
        addSkill(new WaterGun());
    }
}

class Magnemite extends Pokemon {
    public Magnemite(int i) {
        super("코일", 5, 80, 5, 60, 9, 4); // 전기
        addSkill(new Tackle());
        addSkill(new ThunderShock());
    }
}

class Oddish extends Pokemon {
    public Oddish(int i) {
        super("모다피", 5, 85, 6, 50, 10, 3); // 풀속성
        addSkill(new Tackle());
        addSkill(new VineWhip());
    }
}

class Ponyta extends Pokemon {
    public Ponyta(int i) {
        super("포니타", i, 90, 7, 50, 10, 1); // 불속성
        addSkill(new Tackle());
        addSkill(new FlameThrower());
    }
}

class Meowth extends Pokemon {
    public Meowth(int i) {
        super("나옹", i, 88, 6, 45, 9, 0); // 노말속성
        addSkill(new Tackle());
    }
}

class Growlithe extends Pokemon {
    public Growlithe(int i) {
        super("가디", i, 92, 7, 50, 10, 1); // 불속성
        addSkill(new Tackle());
        addSkill(new FlameThrower());
    }
}

class Poliwag extends Pokemon {
    public Poliwag(int i) {
        super("발챙이", i, 88, 7, 48, 10, 2); // 물속성
        addSkill(new Tackle());
        addSkill(new WaterGun());
    }
}

class Bellsprout extends Pokemon {
    public Bellsprout(int i) {
        super("뚜벅쵸", i, 85, 6, 45, 9, 3); // 풀속성
        addSkill(new Tackle());
        addSkill(new VineWhip());
    }
}

class Doduo extends Pokemon {
    public Doduo(int level) {
        super("두두", level, 85, 6, 45, 9, 0); // 노말 타입
        addSkill(new Tackle());
        addSkill(new DoublePeck());
    }
}

class Rattata extends Pokemon {
    public Rattata(int level) {
        super("꼬렛", level, 80, 6, 45, 9, 0); // 노말 타입
        addSkill(new Tackle());
    }
}

class Eevee extends Pokemon {
    public Eevee(int level) {
        super("이브이", level, 90, 7, 50, 10, 0); // 노말 타입
        addSkill(new Tackle());
        addSkill(new QuickAttack());
    }
}

