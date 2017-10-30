package learn.observable;

import io.reactivex.Observable;

public class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }

    void setName(String name) {
        this.name = name;
    }

    Observable<String> deferObservable() {
        return Observable.defer(() -> Observable.just(name));
    }

    Observable<String> nonDeferObservable() {
        return Observable.just(name);
    }
}
