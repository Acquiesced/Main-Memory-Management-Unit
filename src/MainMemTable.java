
public class MainMemTable {
	boolean[] memArray;

	public MainMemTable(int mainMemSlots) {
		memArray = new boolean[mainMemSlots];
	}

	public int addtoEmptySlot() {
		for (int i = 0; i < memArray.length; i++) {
			if (memArray[i] == false) {
				System.out.println("Adding Process to Main Memory");
				memArray[i] = true;
				return i;
			}
		}
		//Reaches here if no available slots.
		System.out.println("No Empty Main Memory Slots Available");
		return -1;

	}
	
}
