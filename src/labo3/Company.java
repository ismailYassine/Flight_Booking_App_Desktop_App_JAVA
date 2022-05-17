package labo3;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Font;

public class Company {
	
	private static int MAX_PLACES = 340;
	private static String companyName;
	private static int maxFlightNumber;
	static ArrayList<Vol> companyFlight;
	private static int activeFlight;
	
	public Company(String nomCompagnie, int nombreMaxVol) {
		
		this.companyName = nomCompagnie;
		this.maxFlightNumber = nombreMaxVol;
	}
		
	public static void fillFlightArray() throws IOException {
		
		companyFlight = new ArrayList<Vol>();
		FileReader readFile = new FileReader("cieAirRelax.txt");
		BufferedReader fileRead = new BufferedReader(readFile);
		
		int k = 0;
		String ligne;
		String[] temTab;
		
		int flightNumber;
		String flightDestination;
		int day;
		int month;
		int year;
		Date departureDate;
		int flightTotalReservationNumber;
		
		ligne = fileRead.readLine();
		
		while(ligne != null && k < maxFlightNumber) {
			
			temTab = ligne.split(";");
			
			flightNumber = Integer.parseInt(temTab[0]);
			flightDestination = temTab[1];
			
			day = Integer.parseInt(temTab[2]);
			month  = Integer.parseInt(temTab[3]);
			year  = Integer.parseInt(temTab[4]);
			
			flightTotalReservationNumber  = Integer.parseInt(temTab[5]); 
			
			departureDate = new Date(day, month, year);
			
			companyFlight.add(new Vol(flightNumber, flightDestination, departureDate, flightTotalReservationNumber));
			k++;
			activeFlight++;

			ligne = fileRead.readLine();
		}
		
		fileRead.close();
		//System.out.println(nombreVolActifs);
			
	}
	
	private static int findFlight(ArrayList<Vol> companyFlight, int flightNumber) {
		
		int pos = -1;
		boolean find = false;
		
	for (int i = 0; i < companyFlight.size(); i++) {
		
		if(companyFlight.get(i).getNumeroVol()  == flightNumber) {
			pos = i;
			find = true;
		}
	}
	
	return pos;
}

	private static void insert(Vol flight) {
	
			companyFlight.add(flight);		
			activeFlight++;
		
}

	public static void flightList() throws IOException {
		
		JTextArea textArea = new JTextArea(4,20);
		
		textArea.append("		LISTE DES VOLS");

		textArea.append("\n\nNumero\tDestination\t\tDate depart\tReservations");
		
		for(int i = 0; i < companyFlight.size(); i++) {
			textArea.append("\n" + companyFlight.get(i));
		}
		
		textArea.setFont(new Font("Courier", Font.PLAIN, 12));
		textArea.setTabSize(15);
		JOptionPane.showMessageDialog(null, textArea, companyName, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void insertFlight() {

		if(companyFlight.size() == maxFlightNumber) {
			
			JOptionPane.showMessageDialog(null,
					"Nombre maximun (" + maxFlightNumber +") de vols actifs atteint, vous ne pouvez pas ajouter de nouveau vol.", 
					"VOLS ACTIFS", JOptionPane.PLAIN_MESSAGE);			
		} else {
			
			int flightNumber;
			String flightDestination;
			Date departureDate;
			int flightTotalReservationNumber;

			String flightDate;
			
			int day;
			int month;
			int year;
			
			flightNumber = Integer.parseInt(JOptionPane.showInputDialog(
					null,
					"Numero du vol", 
					"NUMERO VOL", 
					JOptionPane.PLAIN_MESSAGE));
			
			int position = findFlight(companyFlight,flightNumber);
			
			if(position != -1) {
				
				JOptionPane.showMessageDialog(null,
						"Le numero du vol fournit correspond a un vol actif enregistrer dans le systeme.", 
						"VOLS ACTIFS", JOptionPane.PLAIN_MESSAGE);
		}else {
			
		flightDestination = JOptionPane.showInputDialog(
				null,
				"Destination du vol", 
				"DESTINATION", 
				JOptionPane.PLAIN_MESSAGE);
		
		flightDate = JOptionPane.showInputDialog(
				null,
				"Date du vol au Format JJ/MM/AAAA", 
				"DATE DE VOL", 
				JOptionPane.PLAIN_MESSAGE);
		
		flightTotalReservationNumber = Integer.parseInt(JOptionPane.showInputDialog(
				null,
				"Nombres de reservations", 
				"NOMBRES DE RESERVATIONS", 
				JOptionPane.PLAIN_MESSAGE));
		
		//jour = Integer.parseInt(dateVo.substring(0,2));
		//mois = Integer.parseInt(dateVo.substring(2,4));
		//annee = Integer.parseInt(dateVo.substring(4));
		
		day = Integer.parseInt(flightDate.substring(0,2));
		month = Integer.parseInt(flightDate.substring(3,5));
		year = Integer.parseInt(flightDate.substring(6));
		
		departureDate = new Date(day, month, year);
		Vol volAdd = new Vol(flightNumber, flightDestination, departureDate, flightTotalReservationNumber);
		
		insert(volAdd);
		
		}
		}
	}	 
	
	public static void removeFlight() {
		
		int flightNumber = Integer.parseInt(JOptionPane.showInputDialog(
				null,
				"Numero du vol", 
				"RETRAIT D'UN VOL", 
				JOptionPane.PLAIN_MESSAGE));
		
		int position = findFlight(companyFlight,flightNumber);
		
		if(position != 1) {
			
			
			int flightNumberToRemove = companyFlight.get(position).getNumeroVol();
			String flightDestination = companyFlight.get(position).getDestinationVol();
			Date departureDate  = companyFlight.get(position).getDateDepart();
			int currentReservationNumber = companyFlight.get(position).getNombreTotReservations();
			
			String message = "\nNumero: " + flightNumberToRemove + "\nDestination: " + flightDestination + "\nDate depart: " + departureDate + "\nReservations: " + currentReservationNumber;
			
			String response = JOptionPane.showInputDialog(null,
					"Details du vol\n" + message + "\n\n\nDésirez-vous vraiment retirer ce vol (O/N)", "RETRAIT D'UN VOL", 
					JOptionPane.WARNING_MESSAGE);
			
			char userResponse = response.toUpperCase().charAt(0);
			
			if(userResponse == 'O') {
				
				companyFlight.remove(position);
				
				JOptionPane.showMessageDialog(null, "Vols retirer avec succes", "RETRAIT D'UN VOL", JOptionPane.PLAIN_MESSAGE);
			}else if(userResponse == 'N') {
				JOptionPane.showMessageDialog(null, "Operation annulee", "RETRAIT D'UN VOL", JOptionPane.PLAIN_MESSAGE);
			}
			
		}else{
			JOptionPane.showMessageDialog(null,
					"Aucun vol actif enregistrer correspond au numero du vol fournit", 
					"RETRAIT D'UN VOL", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public static void changeDate() {
		
		int flightNumber = Integer.parseInt(JOptionPane.showInputDialog(
				null,
				"Numero du vol", 
				"MODIFICATION DATE", 
				JOptionPane.PLAIN_MESSAGE));
		
		int position = findFlight(companyFlight,flightNumber);
		
		if(position != 1) {
			
			int changeDateFlightNumber = companyFlight.get(position).getNumeroVol();
			String flightDestination = companyFlight.get(position).getDestinationVol();
			Date departureDate  = companyFlight.get(position).getDateDepart();
			int currentReservationNumber = companyFlight.get(position).getNombreTotReservations();
			
			String message = "\nNumero: " + changeDateFlightNumber + "\nDestination: " + flightDestination + "\nDate depart: " + departureDate + "\nReservations: " + currentReservationNumber;
			
			String response = JOptionPane.showInputDialog(null,
					"Details du vol\n" + message + "\n\n\nNouvelle date", "MODIFICATION DATE", 
					JOptionPane.WARNING_MESSAGE);
			
			int day = Integer.parseInt(response.substring(0,2));
			int month = Integer.parseInt(response.substring(3,5));
			int year = Integer.parseInt(response.substring(6));
			
			companyFlight.get(position).setDateDepart(new Date(day, month, year));
			}
	}
	
	public static void bookFlight() {
		
		int flightNmuber = Integer.parseInt(JOptionPane.showInputDialog(
				null,
				"Numero du vol", 
				"MODIFICATION DATE", 
				JOptionPane.PLAIN_MESSAGE));
		
		int position = findFlight(companyFlight,flightNmuber);
		int availableSeat = companyFlight.get(position).getNombreTotReservations();

		if(position != 1) {
			
			if(availableSeat >= MAX_PLACES) {
				JOptionPane.showMessageDialog(null,
						"Nombres maximum (" + MAX_PLACES + ") de place atteint sur ce vol.", 
						"RESERVATIONS", JOptionPane.PLAIN_MESSAGE);
			}else {
			
			int flightNumber = companyFlight.get(position).getNumeroVol();
			String flightDestination = companyFlight.get(position).getDestinationVol();
			Date departureDate  = companyFlight.get(position).getDateDepart();
			int flightReservationsTotNumber = companyFlight.get(position).getNombreTotReservations();
			
			String message = "\nNumero: " + flightNumber + "\nDestination: " + flightDestination + "\nDate depart: " + departureDate + "\nReservations: " + flightReservationsTotNumber;
			
			String reponse = JOptionPane.showInputDialog(null,
					"Details du vol\n" + message + "\n\n\nNombres de reservations", "RESRERVATION", 
					JOptionPane.WARNING_MESSAGE);
			
			int book = Integer.parseInt(reponse);
			
			companyFlight.get(position).setNombreTotReservations(companyFlight.get(position).getNombreTotReservations() + book);
				
				 }
			
			}else{
				JOptionPane.showMessageDialog(null,
						"Le numero du vol fournit ne correspond aucun vol actif enregistrer dans le systeme.", 
						"RESERVATIONS", JOptionPane.PLAIN_MESSAGE);
			}
	}
	
	public static void sortFlight() {}
	
	public static void writeOnFile() throws IOException {
		
		FileWriter writeOnFile = new FileWriter("cieAirRelax.txt");
		BufferedWriter writeFile = new BufferedWriter(writeOnFile);
		
		String ligne;
		int number;
		String Destination;
		Date date;
		int reservations;
		int day;
		int month;
		int year;
		String lignedate="";
		String tabdate[]= new String[3];
		
		for (int i = 0; i < companyFlight.size(); i++) {
			
			number = companyFlight.get(i).getNumeroVol();
			Destination = companyFlight.get(i).getDestinationVol(); 
			date  = companyFlight.get(i).getDateDepart();
			
			lignedate= date+"";
			tabdate = lignedate.split("/");
			day = Integer.parseInt(tabdate[0]);
			month = Integer.parseInt(tabdate[1]);
			year = Integer.parseInt(tabdate[2]);
			
			reservations = companyFlight.get(i).getNombreTotReservations();
			
			ligne = number + ";" + Destination + ";" + day+ ";"+month+";"+ year + ";" + reservations;
			
			writeFile.write(ligne);
			writeFile.newLine();
		}
		writeFile.close();	
	}
	
	
	public static void main(String[] args) throws IOException {
		
		fillFlightArray();
		writeOnFile();
	}
}
