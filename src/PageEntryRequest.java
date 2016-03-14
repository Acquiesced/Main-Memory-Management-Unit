public class PageEntryRequest {

	static final int VIRTUALADDRBIT = 16;
	int[] virtualAddressBin = new int[VIRTUALADDRBIT];
	int[] offsetBin = new int[12];
	int[] virtualPageNumBin = new int[4];
	String virtualPageNumStr;
	String offsetStr;
	String virtualAddressStr;

	public PageEntryRequest(int virtualAddress, PageEntry[] pageTable, MainMemTable mainMem) {
		this.virtualAddressStr = formatBinary(virtualAddress);
		this.virtualAddressBin = convertAddressStrtoIntArray(virtualAddressStr);
		splitBinFormat(offsetBin, virtualPageNumBin, this.virtualAddressBin);
		this.virtualPageNumStr = virtualAddressStr.substring(0, 4);
		this.offsetStr = virtualAddressStr.substring(4);
		System.out.println("Processing Request...\nVirtual Address: " + virtualAddress);
		System.out.println("Binary Virtual Address: " + virtualAddressStr);

		//clean this....
		if (checkPageTable(pageTable)) {
			System.out.println("Page Hit: " + pageTable[Integer.parseInt(virtualPageNumStr, 2)].physicalAddressStr
					+ offsetStr + " Decimal Physical Address: " + Integer.parseInt(
							pageTable[Integer.parseInt(virtualPageNumStr, 2)].physicalAddressStr + offsetStr, 2));
		} else {
			if (addToMainMemory(mainMem, pageTable)) {
				System.out.println("Page Fault: " + pageTable[Integer.parseInt(virtualPageNumStr, 2)].physicalAddressStr
						+ offsetStr + " Decimal Physical Address: " + Integer.parseInt(
								pageTable[Integer.parseInt(virtualPageNumStr, 2)].physicalAddressStr + offsetStr, 2));
			}
		}
		System.out.println("----------------------------");
	}

	private boolean checkPageTable(PageEntry[] pageTable) {
		int virtualPageNumInt = Integer.parseInt(virtualPageNumStr, 2);
		if (pageTable[virtualPageNumInt].presentBin == 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean addToMainMemory(MainMemTable mainMem, PageEntry[] pageTable) {
		int mainMemoryIndex = mainMem.addtoEmptySlot();
		int virtualPageNumInt = Integer.parseInt(virtualPageNumStr, 2);
		if (mainMemoryIndex < 0) {
			System.err.println("Main Memory has no available slots...");
			return false;
		} else {
			pageTable[virtualPageNumInt] = new PageEntry(mainMemoryIndex);
			pageTable[virtualPageNumInt].setPresentBin(1);
			System.out.println("Page Entry added to Main Memory...");
			return true;
		}
	}

	private String formatBinary(int virtualAddress) {
		int virtBinSize = Integer.toBinaryString(virtualAddress).length();
		virtualAddressStr = new String(new char[VIRTUALADDRBIT - virtBinSize]).replace('\0', '0')
				+ Integer.toBinaryString(virtualAddress);
		return virtualAddressStr;
	}

	private int[] convertAddressStrtoIntArray(String intString) {
		int[] intArray = new int[16];
		for (int i = 0; i < intString.length(); i++) {
			intArray[i] = Character.digit(intString.charAt(i), 10);
		}
		return intArray;
	}

	private void splitBinFormat(int[] offsetBin, int[] virtualPageNumBin, int[] virtualAddressBin) {
		int offsetCount = 0;
		for (int i = 0; i < virtualAddressBin.length; i++) {
			if (i > 3) {
				offsetBin[offsetCount] = virtualAddressBin[i];
				offsetCount++;
			}
			if (i < 4) {
				virtualPageNumBin[i] = virtualAddressBin[i];
			}
		}
	}

}
