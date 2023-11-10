package entidades;
public class Federacao {
    
    private int numFederacao;

    public Federacao(int numFederacao){
        this.numFederacao = numFederacao;
    }

    public int getNumFederacao() {
        return numFederacao;
    }

    @Override
    public String toString(){
        return "numero federacao: " + this.numFederacao;
    }
}
