package com.ipb.frame;

import com.ipb.domain.OrdersCart;

import java.util.List;

public interface MyService<K, V> {
  public OrdersCart register(V v) throws Exception;
  public void modify(V v) throws Exception;
  public void remove(K k) throws Exception;
  public V get(K k) throws Exception;
  public List<V> get() throws Exception;
}