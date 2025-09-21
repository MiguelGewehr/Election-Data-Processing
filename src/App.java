import model.Candidato;
import model.Partido;
import model.enums.TipoDeputado;
import util.LeitorCandidatos;
import util.LeitorVotacao;
import util.ValidadorArgumentos;
import util.GeradorRelatorios;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class App {
    
    public static void main(String[] args) {
        try {
            // Valida argumentos
            ValidadorArgumentos.validar(args);
            
            // Extrai parâmetros
            TipoDeputado tipoDeputado = args[0].equals("--federal") 
                ? TipoDeputado.FEDERAL 
                : TipoDeputado.ESTADUAL;
            String arquivoCandidatos = args[1];
            String arquivoVotacao = args[2];
            LocalDate dataEleicao = ValidadorArgumentos.parseData(args[3]);
            
            // Processa dados
            List<Candidato> candidatos = LeitorCandidatos.lerCandidatos(arquivoCandidatos, tipoDeputado);
            Map<String, Partido> partidos = LeitorCandidatos.getPartidosCarregados();
            LeitorVotacao.lerVotacao(arquivoVotacao, candidatos, partidos, tipoDeputado);
            
            // Gera relatórios
            GeradorRelatorios gerador = new GeradorRelatorios(candidatos, partidos, dataEleicao, tipoDeputado);
            gerador.gerarTodosRelatorios();
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            System.exit(1);
        }
    }
}