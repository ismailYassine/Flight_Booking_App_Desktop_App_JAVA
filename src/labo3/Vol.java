package labo3;

public class Vol {
	
	private int flightNumber;
	private String flightDestination;
	private Date departureDate;
	private int flightTotalReservationNumber;
		
	public Vol(int numeroVol, String destinationVol, Date dateDepart, int nombreReservations) {
		this.flightNumber = numeroVol;
		this.flightDestination = destinationVol;
		this.departureDate = dateDepart;
		this.flightTotalReservationNumber = nombreReservations;	
	}

	public void setDateDepart(Date dateDepart) {
		this.departureDate = dateDepart;
	}

	public void setNombreTotReservations(int nombreTotReservations) {
		this.flightTotalReservationNumber = nombreTotReservations;
	}

	public int getNumeroVol() {
		return flightNumber;
	}

	public String getDestinationVol() {
		return flightDestination;
	}

	public Date getDateDepart() {
		return departureDate;
	}

	public int getNombreTotReservations() {
		return flightTotalReservationNumber;
	}

	@Override
	public String toString() {
		return  flightNumber + "\t" + flightDestination + "\t\t" + departureDate + "\t" + flightTotalReservationNumber;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
