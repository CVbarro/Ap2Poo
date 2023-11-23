package entidades.evento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entidades.ingresso.TipoIngresso;

public abstract class Evento {
    private String nome;
    private LocalDate dataFinal;
    private String local;
	private int ingressosMeia;
	private int ingressosInteira;
    private double precoCheio;
    private static List<Evento> listaEventos = new ArrayList<>();

	public Evento(String nome, LocalDate dataFinal, String local, int ingressosMeia, int ingressosInteira, double precoCheio) {
        this.nome = nome;
        this.dataFinal = dataFinal;
        this.local = local;
        this.ingressosMeia = ingressosMeia;
        this.ingressosInteira = ingressosInteira;
        this.precoCheio = precoCheio;
    }

    public double getPrecoCheio() {
        return this.precoCheio;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIngressos() {
        return this.ingressosInteira + this.ingressosMeia;
    }

    public LocalDate getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}


    public boolean isIngressoDisponivel(TipoIngresso tipo, int quantidade) {
        if (tipo.equals(TipoIngresso.MEIA)) {
            return quantidade <= this.ingressosMeia;
        }

        return quantidade <= this.ingressosInteira;
    }

    public void venderIngresso(TipoIngresso tipo, int quantidade) {
        if (this.isIngressoDisponivel(tipo, quantidade)) {
            if (tipo.equals(TipoIngresso.MEIA)) {
                this.ingressosMeia -= quantidade;
            } else {
                this.ingressosInteira -= quantidade;
            }
        }
    }

    public void adicionarEvento(Evento evento) {
		listaEventos.add(evento);
	}

	public static List<Evento> getListaEventos() {
		return listaEventos;
	}

	public int getIngressosMeia() {
		return ingressosMeia;
	}

	public int getIngressosInteira() {
		return ingressosInteira;
	}

    @Override
    public String toString() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "\r Nome do evento : " + this.nome
        			+ "\r\n Data do evento: " + this.dataFinal.format(formatter)
        			+ "\r\n Local do evento: " + this.local;
    }
}
