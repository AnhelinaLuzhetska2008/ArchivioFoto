package progettoarchivio;

import java.util.*;

/**
 * Catalogo centrale dei soggetti fotografati.
 * Implementa il pattern Singleton (lazy initialization, thread-safe).
 * Garantisce unicità delle chiavi e accesso globale.
 */

public final class CatalogoSoggetti {

    /** Unica istanza del catalogo */
    
    private static final class Holder {
        
        static final CatalogoSoggetti ISTANZA = new CatalogoSoggetti();
    }

    /** Mappa: chiave (maiuscola, trimmed) → Soggetto */
    
    private final Map<String, Soggetto> soggetti;

    /** Costruttore privato: inizializza la mappa */
    
    private CatalogoSoggetti() {
        
        this.soggetti = new HashMap<>();
    }

    /** Restituisce l'unica istanza del catalogo */
    
    public static CatalogoSoggetti getInstance() {
        
        return Holder.ISTANZA;
    }

    /**
     * Aggiunge un soggetto al catalogo.
     * @param s soggetto da aggiungere (non null)
     * @throws IllegalArgumentException se s è null o chiave già esistente
     */
    
    public void aggiungiSoggetto(Soggetto s) {
        
        if (s == null) {
            
            throw new IllegalArgumentException("Il soggetto non può essere vuoto.");
        }
        String chiave = normalizzaChiave(s.getKey());
        
        if (soggetti.containsKey(chiave)) {
            
            throw new IllegalArgumentException("Chiave già esistente: '" + chiave + "'");
        }
        soggetti.put(chiave, s);
    }

    /**
     * Cerca un soggetto per chiave (case-insensitive, trimmed).
     * @param chiave chiave da cercare (non null, non vuota)
     * @return Soggetto corrispondente
     * @throws IllegalArgumentException se chiave invalida
     * @throws NoSuchElementException se non trovato
     */
    
    public Soggetto trovaPerChiave(String chiave) {
        
        String k = normalizzaChiave(chiave);
        Soggetto s = soggetti.get(k);
        
        if (s == null) {
            
            throw new NoSuchElementException("Soggetto non trovato con chiave: '" + k + "'");
        }
        return s;
    }

    /**
     * Rimuove un soggetto dal catalogo.
     * @param chiave chiave del soggetto da rimuovere
     * @return true se rimosso, false se non esiste
     */
    
    public boolean rimuoviSoggetto(String chiave) {
        
        if (chiave == null || chiave.trim().isEmpty()) return false;
        
        return soggetti.remove(normalizzaChiave(chiave)) != null;
    }

    /**
     * Restituisce una vista non modificabile di tutti i soggetti.
     * @return collezione di sola lettura
     */
    
    public Collection<Soggetto> tuttiSoggetti() {
        
        return Collections.unmodifiableCollection(soggetti.values());
    }

    /** Restituisce il numero di soggetti nel catalogo */
    
    public int dimensione() {
        
        return soggetti.size();
    }

    /**
     * Normalizza una chiave: trim + maiuscolo.
     * Usata internamente per uniformità.
     */
    
    private String normalizzaChiave(String chiave) {
        
        if (chiave == null || chiave.trim().isEmpty()) {
            
            throw new IllegalArgumentException("La chiave non può essere null o vuota.");
        }
        return chiave.trim().toUpperCase();
    }
}