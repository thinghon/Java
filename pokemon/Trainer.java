package poketgame;

import java.util.ArrayList;

class Trainer {
    private String name;              // 트레이너 이름
    private int money;                // 트레이너의 돈
    private int pokemoncount;         // 트레이너가 가진 포켓몬 수
    private boolean defeated = false; // 트레이너가 패배했는지 여부
    private ArrayList<Pokemon> pokemonList; // 트레이너가 가진 포켓몬 리스트

    // 트레이너 이름만을 입력받는 생성자
    public Trainer(String name) {
        this.name = name;
        this.pokemonList = new ArrayList<>();
    }

    // 트레이너 이름과 돈, 초기 포켓몬들을 입력받는 생성자
    public Trainer(String name, int money, Pokemon... initialPokemons) {
        this.name = name;
        this.money = money;
        this.pokemonList = new ArrayList<>();
        // 최대 3마리의 포켓몬만 초기화
        for (Pokemon p : initialPokemons) {
            if (pokemonList.size() < 3) {
                pokemonList.add(p);  // 포켓몬 추가
                pokemoncount++;      // 포켓몬 수 증가
            } else {
                break;  // 최대 3마리까지 추가 후 중지
            }
        }
    }

    // 포켓몬을 추가하는 메서드 (최대 3마리까지)
    public boolean addPokemon(Pokemon pokemon) {
        if (pokemonList.size() < 3) {  // 3마리 이하일 때만 추가 가능
            pokemonList.add(pokemon);
            return true;  // 성공적으로 추가
        }
        return false;  // 추가할 수 없으면 false 반환
    }
    
    // 인덱스를 통해 포켓몬을 반환하는 메서드
    public Pokemon getPokemon(int index) {
        return pokemonList.get(index);
    }
    
    // 포켓몬 리스트를 반환하는 메서드
    public ArrayList<Pokemon> getPokemons() {
        return pokemonList;
    }

    // 트레이너 이름을 반환하는 메서드
    public String getName() {
        return name;
    }

    // 배틀 시작 메시지를 출력하는 메서드
    public void battle() {
        System.out.println(name + " 트레이너가 배틀을 걸어왔다!");
    }

    // 트레이너가 가진 돈을 반환하는 메서드
	public int getMoney() {
		return money;
	}

	// 트레이너가 가진 포켓몬 수를 반환하는 메서드
	public int getPokemonCount() {
		return pokemoncount;
	}
	
	// 트레이너가 패배했는지 여부를 반환하는 메서드
	public boolean getDefeated() {
	    return defeated;
	}

	// 트레이너의 패배 여부를 설정하는 메서드
	public void setDefeated(boolean defeated) {
	    this.defeated = defeated;
	}
}
