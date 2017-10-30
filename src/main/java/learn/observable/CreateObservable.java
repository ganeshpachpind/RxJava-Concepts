package learn.observable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

/**
 * Simple Example of creating observable and subscribing on it
 */
public class CreateObservable {
    public static void main(String[] args) throws InterruptedException {

        // by default this executes on current thread can be changes using subscribeOn
        Observable<Integer> ob1 = Observable.<Integer>create((source) -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread is " + Thread.currentThread().getName());
                source.onNext(i); // event
            }
            source.onComplete(); // notification
        });

        // uses decorator patterns and creates new observable -- also moving observable execution from main thread to new thread
        Observable<Integer> filter = ob1.filter((i) -> i % 2 == 0)
                .subscribeOn(Schedulers.newThread());


        filter.subscribe();

        Thread.sleep(1000);

    }
}
