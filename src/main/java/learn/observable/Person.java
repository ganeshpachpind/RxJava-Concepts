package learn.observable;

import io.reactivex.Observable;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Observable<String> deferObservable() {
        return Observable.defer(() -> Observable.just(name));
    }


    public Observable<String> nonDeferObservable() {
        return Observable.just(name);
    }
}
