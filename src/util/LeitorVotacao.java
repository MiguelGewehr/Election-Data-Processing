package util;

import model.Candidato;
import model.Partido;
import model.enums.TipoDeputado;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsável por ler e processar o arquivo de votação
 */
public class LeitorVotacao {
    
    private static final String ENCODING = "ISO-8859-1";
    private static final String SEPARATOR = ";";
    
    // Índices das colunas relevantes no CSV de votação
    private static final int CD_CARGO = 17;
    private static final int NR_VOTAVEL = 19;
    private static final int QT_VOTOS = 21;
    
    // Números que representam votos inválidos (devem ser ignorados)
    private static final String[] VOTOS_INVALIDOS = {"95", "96", "97", "98"};
    
    public static void lerVotacao(String arquivo, List<Candidato> candidatos, 
                                 Map<String, Partido> partidos, TipoDeputado tipoDesejado) 
                                 throws IOException {
        
        // Cria índices para busca rápida
        Map<String, Candidato> candidatosPorNumero = new HashMap<>();
        for (Candidato candidato : candidatos) {
            candidatosPorNumero.put(candidato.getNumeroCandidato(), candidato);
        }
        
        int totalLinhas = 0;
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(arquivo), ENCODING))) {
            
            // Pula o cabeçalho
            String linha = reader.readLine();
            if (linha == null) {
                throw new IOException("Arquivo de votação vazio ou inválido");
            }
            
            System.out.println("=== LENDO ARQUIVO DE VOTAÇÃO ===");
            System.out.println("Processando arquivo: " + arquivo);
            System.out.println("Encoding: " + ENCODING);
            System.out.println("Filtrando para: " + tipoDesejado);
            
            while ((linha = reader.readLine()) != null) {
                totalLinhas++;
                
                try {
                    processarLinhaVotacao(
                        linha, candidatosPorNumero, partidos, tipoDesejado);
              
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha de votação " + (totalLinhas + 1) + ": " + e.getMessage());
                }
            }
        }
    }
    
    private static void processarLinhaVotacao(String linha, 
                                                            Map<String, Candidato> candidatos,
                                                            Map<String, Partido> partidos, 
                                                            TipoDeputado tipoDesejado) {
        
        String[] campos = linha.split(SEPARATOR);
        
        if (campos.length < 22) {
            throw new IllegalArgumentException("Linha de votação com número insuficiente de campos");
        }
        
        int codigoCargo = parseInt(campos[CD_CARGO]);
        if (codigoCargo != tipoDesejado.getCodigo()) {
        }
        
        String nrVotavel = parseString(campos[NR_VOTAVEL]);
        int qtVotos = parseInt(campos[QT_VOTOS]);
        
        if (isVotoInvalido(nrVotavel)) {
        }
        
        // CORREÇÃO CRÍTICA: Verificar candidato primeiro
        Candidato candidato = candidatos.get(nrVotavel);
        if (candidato != null) {
            candidato.adicionarVotos(qtVotos);
            
            if (candidato.votosVaoParaLegenda()) {
                // Votos do candidato vão para legenda do partido
                candidato.getPartido().adicionarVotosLegenda(qtVotos);
            } else {
                // Voto nominal normal - adiciona aos votos nominais do partido
                candidato.getPartido().adicionarVotosNominais(qtVotos);
            }
        }
        
        // Voto direto na legenda (partido)
        Partido partido = partidos.get(nrVotavel);
        if (partido != null) {
            partido.adicionarVotosLegenda(qtVotos);
        }
    }
    
    private static boolean isVotoInvalido(String nrVotavel) {
        for (String invalido : VOTOS_INVALIDOS) {
            if (invalido.equals(nrVotavel)) {
                return true;
            }
        }
        return false;
    }
    
    // Métodos utilitários para parsing (iguais ao LeitorCandidatos)
    private static String parseString(String campo) {
        if (campo == null || campo.length() < 2) {
            throw new IllegalArgumentException("Campo inválido: " + campo);
        }
        return campo.substring(1, campo.length() - 1);
    }
    
    private static int parseInt(String campo) {
        try {
            return Integer.parseInt(parseString(campo));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Não foi possível converter para inteiro: " + campo, e);
        }
    } 
}