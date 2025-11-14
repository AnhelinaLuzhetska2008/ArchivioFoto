//Classe astratta per rappresentare un soggetto fotografato.
//Ogni soggetto ha una chiave univoca per il catalogo

package progettoarchivio;

import java.util.Objects;


public abstract class Soggetto {
    
    private final String key; 
    
/**
     * Costruisce un Soggetto con chiave valida.
     * @param key chiave del soggetto (non null, non vuota, solo A-Z e 0-9)
     * @throws IllegalArgumentException se la chiave è invalida
*/

    public Soggetto(String key) {
        
        if (key == null || key.trim().isEmpty()) {   
            
            throw new IllegalArgumentException("La chiave non può essere vuota o nulla.");
        }
        
        String normalized = key.trim().toUpperCase();
        
        if (!normalized.matches("[A-Z0-9]+")) {
            
            throw new IllegalArgumentException("Chiave può contenere solo lettere maiuscole e numeri. '");
        }

        this.key = normalized;
    }

    public String getKey() {
        return key;
    }
    
/** Restituisce una descrizione testuale del soggetto */
    public abstract String getDescription();
    
    /**
     * Verifica se la chiave contiene la query (case-insensitive, trimmed).
     * @param query stringa da cercare (può essere null)
     * @return true se la chiave contiene la query
     */
    
    public boolean matchesKey(String query) {
        
    if (query == null) {
        
        return false;
    }
    return key.contains(query.trim().toUpperCase());
}

    @Override
    
    public boolean equals(Object o) {
        
        if (this == o){
            
            return true;
        }
        
        if (!(o instanceof Soggetto s)) { //Controlla se un oggetto appartiene a una certa classe
            
            return false;
        }
        
        return getClass() == s.getClass() && key.equals(s.key);
    }

    @Override
    public int hashCode() {     //restituisce un numero intero basato sulla stringa
        
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        
        return String.format("%s[chiave=%s]", getClass().getSimpleName(), key);
    }
}
/**
 * getClass() prende la classe reale, getSimpleName() solo il nome. Così toString() funziona per tutte le sottoclassi
 */