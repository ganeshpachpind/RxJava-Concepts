import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * Understanding basic subscribeOn and observerOn
 */
public class SchedulersExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Started execution in thread " + Thread.currentThread().getName());

        // execute observable on computation thread
        Observable<String> observable = Observable.<String>create((s) -> {
            System.out.println("Internal work of observable on thread" + Thread.currentThread().getName());
            for (int i = 0; i < 2; i++) {
                s.onNext("" + i);
                Thread.sleep(1000);
            }
        }).subscribeOn(Schedulers.computation());

        // observer gets result on new thread
        observable.observeOn(Schedulers.newThread()).subscribe((data) -> {
            System.out.println("data is on IO thread " + Thread.currentThread().getName());
        });

        System.out.println("dfdfdf ---" + Thread.currentThread().getName());

        Thread.sleep(10000);
    }

}
