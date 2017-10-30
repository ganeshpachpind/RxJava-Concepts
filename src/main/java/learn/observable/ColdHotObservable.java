package learn.observable;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

/*
Example converting cold observable to hot observable.
 */
public class ColdHotObservable {

    public static void main(String[] args) throws InterruptedException {

        // cold observables - emits one item to one observer and emits from beginning  like cd
        Observable<Integer> ob1 = Observable.just(1, 2, 3);
        ob1.observeOn(Schedulers.newThread())
                .subscribe((integer) -> System.out.println(integer + " - cold observable"));


        // convert cold observable to hot observable
        // Hot observable - emits items to multiple observer same time and emits item from the point it was subscribed.
        ConnectableObservable<Integer> hotOb1 = ob1.publish();

        // bellow both observable will receive data at the same time
        hotOb1.observeOn(Schedulers.newThread())
                .subscribe(integer -> System.out.println(integer + " - Hot ob 1 "));

        hotOb1.observeOn(Schedulers.newThread())
                .subscribe(integer -> System.out.println(integer + " - Hot ob 2 "));

        // starts hot from cold observable
        hotOb1.connect();


        // sleep main thread to let observables finish
        Thread.sleep(1000);


    }
}
