package cli;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import dao.EventoDao;
import dao.IngressoDao;
import entidades.evento.Evento;
import entidades.evento.Exposicao;
import entidades.evento.Jogo;
import entidades.evento.Show;
import entidades.ingresso.Ingresso;

public class Cli {
    private static Evento evento = null;
    private static Ingresso ingresso = null;
    private static Scanner leitor = new Scanner(System.in);
    private static EventoDao eventoDao = new EventoDao();
    private static IngressoDao ingressoDao = new IngressoDao();

    public static int executar() throws IOException {
        int opcao;

        System.out.println("Seja bem-vindo ao programa de venda de ingressos de eventos!");

        while (true) {
            menu();
            opcao = leitor.nextInt();
            switch (opcao) {
                case 1:
                    cadastroDeEvento();
                    break;
                case 2:
                    listarEventos();
                    break;
                case 3:
                    exibirIngressosRestantes();
                    break;
                case 4:
                    buscarEventoPorNome();
                    break;
                case 5:
                    removerEvento();
                    break;
                case 6:
                    atualizarEventoPorNome();
                    break;
                case 7:
                    comprarIngresso();
                    break;
                case 0:
                    salvarEventosParaArquivo();
                    System.out.println("Programa encerrado.");
                    return opcao;
                default:
                    break;
            }
        }
    }

    private static void menu() {
        System.out.println("\nDigite a opção desejada ou qualquer outro valor para sair:");
        System.out.println("1 - Criar um novo evento;");
        System.out.println("2 - Listar todos os eventos;");
        System.out.println("3 - Exibir quantidade ingressos restantes;");
        System.out.println("4 - Buscar evento pelo nome;");
        System.out.println("5 - Excluir um evento pelo nome;");
        System.out.println("6 - Alterar dados de um evento;");
        System.out.println("7 - Comprar ingressos;");
        System.out.println("0 - Encerrar programa;");
    }

    public static void cadastroDeEvento() throws IOException {
        evento = eventoDao.cadastroDeEvento(leitor);
        if (evento != null) {
            System.out.println("Evento cadastrado com sucesso!");
        }
    }

    public static void salvarEventosParaArquivo() {
        List<Evento> eventos = eventoDao.listarEvt();
        eventoDao.salvarEventosParaArquivo(eventos);
    }

    public static void listarEventos() {
        eventoDao.listarEventos(evento);
    }

    public static void exibirIngressosRestantes() {
        ingressoDao.exibirIngressosRestantes(evento, leitor, 0);
    }

    public static void buscarEventoPorNome() {
        eventoDao.buscarEventoPorNome(leitor, 0);
    }

    public static void removerEvento() {
        eventoDao.removerEvento(leitor, 1);
    }

    public static void atualizarEventoPorNome() {
        eventoDao.atualizarEventoPorNome(leitor, 2);
    }

    public static void comprarIngresso() {
        ingresso = ingressoDao.comprarIngresso(evento, leitor, ingresso);
    }

}
