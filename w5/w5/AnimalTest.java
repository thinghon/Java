package w5;

class Animal {
	String name;
	int age;

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String speak() {
		return "소리를 내지 않습니다.";
	}
	@Override
	public String toString() {
		return String.format("%s는 %d살이고 %s", name, age, speak());
	}
}

class Dog extends Animal {
	String breed;

	public Dog(String name, int age, String breed) {
		super(name, age);
		this.breed = breed;
	}
	@Override
	public String speak() {
		return "멍멍!";
	}
	@Override
	public String toString() {
		return String.format("%s는 %d살이고 %s이며 %s라고 짖습니다."
				, name, age, breed, speak());
	}
}

class Cat extends Animal {
	String color;

	public Cat(String name, int age, String color) {
		super(name, age);
		this.color = color;
	}
	@Override
	public String speak() {
		return "야옹~";
	}
	@Override
	public String toString() {
		return String.format("%s는 %d살이고 %s인 고양이이며 %s이라고 울어대고 있습니다."
				, name, age, color, speak());
	}
}

public class AnimalTest {
	public static void main(String[] args) {
		Animal animal1 = new Animal("뽀삐", 3);
		Animal animal2 = new Dog("백수", 2, "진돗개");
		Animal animal3 = new Cat("톰", 1, "검은색");
		System.out.println(animal1);
		System.out.println(animal2);
		System.out.println(animal3);
	}
}
