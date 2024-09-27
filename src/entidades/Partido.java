package entidades;
import java.text.DecimalFormat;

public class Partido {
    
    private String numPartido;
    private String siglaPartido;
    private int numVotos;
    private Candidato candidatoMenosVotado;
    private int numVotosLegenda;
    private int numVotosNominal;
    private int numCandidatosEleitos;
    
    public Partido(String numPartido, String siglaPartido){
        
        this.numPartido = numPartido;
        this.siglaPartido = siglaPartido;
    }
    
    //Soma os votos totais do partido;
    public void somaVotos(){
        this.numVotos = numVotosLegenda + numVotosNominal;
    }
    
    
    //Soma os votos da legenda do partido;
    public void somaVotosLegenda(int numVotos){
        this.numVotosLegenda += numVotos;
    }
    
    //Soma os votos nominais do partido;
    public void somaVotosNominal(int numVotos){
        this.numVotosNominal += numVotos;
    }
    
    //Incrementa o número de candidatos eleitos presente no partido;
    public void incrementaNumCandidatosEleitos(){
        this.numCandidatosEleitos++;
    }
    
    //Override para printar o partido no formato esperado;
    @Override
    public String toString(){

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        String votos;

        if(this.numVotos > 1) votos = " votos";
        else votos = " voto";
    
        return this.siglaPartido + " - " + this.numPartido +", " + decimalFormat.format(this.numVotos) + votos;
    }

    //Printa o partido no formato pedido pelo relatorio seis;
    public void printaPartidoRelatorioSeis(int i){
        
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        
        String candidatosEleitos;
        
        if(numCandidatosEleitos > 1) candidatosEleitos = " candidatos eleitos";
        else candidatosEleitos = " candidato eleito";

        String nominais;
        
        if(this.numVotosNominal > 1) nominais =  " nominais";
        else nominais = " nominal"; 
        
        System.out.println(i + " - " + this.toString() + " (" + decimalFormat.format(this.numVotosNominal) + nominais + " e " + decimalFormat.format(this.numVotosLegenda) + " de legenda), " + this.numCandidatosEleitos + candidatosEleitos);
    }
    
    public void setCandidatoMenosVotado(Candidato candidatoMenosVotado) {
        this.candidatoMenosVotado = candidatoMenosVotado;
    }

    //getters
    public String getNumPartido() {
        return numPartido;
    }
    
    public String getSiglaPartido() {
        return siglaPartido;
    }
    
    public int getNumVotos() {
        return numVotos;
    }
    
    public Candidato getCandidatoMenosVotado() {
        return candidatoMenosVotado;
    }
    
    public int getNumCandidatosEleitos() {
        return numCandidatosEleitos;
    }
    
    public int getVotosLegenda() {
        return numVotosLegenda;
    }
    
    public int getVotosNominal() {
        return numVotosNominal;
    }
}

