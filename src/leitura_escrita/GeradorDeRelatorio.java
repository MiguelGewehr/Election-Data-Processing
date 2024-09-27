package leitura_escrita;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import entidades.Candidato;
import entidades.Eleicao;
import entidades.Partido;
import entidades.TipoDeputado;

public class GeradorDeRelatorio {

    //Funcao maior que chama as funcoes especificas de cada relatorio;
    public static void geraRelatorio(Eleicao eleicao, TipoDeputado TipoDeputado, LocalDate dataEleicao) {

        System.out.println("Número de vagas: " + eleicao.getNumCandidatosEleitos() + "\n");

        ArrayList<Candidato> candidatosEleitosOrdenados = relatorioDois(eleicao);
        
        ArrayList<Candidato> candidatosOrdenados = relatorioTresEQuatro(eleicao, TipoDeputado);
        
        relatorioCinco(eleicao, candidatosEleitosOrdenados, candidatosOrdenados);

        relatorioSeis(eleicao, candidatosOrdenados);

        relatorioSete(candidatosOrdenados);

        relatorioOito(eleicao, dataEleicao);

        relatorioNove(eleicao);

        relatorioDez(eleicao);     
    }

    private static ArrayList<Candidato> relatorioDois(Eleicao eleicao){

        ArrayList<Candidato> candidatosEleitosOrdenados = eleicao.ordenaCandidatosEleitosPorVoto();

        String deputado;
        
        if(eleicao.getTipoDeputado() == TipoDeputado.ESTADUAL) deputado = "Deputados estaduais";
        else deputado = "Deputados federais";
        
        System.out.println(deputado + " eleitos:");

        int i=1;

        for(Candidato c : candidatosEleitosOrdenados){
            System.out.println(i+" - " + c.toString());
            i++;
        }

        return candidatosEleitosOrdenados;
    }

    private static ArrayList<Candidato> relatorioTresEQuatro(Eleicao eleicao, TipoDeputado TipoDeputado){

        System.out.println("\nCandidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");

        ArrayList<Candidato> candidatosOrdenados = eleicao.ordenaCandidatosPorVoto(TipoDeputado);

        ArrayList<Candidato> candidatosQueSeriamEleitos = new ArrayList<>();
        int[] idxCandidatosQueSeriamEleitos = new int[eleicao.getNumCandidatosEleitos()]; 

        int j = 1;
        int idx = 0;
        
        for(Candidato c : candidatosOrdenados){
            
            if(j > eleicao.getNumCandidatosEleitos()) break;

            if(!c.getCandidatoEleito()){
                candidatosQueSeriamEleitos.add(c);
                idxCandidatosQueSeriamEleitos[idx] = j;
                idx++;
            }

            System.out.println(j+" - " + c.toString());
            j++;
        }

        System.out.println("\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
        
        int i=0;
        for(Candidato c : candidatosQueSeriamEleitos){
           System.out.println(idxCandidatosQueSeriamEleitos[i] + " - " + c.toString());
           i++; 
        }
        
        return candidatosOrdenados;
    }

    private static void relatorioCinco(Eleicao eleicao, ArrayList<Candidato> candidatosEleitosOrdenados, ArrayList<Candidato> candidatosOrdenados){

        ArrayList<Candidato> candidatosQueNaoSeriamEleitos = new ArrayList<>();

        int[] idxCandidato = new int[candidatosEleitosOrdenados.size()];
        int idx=0; 
        
        int lastSize = candidatosEleitosOrdenados.size() -1;

        for(Candidato c : candidatosEleitosOrdenados){

            if(c.getNumVotos() < candidatosOrdenados.get(lastSize).getNumVotos()){
                idxCandidato[idx] = candidatosOrdenados.indexOf(c)+1;
                candidatosQueNaoSeriamEleitos.add(c);
                idx++;
            }
        }

        System.out.println("\nEleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");

        int i=0;
        for(Candidato c : candidatosQueNaoSeriamEleitos){
            System.out.println(idxCandidato[i]+ " - " + c.toString());   
            i++;
        }
    }

    private static void relatorioSeis(Eleicao eleicao, ArrayList<Candidato> candidatos){

        eleicao.calculaVotosNominais(candidatos);

        System.out.println("\nVotação dos partidos e número de candidatos eleitos:");

        ArrayList<Partido> partidos = eleicao.ordenaVotosPartidos();

        int i = 1;
        for(Partido p : partidos){
            p.printaPartidoRelatorioSeis(i);
            i++;
        }
    }

    private static void relatorioSete(ArrayList<Candidato> candidatosOrdenados){
        System.out.println("\nPrimeiro e último colocados de cada partido:");

        HashMap<String, Partido> partidos1 = new HashMap<>();

        for(int i = candidatosOrdenados.size()-1; i >=0; i--){
            
            Candidato c = candidatosOrdenados.get(i);

            if(partidos1.get(c.getPartido().getNumPartido()) == null){
                partidos1.put(c.getPartido().getNumPartido(), c.getPartido());
                c.getPartido().setCandidatoMenosVotado(c);
            }

        }

        HashMap<String, Partido> partidos = new HashMap<>();

        int i=1;
        for(Candidato c : candidatosOrdenados){

            if(partidos.get(c.getPartido().getNumPartido()) == null){
                partidos.put(c.getPartido().getNumPartido(), c.getPartido());
                Partido p = partidos1.get(c.getPartido().getNumPartido());
                c.printaCandidatoRelatorioSete(i, p.getCandidatoMenosVotado());
                i++;
            }
            
        }

    }

    private static void relatorioOito(Eleicao eleicao, LocalDate dataEleicao){

        System.out.println("\nEleitos, por faixa etária (na data da eleição):");

        eleicao.calculaIdadeCandidatos(dataEleicao);
        
    }

    private static void relatorioNove(Eleicao eleicao){

        System.out.println("\nEleitos, por gênero:");

        eleicao.calculaPorcentagemGenero();
    }

    private static void relatorioDez(Eleicao eleicao){

        eleicao.somaVotosTotais();

        DecimalFormat df = new DecimalFormat("0.00");
        
        String porcentagemVotoNominal = df.format((eleicao.getNumVotosNominais()*100.0)/eleicao.getNumVotosTotais());
        String porcentagemVotoLegenda = df.format((eleicao.getNumVotosLegenda()*100.0)/eleicao.getNumVotosTotais());

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        System.out.println("\nTotal de votos válidos:    " + decimalFormat.format(eleicao.getNumVotosTotais()));
        System.out.println("Total de votos nominais:   " + decimalFormat.format(eleicao.getNumVotosNominais()) + " (" + porcentagemVotoNominal + "%)");
        System.out.println("Total de votos de legenda: " + decimalFormat.format(eleicao.getNumVotosLegenda()) + " (" + porcentagemVotoLegenda + "%)");
    }
}
