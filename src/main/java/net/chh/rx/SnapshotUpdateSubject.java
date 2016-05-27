package net.chh.rx;

import rx.Observable;
import rx.Observer;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class SnapshotUpdateSubject<T>
{
    private RxKey key;
    private BehaviorSubject<T> snapshotSubject;
    private PublishSubject<T> updateSubject;

    public SnapshotUpdateSubject(RxKey key)
    {
        this.key = key;
        this.snapshotSubject = BehaviorSubject.create();
        this.updateSubject = PublishSubject.create();
    }

    void startSubscription(RxProvider<T> provider)
    {
        provider.subscribe(data -> populate(data));
    }

    public void addListener(Observer<T> observer)
    {
        Observable.concat(Observable.just(snapshotSubject.getValue()), updateSubject)
            .subscribe(observer);
    }

    private void populate(T data)
    {
        mergeSnapshot(snapshotSubject.getValue(), data);
        updateSubject.onNext(data);
    }

    private void mergeSnapshot(T value, T data)
    {
        // TODO
    }

}
