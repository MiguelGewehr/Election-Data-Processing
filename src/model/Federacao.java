package model;

import java.util.Objects;

/**
 * Representa uma federação partidária
 */
public class Federacao {
    
    private final int numero;
    
    public Federacao(int numero) {
        this.numero = numero;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public boolean isValida() {
        return numero != -1;
    }
    
    @Override
    public String toString() {
        return "Federacao{" +
                "numero=" + numero +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Federacao federacao = (Federacao) o;
        return numero == federacao.numero;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}