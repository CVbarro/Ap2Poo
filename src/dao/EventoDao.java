package dao;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

import entidades.evento.Evento;
import entidades.evento.Exposicao;
import entidades.evento.Jogo;
import entidades.evento.Show;

public class EventoDao {
    private List<Evento> listaEventos = new ArrayList<>();

    public void AcaoEventoGeral(Scanner leitor, int buscaEvento) {
    	//buscaEvento = 0 - Consulta evento - default
    	//buscaEvento = 1 - Remove evento
    	//buscaEvento = 2 - Altera evento
    	//buscaEvento = 3 - Busca tipo ingresso meia/inteira

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


    public Evento cadastrarEvento(Scanner leitor) throws IOException {
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
	public void exibirEvento(Evento evento) {
        if (evento == null) {
            System.out.println("Nenhum evento foi cadastrado no momento !");
        } else {
        	listarEvt();
        }
    }
	public void AtualizarEventoPorNome(Scanner leitor, int buscaEvento) {
    	//buscaEvento = 0 - Consulta evento - default
    	//buscaEvento = 1 - Remove evento
    	//buscaEvento = 2 - Altera evento
    	//buscaEvento = 3 - Busca tipo ingresso meia/inteira

    	buscaEvento = 2;
    	AcaoEventoGeral(leitor, buscaEvento);
    }

	public void buscarEventoPorNome(Scanner leitor, int buscaEvento) {
    	//buscaEvento = 0 - Consulta evento - default
    	//buscaEvento = 1 - Remove evento
    	//buscaEvento = 2 - Altera evento
    	//buscaEvento = 3 - Busca tipo ingresso meia/inteira

    	AcaoEventoGeral(leitor, buscaEvento);
    }

	public void RemoverEventoPorNome(Scanner leitor, int buscaEvento) {
    	//buscaEvento = 0 - Consulta evento - default
    	//buscaEvento = 1 - Remove evento
    	//buscaEvento = 2 - Altera evento
    	//buscaEvento = 3 - Busca tipo ingresso meia/inteira

    	buscaEvento = 1;
    	AcaoEventoGeral(leitor, buscaEvento);
    }

	public void gravarArqTxt() throws IOException {
    	try {
    		FileWriter arq = new FileWriter("c:\\Projeto_Eventos.txt");
    		PrintWriter gravarArq = new PrintWriter(arq);
    		List<Evento> eventos = listarEvt();

	   	 	gravarArq.printf("+--Resultado--+%n");
	   	 		if(eventos.isEmpty()) {
	   	 			gravarArq.println("Nao existem eventos cadastrados. \n");
	   	 			System.out.println("Nao existem eventos cadastrados para salvar no txt. \n" +
	   	 				"Arquivo gerado em c:\\Projeto_Eventos.txt. \n" + "Volte sempre !");
	   	 		} else {
	   	 			gravarArq.println(eventos);
	   	 			System.out.println("\n Os dados dos eventos acima foram gravados com sucesso no caminho c:\\Projeto_Eventos.txt \r\n" +
	   	 					"Volte sempre !");
	   	 		}
	   	 	gravarArq.printf("+----Fim do arquivo---------+%n");
    	    arq.close();

		} catch (Exception e) {
			System.out.println("Sem permissao de acesso para gravar o arquivo. \n" +
					"Por favor, execute o programa como administrador do sistema para sanar o problema de acesso.");
		}

    }

}
