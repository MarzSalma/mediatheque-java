package src;

public class DocumentIndisponibleException extends MediathequeException{
    public DocumentIndisponibleException(String titre) {
        super("Document indisponible: " + titre);
    }

    public DocumentIndisponibleException(String titre, Throwable cause) {
        super("Document indisponible: " + titre, cause);
    }

}
