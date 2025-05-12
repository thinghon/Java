package w4;

class Dogs {
	String name;
	int age;
	String color;
	private static int count = 0;

	public Dogs(String name, int age, String color) {
		this.name = name;
		this.age = age;
		this.color = color;
		count++;
	}
	public String getName() {return name;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	public String getColor() {return color;}
	public static int getCount() {return count;}
}

class Witch{
	public static void younger(Dogs obj) {
		obj.setAge(obj.getAge()-5);
	}
}

class Dog{
	public static void main(String[] args) {
		Dogs dogs[] = {
			new Dogs("Molly", 10, "brown"),
			new Dogs("Daisy", 6, "black"),
			new Dogs("Bella", 7, "white")
		};
		int num = 1;
		for(Dogs dog : dogs) {
			System.out.printf("강아지 #%d = \"%s\", %d, \"%s\"\n"
					, num++, dog.getName(), dog.getAge(), dog.getColor());
		}
		System.out.println("현재까지 생성된 강아지의 수 = " + Dogs.getCount());
		for(Dogs dog : dogs) {
			Witch.younger(dog);
		}
		num = 1;
		for(Dogs dog : dogs) {
			System.out.printf("강아지 #%d = \"%s\", %d, \"%s\"\n"
					, num++, dog.getName(), dog.getAge(), dog.getColor());
		}
	}
}