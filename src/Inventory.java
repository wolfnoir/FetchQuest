public class Inventory {
	private String[] inv;
	
	public Inventory() { //creates a new inventory (no parameters)
		inv = new String[10];
	}
	
	public void addItem(String item, int i) { //adds an item at a specific index
		inv[i] = item;
	}
	
	/*LIST OF ITEM STRINGS TO BE USED:
	 * 0: -Jellyfish\nMagazine
	 * 1: -Lettuce
	 * 2: -Empty Can
	 * 3: -Can of Worms
	 * 4: -$20
	 * 5: -Macaron Box
	 * 6: -Macaron Box\nw/ Letter
	 * 7: -Jellyfish\nFilters
	 */
	
	public void removeItem(int i) {
		inv[i] = null;
	}
	
	public boolean hasItem(String item) { //checks whether or not the inventory has an item
		boolean check = false;
		for(int i = 0; i < inv.length; i++) {
			if(item.equals(inv[i])) {
				check = true;
			}
		}
		return check;
	}
	
	public String toString() {
		String content = "INVENTORY\n";
		for(int i = 0; i < inv.length; i++) {
			if(inv[i] != null) {
				content+= inv[i] + "\n";
			}
		}
		return content;
	}
}

/*TESTING INVENTORY DELETE LATER!!!!!!!!!!!!!!!!!!!!!!!!
mapButton.setOnMouseClicked(e -> {inv.addItem("-Jellyfish\nFilters", 0);
		inv.addItem("-fodor", 4);
		invTitle.setText(inv.toString());});*/
