package w3;

class Counter {
	int counter;
	
	void up() {
		counter++;
		System.out.println("up() 메소드 호출 후 Counter value: " + counter);
	}

	void down() {
		counter--;
		System.out.println("down() 메소드 호출 후 Counter value: " + counter);
	}

	void reset() {
		counter = 0;
	}

	Counter(int counter) {
		this.counter = counter;
		System.out.println("객체 생성 후 Count value: " + this.counter);
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}

public class CounterTest {

	public static void main(String[] args) {
		Counter ct = new Counter(0);
		ct.up();
		ct.up();
		ct.down();
		System.out.println("최종 Count value: " + ct.getCounter());
	}
}
