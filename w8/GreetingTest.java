package w8;

interface Greeting{
	void sayHello();
}

class Hello {
	class InnerGreeting implements Greeting{
		@Override
		public void sayHello() {
			System.out.println("Hello, Java!");
		}
	}

}

public class GreetingTest {

	public static void main(String[] args) {
		Hello hello = new Hello();
		
		Hello.InnerGreeting greeter = hello.new InnerGreeting();
		greeter.sayHello();
	}

}
