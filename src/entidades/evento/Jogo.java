package entidades.evento;

public class Jogo extends Evento {
    private String esporte, equipeCasa, equipeAdversaria;

    public Jogo(String nome, String data, String local, int ingressosMeia, int ingressosInteira, double precoCheio, String esporte, String equipeCasa, String equipeAdversaria) {
        super(nome, data, local, ingressosMeia, ingressosInteira, precoCheio);
        this.esporte = esporte;
        this.equipeAdversaria = equipeAdversaria;
        this.equipeCasa = equipeCasa;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + this.esporte + " - " + this.equipeCasa + " x " + this.equipeAdversaria;
    }
}
