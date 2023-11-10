package entidades;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Candidato{

    private TipoDeputado cargo;
    private String numCandidato;
    private String nomeCandidato;
    private Partido partido;
    private Federacao federacao;
    private LocalDate dataNascimento;
    private Boolean candidatoEleito = false;
    private Genero genero;
    private boolean votosVaoParaLegenda = false;
    private int numVotos;
    
    public Candidato(TipoDeputado cargo, String numCandidato, String nomeCandidato, Partido partido,
    Federacao federacao, LocalDate dataNascimento, Genero genero, boolean votosVaoParaLegenda, boolean candidatoEleito) {
        
        this.cargo = cargo;
        this.numCandidato = numCandidato;
        this.nomeCandidato = nomeCandidato;
        this.partido = partido;
        this.federacao = federacao;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.votosVaoParaLegenda = votosVaoParaLegenda;
        this.candidatoEleito = candidatoEleito;
    }
    
    public void somaVotos(int numVotos) {
        this.numVotos += numVotos;
    }
        
    public TipoDeputado getCargo() {
        return cargo;
    }
    
    public String getNumCandidato() {
        return numCandidato;
    }
    
    public String getNomeCandidato() {
        return nomeCandidato;
    }
    
    public Partido getPartido() {
        return partido;
    }

    public Federacao getFederacao() {
        return federacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Boolean getCandidatoEleito() {
        return candidatoEleito;
    }

    public Genero getGenero() {
        return genero;
    }

    public boolean getVotosVaoParaLegenda() {
        return votosVaoParaLegenda;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public void printaCandidatoRelatorioSete(int idx, Candidato c){
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        String votos;

        if(this.numVotos > 1) votos = " votos";
        else votos = " voto";

        String votosC;

        if(c.getNumVotos() > 1) votosC = " votos";
        else votosC = " voto";

        System.out.println(idx + " - " + this.partido.getSiglaPartido() + " - " + this.partido.getNumPartido() + ", " + this.nomeCandidato + " (" + this.numCandidato + ", " + decimalFormat.format(this.numVotos) + votos + ") / " + c.nomeCandidato + " (" + c.numCandidato + ", " + decimalFormat.format(c.numVotos) + votosC + ")");
    }

    @Override
    public String toString(){
        
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        if(this.federacao.getNumFederacao() == -1)
            return this.nomeCandidato + " (" + this.partido.getSiglaPartido() +", " + decimalFormat.format(this.numVotos) + " votos)";
        else
        return "*" + this.nomeCandidato + " (" + this.partido.getSiglaPartido() +", " + decimalFormat.format(this.numVotos) + " votos)";
    }

}