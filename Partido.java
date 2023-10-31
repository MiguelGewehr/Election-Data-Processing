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
    
    public void somaVotos(){
        this.numVotos = numVotosLegenda + numVotosNominal;
    }
    
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
    
    void setCandidatoMenosVotado(Candidato candidatoMenosVotado) {
        this.candidatoMenosVotado = candidatoMenosVotado;
    }
    
    @Override
    public String toString(){

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        String votos;

        if(this.numVotos > 1) votos = " votos";
        else votos = " voto";
    
        return this.siglaPartido + " - " + this.numPartido +", " + decimalFormat.format(this.numVotos) + votos;
    }
    
    public int getVotosLegenda() {
        return numVotosLegenda;
    }
    
    public int getVotosNominal() {
        return numVotosNominal;
    }
    
    public void somaVotosLegenda(int numVotos){
        this.numVotosLegenda += numVotos;
    }
    
    public void somaVotosNominal(int numVotos){
        this.numVotosNominal += numVotos;
    }
    
    public int getNumCandidatosEleitos() {
        return numCandidatosEleitos;
    }

    public void incrementaNumCandidatosEleitos(){
        this.numCandidatosEleitos++;
    }

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
}
