package es.florida.accesdades.examen2av;

public class Destinacio {
	private int id, preu, preuOferta;
	private String lloc, passaportCOVID;
	
	public Destinacio() {
		super();
	}
	
	public Destinacio(String lloc, int preu, int preuOferta, String passaportCOVID) {
		this.preu = preu;
		this.preuOferta = preuOferta;
		this.lloc = lloc;
		this.passaportCOVID = passaportCOVID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPreu() {
		return preu;
	}

	public void setPreu(int preu) {
		this.preu = preu;
	}

	public int getPreuOferta() {
		return preuOferta;
	}

	public void setPreuOferta(int preuOferta) {
		this.preuOferta = preuOferta;
	}

	public String getLloc() {
		return lloc;
	}

	public void setLloc(String lloc) {
		this.lloc = lloc;
	}

	public String getPassaportCOVID() {
		return passaportCOVID;
	}

	public void setPassaportCOVID(String passaportCOVID) {
		this.passaportCOVID = passaportCOVID;
	}

	@Override
	public String toString() {
		return "Destinacio [id=" + id + ", preu=" + preu + ", preuOferta=" + preuOferta + ", lloc=" + lloc
				+ ", passaportCOVID=" + passaportCOVID + "]";
	}
	
}
