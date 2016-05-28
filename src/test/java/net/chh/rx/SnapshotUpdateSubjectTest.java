package net.chh.rx;

import static org.mockito.Matchers.eq;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import rx.Observer;
import rx.Subscription;


public class SnapshotUpdateSubjectTest {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Test
  public void testSubscribe()
  {
    Observer<RxData> listener = Mockito.mock(Observer.class);
    RxKey key = new RxKey();
    SnapshotUpdateSubject<RxData> subject = new SnapshotUpdateSubject<>(key);
    Subscription sub1 = subject.addListener(listener);
    
    RxProvider<RxData> provider = Mockito.mock(RxProvider.class);
    
    subject.startSubscription(provider);
    
    ArgumentCaptor<RxListener> listenerCaptor = ArgumentCaptor.forClass(RxListener.class);
    verify(provider, times(1)).subscribe(listenerCaptor.capture());
    
    RxListener<RxData> callback = listenerCaptor.getValue();
    assertThat(callback).isNotNull();
    
    RxDataDummy data = new RxDataDummy("key", "value");
    callback.onData(data);
    
    verify(listener, times(1)).onNext(eq(data));
    
    RxDataDummy data2 = new RxDataDummy("key2", "value2");
    callback.onData(data2);
    verify(listener, times(1)).onNext(eq(data2));
    
    Observer<RxData> listener2 = Mockito.mock(Observer.class);
    Subscription sub2 = subject.addListener(listener2);
    
    verify(listener2, times(1)).onNext(eq(data2));
    
    RxDataDummy data3 = new RxDataDummy("key3", "value3");
    callback.onData(data3);
    verify(listener, times(1)).onNext(eq(data3));
    verify(listener2, times(1)).onNext(eq(data3));
    
    sub1.unsubscribe();
    
    RxDataDummy data4 = new RxDataDummy("key4", "value4");
    callback.onData(data4);
    verify(listener2, times(1)).onNext(eq(data4));

    subject.end();
    
    verify(listener2, times(1)).onCompleted();
    assertThat(sub2.isUnsubscribed()).isTrue();
    
    Mockito.verifyNoMoreInteractions(listener, listener2);
    
  }
}
