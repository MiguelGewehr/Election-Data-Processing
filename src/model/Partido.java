package model;

import java.util.Objects;

/**
 * Representa um partido político
 */
public class Partido {
    
    private final String numero;
    private final String sigla;
    
    // Estatísticas de votação
    private int votosNominais = 0;
    private int votosLegenda = 0;
    private int candidatosEleitos = 0;
    
    public Partido(String numero, String sigla) {
        this.numero = Objects.requireNonNull(numero, "Número do partido não pode ser nulo");
        this.sigla = Objects.requireNonNull(sigla, "Sigla do partido não pode ser nula");
    }
    
    // Getters
    public String getNumero() { return numero; }
    public String getSigla() { return sigla; }
    public int getVotosNominais() { return votosNominais; }
    public int getVotosLegenda() { return votosLegenda; }
    public int getTotalVotos() { return votosNominais + votosLegenda; }
    public int getCandidatosEleitos() { return candidatosEleitos; }
    
    // Métodos para atualizar estatísticas
    public void adicionarVotosNominais(int votos) {
        if (votos < 0) {
            throw new IllegalArgumentException("Votos não podem ser negativos");
        }
        this.votosNominais += votos;
    }
    
    public void adicionarVotosLegenda(int votos) {
        if (votos < 0) {
            throw new IllegalArgumentException("Votos não podem ser negativos");
        }
        this.votosLegenda += votos;
    }
    
    public void incrementarCandidatosEleitos() {
        this.candidatosEleitos++;
    }
    
    /**
     * Comparador por total de votos (decrescente) com critério de desempate por número do partido
     */
    public static int compararPorVotosENumero(Partido p1, Partido p2) {
        int comparacaoVotos = Integer.compare(p2.getTotalVotos(), p1.getTotalVotos());
        if (comparacaoVotos != 0) {
            return comparacaoVotos;
        }
        // Em caso de empate, menor número partidário tem prioridade
        try {
            int num1 = Integer.parseInt(p1.getNumero());
            int num2 = Integer.parseInt(p2.getNumero());
            return Integer.compare(num1, num2);
        } catch (NumberFormatException e) {
            return p1.getNumero().compareTo(p2.getNumero());
        }
    }
    
    /**
     * Formata o partido para exibição no relatório
     */
    public String formatarParaRelatorio(int posicao) {
        String candidatosText = candidatosEleitos == 1 ? "candidato eleito" : "candidatos eleitos";
        String nominaisText = votosNominais == 1 ? "nominal" : "nominais";
        
        return String.format("%d - %s - %s, %,d votos (%,d %s e %,d de legenda), %d %s", 
            posicao, sigla, numero, getTotalVotos(), 
            votosNominais, nominaisText, votosLegenda, 
            candidatosEleitos, candidatosText);
    }
    
    @Override
    public String toString() {
        return "Partido{" +
                "numero='" + numero + '\'' +
                ", sigla='" + sigla + '\'' +
                ", votosNominais=" + votosNominais +
                ", votosLegenda=" + votosLegenda +
                ", totalVotos=" + getTotalVotos() +
                ", candidatosEleitos=" + candidatosEleitos +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return Objects.equals(numero, partido.numero);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}