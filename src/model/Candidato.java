package model;

import model.enums.Genero;
import model.enums.TipoDeputado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.Objects;

/**
 * Representa um candidato no sistema eleitoral
 */
public class Candidato {
    
    private final TipoDeputado cargo;
    private final String numeroCandidato;
    private final String nomeUrna;
    private final Partido partido;
    private final Federacao federacao;
    private final LocalDate dataNascimento;
    private final Genero genero;
    private final boolean eleito;
    private final boolean votosVaoParaLegenda;
    
    // Votos recebidos (será preenchido na leitura do arquivo de votação)
    private int votosRecebidos = 0;
    
    public Candidato(TipoDeputado cargo, String numeroCandidato, String nomeUrna, 
                    Partido partido, Federacao federacao, LocalDate dataNascimento,
                    Genero genero, boolean eleito, boolean votosVaoParaLegenda) {
        this.cargo = Objects.requireNonNull(cargo, "Cargo não pode ser nulo");
        this.numeroCandidato = Objects.requireNonNull(numeroCandidato, "Número do candidato não pode ser nulo");
        this.nomeUrna = Objects.requireNonNull(nomeUrna, "Nome de urna não pode ser nulo");
        this.partido = Objects.requireNonNull(partido, "Partido não pode ser nulo");
        this.federacao = federacao; // Pode ser null para candidatos sem federação
        this.dataNascimento = Objects.requireNonNull(dataNascimento, "Data de nascimento não pode ser nula");
        this.genero = Objects.requireNonNull(genero, "Gênero não pode ser nulo");
        this.eleito = eleito;
        this.votosVaoParaLegenda = votosVaoParaLegenda;
    }
    
    // Getters
    public TipoDeputado getCargo() { return cargo; }
    public String getNumeroCandidato() { return numeroCandidato; }
    public String getNomeUrna() { return nomeUrna; }
    public Partido getPartido() { return partido; }
    public Federacao getFederacao() { return federacao; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public Genero getGenero() { return genero; }
    public boolean isEleito() { return eleito; }
    public boolean votosVaoParaLegenda() { return votosVaoParaLegenda; }
    public int getVotosRecebidos() { return votosRecebidos; }
    
    // Métodos utilitários
    public boolean pertenceFederacao() {
        return federacao != null && federacao.isValida();
    }
    
    public void adicionarVotos(int votos) {
        if (votos < 0) {
            throw new IllegalArgumentException("Número de votos não pode ser negativo");
        }
        this.votosRecebidos += votos;
    }
    
    public int getIdade(LocalDate dataReferencia) {
        return Period.between(dataNascimento, dataReferencia).getYears();
    }
    
    /**
     * Comparador por votos (decrescente) com critério de desempate por idade
     * Candidatos mais velhos têm prioridade em caso de empate
     */
    public static int compararPorVotosEIdade(Candidato c1, Candidato c2) {
        int comparacaoVotos = Integer.compare(c2.getVotosRecebidos(), c1.getVotosRecebidos());
        if (comparacaoVotos != 0) {
            return comparacaoVotos;
        }
        // Em caso de empate, candidato mais velho (nascimento anterior) tem prioridade
        return c1.getDataNascimento().compareTo(c2.getDataNascimento());
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        StringBuilder sb = new StringBuilder();
        sb.append("Candidato{")
          .append("numero='").append(numeroCandidato).append('\'')
          .append(", nome='").append(nomeUrna).append('\'')
          .append(", partido=").append(partido.getSigla())
          .append(", cargo=").append(cargo)
          .append(", nascimento=").append(dataNascimento.format(formatter))
          .append(", genero=").append(genero)
          .append(", eleito=").append(eleito);
        
        if (pertenceFederacao()) {
            sb.append(", federacao=").append(federacao.getNumero());
        }
        
        if (votosVaoParaLegenda) {
            sb.append(", votosParaLegenda=true");
        }
        
        sb.append(", votos=").append(votosRecebidos)
          .append('}');
        
        return sb.toString();
    }
    
    /**
     * Formata o candidato para exibição nos relatórios
     */
    public String formatarParaRelatorio() {
        String prefixoFederacao = pertenceFederacao() ? "*" : "";
        return String.format("%s%s (%s, %,d votos)", 
            prefixoFederacao, nomeUrna, partido.getSigla(), votosRecebidos);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidato candidato = (Candidato) o;
        return Objects.equals(numeroCandidato, candidato.numeroCandidato) &&
               cargo == candidato.cargo;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(numeroCandidato, cargo);
    }
}