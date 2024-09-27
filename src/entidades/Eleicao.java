package entidades;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Eleicao {

    private Map<String, Candidato> candidatos = new HashMap<>();
    private Map<String, Candidato> candidatosEleitos = new HashMap<>();
    private Map<String, Partido> partidos = new HashMap<>();
    private TipoDeputado tipoDeputado;
    private int numVotosNominais;
    private int numVotosLegenda;
    private int numVotosTotais;
    
    public Eleicao(TipoDeputado td) {
        this.tipoDeputado = td;
    }

    //Adiciona um candidato ao HashMap de candidatos, inserindo seu numero como chave;
    public void addCandidato(Candidato c) {
        this.candidatos.put(c.getNumCandidato(), c);
    }
    
    //Adiciona um candidato eleito ao HashMap de candidatos eleitos, inserindo seu numero como chave;
    public void addCandidatoEleito(Candidato c) {
        this.candidatosEleitos.put(c.getNumCandidato(), c);
    }
    
    //Adiciona um partido ao HashMap de partidos, inserindo seu numero como chave;
    public void addPartido(Partido p) {
        this.partidos.put(p.getNumPartido(), p);
    }
    
    //Tranforma um HashMap de candidatos em ArrayList para que eles possam ser ordenados;
    public ArrayList<Candidato> transformaMapEmList(){
        
        ArrayList<Candidato> candidatos = new ArrayList<>();
        
        for (Map.Entry<String, Candidato> entry : this.candidatosEleitos.entrySet()) {
            
            Candidato candidato = entry.getValue();

            candidatos.add(candidato);       
        }
        return candidatos;
    }
    
    //Ordena os candidatos eleitos em ordem decrescente de votos, usando a funcao sort e passando um comparador;
    public ArrayList<Candidato> ordenaCandidatosEleitosPorVoto(){

        ArrayList<Candidato> candidatos = transformaMapEmList();
        
        Collections.sort(candidatos, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                return c2.getNumVotos() - (c1.getNumVotos());
            }
        });

        return candidatos;
    } 
    
    //Ordena os candidatos eleitos em ordem decrescente de votos, usando a funcao sort e passando um comparador;
    public ArrayList<Candidato> ordenaCandidatosPorVoto(TipoDeputado tipoDeputado){

        ArrayList<Candidato> candidatos = new ArrayList<>();

        for (Map.Entry<String, Candidato> entry : this.candidatos.entrySet()) {

            Candidato candidato = entry.getValue();

            if(candidato.getCargo() == tipoDeputado)
                candidatos.add(candidato);
        }

        Collections.sort(candidatos, new Comparator<Candidato>() {
            @Override
            public int compare(Candidato c1, Candidato c2) {
                return c2.getNumVotos() - (c1.getNumVotos());
            }
        });
        
        return candidatos;
    }
    
    //Ordena os partidos em ordem decrescente de votos, usando a funcao sort e passando um comparador;
    public ArrayList<Partido> ordenaVotosPartidos(){

        ArrayList<Partido> partidos = new ArrayList<>();

        for (Map.Entry<String, Partido> entry : this.partidos.entrySet()) {

            Partido partido = entry.getValue();
            partido.somaVotos();
            partidos.add(partido);
        }

        Collections.sort(partidos, new Comparator<Partido>() {
            @Override
            public int compare(Partido c1, Partido c2) {
                int resultado = c2.getNumVotos() - (c1.getNumVotos());
                
                if(resultado == 0) resultado = c1.getNumPartido().compareTo(c2.getNumPartido());

                return resultado;
            }
        });

        return partidos;
    }

    //Calcula a idade dos candidatos para saber a porcentagem de e quantidade de candidatos em cada faixa etaria; 
    public void calculaIdadeCandidatos(LocalDate dataEleicao){

        int menosQueTrinta = 0;
        int entreTrintaEQuarenta = 0;
        int entreQuarentaECinquenta = 0;
        int entreCinquentaESessenta = 0;
        int maisQueSessenta = 0;
        
        for (Map.Entry<String, Candidato> entry : this.candidatosEleitos.entrySet()) {

            Candidato c = entry.getValue();
            
            Period periodo = Period.between(c.getDataNascimento(), dataEleicao);
            
            int anos = periodo.getYears();

            if(anos < 30) menosQueTrinta++;
            else if(anos >= 30 && anos < 40) entreTrintaEQuarenta++;
            else if(anos >= 40 && anos < 50) entreQuarentaECinquenta++;
            else if(anos >= 50 && anos < 60) entreCinquentaESessenta++;
            else maisQueSessenta++;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        
        String porcetagemMenosQueTrinta = df.format((menosQueTrinta*100.0)/this.candidatosEleitos.size());
        String porcentagemEntreTrintaEQuarenta = df.format((entreTrintaEQuarenta*100.0)/this.candidatosEleitos.size());
        String porcentagemEntreQuarentaECinquenta = df.format((entreQuarentaECinquenta*100.0)/this.candidatosEleitos.size());
        String porcentagemEntreCinquentaESessenta = df.format((entreCinquentaESessenta*100.0)/this.candidatosEleitos.size());
        String porcentagemMaisQueSessenta = df.format((maisQueSessenta*100.0)/this.candidatosEleitos.size());

        System.out.println("      Idade < 30: " + menosQueTrinta +" ("+ porcetagemMenosQueTrinta +"%)");
        System.out.println("30 <= Idade < 40: " + entreTrintaEQuarenta + " (" + porcentagemEntreTrintaEQuarenta + "%)");
        System.out.println("40 <= Idade < 50: " + entreQuarentaECinquenta + " (" + porcentagemEntreQuarentaECinquenta + "%)");
        System.out.println("50 <= Idade < 60: " + entreCinquentaESessenta + " (" + porcentagemEntreCinquentaESessenta + "%)");
        System.out.println("60 <= Idade     : " + maisQueSessenta + " (" + porcentagemMaisQueSessenta + "%)");
    }
    
    //Calcula a porcentagem de candidatos de cada genero;
    public  void calculaPorcentagemGenero(){

        int  mulher = 0;
        int homem = 0;

        for (Map.Entry<String, Candidato> entry : this.candidatosEleitos.entrySet()) {

            Candidato c = entry.getValue();

            if(c.getGenero() == Genero.MASCULINO) homem++;
            else mulher++; 
        }
        
        DecimalFormat df = new DecimalFormat("0.00");
        
        String porcentagemHomem = df.format((homem*100.0)/this.candidatosEleitos.size());
        String porcentagemMulher = df.format((mulher*100.0)/this.candidatosEleitos.size());

        System.out.println("Feminino:  " + mulher + " (" + porcentagemMulher + "%)");
        System.out.println("Masculino: " + homem + " (" + porcentagemHomem + "%)");
    }

    //Calcula a soma de votos totais;
    public void somaVotosTotais(){
        this.numVotosTotais = this.numVotosLegenda + this.numVotosNominais;
    }
    
    //Calcula a soma dos votos nominais;
    public void somaVotosNominal(int numVotos){
        this.numVotosNominais += numVotos;
    }
    
    //Calcula a quantidade de votos de legenda;
    public void somaVotosLegenda(int numVotos){
        this.numVotosLegenda += numVotos;
    }
    
    //Recebe uma chave de um candidato e verifica se ele esta no Map de candidatos;
    public boolean estaNoConjuntoDeCandidatos(String numCandidato){
        return this.candidatos.get(numCandidato) != null;   
    }
    
    //Recebe uma chave de um partido e verifica se ele esta no Map de partidos;
    public boolean estaNoConjuntoDePartidos(String numPartido){
        return this.candidatos.get(numPartido) != null;
    }
    
    //Calcula a quantidade de votos nominais interando o conjunto de candidatos;
    public void calculaVotosNominais(ArrayList<Candidato> candidatos){      
        for(Candidato c : candidatos){
            c.getPartido().somaVotosNominal(c.getNumVotos());
            this.numVotosNominais += c.getNumVotos();   
        }       
    }
    
    //getters
    public int getNumVotosTotais() {
        return numVotosTotais;
    }
    
    public TipoDeputado getTipoDeputado() {
        return tipoDeputado;
    }
    
    public int getNumVotosLegenda() {
        return numVotosLegenda;
    }
    
    public int getNumVotosNominais() {
        return numVotosNominais;
    }
    
    //Retorna um partido especifico do HashMap;
    public Partido getPartido(String key){
        return this.partidos.get(key);
    }
    
    //Retorna um candidato especifico do HashMap;
    public Candidato getCandidato(String key){
        return this.candidatos.get(key);
    }
    
    public int getNumCandidatosEleitos(){
        return this.candidatosEleitos.size();
    }
}
