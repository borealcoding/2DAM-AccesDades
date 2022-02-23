package es.florida.accesdades.ae5;

public class Destinacio {
	// identificador, titol, autor, any publicacio, editorial, num pagines
	private int identificador;
	private String titol, autor, editorial, anyPublicacio, numPagines, anyNaixement;
	
	public Destinacio() {
		super();
	}
	
	public Destinacio(String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String numPagines) {
		this.titol = titol;
		this.autor = autor;
		this.anyNaixement = anyNaixement;
		this.anyPublicacio = anyPublicacio;
		this.editorial = editorial;
		this.numPagines = numPagines;
	}
	
	
	public Destinacio(int identificador, String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String numPagines) {
		this.identificador = identificador;
		this.titol = titol;
		this.autor = autor;
		this.anyNaixement = anyNaixement;
		this.anyPublicacio = anyPublicacio;
		this.editorial = editorial;
		this.numPagines = numPagines;
	}
	
//	public Llibre(int identificador) {
//		this.identificador = identificador;
//	}

	// ---- SETTERS
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
	public void setTitol(String titol) {
		this.titol = titol;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public void setAnyNaixement(String anyNaixement) {
		this.anyNaixement = anyNaixement;
	}
	
	public void setAnyPublicacio(String anyPublicacio) {
		this.anyPublicacio = anyPublicacio;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	public void setNumPagines(String numPagines) {
		this.numPagines = numPagines;
	}
	
	// ---- GETTERS
	public int getIdentificador() {
		return identificador;
	}
	
	public String getTitol() {
		return this.titol;
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	public String getAnyNaixement() {
		return this.anyNaixement;
	}
	
	public String getAnyPublicacio() {
		return this.anyPublicacio;
	}
	
	public String getEditorial() {
		return this.editorial;
	}
	
	public String getNumPagines() {
		return this.numPagines;
	}

	@Override
	public String toString() {
		return "Llibre [identificador=" + identificador + ", titol=" + titol + ", autor=" + autor + ", editorial="
				+ editorial + ", anyPublicacio=" + anyPublicacio + ", numPagines=" + numPagines + ", anyNaixement="
				+ anyNaixement + "]";
	}

	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/
}
