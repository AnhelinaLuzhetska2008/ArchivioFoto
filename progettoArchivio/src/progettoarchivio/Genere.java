package progettoarchivio;

/**
 * Enum per rappresentare il genere di una persona.
 * Valori: M (Maschio), F (Femmina), A (Altro).
 */

public enum Genere {
    
    M("Maschio"),
    F("Femmina"),
    A("Altro");

    private final String descrizione;

    /** Costruttore privato per inizializzare la descrizione leggibile. */

    Genere(String descrizione) {
        
        this.descrizione = descrizione;
    }

    /** Restituisce la descrizione leggibile */
    
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return descrizione;
    }

/**
     * Converte un char in Genere.
     * @param c carattere ('M', 'm', 'F', f', 'A', 'a')
     * @return sesso corrispondente
     * @throws IllegalArgumentException se il carattere è invalido
     */
    
    public static Genere daChar(char c) {
        
        return switch (Character.toUpperCase(c)) {
            
            case 'M' -> M;
            case 'F' -> F;
            case 'A' -> A;
            default -> throw new IllegalArgumentException(
                    
                "Genere non valido: '%c'. Usa 'M', 'F' oppure 'A'.".formatted(c)
            );
        };
    }
    
    /**
     * Converte una stringa in Genere (case-insensitive).
     * Accetta: "M", "MASCHIO", "MAN", "W", "FEMMINA", "WOMAN", "O", "ALTRO", "OTHER".
     * @param s stringa da convertire (non null)
     * @return Genere corrispondente
     * @throws IllegalArgumentException se la stringa è invalida o null
     */
    
    public static Genere fromString(String s) {
        
        if (s == null) {
            
            throw new IllegalArgumentException("Il genere è obbligatorio e non può essere vuoto.");
        }
    
    return switch (s.trim().toUpperCase()) {
        
        case "M", "MASCHIO" -> M;
        case "F", "FEMMINA" -> F;
        case "A", "ALTRO"-> A;
            
        default -> throw new IllegalArgumentException("Genere non valido: '%s'.".formatted(s));
    };
}
}