package net.myphenotype.Librarian.ExceptionHandler;

public class BookErrors {

    private int errorStatus;
    private String errorMessage;
    private long timeStamp;

    public BookErrors() {
    }

    public BookErrors(int errorStatus, String errorMessage, long timeStamp) {
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;
    }

    public int getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(int errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "BookErrors{" +
                "errorStatus=" + errorStatus +
                ", errorMessage='" + errorMessage + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
