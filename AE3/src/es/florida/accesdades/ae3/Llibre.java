package es.florida.accesdades.ae3;

public class Llibre {
	
	// identificador, titol, autor, any publicacio, editorial, num pagines
	private int identificador, any_publicacio, numPagines;
	private String titol, autor, editorial;
	
	public Llibre(int identificador, int any_publicacio, int numPagines, String titol, String autor, String editorial) {
		super();
		this.identificador = identificador;
		this.any_publicacio = any_publicacio;
		this.numPagines = numPagines;
		this.titol = titol;
		this.autor = autor;
		this.editorial = editorial;
	}

	// ---- SETTERS
	public void setId(int identificador) {
		this.identificador = identificador;
	}
	
	public void setTitol(String titol) {
		this.titol = titol;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public void setAnyPublicacio(int any_publicacio) {
		this.any_publicacio = any_publicacio;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	public void setNumPagines(int numPagines) {
		this.numPagines = numPagines;
	}
	
	// ---- GETTERS
	public int getId() {
		return this.identificador;
	}
	
	public String getTitol() {
		return this.titol;
	}
	
	public String getAutor() {
		return this.autor;
	}
	
	public int getAnyPublicacio() {
		return this.any_publicacio;
	}
	
	public String getEditorial() {
		return this.editorial;
	}
	
	public int getNumPagines() {
		return this.numPagines;
	}

	@Override
	public String toString() {
		return "Llibre [identificador=" + identificador + ", any_publicacio=" + any_publicacio + ", numPagines="
				+ numPagines + ", titol=" + titol + ", autor=" + autor + ", editorial=" + editorial + "]";
	}

	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/
}
