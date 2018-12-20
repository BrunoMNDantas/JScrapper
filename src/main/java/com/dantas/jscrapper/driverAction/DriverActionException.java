package com.dantas.jscrapper.driverAction;

public class DriverActionException extends Exception {

    public DriverActionException(String message) {
        super(message);
    }

    public DriverActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverActionException(Throwable cause) {
        super(cause);
    }

}
