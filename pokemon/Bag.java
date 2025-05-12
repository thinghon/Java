package poketgame;

import java.util.ArrayList;

public class Bag {
    private ArrayList<Pokemon> pokemonList; // 포켓몬 리스트
    private ArrayList<Item> itemList;       // 아이템 리스트
    private int maxSize;                    // 가방의 최대 포켓몬 수
    private int currentSize;                // 현재 가방에 들어 있는 포켓몬 수
    private int money = 1500;               // 가방의 초기 돈

    public Bag() {
        maxSize = 3; // 가방에 최대 3마리 포켓몬을 담을 수 있음
        setPokemonTeam(new ArrayList<>()); // 포켓몬 팀 초기화
        itemList = new ArrayList<>();     // 아이템 리스트 초기화
        currentSize = 0;                 // 초기 포켓몬 수 0
    }

    // 포켓몬 추가 메서드
    public boolean addPokemon(Pokemon pokemon) {
        if (currentSize < maxSize) {
            getPokemonTeam().add(pokemon);  // 포켓몬 추가
            currentSize++;                  // 포켓몬 수 증가
            return true;
        } else {
            System.out.println("가방이 꽉 찼습니다. 포켓몬은 최대 3마리만 들고 다닐 수 있습니다.");
            return false; // 가방이 꽉 찼을 경우 false 반환
        }
    }

    // 아이템 추가 메서드
    public void addItem(Item item) {
        // 이미 아이템이 존재하면 수량만 증가
        for (Item i : itemList) {
            if (i.getName().equals(item.getName())) {
                i.setCount(i.getCount() + item.getCount());
                System.out.println(item.getName() + " 아이템 수량이 증가했습니다.");
                return;
            }
        }
        itemList.add(item); // 새로운 아이템 추가
        System.out.println(item.getName() + " 아이템이 가방에 추가되었습니다.");
    }

    // 포켓몬 삭제 메서드 (이름으로)
    public void removePokemon(String pokemonName) {
        for (int i = 0; i < getPokemonTeam().size(); i++) {
            if (getPokemonTeam().get(i).getName().equals(pokemonName)) {
                getPokemonTeam().remove(i); // 해당 포켓몬 삭제
                currentSize--;              // 포켓몬 수 감소
                System.out.println(pokemonName + "이/가 가방에서 제거되었습니다.");
                return;
            }
        }
        System.out.println("가방에 해당 포켓몬이 없습니다.");
    }
    
    // 포켓몬 삭제 메서드 (인덱스로)
    public void removePokemon(int index) {
        if (index >= 0 && index < pokemonList.size()) {
            pokemonList.remove(index); // 포켓몬 리스트에서 해당 인덱스의 포켓몬 삭제
        }
    }
    
    // 아이템 삭제 메서드 (이름으로)
    public void removeItem(String itemName) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getName().equals(itemName)) {
                itemList.remove(i); // 해당 아이템 삭제
                System.out.println(itemName + " 아이템이 가방에서 제거되었습니다.");
                return;
            }
        }
        System.out.println("가방에 해당 아이템이 없습니다.");
    }
    
    // 아이템 삭제 메서드 (인덱스로)
    public void removeItem(int index) {
        if (index >= 0 && index < itemList.size()) {
            Item item = itemList.get(index); // 해당 인덱스의 아이템 가져오기
            if (item.getCount() <= 1) {
                itemList.remove(index); // 아이템 수량이 1이면 리스트에서 삭제
            } else {
                item.setCount(item.getCount() - 1); // 수량 감소
            }
        }
    }

    // 가방에 있는 포켓몬 출력
    public void printPokemonList() {
        if (getPokemonTeam().isEmpty()) {
            System.out.println("가방에 포켓몬이 없습니다.");
        } else {
            System.out.println("가방에 있는 포켓몬들:");
            for (Pokemon pokemon : getPokemonTeam()) {
                pokemon.printPokemonInfo(); // 포켓몬 정보 출력
                System.out.println();
            }
        }
    }

    // 가방에 있는 아이템 출력
    public void printItemList() {
        if (itemList.isEmpty()) {
            System.out.println("가방에 아이템이 없습니다.");
        } else {
            System.out.println("가방에 있는 아이템들:");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.print((i + 1) + ". ");
                itemList.get(i).printItemInfo(); // 아이템 정보 출력
                System.out.println();
            }
        }
    }
    
    // 아이템 사용 메서드
    public void useItem(int index, Pokemon target) {
        if (index < 0 || index >= itemList.size()) {
            System.out.println("잘못된 아이템 번호입니다.");
            return;
        }

        Item item = itemList.get(index); // 아이템 가져오기
        System.out.println(item.getName() + "을(를) " + target.getName() + "에게 사용했습니다.");
        item.use(target); // 아이템 사용

        // 사용 후 아이템 수량 업데이트
        if (item.getCount() <= 1) {
            itemList.remove(index); // 아이템 수량이 1이면 리스트에서 삭제
        } else {
            item.setCount(item.getCount() - 1); // 수량 감소
        }
    }

    // 포켓몬 반환
    public Pokemon getPokemon(int index) {
        return pokemonList.get(index); // 포켓몬 반환
    }

    // 포켓몬 수 반환
    public int getPokemonCount() {
        return pokemonList.size(); // 포켓몬 수 반환
    }

    // 포켓몬 팀 반환
    public ArrayList<Pokemon> getPokemonTeam() {
        return pokemonList; // 포켓몬 팀 반환
    }

    // 포켓몬 팀 설정
    public void setPokemonTeam(ArrayList<Pokemon> pokemonTeam) {
        this.pokemonList = pokemonTeam;
    }

    // 가방의 돈 반환
    public int getMoney() {
        return money;
    }

    // 가방의 돈 추가
    public void addMoney(int amount) {
        money += amount;
    }

    // 가방에서 돈 차감
    public void useMoney(int amount) {
        money -= amount;
    }

    // 아이템 리스트 반환
    public ArrayList<Item> getItems() {
        return itemList; // 아이템 리스트 반환
    }

    // 특정 아이템 반환
    public Item getItem(int index) {
        if (index >= 0 && index < itemList.size()) {
            return itemList.get(index); // 인덱스에 해당하는 아이템 반환
        }
        return null; // 잘못된 인덱스일 경우 null 반환
    }
    
    // 아이템 수 반환
    public int getItemCount() {
        return itemList.size(); // 아이템 수 반환
    }
}
