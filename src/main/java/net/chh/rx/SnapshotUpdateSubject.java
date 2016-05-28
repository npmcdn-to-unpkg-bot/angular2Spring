package net.chh.rx;

import lombok.Data;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Subject that support snapshot and update.
 * 
 * @param <T>
 *          Type of the Snapshot
 */
@Data
public class SnapshotUpdateSubject<T> {

  private RxKey key;
  private BehaviorSubject<T> snapshotSubject;
  private PublishSubject<T> updateSubject;

  /**
   * Default constructor to take key into account.
   * 
   * @param key
   *          Key of the subject
   */
  public SnapshotUpdateSubject(RxKey key) {
    this.key = key;
    this.snapshotSubject = BehaviorSubject.create();
    this.updateSubject = PublishSubject.create();
  }

  void startSubscription(RxProvider<T> provider) {
    provider.subscribe(data -> populate(data));
  }

  public Subscription addListener(Observer<T> observer) {
    Observable<T> stream;
    if (snapshotSubject.getValue() != null) {
      stream = Observable.concat(Observable.just(snapshotSubject.getValue()), updateSubject);
    }
    else {
      stream = updateSubject;
    }
    Subscription subscription = stream.subscribe(observer);
    return subscription;
  }

  private void populate(T data) {
    snapshotSubject.onNext(mergeSnapshot(snapshotSubject.getValue(), data));
    updateSubject.onNext(data);
  }

  private T mergeSnapshot(T existingValue, T update) {
    // TODO
    return update;
  }

  public void end() {
    snapshotSubject.onCompleted();
    updateSubject.onCompleted();
  }
}
