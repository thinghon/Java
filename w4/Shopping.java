package w4;

import java.util.ArrayList;
import java.util.Scanner;

class Item{
	private String name;
	private int price;
	
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setPrice(int price) {this.price = price;}
	public int getPrice() {return price;}
	public String toString() {
		return String.format("이름: %s, 가격: %d", name, price);
	}
}
class ShoppingCart{
	private ArrayList<Item>items;
	
	public ShoppingCart() {
		this.items = new ArrayList<>();
	}
	public void addItem(Item item) {
		items.add(item);
	}
	public void removeItem(String name) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals(name)) {
				System.out.println("상품 삭제: " + items.get(i).getName());
				items.remove(i);
				return;
			}
		}
		System.out.println("해당 상품은 존재하지 않습니다.");
	}
	public void sumPrice() {
		int price = 0;
		for(Item item : items) {
			price += item.getPrice();
		}
		System.out.println("상품의 총 가격: " + price);
	}
	public void removeAll() {
		items.clear();
		System.out.println("삭제 완료!");
	}
	public void viewItemList() {
		for(Item item : items) {
			System.out.println(item);
		}
	}
}
public class Shopping {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ShoppingCart shopcart = new ShoppingCart();
		
		while(true) {
			System.out.println("1. 상품 추가");
	        System.out.println("2. 상품 삭제");
	        System.out.println("3. 상품 총 가격");
	        System.out.println("4. 상품 목록 전체 삭제");
	        System.out.println("5. 상품 목록");
	        System.out.println("6. 종료");
	        System.out.print("선택: ");
	        int choice = sc.nextInt();
	        sc.nextLine(); 
	        
	        if (choice == 1) {
	            Item item = new Item();
	            System.out.print("상품이름: ");
	            item.setName(sc.nextLine());
	            System.out.print("가격: ");
	            item.setPrice(sc.nextInt());
	            sc.nextLine();
	            
	            shopcart.addItem(item);
	            System.out.println("완료!");
	        }else if(choice == 2) {
	        	System.out.print("삭제할 상품 이름: ");
	        	String name = sc.nextLine();
	        	shopcart.removeItem(name);
	        }else if(choice == 3) {
	        	shopcart.sumPrice();
	        }else if(choice == 4) {
	        	shopcart.removeAll();
	        }else if(choice == 5) {
	        	shopcart.viewItemList();
	        }else if(choice == 6) {
	        	System.out.println("프로그램 종료");
	        	break;
	        }else {
	        	System.out.println("올바른 번호를 선택하세요.");
	        }
		}
	}
}
