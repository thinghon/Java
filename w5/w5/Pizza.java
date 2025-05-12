package w5;

class Circle{
	protected int radius;
	public Circle(int r) { radius = r; }
}

class Pizza extends Circle{
	String name;
	public Pizza(String name, int r) {
		super(r);
		this.name = name;
	}
	public void print() {
		System.out.printf("피자의 종류: %s, 피자의 크기: %d",name, radius);
	}
	public static void main(String[] args) {
			Pizza obj = new Pizza("Pepporoni",20);
			obj.print();
	}
}
