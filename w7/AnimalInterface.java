package w6;

interface Animal{
	void makeSound();
}
class Dog implements Animal{
	@Override
	public void makeSound() {
		System.out.println("멍멍!");
	}
}
class Cat implements Animal{
	@Override
	public void makeSound() {
		System.out.println("야옹~");
	}
}
class Bird implements Animal{
	@Override
	public void makeSound() {
		System.out.println("짹짹!");
	}
}
public class AnimalInterface {
	public static void main(String[] args) {
		Animal dog = new Dog();
		dog.makeSound();
		Animal cat = new Cat();
		cat.makeSound();
		Animal bird = new Bird();
		bird.makeSound();
	}
}
