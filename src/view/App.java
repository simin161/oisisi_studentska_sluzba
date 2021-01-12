package view;

import controller.serijalizacijaDeserijalizacija.Deserijalizacija;

public class App {

	public static void main(String[] args) {

		Deserijalizacija.deserijalizacija();
		GlavniProzor prozor = new GlavniProzor();
		prozor.setVisible(true);
	}

}
