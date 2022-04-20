package by.it.academy.MK_JD2_88_2.hw1.repository.exceptions;

public class EssenceNotFound extends RuntimeException{
    public EssenceNotFound() {
        super();
    }

    public EssenceNotFound(String message) {
        super(message);
    }

    public EssenceNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public EssenceNotFound(Throwable cause) {
        super(cause);
    }

    protected EssenceNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
