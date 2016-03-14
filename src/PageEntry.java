
public class PageEntry {
	int[] physicalAddressBin = new int[3];
	String physicalAddressStr;
	int physicalMemIndex;
	int presentBin = 0;


	public PageEntry(int physicalMemIndex){
		this.physicalAddressStr = Integer.toBinaryString(physicalMemIndex);
		this.physicalAddressBin = convertAddressStrtoIntArray(physicalAddressStr);
		this.physicalMemIndex = physicalMemIndex;
	}

	public void setPresentBin(int value){
		presentBin = value;
	}
	
	public int getPresentBin(){
		return presentBin;
	}

	private int[] convertAddressStrtoIntArray(String intString) {
		int[] intArray = new int[16];
		for (int i = 0; i < intString.length(); i++) {
			intArray[i] = Character.digit(intString.charAt(i), 10);
		}
		return intArray;
	}
}
