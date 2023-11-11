package entidades;
public class Federacao {
    
    private int numFederacao;

    public Federacao(int numFederacao){
        this.numFederacao = numFederacao;
    }

    //getter
    public int getNumFederacao() {
        return numFederacao;
    }

    //Overide para poder imprimir o numero da federacao de maneira adequada;
    @Override
    public String toString(){
        return "numero federacao: " + this.numFederacao;
    }
}
