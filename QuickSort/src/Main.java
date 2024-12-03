import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Pedido {
        int id_pedido;
        String data_pedido;
        String nome_cliente;
        double valor_total;
        String status_pedido;
        int quantidade_itens;
        String metodo_pagamento;
        String data_estimada;
        String cidade_cliente;
        String categoria_produto;

        public Pedido(int id_pedido, String data_pedido, String nome_cliente, double valor_total, String status_pedido,
                      int quantidade_itens, String metodo_pagamento, String data_estimada, String cidade_cliente,
                      String categoria_produto) {
            this.id_pedido = id_pedido;
            this.data_pedido = data_pedido;
            this.nome_cliente = nome_cliente;
            this.valor_total = valor_total;
            this.status_pedido = status_pedido;
            this.quantidade_itens = quantidade_itens;
            this.metodo_pagamento = metodo_pagamento;
            this.data_estimada = data_estimada;
            this.cidade_cliente = cidade_cliente;
            this.categoria_produto = categoria_produto;
        }

        @Override
        public String toString() {
            return id_pedido + " | " + data_pedido + " | " + nome_cliente + " | " + valor_total + " | " +
                    status_pedido + " | " + quantidade_itens + " | " + metodo_pagamento + " | " +
                    data_estimada + " | " + cidade_cliente + " | " + categoria_produto;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Pedido> pedidos = receber_arquivo();
        Scanner scanner = new Scanner(System.in);

        System.out.println("------- Deseja ordenar por qual variável: -------");
        System.out.println("1. Valor Total");
        System.out.println("2. Data do Pedido");
        System.out.println("3. Data de Entrega Estimada");
        System.out.println("4. Status do Pedido");
        System.out.println("5. Cidade do Cliente");
        System.out.println("6. Método de Pagamento");
        System.out.println("7. Quantidade de Itens");
        System.out.println();
        System.out.print("Escolha um número: ");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                quickSort(pedidos, 0, pedidos.size() - 1, "valor_total");
                break;
            case 2:
                quickSort(pedidos, 0, pedidos.size() - 1, "data_pedido");
                break;
            case 3:
                quickSort(pedidos, 0, pedidos.size() - 1, "data_estimada");
                break;
            case 4:
                quickSort(pedidos, 0, pedidos.size() - 1, "status_pedido");
                break;
            case 5:
                quickSort(pedidos, 0, pedidos.size() - 1, "cidade_cliente");
                break;
            case 6:
                quickSort(pedidos, 0, pedidos.size() - 1, "metodo_pagamento");
                break;
            case 7:
                quickSort(pedidos, 0, pedidos.size() - 1, "quantidade_itens");
                break;
            default:
                System.out.println("Acho que você digitou errado!");
                return;
        }

        gerar_arquivo(pedidos);
        System.out.println("Tudo certo meu patrão 👍!");
    }

    static void quickSort(List<Pedido> pedidos, int inicio, int fim, String variavel) {
        if (inicio < fim) {
            int particao = particao(pedidos, inicio, fim, variavel);
            quickSort(pedidos, inicio, particao - 1, variavel);
            quickSort(pedidos, particao + 1, fim, variavel);
        }
    }

    static int particao(List<Pedido> pedidos, int inicio, int fim, String variavel) {
        Pedido pivo = pedidos.get(fim);
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            boolean condition = compare(pedidos.get(j), pivo, variavel);
            if (condition) {
                i++;
                Pedido temporario = pedidos.get(i);
                pedidos.set(i, pedidos.get(j));
                pedidos.set(j, temporario);
            }
        }

        Pedido temporario = pedidos.get(i + 1);
        pedidos.set(i + 1, pedidos.get(fim));
        pedidos.set(fim, temporario);

        return i + 1;
    }

    static boolean compare(Pedido a, Pedido b, String criterio) {
        return switch (criterio) {
            case "valor_total" -> a.valor_total > b.valor_total;
            case "data_pedido" -> a.data_pedido.compareTo(b.data_pedido) < 0;
            case "data_estimada" -> a.data_estimada.compareTo(b.data_estimada) < 0;
            case "status_pedido" -> a.status_pedido.compareTo(b.status_pedido) < 0;
            case "cidade_cliente" -> a.cidade_cliente.compareTo(b.cidade_cliente) < 0;
            case "metodo_pagamento" -> a.metodo_pagamento.compareTo(b.metodo_pagamento) < 0;
            case "quantidade_itens" -> a.quantidade_itens > b.quantidade_itens;
            default -> false;
        };
    }

    static List<Pedido> receber_arquivo() throws IOException {
        String arquivoEntrada = "src/pedidos_quicksort.txt";

        List<Pedido> dados = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
        String linha;

        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(" \\| ");

            int id = Integer.parseInt(partes[0]);
            String data_pedido = partes[1];
            String nome_cliente = partes[2];
            double valor_total = Double.parseDouble(partes[3]);
            String status_pedido = partes[4];
            int quantidade_itens = Integer.parseInt(partes[5]);
            String metodo_pagamento = partes[6];
            String data_estimada = partes[7];
            String cidade_cliente = partes[8];
            String categoria_produto = partes[9];

            dados.add(new Pedido(id, data_pedido, nome_cliente, valor_total, status_pedido, quantidade_itens, metodo_pagamento, data_estimada, cidade_cliente, categoria_produto));
        }

        return dados;
    }

    static void gerar_arquivo(List<Pedido> pedidos) throws IOException {
        String arquivoSaida = "src/pedidos_ordenados_quicksort.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida));
        for (Pedido pedido : pedidos) {
            bw.write(pedido.toString());
            bw.newLine();
        }
        bw.close();
    }
}
