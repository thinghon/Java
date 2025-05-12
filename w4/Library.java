package w4;

import java.util.ArrayList;
import java.util.Scanner;

class Book{
	private String title;
	private String author;
	private String publisher;
	private int price;
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}
	public String getPublisher() {return publisher;}
	public void setPublisher(String publisher) {this.publisher = publisher;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public String toString() {
		return String.format("제목: %s, 저자: %s, 출판사: %s, 가격: %d원", title, author, publisher, price);
	}
}
public class Library {
	private ArrayList<Book> books;
	
	public Library() {
		this.books = new ArrayList<>(); 
	}
	public void addBook(Book book) {
		books.add(book);
	}
	public void removeBook(String title) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(title)) {
				System.out.println("도서 삭제: " + books.get(i).getTitle());
				books.remove(i);
				return;
			}
		}
		System.out.println("해당 도서가 존재하지 않습니다.");
	}
	public void searchBook(String title) {
		for (Book book : books) {
	        if (book.getTitle().equals(title)) {
	            System.out.println("검색 결과: " + book);
	            return;
	        }
	    }
		System.out.println("해당 제목의 도서를 찾을 수 없습니다.");
	}
	public void viewBookList() {
		for (Book book : books) {
			System.out.println(book);
		}
	}
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    Library lib = new Library();

	    while (true) {
	        System.out.println("1. 도서추가");
	        System.out.println("2. 도서삭제");
	        System.out.println("3. 도서검색");
	        System.out.println("4. 도서 목록");
	        System.out.println("5. 종료");
	        System.out.print("선택: ");
	        int choice = sc.nextInt();
	        sc.nextLine(); 

	        if (choice == 1) {
	            Book book = new Book();
	            System.out.print("제목: ");
	            book.setTitle(sc.nextLine());
	            System.out.print("저자: ");
	            book.setAuthor(sc.nextLine());
	            System.out.print("출판사: ");
	            book.setPublisher(sc.nextLine());
	            System.out.print("가격: ");
	            book.setPrice(sc.nextInt());
	            sc.nextLine(); 

	            lib.addBook(book);
	            System.out.println("완료!");

	        } else if (choice == 2) {
	            System.out.print("삭제할 도서 제목: ");
	            String title = sc.nextLine();
	            lib.removeBook(title);

	        } else if (choice == 3) {
	            System.out.print("검색할 도서 제목: ");
	            String title = sc.nextLine();
	            lib.searchBook(title);

	        } else if (choice == 4) {
	        	System.out.println("도서 목록:");
	        	lib.viewBookList();
	        }else if (choice == 5) {
	            System.out.println("프로그램 종료");
	            break;
	        } else {
	            System.out.println("올바른 번호를 선택하세요.");
	        }
	    }
	}
}
