package sample.commun.exception;

import sample.commun.strings.STRINGS;

/**
 * Created by maheryHaja on 6/23/2018.
 */
public class SQLQueryException extends GenericRuntimeException {

    public SQLQueryException() {
        super(STRINGS.ErrorMessage.ERROR_SQL_QUERY);
    }
}
