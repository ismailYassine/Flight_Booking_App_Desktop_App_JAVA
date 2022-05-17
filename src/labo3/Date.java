package labo3;

public class Date {

	private int day;
	private int month;
	private int year;

	public Date(int jour, int mois, int annee) {

		this.day = jour;
		this.month = mois;
		this.year = annee;
	}

	public int getJour() {
		return day;
	}

	public void setJour(int jour) {
		this.day = jour;
	}

	public int getMois() {
		return month;
	}

	public void setMois(int mois) {
		this.month = mois;
	}

	public int getAnnee() {
		return year;
	}

	public void setAnnee(int annee) {
		this.year = annee;
	}

	
	@Override
	public String toString() {
		return String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//	}
}
