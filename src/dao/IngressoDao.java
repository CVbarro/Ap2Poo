package dao;

import entidades.evento.Evento;
import entidades.evento.Exposicao;
import entidades.evento.Jogo;
import entidades.evento.Show;


import entidades.ingresso.Ingresso;
import entidades.ingresso.TipoIngresso;
import entidades.ingresso.IngExposicao;
import entidades.ingresso.IngJogo;
import entidades.ingresso.IngShow;

import java.util.List;
import java.util.Scanner;

public class IngressoDao extends EventoDao {

    public void exibirIngressosRestantes(Evento evento, Scanner leitor, int buscaEvento) {
        if (evento == null) {
            System.out.println("Erro: Evento não encontrado.");
        } else if (evento instanceof Exposicao) {
            Exposicao exposicao = (Exposicao) evento;
            System.out.println("Ingressos Meia Restantes: " + exposicao.getIngressosMeia() + "\n" + "Ingressos Inteira Restantes: " + exposicao.getIngressosInteira());
        } else if (evento instanceof Jogo) {
            Jogo jogo = (Jogo) evento;
            System.out.println("Ingressos Meia Restantes: " + jogo.getIngressosMeia() + "\n" + "Ingressos Inteira Restantes: " + jogo.getIngressosInteira());
        } else if (evento instanceof Show) {
            Show show = (Show) evento;
            System.out.println("Ingressos Meia Restantes: " + show.getIngressosMeia() + "\n" + "Ingressos Inteira Restantes: " + show.getIngressosInteira());
        }
    }

    public Ingresso comprarIngresso(Evento evento, Scanner leitor, Ingresso ingresso) {
        String tipo, nomeEventoDigitado;
        TipoIngresso tipoIngresso;
        int quantidade;
        boolean achou = false;

        if (evento == null) {
            System.out.println("Evento ainda não foi cadastrado!");
            return null;
        }

        List<Evento> listarTdsEventos = Evento.getListaEventos();
        if (listarTdsEventos.isEmpty()) {
            System.out.print("Nenhum evento foi cadastrado no momento ! \n");
            return null;
        } else {
            int i = 0;

            System.out.print("Informe o nome do evento: ");
            nomeEventoDigitado = leitor.next();

            for (Evento evt : listarTdsEventos) {
                if (evt.getNome().equalsIgnoreCase(nomeEventoDigitado)) {
                    System.out.print("Informe o tipo do ingresso (meia ou inteira): ");
                    tipo = leitor.next();
                    if (!(tipo.equals("meia") || tipo.equals("inteira"))) {
                        System.out.println("Tipo selecionado inválido!");
                        return null;
                    }

                    tipoIngresso = tipo.equals("meia") ? TipoIngresso.MEIA : TipoIngresso.INTEIRA;

                    System.out.print("Informe quantos ingressos você deseja: ");
                    quantidade = leitor.nextInt();

                    if (!evento.isIngressoDisponivel(tipoIngresso, quantidade)) {
                        System.out.println("Não há ingressos disponíveis desse tipo!");
                        return null;
                    }

                    if (evento instanceof Jogo) {
                        int percentual;

                        System.out.print("Informe o percentual do desconto de sócio torcedor: ");
                        percentual = leitor.nextInt();
                        ingresso = new IngJogo(evento, tipoIngresso, percentual);
                    } else if (evento instanceof Show) {
                        String localizacao;

                        System.out.print("Informe a localização do ingresso (pista ou camarote): ");
                        localizacao = leitor.next();

                        if (!(localizacao.equals("pista") || localizacao.equals("camarote"))) {
                            System.out.println("Localização inválida!");
                            return null;
                        }
                        ingresso = new IngShow(evento, tipoIngresso, localizacao);
                    } else {
                        String desconto;

                        System.out.print("Informe se possui desconto social (s/n): ");
                        desconto = leitor.next();

                        ingresso = new IngExposicao(evento, tipoIngresso, desconto.equals("s"));
                    }

                    achou = true;
                    evento.venderIngresso(tipoIngresso, quantidade);
                    System.out.println("Ingresso vendido com sucesso!");
                }

            }
            if (!achou) {
                System.out.print("\n Nao existe nenhum evento cadastrado com o nome buscado! \n");
            }
            return ingresso;
        }
    }
}

