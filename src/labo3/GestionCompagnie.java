package labo3;

import java.io.IOException;

import javax.swing.JOptionPane;

public class GestionCompagnie {

	public static void createCompany() throws IOException {

		String companyName;
		int maxFlightNumber;

		companyName = JOptionPane.showInputDialog(null, "Le nom de la compagie:", "NOM DE LA COMPAGNIE",
				JOptionPane.PLAIN_MESSAGE);

		maxFlightNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrer le nombre maximun de vol:",
				"NOMBRE MAXIMUM DE VOLS", JOptionPane.PLAIN_MESSAGE));

		Company company = new Company(companyName, maxFlightNumber);

		Company.fillFlightArray();

		int menu;

		do {
			menu = menu();

			switch (menu) {

			case 0:
				Company.writeOnFile();
				break;
			case 1:
				Company.flightList();
				break;
			case 2:
				Company.insertFlight();
				break;
			case 3:
				Company.removeFlight();
				break;
			case 4:
				Company.changeDate();
				break;
			case 5:
				Company.bookFlight();
				break;
			}

		} while (menu != 0);

	}

	public static int menu() {

	int selectedMenu;

		selectedMenu = Integer.parseInt(JOptionPane.showInputDialog(null,
				"GESTION DES VOLS\n\n1. Listes des vols\n2. Ajout d'un vol\n3. Retrait d'un vol\n4. Modification de la date de depart\n5. Reservation d'un vol\n0. Terminer\n\nFaite votre choix:",
				"CIE AIR RELAX", JOptionPane.PLAIN_MESSAGE));

		return selectedMenu;

	}

	public static void main(String[] args) throws IOException {

		createCompany();

	}

}
