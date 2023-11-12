package entidades.evento;

public class Show extends Evento {
    private String artista, genero;

    public Show(String nome, String data, String local, int ingressosMeia, int ingressosInteira, double precoCheio, String artista, String genero) {
        super(nome, data, local, ingressosMeia, ingressosInteira, precoCheio);
        this.artista = artista;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + this.artista + " - " + this.genero;
    }
}
