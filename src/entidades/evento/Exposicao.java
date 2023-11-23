package entidades.evento;

import java.time.LocalDate;
import java.util.List;

public class Exposicao extends Evento {
    private int faixaEtariaMinima;
	private int duracaoDias;

    public Exposicao(String nome, LocalDate dataFinal, String local, int ingressosMeia, int ingressosInteira, double precoCheio, int faixaEtariaMinima, int duracaoDias) {
        super(nome, dataFinal, local, ingressosMeia, ingressosInteira, precoCheio);
        this.faixaEtariaMinima = faixaEtariaMinima;
        this.duracaoDias = duracaoDias;
    }

    public int getFaixaEtariaMinima() {
    	return faixaEtariaMinima;
    }
    
    public void setFaixaEtariaMinima(int faixaEtariaMinima) {
    	this.faixaEtariaMinima = faixaEtariaMinima;
    }
    
    public int getDuracaoDias() {
    	return duracaoDias;
    }
    
    public void setDuracaoDias(int duracaoDias) {
    	this.duracaoDias = duracaoDias;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nIdade mínima: " + this.faixaEtariaMinima + "\nDuração: " + this.duracaoDias + " dias";
    }
}
