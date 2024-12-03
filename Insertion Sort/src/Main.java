import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Registro {
        String data;
        String piloto;
        String equipe;
        double tempo;

        public Registro(String data, String piloto, String equipe, double tempo) {
            this.data = data;
            this.piloto = piloto;
            this.equipe = equipe;
            this.tempo = tempo;
        }

        @Override
        public String toString() {
            return data + " | " + piloto + " | " + equipe + " | " + tempo;
        }
    }

    /* Main */
    public static void main(String[] args) throws IOException {

        List<Registro> registros = receber_arquivo();

        insertionSort(registros);

        gerar_arquivo(registros);

        System.out.println("Arquivo feito \uD83D\uDC4D");
    }

    /* Insertion Sort */
    static void insertionSort(List<Registro> registros) {
        for (int i = 1; i < registros.size(); i++) {
            Registro atual = registros.get(i);
            int j = i - 1;

            while (j >= 0 && comparar(registros.get(j), atual) > 0) {
                registros.set(j + 1, registros.get(j));
                j--;
            }
            registros.set(j + 1, atual);
        }
    }

    /* Função de comparação */
    static int comparar(Registro r1, Registro r2) {
        // Tempo
        if (r1.tempo != r2.tempo) {
            return Double.compare(r1.tempo, r2.tempo);
        }
        // Nome
        return r1.piloto.compareTo(r2.piloto);
    }

    /* Extrair texto do arquivo */
    static List<Registro> receber_arquivo() throws IOException {
        String arquivoEntrada = "src/Formula1_InsertionSort.txt";

        List<Registro> dados = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
        String linha;

        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(" - ");

            String dataHora = partes[0];
            String nome = partes[1];
            String empresa = partes[2];
            double tempoVolta = Double.parseDouble(partes[3].replace(":", "").trim());

            dados.add(new Registro(dataHora, nome, empresa, tempoVolta));
        }

        return dados;
    }

    /* Gerar novo arquivo */
    static void gerar_arquivo(List<Registro> registros) throws IOException {
        String arquivoSaida = "src/Empresas_InsertionSort_Ordenadas.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida));
        for (Registro registro : registros) {
            bw.write(registro.toString());
            bw.newLine();
        }
        bw.close();
    }
}
