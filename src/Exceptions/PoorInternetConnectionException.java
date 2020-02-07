package Exceptions;

public class PoorInternetConnectionException extends Exception {
    public PoorInternetConnectionException(String errorMessage){
        super(errorMessage);
    }
}
