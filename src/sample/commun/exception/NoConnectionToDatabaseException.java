package sample.commun.exception;

import sample.commun.strings.STRINGS;

/**
 * Created by maheryHaja on 6/22/2018.
 */
public class NoConnectionToDatabaseException extends GenericRuntimeException{

    public NoConnectionToDatabaseException() {
        super(STRINGS.ErrorMessage.NO_CONNECTION_TO_DATABASE);
    }
}
