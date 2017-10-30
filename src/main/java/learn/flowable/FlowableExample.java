package learn.flowable;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Basic example of flowbale with Subscriber - downstream controlling  amount of information required
 */
public class FlowableExample {
    public static void main(String[] args) throws InterruptedException {

        Flowable<Integer> flowable = Flowable.range(1, 1000);

        flowable.doOnNext(x -> System.out.println("Publishing val - " + x))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(100); // requesting only first 100 value
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Received val " + integer);

                        // consuming values slower than publisher
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("Error ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Finish it");
                    }
                });

        Thread.sleep(3000);

    }
}
