package leitura_escrita;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entidades.Candidato;
import entidades.Eleicao;
import entidades.Federacao;
import entidades.Genero;
import entidades.Partido;
import entidades.TipoDeputado;

public class LeitorDeArquivo {

    public static void leituraDosCandidatos(Eleicao eleicao, String nomeArquivo) {
        
        try {
            File arquivo = new File(nomeArquivo);
            
            //Definindo a codificação do arquivo;
            Scanner scanner = new Scanner(arquivo, "ISO-8859-1");

            // Lê e ignora a primeira linha (cabeçalho);
            scanner.nextLine();
            
            // Loop para ler e guardar os dados das linhas com as informações dos candidatos;
            while (scanner.hasNextLine()) {
                
                String linha = scanner.nextLine();
                
                // Separa a linha em partes sempre que encontra o separador ";". 
                // Dessa forma, podemos acessar apenas as informações relevantes;
                String[] partes = linha.split(";"); 

                int situacaoCandidato = parseSituacaoCandidato(partes[68]);
                
                boolean votosVaoParaLegenda = parseVotosVaoParaLegenda(partes[67]);
                
                String numPartido = removeAspas(partes[27]);

                String siglaPartido = removeAspas(partes[28]);

                // Pega o partido para ser atribuido ao candidato; 
                Partido partido = eleicao.getPartido(numPartido);   

                // Se o partido não existir cria um novo partido e coloca no HashMap de Partidos;
                if(partido == null){
                    partido = new Partido(numPartido, siglaPartido);
                    eleicao.addPartido(partido);
                }

                //Confere se o candidato teve a candidatura deferida ou se os votos vão para a legenda;
                if (situacaoCandidato == 2 || situacaoCandidato == 16 || votosVaoParaLegenda) {

                    // Transformando de String para os tipos adequados;
                    TipoDeputado cargo = parseCargo(partes[13]);
                
                    String numCandidato = removeAspas(partes[16]);

                    String nomeCandidato = removeAspas(partes[18]);
                   
                    int numFederacao = parseNumFederacao(partes[30]);
                    
                    LocalDate dataNascimento = parseDataDeNascimento(partes[42]);
                
                    Boolean candidatoEleito = parseCandidatoEleito(partes[56]);
                    
                    Genero genero = parseGenero(partes[45]);
                    
                    // Criando uma federacao que vai ser passada para o candidato;
                    Federacao federacao = new Federacao(numFederacao);

                    // Cria um novo candidato ja com seus atributos (Menos o numero de votos, que vai ser lido no proximo arquivo);
                    Candidato candidato = new Candidato(cargo, numCandidato, nomeCandidato, partido, federacao,
                            dataNascimento, genero, votosVaoParaLegenda, candidatoEleito);

                    // Se o candidato foi eleito ele é adicionado em um HashMap so com os candidatos eleitos 
                    // e  o número de candidatos eleitos do seu partido é incrementado;
                    if (candidatoEleito && cargo == eleicao.getTipoDeputado()){
                        eleicao.addCandidatoEleito(candidato);
                        candidato.getPartido().incrementaNumCandidatosEleitos();
                    }   
                    
                    //Adiciona os candidatos eleitos e não eleitos;
                    eleicao.addCandidato(candidato);
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void leituraDeVotos(Eleicao eleicao, String nomeArquivo) {

        try {
            File arquivo = new File(nomeArquivo);

            //Definindo a codificação do arquivo;
            Scanner scanner = new Scanner(arquivo, "ISO-8859-1");

            // Lê e ignora a primeira linha (cabeçalho);
            scanner.nextLine();
            
            // Loop para ler e guardar os dados das linhas com as informações dos votos;
            while (scanner.hasNextLine()) {
                
                String linha = scanner.nextLine();
                
                // Separa a linha em partes sempre que encontra o separador ";". 
                // Dessa forma, podemos acessar apenas as informações relevantes;
                String[] partes = linha.split(";"); 

                String nrVotavel = removeAspas(partes[19]);

                TipoDeputado cargo = parseCargo(partes[17]);

                //Confere se o cargo e o mesmo que foi passado na entrada e se o voto é válido;
                if (cargo == eleicao.getTipoDeputado() && !(nrVotavel.equals("95") || nrVotavel.equals("96") || nrVotavel.equals("97") || nrVotavel.equals("98"))) {
                    
                    int numVotos = parseNumVotos(partes[21]);

                    Candidato c = eleicao.getCandidato(nrVotavel);

                    if(c != null){

                        Partido p = c.getPartido();

                        if(c.getVotosVaoParaLegenda()){
                            p.somaVotosLegenda(numVotos);
                            eleicao.somaVotosLegenda(numVotos);
                        }
                        else{
                            c.somaVotos(numVotos);
                        }
                    }

                    Partido p = eleicao.getPartido(nrVotavel);
                    
                    if(p != null){
                        p.somaVotosLegenda(numVotos);
                        eleicao.somaVotosLegenda(numVotos);
                    }
                }    
            }
            
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Função que retira as aspas do ínicio e final de uma string;
    public static String removeAspas(String texto) {
        return texto.substring(1, texto.length() - 1);
    }

    // Função que transforma a string do arquivo referente a situação do candidato em inteiro;
    public static int parseSituacaoCandidato(String situacaoCandidato){
        
        situacaoCandidato = LeitorDeArquivo.removeAspas(situacaoCandidato);
        
        return Integer.parseInt(situacaoCandidato);
    }

    // Função que transforma a string do arquivo referente a destinaçaõ dos votos em boolean, onde true indica que o voto vai para a legenda;
    public static boolean parseVotosVaoParaLegenda(String votosVaoParaLegenda){
        
        votosVaoParaLegenda = LeitorDeArquivo.removeAspas(votosVaoParaLegenda);
        
        return votosVaoParaLegenda.equals("Válido (legenda)");
    }

    // Função que transforma a string do arquivo referente ao cargo em TipoDeputado;
    public static TipoDeputado parseCargo(String cargo){

        cargo = removeAspas(cargo);
        int cargoInt = Integer.parseInt(cargo);
        TipoDeputado tipoDeputado;

        if(cargoInt == 7) tipoDeputado = TipoDeputado.ESTADUAL;
        else if(cargoInt == 6) tipoDeputado = TipoDeputado.FEDERAL;
        else tipoDeputado = TipoDeputado.OUTROCARGO;

        return tipoDeputado;
    }

    // Função que transforma a string do arquivo referente ao número da federação em inteiro;
    public static int parseNumFederacao(String numFederacao){
        
        numFederacao = LeitorDeArquivo.removeAspas(numFederacao);
        
        return Integer.parseInt(numFederacao);
    }

    // Função que transforma a string do arquivo referente a data de nascimento em LocalDate;
    public static LocalDate parseDataDeNascimento(String dataNascimento){

        dataNascimento = LeitorDeArquivo.removeAspas(dataNascimento);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return LocalDate.parse(dataNascimento, formatter);
    }

    // Função que transforma a string do arquivo referente a se o candidato foi eleito em boolean, onde o true é se ele foi eleito;
    public static boolean parseCandidatoEleito(String candidatoEleito){

        candidatoEleito = LeitorDeArquivo.removeAspas(candidatoEleito);
        int candidatoEleitoInt = Integer.parseInt(candidatoEleito);

        return (candidatoEleitoInt == 2 || candidatoEleitoInt == 3);
    }

    // Função que transforma a string do arquivo referente ao genero do candidato no tipo Genero;
    public static Genero parseGenero(String genero){
        
        genero = LeitorDeArquivo.removeAspas(genero);
        int generoInt = Integer.parseInt(genero);

        Genero generoRetorno;
        
        if(generoInt == 2) generoRetorno = Genero.MASCULINO;
        else generoRetorno = Genero.FEMININO;

        return generoRetorno;
    }
    //Transforma os números de votos em inteiros;
    public static int parseNumVotos(String numVotos){

        numVotos = LeitorDeArquivo.removeAspas(numVotos);
        
        return Integer.parseInt(numVotos);
    }

}
