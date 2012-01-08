package jpaddlegame.com;

public class KeyBoardState {
	private static KeyBoardState instance;
	
	private KeyProcessor keyProcessor;
	
	public static KeyProcessor getProcessor() {
		if (instance == null){
			instance = new KeyBoardState();
		}
		
		return instance.keyProcessor;
	}
	
	private KeyBoardState() {
		keyProcessor = new KeyProcessor();
	}
}
