package entidades.evento;

import java.time.LocalDate;
import java.util.List;

public class Show extends Evento {
    private String artista, genero;

	public Show(String nome, LocalDate dataFinal, String local, int ingressosMeia, int ingressosInteira, double precoCheio, String artista, String genero) {
        super(nome, dataFinal, local, ingressosMeia, ingressosInteira, precoCheio);
        this.artista = artista;
        this.genero = genero;
    }

	public String getArtista() {
		return artista;
	}
	
	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
    @Override
    public String toString() {
        return super.toString() + "\n Nome do artista: " + this.artista + "\n Genero do evento: " + this.genero;
    }
}
