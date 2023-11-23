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
                    cadastrarEvento();
                    break;
                case 2:
                    exibirEvento();
                    break;
                case 3:
                    exibirIngressosRestantes();
                    break;
                case 4:
                    buscarEventoPorNome();
                    break;
                case 5:
                    removerEventoPorNome();
                    break;
                case 6:
                    atualizarEventoPorNome();
                    break;
                case 11:
                    venderIngresso();
                    break;
                default:
                    gravarArqTxt();
                    leitor.close();
                    return 0;
            }
        }
    }

    private static void menu() {
        System.out.println("\nDigite a opção desejada ou qualquer outro valor para sair:");
        System.out.println("1 - Cadastrar um novo evento;");
        System.out.println("2 - Exibir todos os eventos cadastrados;");
        System.out.println("3 - Exibir ingressos restantes;");
        System.out.println("4 - Buscar evento pelo nome;");
        System.out.println("5 - Excluir um evento;");
        System.out.println("6 - Alterar um evento (Somente data e local);");
        System.out.println("11 - Vender um ingresso;");
    }

    public static void cadastrarEvento() throws IOException {
        evento = eventoDao.cadastrarEvento(leitor);
        if (evento != null) {
            System.out.println("Evento cadastrado com sucesso!");
        }
    }

    public static void exibirEvento() {
        eventoDao.exibirEvento(evento);
    }

    public static void exibirIngressosRestantes() {
        ingressoDao.exibirIngressosRestantes(evento, leitor, 0);
    }

    public static void buscarEventoPorNome() {
        eventoDao.buscarEventoPorNome(leitor, 0);
    }

    public static void removerEventoPorNome() {
        eventoDao.RemoverEventoPorNome(leitor, 1);
    }

    public static void atualizarEventoPorNome() {
        eventoDao.AtualizarEventoPorNome(leitor, 2);
    }

    public static void venderIngresso() {
        ingresso = ingressoDao.venderIngresso(evento, leitor, ingresso);
    }

    public static  void gravarArqTxt() throws IOException {
        eventoDao.gravarArqTxt();
    }

}
