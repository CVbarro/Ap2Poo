package dao;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import entidades.evento.Evento;
import entidades.evento.Exposicao;
import entidades.evento.Jogo;
import entidades.evento.Show;

public class EventoDao {
    private List<Evento> listaEventos = new ArrayList<>();

    public void AcaoEventoGeral(Scanner leitor, int buscaEvento) {
    	LocalDate dataFinal = null;
    	List<Evento> listarTdsEventos = Evento.getListaEventos();
    	if (listarTdsEventos.isEmpty()) {
    		System.out.print("Nenhum evento foi cadastrado no momento ! \n");
    	} else {
    		String nomeEventoDigitado, localDigitado, dataDigitada;
    		boolean achou = false;
    		System.out.print("Informe o nome do evento: ");
    		nomeEventoDigitado = leitor.next();

    		int i = 0;
    		for (Evento evento : listarTdsEventos) {
    				if(evento.getNome().equalsIgnoreCase(nomeEventoDigitado)) {
    					if (buscaEvento == 0) {
    						System.out.print("\n Os dados do evento buscado sao : \n");
    						System.out.println(evento);
    					} else if (buscaEvento == 1) {
    						listarTdsEventos.remove(i);
    						System.out.print("\n Evento removido com sucesso \n");
    						achou = true;
    						break;
    					} else if (buscaEvento == 2){
    						System.out.println(evento);
    						System.out.print("\n O evento digitado esta listado acima. \n");
    						System.out.print("Informe o novo valor do campo local: ");
    			    		localDigitado = leitor.next();
    			    		System.out.print("Informe o novo valor do campo data: ");
    			    		dataDigitada = leitor.next();
    			    		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    			            try {
    			            	dataFinal = LocalDate.parse(dataDigitada, formatacao);
    			            	evento.setLocal(localDigitado);
    			            	evento.setDataFinal(dataFinal);
    			    		} catch (Exception e) {
    			    			System.out.println("Data no formato errado ! Tente novamente ! ");
    			    			System.out.println("----------------------------------------- \n ");
    			    			achou = true;
    			    			break;
    			    		}
    						System.out.print("\n Campos do evento atualizados com sucesso \n");
    					} else if (buscaEvento == 3){
    						System.out.print("\n O evento digitado apresenta:");
    						System.out.print("\n Ingresso tipo Meia: " + evento.getIngressosMeia());
    						System.out.print("\n Ingresso tipo Inteira: " + evento.getIngressosInteira() + " \n");
    						}
    					achou = true;
    				}
    				i++;
			}
    		if(!achou) {
    			System.out.print("\n Nao existe nenhum evento cadastrado com o nome buscado! \n");
    		}
    	}

	}

	public List<Evento> listarEvt() {
		List<Evento> listarTdsEventos = Evento.getListaEventos();
    	if(listarTdsEventos.isEmpty()) {
    		System.out.println("Nenhum evento foi cadastrado no momento !");
    	} else {
    		for (Evento eventosCadastrados : listarTdsEventos) {
    			System.out.println(eventosCadastrados);
    		}
    	}
		return listarTdsEventos;
	}


    public Evento cadastroDeEvento(Scanner leitor) throws IOException {
        String nome, data, local, tipo;
        LocalDate dataFinal = LocalDate.now();
        int ingMeia, ingInteira;
        double preco;

        System.out.print("Informe o nome do evento: ");
        nome = leitor.next();
        System.out.print("Informe a data do evento: (dd/mm/yyyy) ");
        data = leitor.next();
        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
        	dataFinal = LocalDate.parse(data, formatacao);
		} catch (Exception e) {
			System.out.println("Data no formato errado ! Tente novamente ! ");
			System.out.println("----------------------------------------- \n ");
			return null;
		}
        System.out.print("Informe o local do evento: ");
        local = leitor.next();
        System.out.print("Informe o número de entradas tipo meia: ");
        ingMeia = leitor.nextInt();
        System.out.print("Informe o número de entradas tipo inteira: ");
        ingInteira = leitor.nextInt();
        System.out.print("Informe o preço cheio do evento: ");
        preco = leitor.nextDouble();
        System.out.print("Informe o tipo do evento (show, jogo ou exposição): ");
        tipo = leitor.next();

        if (tipo.equals("show")) {

        	String artista = null;
            String genero = null;
            Show show = new Show(nome, dataFinal, local, ingMeia, ingInteira, preco, artista, genero);

            System.out.print("Informe o nome do artista: ");
            show.setArtista(leitor.next());
            System.out.print("Informe o gênero do show: ");
            show.setGenero(leitor.next());

            show.adicionarEvento(show);

            return show;
        }

        else if (tipo.equals("jogo")) {

        	Jogo jogo = new Jogo(nome, dataFinal, local, ingMeia, ingInteira, preco, data, local, tipo);

            System.out.print("Informe o esporte: ");
            jogo.setEsporte(leitor.next());
            System.out.print("Informe a equipe da casa: ");
            jogo.setEquipeCasa(leitor.next());
            System.out.print("Informe a equipe adversária: ");
            jogo.setEquipeAdversaria(leitor.next());

            jogo.adicionarEvento(jogo);

            return jogo;

        } else {

	        int idadeMin = 0, duracao = 0;

	        Exposicao exposicao = new Exposicao(nome, dataFinal, local, ingMeia, ingInteira, preco, idadeMin, duracao);

	        System.out.print("Informe a idade mínima para entrar na exposição: ");
	        exposicao.setFaixaEtariaMinima(leitor.nextInt());
	        System.out.print("Informe a duração em dias da exposição: ");
	        exposicao.setDuracaoDias(leitor.nextInt());

	        exposicao.adicionarEvento(exposicao);

	        return exposicao;
        }
    }
	public void listarEventos(Evento evento) {
        if (evento == null) {
            System.out.println("Nenhum evento foi cadastrado no momento !");
        } else {
        	listarEvt();
        }
    }
	public void atualizarEventoPorNome(Scanner leitor, int buscaEvento) {
    	buscaEvento = 2;
    	AcaoEventoGeral(leitor, buscaEvento);
    }

	public void buscarEventoPorNome(Scanner leitor, int buscaEvento) {
    	AcaoEventoGeral(leitor, buscaEvento);
    }

	public void removerEvento(Scanner leitor, int buscaEvento) {
    	buscaEvento = 1;
    	AcaoEventoGeral(leitor, buscaEvento);
    }
    
	public void salvarEventosParaArquivo(List<Evento> eventos) {
		String nomeArquivo = "Eventos.txt";
		String diretorioProjeto = System.getProperty("user.dir");

		String caminhoCompleto = diretorioProjeto + File.separator + nomeArquivo;

		try (PrintWriter gravarArq = new PrintWriter(new BufferedWriter(new FileWriter(caminhoCompleto)))) {
			gravarArq.println("+--Lista de Eventos--+");

			if (eventos.isEmpty()) {
				gravarArq.println("Nenhum evento cadastrado.");
				System.out.println("Nenhum evento cadastrado para salvar no arquivo.");
			} else {
				for (Evento evento : eventos) {
					gravarArq.println(evento.toString());
					gravarArq.println("---------------------------------");
				}
				System.out.println("Os dados dos eventos foram salvos com sucesso no arquivo: " + caminhoCompleto);
			}

			gravarArq.println("+--Fim da Lista de Eventos--+");
		} catch (IOException e) {
			System.out.println("Erro ao salvar os eventos no arquivo: " + e.getMessage());
		}
	}
}
