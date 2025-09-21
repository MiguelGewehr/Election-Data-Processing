package util;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Valida argumentos da linha de comando
 */
public class ValidadorArgumentos {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static void validar(String[] args) {
        if (args.length != 4) {
            mostrarUso();
            throw new IllegalArgumentException("Número incorreto de argumentos");
        }
        
        validarTipoDeputado(args[0]);
        validarArquivo(args[1], "candidatos");
        validarArquivo(args[2], "votação");
        validarData(args[3]);
    }
    
    private static void validarTipoDeputado(String tipo) {
        if (!tipo.equals("--federal") && !tipo.equals("--estadual")) {
            throw new IllegalArgumentException(
                "Tipo de deputado deve ser --federal ou --estadual, recebido: " + tipo
            );
        }
    }
    
    private static void validarArquivo(String caminho, String tipo) {
        File arquivo = new File(caminho);
        
        if (!arquivo.exists()) {
            throw new IllegalArgumentException(
                "Arquivo de " + tipo + " não encontrado: " + caminho
            );
        }
        
        if (!arquivo.isFile()) {
            throw new IllegalArgumentException(
                "Caminho não é um arquivo válido: " + caminho
            );
        }
        
        if (!arquivo.canRead()) {
            throw new IllegalArgumentException(
                "Não é possível ler o arquivo: " + caminho
            );
        }
    }
    
    private static void validarData(String data) {
        try {
            LocalDate.parse(data, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                "Data inválida. Use o formato dd/mm/aaaa. Recebido: " + data
            );
        }
    }
    
    public static LocalDate parseData(String data) {
        try {
            return LocalDate.parse(data, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Erro ao converter data: " + data, e);
        }
    }
    
    public static String formatarData(LocalDate data) {
        return data.format(FORMATTER);
    }
    
    private static void mostrarUso() {
        System.err.println("Uso: java -jar deputados.jar <tipo> <candidatos.csv> <votacao.csv> <data>");
        System.err.println("  <tipo>: --federal ou --estadual");
        System.err.println("  <data>: formato dd/mm/aaaa");
        System.err.println();
        System.err.println("Exemplos:");
        System.err.println("  java -jar deputados.jar --federal candidatos.csv votacao.csv 02/10/2022");
        System.err.println("  java -jar deputados.jar --estadual candidatos.csv votacao.csv 02/10/2022");
    }
}