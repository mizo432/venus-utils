package org.venuspj.util.collect;

import java.util.Map;
import java.util.Set;

public interface BiMap<K, V> extends Map<K, V> {

    @Override
    V put(K key, V value);

    V forcePut(K key, V value);

    // Bulk Operations

    @Override
    void putAll(Map<? extends K, ? extends V> map);

    // Views

    @Override
    Set<V> values();

    BiMap<V, K> inverse();
}