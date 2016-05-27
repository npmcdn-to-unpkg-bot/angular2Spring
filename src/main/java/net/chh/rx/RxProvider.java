package net.chh.rx;

public interface RxProvider<T>
{
    public void subscribe(RxListener<T> listener);

}
