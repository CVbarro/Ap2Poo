package dao;

import entidades.evento.Evento;
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
        // buscaEvento = 0 - Consulta evento - default
        // buscaEvento = 1 - Remove evento
        // buscaEvento = 2 - Altera evento
        // buscaEvento = 3 - Busca tipo ingresso meia/inteira

        // Remove this line, and use the value passed as an argument
        // buscaEvento = 3;

        if (evento == null) {
            System.out.println("Evento ainda não foi cadastrado!");
        } else {
            // Use the value passed as an argument
            AcaoEventoGeral(leitor, buscaEvento);
        }
    }

    public Ingresso venderIngresso(Evento evento, Scanner leitor, Ingresso ingresso) {
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

