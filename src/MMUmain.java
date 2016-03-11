/**
 * 
 */

/**
 * @author Bradley Chow
 * @CSID bachow
 * @course: cps801
 */
public class MMUmain {

	/**
	 * @param args
	 */
	static final int VIRTUALADDRBIT = 16;
	static final int PAGESIZE = 4096;
	static final int RAMSIZE = 32768;
	static final int OFFBITSIZE = 12;
	static final int LOGICALMEMSIZE = 4;
	static MainMemTable mainMemory;
	static PageEntry[] pageTable;

	public static void main(String[] args) {
		initMem();
		initPageTable();
		fillPageTable(pageTable, mainMemory);

	}

	public static void fillPageTable(PageEntry[] pageTable, MainMemTable mainMemory) {
		new PageEntryRequest(12288, pageTable, mainMemory);
		new PageEntryRequest(5151, pageTable, mainMemory);
		new PageEntryRequest(1523, pageTable, mainMemory);
		new PageEntryRequest(20484, pageTable, mainMemory);
		new PageEntryRequest(18384, pageTable, mainMemory);
		new PageEntryRequest(36864, pageTable, mainMemory);
		new PageEntryRequest(8989, pageTable, mainMemory);
		new PageEntryRequest(45756, pageTable, mainMemory);
		new PageEntryRequest(20, pageTable, mainMemory);
		new PageEntryRequest(4100, pageTable, mainMemory);
		new PageEntryRequest(8300, pageTable, mainMemory);
	}

	public static void initPageTable() {
		pageTable = new PageEntry[VIRTUALADDRBIT];
		for (int i = 0; i < pageTable.length; i++) {
			pageTable[i] = new PageEntry(0);
		}
	}

	private static void initMem() {
		int memSlots = RAMSIZE / PAGESIZE;
		mainMemory = new MainMemTable(memSlots);

	}

}
