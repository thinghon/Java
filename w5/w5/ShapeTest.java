package w5;

class Shape{
	int x;
	int y;
	String color;
	public Shape(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public String getArea() {
		return "아직 면적을 계산할 수 없습니다.";
	}
	@Override
	public String toString() {
		return String.format("%s", getArea());
	}
}

class Circles extends Shape{
	int radius;
	public Circles(int x, int y, String color, int radius) {
		super(x,y, color);
		this.radius = radius;
	}
	@Override
	public String getArea() {
		return String.format("%.0f",radius*radius*Math.PI);
	}
	@Override
	public String toString() {
		return String.format("원은 (%d, %d)에 위치하며 색상은 %s이고 면적은 %s입니다."
				, x, y, color, getArea());
	}
}

class Rectangle extends Shape{
	int width, height;
	public Rectangle(int x, int y, String color, int width, int height) {
		super(x, y, color);
		this.width = width;
		this.height = height;
	}
	@Override
	public String getArea() {
		return "" + width*height;
	}
	@Override
	public String toString() {
		return String.format("사격형은 (%d, %d)에 위치하며 색상은 %s이고 면적은 %s입니다."
				, x, y, color, getArea());
	}
}

class Triangle extends Shape{
	int width, height;
	public Triangle(int x, int y, String color, int width, int height) {
		super(x, y, color);
		this.width = width;
		this.height = height;
	}
	@Override
	public String getArea() {
		return "" + ((width * height) / 2);
	}
	@Override
	public String toString() {
		return String.format("삼격형은 (%d, %d)에 위치하며 색상은 %s이고 면적은 %s입니다."
				, x, y, color, getArea());
	}
}
public class ShapeTest {

	public static void main(String[] args) {
		Shape shape []= {
			new Rectangle(100,200,"blue",300,400),
			new Circles(100,200,"white",300),
			new Triangle(10,200,"yellow",300,400)
		};
		for (int i =0; i<shape.length;i++) {
			System.out.println(shape[i]);
		}
	}
}
