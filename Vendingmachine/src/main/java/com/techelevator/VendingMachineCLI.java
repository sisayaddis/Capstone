package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

		private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
		private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
		private static final String EXIT_VENDINGMACHINE = "Exit";
		private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, EXIT_VENDINGMACHINE };
		private static final String FEED_MONEY_OPTION = "Feed Money";
		private static final String PURCHASE_OPTION = "Select Product";
		private static final String END_TRANSACTION = "Finish Transaction";
		private static final String []PURCHASE_MENU_OPTION = {FEED_MONEY_OPTION,PURCHASE_OPTION,END_TRANSACTION};

		Scanner input = new Scanner(System.in);
		VendingMachine vendingMachine = new VendingMachine();

		private Menu menu;

		public VendingMachineCLI(Menu menu) {
			this.menu = menu;
			vendingMachine.establishInventory();
		}

		public void run ()throws NumberFormatException {
			while (true) {

				String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

				if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS) || choice.equals("1")) {
					DisplayMenuItem(vendingMachine);
				} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					mainMenuOptionPurchase();
				} else if (choice.equals(EXIT_VENDINGMACHINE) || choice.equals("3")) {
					System.exit(1);
				}
			}
		}

		private void mainMenuOptionPurchase() {
			String select = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTION);
			if (select.equals(FEED_MONEY_OPTION) || select.equals("1")) {
				System.out.println("Enter amount to feed");
				double amount = input.nextDouble();
				input.nextLine();
				vendingMachine.feedMoney(amount);
			} else if (select.equals(PURCHASE_OPTION) || select.equals("2")) {
				purchaseOption();
			} else if (select.equals("Finish Transaction") || select.equals("3")) {
				vendingMachine.change();
			}
		}

		private void purchaseOption() {
			DisplayMenuItem(vendingMachine);
			System.out.println("Select a product");
			String productId = input.nextLine();
			if (vendingMachine.getItems().containsKey(productId)){
				if(vendingMachine.getItems().get(productId).getItems_quantity() >= 1){
					if(vendingMachine.getBalance() >= vendingMachine.getItems().get(productId).getPrice()){
						vendingMachine.purchaseValidation(productId);
					}else{
						System.out.println("Not enough money");
					}
				}else {
					System.out.println(vendingMachine.getItems().get(productId).getName()+" is sold out, I'm Sorry" );
				}
			} else {
				System.out.println("This product does not exist");
			}
		}

		private void DisplayMenuItem(VendingMachine vendingMachine) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("-----------------------------------------------------------------");
			for (Map.Entry<String, Product> item : vendingMachine.getItems().entrySet()) {
				System.out.println("Item Code: "+item.getKey() + "   | "  + item.getValue().getName() + "   |"+" Price: " + item.getValue().getPrice()+"   | "+" Remaining Quantity: "+item.getValue().getItems_quantity());
				System.out.println("");
			}
			System.out.println("--------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------");
		}

		public static void main(String[] args) {
			Menu menu = new Menu(System.in, System.out);
			VendingMachineCLI cli = new VendingMachineCLI(menu);
			cli.run();
		}
}
