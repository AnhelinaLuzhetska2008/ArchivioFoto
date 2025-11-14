package progettoarchivio;

import java.util.Map;
import java.util.HashMap;

/**
 * Enum per le attività prevalenti di un artista.
 * Supporta lookup per nome enum o alias (case-insensitive).
 * Se non trovato → {@link #ALTRO}.
 */

public enum AttivitaPrevalente {
    
    PITTURA("Pittura", "pittura", "pittore", "pittrice"),
    SCULTURA("Scultura", "scultura", "scultore", "scultrice"),
    FOTOGRAFIA("Fotografia", "fotografia", "fotografo", "fotografa"),
    ARCHITETTURA("Architettura", "architettura", "architetto", "architetta"),
    GRAFICA("Grafica", "grafica", "grafico", "grafica"),
    DESIGN("Design", "design", "designer"),
    ALTRO("Attività personalizzata");
    
    private final String label; //es pittura
    private final String[] aliases; //es pittrice

/** Mappa per ricerca rapida: chiave maiuscola → valore enum */
    private static final Map<String, AttivitaPrevalente> LOOKUP = new HashMap<>();

    //static eseguito 1 volta
    static {
        
        for (AttivitaPrevalente attivita : values()) { //scorrimento tutti valori
            
            LOOKUP.put(attivita.name().toUpperCase(), attivita);  //aggiunta all'enum in maiuscolo
            
            for (String a : attivita.aliases) {    //aggiunta ogni allias in maiusc
                
                LOOKUP.put(a.toUpperCase(), attivita);
            }
        }
    }

/**
     * Costruttore con descrizione e alias variadici.
     * @param descrizione nome formale (es. "Pittura")
     * @param alias vari termini associati (es. "pittore")
*/
    
    AttivitaPrevalente(String label, String... aliases) {
        
        this.label = label;
        this.aliases = aliases;
    }

/** Restituisce il nome formale dell'attività */
    
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

/**
     * Converte una stringa in AttivitaPrevalente.
     * Accetta: nome enum, alias, o qualsiasi variante case-insensitive.
     * @param input stringa da convertire (non null, non vuota)
     * @return valore corrispondente, o {@link #ALTRO} se non trovato
     * @throws IllegalArgumentException se input è null o vuoto
*/
    
    public static AttivitaPrevalente daStringa(String input) {
        
        if (input == null || input.trim().isEmpty()) {
            
            throw new IllegalArgumentException("L'attività prevalente è obbligatoria e non può essere vuota.");
        }
        String chiave = input.trim().toUpperCase();
        
        return LOOKUP.getOrDefault(chiave, ALTRO);
        //cerca nella mappa se trova -> valore se no -> altro
    }
}