package learn.observable;


import io.reactivex.Observable;

public class ObservableDefer {
    public static void main(String[] args) {

        Person person = new Person("user1");

        Observable<String> nonDeferObservable = person.nonDeferObservable();
        Observable<String> deferObservable = person.deferObservable();

        person.setName("user2");

        // even value of person is changed non defer observable  will emit old value as value1
        nonDeferObservable.subscribe(p -> System.out.println("Non defer observable returns value at time observable was created  - " + p));

        // defer observable creates observable only when subscribed
        deferObservable.subscribe(p -> System.out.println("Defer observable returns latest value - " + p));

    }
}
