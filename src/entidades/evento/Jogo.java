package entidades.evento;

import java.time.LocalDate;
import java.util.List;

public class Jogo extends Evento {
    private String esporte, equipeCasa, equipeAdversaria;


	public Jogo(String nome, LocalDate dataFinal, String local, int ingressosMeia, int ingressosInteira, double precoCheio, String esporte, String equipeCasa, String equipeAdversaria) {
        super(nome, dataFinal, local, ingressosMeia, ingressosInteira, precoCheio);
        this.esporte = esporte;
        this.equipeAdversaria = equipeAdversaria;
        this.equipeCasa = equipeCasa;
    }

    
    public String getEsporte() {
    	return esporte;
    }
    
    public void setEsporte(String esporte) {
    	this.esporte = esporte;
    }
    
    public String getEquipeCasa() {
    	return equipeCasa;
    }
    
    public void setEquipeCasa(String equipeCasa) {
    	this.equipeCasa = equipeCasa;
    }
    
    public String getEquipeAdversaria() {
    	return equipeAdversaria;
    }
    
    public void setEquipeAdversaria(String equipeAdversaria) {
    	this.equipeAdversaria = equipeAdversaria;
    }
    
    @Override
    public String toString() {
    	return super.toString() + "\n Esporte : " + this.esporte 
    			+ " \n Equipe da casa: " + this.equipeCasa 
    			+ " x Equipe adversaria : " + this.equipeAdversaria;
    }
}
