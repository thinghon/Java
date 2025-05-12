package w3;

import java.util.Random;

class CircleInfo {
	Random rd = new Random();
	int maxNum = 10;
	private static int count = 0;
	private int id;
	private int radius;
	private int x, y;
	private String color;

	public CircleInfo(int radius, String color, int x, int y) {
		this.radius = radius;
		this.color = color;
		this.x = x;
		this.y = y;
		this.id = ++count; 
	}

	public void move() {
		this.x += rd.nextInt(maxNum);
		this.y += rd.nextInt(maxNum);
	}

	public int getId() {
		return id;
	}

	public int getRadius() {
		return radius;
	}

	public String getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

public class Circle {
	public static void main(String[] args) {
		CircleInfo[] circles = {
			new CircleInfo(20, "파란색", 0, 0),
			new CircleInfo(50, "빨강색", 0, 0),
			new CircleInfo(100, "노랑색", 0, 0)
		};

		for (int i = 0; i < 100; i++) {
			for (CircleInfo circle : circles) {
				circle.move();
				System.out.printf("원 #%d (반지름: %d, 색상: %s)은 (%d, %d)으로 이동하였음\n",
					circle.getId(), circle.getRadius(), circle.getColor(), circle.getX(), circle.getY());
			}
			System.out.println();
		}
	}
}
