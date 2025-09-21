package util;

import model.Candidato;
import model.Federacao;
import model.Partido;
import model.enums.Genero;
import model.enums.TipoDeputado;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsável por ler e processar o arquivo de candidatos
 */
public class LeitorCandidatos {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String ENCODING = "ISO-8859-1";
    private static final String SEPARATOR = ";";
    private static final String VOTOS_LEGENDA_MARKER = "Válido (legenda)";
    
    // Índices das colunas relevantes no CSV
    private static final int CD_CARGO = 13;
    private static final int CD_SITUACAO_CANDIDADO_TOT = 68;
    private static final int NR_CANDIDATO = 16;
    private static final int NM_URNA_CANDIDATO = 18;
    private static final int NR_PARTIDO = 27;
    private static final int SG_PARTIDO = 28;
    private static final int NR_FEDERACAO = 30;
    private static final int DT_NASCIMENTO = 42;
    private static final int CD_SIT_TOT_TURNO = 56;
    private static final int CD_GENERO = 45;
    private static final int NM_TIPO_DESTINACAO_VOTOS = 67;
    
    private static Map<String, Partido> partidosCache = new HashMap<>();
    
    public static List<Candidato> lerCandidatos(String arquivo, TipoDeputado tipoDesejado) 
            throws IOException {
        
        List<Candidato> candidatos = new ArrayList<>();
        partidosCache.clear(); // Limpa o cache de partidos
        
        int totalLinhas = 0;
        
        Map<Integer, Integer> cargosCounts = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(arquivo), ENCODING))) {
            
            // Pula o cabeçalho
            String linha = reader.readLine();
            if (linha == null) {
                throw new IOException("Arquivo vazio ou inválido");
            }
                   
            while ((linha = reader.readLine()) != null) {
                totalLinhas++;
                
                try {
                    Candidato candidato = processarLinha(linha, tipoDesejado, cargosCounts);
                    if (candidato != null) {
                        candidatos.add(candidato);
                    } 
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha " + (totalLinhas + 1) + ": " + e.getMessage());
                    // Continua processamento mesmo com erros individuais
                }
            }
        }
        
        // Mostra estatísticas de cargos encontrados
        if (!cargosCounts.isEmpty()) {
            System.out.println("\nEstatísticas de cargos no arquivo:");
            cargosCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String nomeCargo = getNomeCargo(entry.getKey());
                    System.out.println("- Cargo " + entry.getKey() + " (" + nomeCargo + "): " + entry.getValue() + " candidatos");
                });
        }
        
        System.out.println();
        
        return candidatos;
    }
    
    private static Candidato processarLinha(String linha, TipoDeputado tipoDesejado, Map<Integer, Integer> cargosCounts) {
        String[] campos = linha.split(SEPARATOR);
        
        if (campos.length < 70) { // Verifica se tem campos suficientes
            throw new IllegalArgumentException("Linha com número insuficiente de campos");
        }
        
        // Verifica cargo
        int codigoCargo = parseInt(campos[CD_CARGO]);
        cargosCounts.merge(codigoCargo, 1, Integer::sum);
        
        // Se não é deputado federal ou estadual
        if (codigoCargo != 6 && codigoCargo != 7) {
            return null; // Não é deputado, ignora
        }
        
        TipoDeputado cargo = TipoDeputado.fromCodigo(codigoCargo);
        if (cargo != tipoDesejado) {
            return null; // É deputado, mas não do tipo desejado
        }
        
        // Verifica situação do candidato
        int situacao = parseInt(campos[CD_SITUACAO_CANDIDADO_TOT]);
        boolean votosParaLegenda = parseString(campos[NM_TIPO_DESTINACAO_VOTOS])
            .equals(VOTOS_LEGENDA_MARKER);
        
        // Processa apenas candidatos válidos ou com votos para legenda
        if (situacao != 2 && situacao != 16 && !votosParaLegenda) {
            return null;
        }
        
        // Extrai dados do candidato
        String numeroCandidato = parseString(campos[NR_CANDIDATO]);
        String nomeUrna = parseString(campos[NM_URNA_CANDIDATO]);
        String numeroPartido = parseString(campos[NR_PARTIDO]);
        String siglaPartido = parseString(campos[SG_PARTIDO]);
        int numeroFederacao = parseInt(campos[NR_FEDERACAO]);
        LocalDate dataNascimento = parseData(campos[DT_NASCIMENTO]);
        Genero genero = Genero.fromCodigo(parseInt(campos[CD_GENERO]));
        
        // Verifica se candidato foi eleito
        int situacaoTurno = parseInt(campos[CD_SIT_TOT_TURNO]);
        boolean eleito = (situacaoTurno == 2 || situacaoTurno == 3);
        
        // Obtém ou cria partido
        Partido partido = partidosCache.computeIfAbsent(numeroPartido, 
            k -> new Partido(numeroPartido, siglaPartido));
        
        // Cria federação
        Federacao federacao = numeroFederacao == -1 ? null : new Federacao(numeroFederacao);
        
        return new Candidato(cargo, numeroCandidato, nomeUrna, partido, federacao,
                           dataNascimento, genero, eleito, votosParaLegenda);
    }
    
    private static String getNomeCargo(int codigo) {
        switch (codigo) {
            case 3: return "Senador";
            case 4: return "Governador";
            case 5: return "Presidente";
            case 6: return "Deputado Federal";
            case 7: return "Deputado Estadual";
            case 9: return "Vice-governador";
            case 10: return "Vice-presidente";
            default: return "Desconhecido";
        }
    }
    
    // Métodos utilitários para parsing
    private static String parseString(String campo) {
        if (campo == null || campo.length() < 2) {
            throw new IllegalArgumentException("Campo inválido: " + campo);
        }
        // Remove aspas do início e fim
        return campo.substring(1, campo.length() - 1);
    }
    
    private static int parseInt(String campo) {
        try {
            return Integer.parseInt(parseString(campo));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Não foi possível converter para inteiro: " + campo, e);
        }
    }
    
    private static LocalDate parseData(String campo) {
        try {
            return LocalDate.parse(parseString(campo), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: " + campo, e);
        }
    }
    
    // Método para acessar o cache de partidos
    public static Map<String, Partido> getPartidosCarregados() {
        return new HashMap<>(partidosCache);
    }
}