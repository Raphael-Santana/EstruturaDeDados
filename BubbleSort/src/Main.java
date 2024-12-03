import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /* Classe para armazenar o registro */
    static class Registro {
        String nomeFantasia;
        String inscricaoEstadual;
        double valorMercado;

        /* Contrutor */
        public Registro(String nomeFantasia, String inscricaoEstadual, double valorMercado) {
            this.nomeFantasia = nomeFantasia;
            this.inscricaoEstadual = inscricaoEstadual;
            this.valorMercado = valorMercado;
        }

        @Override
        public String toString() {
            return nomeFantasia + " | " + inscricaoEstadual + " | " + valorMercado;
        }
    }

    /* Main - roda o código e suas funções */
    public static void main(String[] args) throws IOException {

        List<Registro> registros = receber_arquivo();
        bubbleSort(registros);
        gerar_arquivo(registros);

        System.out.println("Arquivo feito \uD83D\uDC4D");
    }

    /* Extrair texto do arquivo */
    static List<Registro> receber_arquivo() throws IOException {
        String arquivoEntrada = "src/Empresas_BubbleSort.txt";

        List<Registro> dados = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(" \\| ");
            String nomeFantasia = partes[0];
            String inscricaoEstadual = partes[1];
            double valorDeMercado = Double.parseDouble(partes[2]);
            dados.add(new Registro(nomeFantasia, inscricaoEstadual, valorDeMercado));
        }
        return dados;
    }

    /* BubbleSor - Organizar o arquivo bruto */
    static void bubbleSort(List<Registro> registros) {
        int n = registros.size();
        int i, j;
        Registro tmp;
        for (i = 0; i < n - 1; i++) {
            for (j = n - 1; j > i; j--) {
                if (registros.get(j).valorMercado > registros.get(j - 1).valorMercado) {
                    tmp = registros.get(j - 1);
                    registros.set(j - 1, registros.get(j));
                    registros.set(j, tmp);
                }
            }
        }
    }

    /* Gerar novo arquivo */
    static void gerar_arquivo(List<Registro> registros) throws IOException {
        String arquivoSaida = "src/Empresas_BubbleSort_Ordenadas.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida));
        for (Registro registro : registros) {
            bw.write(registro.toString());
            bw.newLine();
        }
    }
}
