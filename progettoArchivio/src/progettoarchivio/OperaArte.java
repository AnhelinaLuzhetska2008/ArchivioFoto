package progettoarchivio;

/**
 * Rappresenta un'opera d'arte fotografata.
 * Estende Soggetto con chiave univoca.
 */

public class OperaArte extends Soggetto {
    
    private final String nomeOpera;  
    private final String artista;   
    private final String luogo;
    private final int anno;

    /**
     * Costruisce un'OperaArte.
     * @param key chiave univoca (validata in Soggetto)
     * @param nomeOpera nome dell'opera (obbligatorio, trimmed)
     * @param artista nome dell'artista (obbligatorio, trimmed)
     * @param luogo luogo dell'opera (obbligatorio, trimmed)
     * @param anno anno di creazione (0 ≤ anno ≤ 2100)
     * @throws IllegalArgumentException se parametri invalidi
     */
    
    public OperaArte(String key, String nomeOpera, String artista, String luogo, int anno) {
        
        super(key);
        
        this.nomeOpera = validaNomeOpera(nomeOpera);
        this.artista = validaArtista(artista);
        this.luogo = validaLuogo(luogo);
        this.anno = validaAnno(anno);
    }

    private String validaNomeOpera(String nome) {
        
        if (nome == null || nome.trim().isEmpty()){
            
            throw new IllegalArgumentException("E' obbligatorio inserire il nome dell'opera.");
        }
        return nome.trim();
    }

    private String validaArtista(String artista) {
        
        if (artista == null || artista.trim().isEmpty()) {
            
            throw new IllegalArgumentException("E' obbligatorio inserire il nome dell'artista.");
        }
        return artista.trim();
    }

    private String validaLuogo(String luogo) {
        
        if (luogo == null || luogo.trim().isEmpty()){
            
            throw new IllegalArgumentException("E' obbligatorio inserire il luogo dell'opera.");
        }
        return luogo.trim();
    }

    private int validaAnno(int anno) {
        
        if (anno < 0 || anno > 2100){
            
            throw new IllegalArgumentException(
                    
                    String.format("Anno inserito non valido: %d. Deve essere tra 0 e 2025", anno)
            );
        }
        return anno;
    }

    public String getNomeOpera() { 
        
        return nomeOpera;
    }
    
    public String getArtista() { 
        
        return artista; 
    }
    
    public String getLuogo() { 
        
        return luogo; 
    }
    
    public int getAnno() {
        
        return anno; 
    }

    @Override
    
    public String getDescription() {
        
        return String.format("%s di %s (%d), %s", nomeOpera, artista, anno, luogo);
    }

    @Override
    
    public String toString() {
        
        return String.format("%s - %s", super.toString(), getDescription());
    }
}