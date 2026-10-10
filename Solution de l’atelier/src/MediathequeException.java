package src;

public abstract class MediathequeException extends Exception {

    public MediathequeException(String message) {
        super(message);
    }
    public MediathequeException(String message, Throwable cause) {
        super(message, cause);
    }
}


