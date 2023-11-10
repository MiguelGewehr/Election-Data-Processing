import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entidades.Eleicao;
import entidades.TipoDeputado;
import leitura_escrita.GeradorDeRelatorio;
import leitura_escrita.LeitorDeArquivo;

public class App {
    public static void main(String[] args) {

        TipoDeputado tipoDeputado;

        //Define se o programa vai processar os dados dos deputados estaduais ou federais;
        if (args[0].equals("--federal")) {
            tipoDeputado = TipoDeputado.FEDERAL;
        } else if (args[0].equals("--estadual")) {
            tipoDeputado = TipoDeputado.ESTADUAL;
        } else {
            tipoDeputado = TipoDeputado.OUTROCARGO;
            System.out.println("Erro ao ler o args[0]");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        //Transforma o argumento passado na linha de comanto em LocalDate;
        LocalDate dataEleicao = LocalDate.parse(args[3], formatter);;
        
        //Cria a estrutura Eleicao que vai ser usada para armazenar os dados;
        Eleicao eleicao = new Eleicao(tipoDeputado);

        //Le o arquivo de candidatos;
        LeitorDeArquivo.leituraDosCandidatos(eleicao, args[1]);
        
        //Le o arquivo com os votos;
        LeitorDeArquivo.leituraDeVotos(eleicao, args[2]);

        //Gera os relatorios pedidos na especificação na saída padrão;
        GeradorDeRelatorio.geraRelatorio(eleicao, tipoDeputado, dataEleicao);
    }
}
