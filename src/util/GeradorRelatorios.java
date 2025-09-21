package util;

import model.Candidato;
import model.Partido;
import model.enums.Genero;
import model.enums.TipoDeputado;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Responsável por gerar todos os relatórios do sistema eleitoral
 */
public class GeradorRelatorios {
    
    private final List<Candidato> candidatos;
    private final Map<String, Partido> partidos;
    private final LocalDate dataEleicao;
    private final TipoDeputado tipoDeputado;
    private final int numeroVagas;
    
    public GeradorRelatorios(List<Candidato> candidatos, Map<String, Partido> partidos, 
                           LocalDate dataEleicao, TipoDeputado tipoDeputado) {
        this.candidatos = candidatos;
        this.partidos = partidos;
        this.dataEleicao = dataEleicao;
        this.tipoDeputado = tipoDeputado;
        this.numeroVagas = (int) candidatos.stream().filter(Candidato::isEleito).count();
    }
    
    public void gerarTodosRelatorios() {
        relatorio1NumeroVagas();
        relatorio2CandidatosEleitos();
        relatorio3CandidatosMaisVotados();
        relatorio4TeriamSidoEleitos();
        relatorio5BeneficiadosSistemaProporcional();
        relatorio6VotacaoPartidos();
        relatorio7PrimeiroUltimoColocados();
        relatorio8DistribuicaoIdade();
        relatorio9DistribuicaoGenero();
        relatorio10TotalVotos();
    }
    
    private void relatorio1NumeroVagas() {
        System.out.println("Número de vagas: " + numeroVagas);
        System.out.println();
    }
    
    private void relatorio2CandidatosEleitos() {
        String tipoTexto = tipoDeputado == TipoDeputado.FEDERAL ? "federais" : "estaduais";
        System.out.println("Deputados " + tipoTexto + " eleitos:");
        
        List<Candidato> eleitos = candidatos.stream()
            .filter(Candidato::isEleito)
            .sorted(Candidato::compararPorVotosEIdade)
            .collect(Collectors.toList());
        
        for (int i = 0; i < eleitos.size(); i++) {
            Candidato candidato = eleitos.get(i);
            String prefixoFederacao = candidato.pertenceFederacao() ? "*" : "";
            System.out.println(String.format("%d - %s%s (%s, %,d votos)",
                i + 1, prefixoFederacao, candidato.getNomeUrna(),
                candidato.getPartido().getSigla(), candidato.getVotosRecebidos()));
        }
        System.out.println();
    }
    
    private void relatorio3CandidatosMaisVotados() {
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        
        List<Candidato> maisVotados = candidatos.stream()
            .sorted(Candidato::compararPorVotosEIdade)
            .limit(numeroVagas)
            .collect(Collectors.toList());
        
        for (int i = 0; i < maisVotados.size(); i++) {
            Candidato candidato = maisVotados.get(i);
            String prefixoFederacao = candidato.pertenceFederacao() ? "*" : "";
            System.out.println(String.format("%d - %s%s (%s, %,d votos)",
                i + 1, prefixoFederacao, candidato.getNomeUrna(),
                candidato.getPartido().getSigla(), candidato.getVotosRecebidos()));
        }
        System.out.println();
    }
    
    private void relatorio4TeriamSidoEleitos() {
        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:");
        System.out.println("(com sua posição no ranking de mais votados)");
        
        List<Candidato> candidatosOrdenados = candidatos.stream()
            .sorted(Candidato::compararPorVotosEIdade)
            .collect(Collectors.toList());
        
        Set<String> eleitosSet = candidatos.stream()
            .filter(Candidato::isEleito)
            .map(c -> c.getNumeroCandidato())
            .collect(Collectors.toSet());
        
        int posicao = 1;
        int encontrados = 0;
        
        for (Candidato candidato : candidatosOrdenados) {
            if (posicao <= numeroVagas && !eleitosSet.contains(candidato.getNumeroCandidato())) {
                String prefixoFederacao = candidato.pertenceFederacao() ? "*" : "";
                System.out.println(String.format("%d - %s%s (%s, %,d votos)",
                    posicao, prefixoFederacao, candidato.getNomeUrna(),
                    candidato.getPartido().getSigla(), candidato.getVotosRecebidos()));
                encontrados++;
            }
            posicao++;
            if (posicao > numeroVagas && encontrados > 0) break;
        }
        
        if (encontrados == 0) {
            System.out.println("Nenhum candidato nesta situação.");
        }
        System.out.println();
    }
    
    private void relatorio5BeneficiadosSistemaProporcional() {
        System.out.println("Eleitos, que se beneficiaram do sistema proporcional:");
        System.out.println("(com sua posição no ranking de mais votados)");
        
        List<Candidato> candidatosOrdenados = candidatos.stream()
            .sorted(Candidato::compararPorVotosEIdade)
            .collect(Collectors.toList());
        
        Set<String> eleitosSet = candidatos.stream()
            .filter(Candidato::isEleito)
            .map(c -> c.getNumeroCandidato())
            .collect(Collectors.toSet());
        
        List<String> beneficiados = new ArrayList<>();
        
        for (int i = 0; i < candidatosOrdenados.size(); i++) {
            Candidato candidato = candidatosOrdenados.get(i);
            int posicao = i + 1;
            
            if (posicao > numeroVagas && eleitosSet.contains(candidato.getNumeroCandidato())) {
                String prefixoFederacao = candidato.pertenceFederacao() ? "*" : "";
                beneficiados.add(String.format("%d - %s%s (%s, %,d votos)",
                    posicao, prefixoFederacao, candidato.getNomeUrna(),
                    candidato.getPartido().getSigla(), candidato.getVotosRecebidos()));
            }
        }
        
        for (String linha : beneficiados) {
            System.out.println(linha);
        }
        
        if (beneficiados.isEmpty()) {
            System.out.println("Nenhum candidato nesta situação.");
        }
        System.out.println();
    }
    
    private void relatorio6VotacaoPartidos() {
        System.out.println("Votação dos partidos e número de candidatos eleitos:");
        
        // Conta candidatos eleitos por partido
        Map<String, Integer> eleitosPorPartido = new HashMap<>();
        for (Candidato candidato : candidatos) {
            if (candidato.isEleito()) {
                String sigla = candidato.getPartido().getSigla();
                eleitosPorPartido.merge(sigla, 1, Integer::sum);
            }
        }
        
        // Calcula votos nominais por partido (excluindo os redirecionados para legenda)
        Map<String, Integer> votosNominaisPorPartido = new HashMap<>();
        for (Candidato candidato : candidatos) {
            if (!candidato.votosVaoParaLegenda()) {
                String sigla = candidato.getPartido().getSigla();
                votosNominaisPorPartido.merge(sigla, candidato.getVotosRecebidos(), Integer::sum);
            }
        }
        
        List<Partido> partidosOrdenados = partidos.values().stream()
            .sorted(Partido::compararPorVotosENumero)
            .collect(Collectors.toList());
        
        for (int i = 0; i < partidosOrdenados.size(); i++) {
            Partido partido = partidosOrdenados.get(i);
            int eleitos = eleitosPorPartido.getOrDefault(partido.getSigla(), 0);
            int nominais = votosNominaisPorPartido.getOrDefault(partido.getSigla(), 0);
            
            String candidatosText = eleitos == 1 ? "candidato eleito" : "candidatos eleitos";
            String nominaisText = nominais == 1 ? "nominal" : "nominais";
            
            System.out.println(String.format("%d - %s - %s, %,d votos (%,d %s e %,d de legenda), %d %s",
                i + 1, partido.getSigla(), partido.getNumero(), partido.getTotalVotos(),
                nominais, nominaisText, partido.getVotosLegenda(),
                eleitos, candidatosText));
        }
        System.out.println();
    }
    
    private void relatorio7PrimeiroUltimoColocados() {
        System.out.println("Primeiro e último colocados de cada partido:");
        
        // Agrupa candidatos por partido e filtra apenas os com votos > 0
        Map<String, List<Candidato>> candidatosPorPartido = candidatos.stream()
            .filter(c -> c.getVotosRecebidos() > 0)
            .collect(Collectors.groupingBy(c -> c.getPartido().getSigla()));
        
        // Remove partidos sem candidatos com votos válidos
        candidatosPorPartido.entrySet().removeIf(entry -> entry.getValue().isEmpty());
        
        // Calcula o candidato mais votado de cada partido para ordenação
        Map<String, Integer> maisVotadoPorPartido = new HashMap<>();
        for (Map.Entry<String, List<Candidato>> entry : candidatosPorPartido.entrySet()) {
            int maxVotos = entry.getValue().stream()
                .mapToInt(Candidato::getVotosRecebidos)
                .max()
                .orElse(0);
            maisVotadoPorPartido.put(entry.getKey(), maxVotos);
        }
        
        // Ordena partidos por votos do mais votado (decrescente) e depois por número
        List<String> partidosOrdenados = candidatosPorPartido.keySet().stream()
            .sorted((p1, p2) -> {
                int comp = Integer.compare(
                    maisVotadoPorPartido.get(p2),
                    maisVotadoPorPartido.get(p1)
                );
                if (comp != 0) return comp;
                
                // Em caso de empate, menor número partidário tem prioridade
                return partidos.get(getNumeroPartido(p1)).getNumero()
                    .compareTo(partidos.get(getNumeroPartido(p2)).getNumero());
            })
            .collect(Collectors.toList());
        
        int posicao = 1;
        for (String siglaPartido : partidosOrdenados) {
            List<Candidato> candidatosDoPartido = candidatosPorPartido.get(siglaPartido);
            
            // Ordena candidatos do partido por votos (decrescente) e idade (mais velho primeiro)
            candidatosDoPartido.sort(Candidato::compararPorVotosEIdade);
            
            Candidato primeiro = candidatosDoPartido.get(0);
            Candidato ultimo = candidatosDoPartido.get(candidatosDoPartido.size() - 1);
            
            System.out.println(String.format("%d - %s - %s, %s (%s, %,d votos) / %s (%s, %,d votos)",
                posicao, siglaPartido, primeiro.getPartido().getNumero(),
                primeiro.getNomeUrna(), primeiro.getNumeroCandidato(), primeiro.getVotosRecebidos(),
                ultimo.getNomeUrna(), ultimo.getNumeroCandidato(), ultimo.getVotosRecebidos()));
            
            posicao++;
        }
        System.out.println();
    }
    
    private String getNumeroPartido(String sigla) {
        return partidos.values().stream()
            .filter(p -> p.getSigla().equals(sigla))
            .findFirst()
            .map(Partido::getNumero)
            .orElse("");
    }
    
    private void relatorio8DistribuicaoIdade() {
        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        
        Map<String, Integer> faixasEtarias = new LinkedHashMap<>();
        faixasEtarias.put("Idade < 30", 0);
        faixasEtarias.put("30 <= Idade < 40", 0);
        faixasEtarias.put("40 <= Idade < 50", 0);
        faixasEtarias.put("50 <= Idade < 60", 0);
        faixasEtarias.put("60 <= Idade", 0);
        
        for (Candidato candidato : candidatos) {
            if (candidato.isEleito()) {
                int idade = candidato.getIdade(dataEleicao);
                
                if (idade < 30) {
                    faixasEtarias.merge("Idade < 30", 1, Integer::sum);
                } else if (idade < 40) {
                    faixasEtarias.merge("30 <= Idade < 40", 1, Integer::sum);
                } else if (idade < 50) {
                    faixasEtarias.merge("40 <= Idade < 50", 1, Integer::sum);
                } else if (idade < 60) {
                    faixasEtarias.merge("50 <= Idade < 60", 1, Integer::sum);
                } else {
                    faixasEtarias.merge("60 <= Idade", 1, Integer::sum);
                }
            }
        }
        
        for (Map.Entry<String, Integer> entry : faixasEtarias.entrySet()) {
            double percentual = (entry.getValue() * 100.0) / numeroVagas;
            System.out.println(String.format("%s: %d (%.2f%%)",
                entry.getKey(), entry.getValue(), percentual));
        }
        System.out.println();
    }
    
    private void relatorio9DistribuicaoGenero() {
        System.out.println("Eleitos, por gênero:");
        
        long feminino = candidatos.stream()
            .filter(Candidato::isEleito)
            .filter(c -> c.getGenero() == Genero.FEMININO)
            .count();
        
        long masculino = numeroVagas - feminino;
        
        double percentualFeminino = (feminino * 100.0) / numeroVagas;
        double percentualMasculino = (masculino * 100.0) / numeroVagas;
        
        System.out.println(String.format("Feminino: %d (%.2f%%)", feminino, percentualFeminino));
        System.out.println(String.format("Masculino: %d (%.2f%%)", masculino, percentualMasculino));
        System.out.println();
    }
    
    private void relatorio10TotalVotos() {
        System.out.println("Total de votos válidos: " + String.format("%,d", calcularTotalVotos()));
        System.out.println("Total de votos nominais: " + String.format("%,d", calcularVotosNominais()) + 
                          String.format(" (%.2f%%)", calcularPercentualNominais()));
        System.out.println("Total de votos de legenda: " + String.format("%,d", calcularVotosLegenda()) + 
                          String.format(" (%.2f%%)", calcularPercentualLegenda()));
    }
    
    private int calcularTotalVotos() {
        return calcularVotosNominais() + calcularVotosLegenda();
    }
    
    private int calcularVotosNominais() {
        return candidatos.stream()
            .filter(c -> !c.votosVaoParaLegenda())
            .mapToInt(Candidato::getVotosRecebidos)
            .sum();
    }
    
    private int calcularVotosLegenda() {
        return partidos.values().stream()
            .mapToInt(Partido::getVotosLegenda)
            .sum();
    }
    
    private double calcularPercentualNominais() {
        int total = calcularTotalVotos();
        return total > 0 ? (calcularVotosNominais() * 100.0) / total : 0.0;
    }
    
    private double calcularPercentualLegenda() {
        int total = calcularTotalVotos();
        return total > 0 ? (calcularVotosLegenda() * 100.0) / total : 0.0;
    }
}