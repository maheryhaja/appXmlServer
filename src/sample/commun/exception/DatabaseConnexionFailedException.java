package sample.commun.exception;

import sample.commun.strings.STRINGS;

/**
 * Created by maheryHaja on 6/22/2018.
 */
public class DatabaseConnexionFailedException extends GenericRuntimeException {

    public DatabaseConnexionFailedException() {
        super(STRINGS.ErrorMessage.ERROR_DATABASE_CONF);
    }
}
