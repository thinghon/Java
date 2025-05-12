package w6;

interface QueType{
	int dequeue() throws Exception;
	void enqueue(int item) throws Exception;
	boolean isEmpty();
	boolean isFull();
}

class MyQueue implements QueType{
	int[] queue;
	int front = -1;
	int back = -1;
	
	public MyQueue(int size) {
		queue = new int[size];
	}
	
	@Override
	public int dequeue() throws Exception {
		int temp;
		if(isEmpty()) {
			throw new Exception("배열이 비어있습니다.");
		}
		temp = queue[++front];
		queue[front] = 0;
		return temp;
	}

	@Override
	public void enqueue(int item) throws Exception {
		if(isFull()) {
			throw new Exception("배열이 가득 찼습니다.");
		}
		queue[++back] = item;
	}

	@Override
	public boolean isEmpty() {
		return back == front;
	}
	@Override
	public boolean isFull() {
		return back == queue.length - 1;
	}
	
}
public class Queue {

	public static void main(String[] args) {
		try {
			QueType queue = new MyQueue(6);
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			System.out.println(queue.dequeue()); // 출력: 1
			System.out.println(queue.dequeue()); // 출력: 2
			System.out.println(queue.isEmpty()); // 출력: false
			queue.enqueue(4);
			queue.enqueue(5);
			queue.enqueue(6);
			System.out.println(queue.dequeue()); // 출력: 3
			System.out.println(queue.dequeue()); // 출력: 4
			System.out.println(queue.dequeue()); // 출력: 5
			System.out.println(queue.dequeue()); // 출력: 6
			System.out.println(queue.isEmpty()); // 출력: true
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
