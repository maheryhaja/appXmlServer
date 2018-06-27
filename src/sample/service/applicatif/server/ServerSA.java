package sample.service.applicatif.server;

import io.reactivex.Observable;

public interface ServerSA {
    void start();
    void stop();

    Observable<Boolean> isRunningObservable();
}
