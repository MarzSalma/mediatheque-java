package src;

public class DocumentIntrouvableException extends MediathequeException{
    public DocumentIntrouvableException(String titre) {
        super("DocumentIntrouvableException: " + titre);
    }
    public DocumentIntrouvableException(String titre, Throwable cause) {
        super("DocumentIntrouvableException: " + titre, cause);
    }
}
