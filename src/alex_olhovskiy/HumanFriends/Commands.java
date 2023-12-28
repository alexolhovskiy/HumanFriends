package alex_olhovskiy.HumanFriends;

public enum Commands {
	Sit("Sit"),
	Stay("Stay"),
	Down("Down"),
	Fetch("Fetch"),
	Pounce("Pounce"),
	Roll("Roll"), 
	Hide("Hide"),
	Paw("Paw"), 
	Bark("Bark"),
	Scratch("Scratch"),
	Spin("Spin"),
	Meow("Meow"), 
	Jump("Jump"),
	Trot("Trot"), 
	Canter("Canter"), 
	Gallop("Gallop"),
	Walk("Walk"), 
	CarryLoad("CarryLoad"), 
	Bray("Bray"),
	Kick("Kick"),
	Run("Run");
	
	private String c_name;
	
	Commands(String name){
		c_name=name;
	}
	
	public String toString() {
		return c_name;
	}
}
