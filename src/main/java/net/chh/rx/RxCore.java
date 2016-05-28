package net.chh.rx;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import com.google.common.collect.Maps;

public class RxCore {

  private Map<RxKey, SnapshotUpdateSubject<RxData>> subscriptionStreams = Maps.newConcurrentMap();

  private ReentrantLock subscriptionLocks = new ReentrantLock();

  private RxProvider<RxData> provider;
  public RxCore(RxProvider<RxData> provider) {
    this.provider = provider;
  }

  public SnapshotUpdateSubject<RxData> instrumentSubscription(RxKey key) {
    subscriptionLocks.lock();
    try {
      SnapshotUpdateSubject<RxData> subject = subscriptionStreams.get(key);
      if (subject == null) {
        subject = new SnapshotUpdateSubject<RxData>(key);
        subject.startSubscription(provider);
        subscriptionStreams.put(key, subject);
      }
      return subject;
    } finally {
      subscriptionLocks.unlock();
    }
  }
}
