package model.enums;

/**
 * Enumera os gêneros dos candidatos
 */
public enum Genero {
    
    MASCULINO(2),
    FEMININO(4);
    
    private final int codigo;
    
    Genero(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public static Genero fromCodigo(int codigo) {
        for (Genero genero : values()) {
            if (genero.codigo == codigo) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Código de gênero inválido: " + codigo);
    }
    
    @Override
    public String toString() {
        return this == MASCULINO ? "Masculino" : "Feminino";
    }
}