package net.chh.rx;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import com.google.common.collect.Maps;

public class RxCore
{

    private Map<RxKey, SnapshotUpdateSubject<RxData>> subscriptionStreams = Maps.newConcurrentMap();

    private ReentrantLock subscriptionLocks = new ReentrantLock();

    public RxCore()
    {

    }

    public Object instrumentSubscription(RxKey key)
    {
        subscriptionLocks.lock();
        try
        {
            SnapshotUpdateSubject<RxData> subject = subscriptionStreams.get(key);
            if (subject == null)
            {
                subject = new SnapshotUpdateSubject();
                subscriptionStreams.put(key, subject);
            }
            return subject;
        } finally
        {
            subscriptionLocks.unlock();
        }

    }
}
