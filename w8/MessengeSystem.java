package w8;
	
interface Messenger{
	void sendMessage(String msg);
}

public class MessengeSystem {
	class InnerMessenger implements Messenger{
		@Override
		public void sendMessage(String msg) {
			System.out.println(msg);
		}
	}
	
	static class StaticMessenger implements Messenger{
		@Override
		public void sendMessage(String msg) {
			System.out.println(msg);
		}
	}
	
	void runLocalMessanger(String msg) {
		
		class LocalMessanger{
			void printMessage() {
				System.out.println(msg);
			}
		}
		LocalMessanger obj = new LocalMessanger();
		obj.printMessage();
	}
	
	void runAnonymousMessanger(String str){
		Messenger msg = new Messenger() {
			@Override
			public void sendMessage(String str) {
				System.out.println(str);
			}
		};
		msg.sendMessage(str);
	}
	
	public static void main(String[] args) {
		MessengeSystem system = new MessengeSystem();
		
		Messenger s1 = system.new InnerMessenger();
		s1.sendMessage("Hi, Inner class!");
		
		Messenger s2 = new MessengeSystem.StaticMessenger();
		s2.sendMessage("Hi, Static inner class!");
		
		system.runLocalMessanger("Hi, Local class!");
		
		system.runAnonymousMessanger("Hi, Anonymous class!");
	}

}
