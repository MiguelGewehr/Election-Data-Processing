package model.enums;

/**
 * Enumera os tipos de deputado
 */
public enum TipoDeputado {
    
    FEDERAL(6),
    ESTADUAL(7);
    
    private final int codigo;
    
    TipoDeputado(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public static TipoDeputado fromCodigo(int codigo) {
        for (TipoDeputado tipo : values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de cargo inválido: " + codigo);
    }
    
    @Override
    public String toString() {
        return this == FEDERAL ? "Deputado Federal" : "Deputado Estadual";
    }
}