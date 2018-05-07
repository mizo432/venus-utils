package org.venuspj.util.collect;

import java.io.Serializable;

class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
    final K key;
    final V value;

    ImmutableEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public final K getKey() {
        return key;
    }

    @Override
    public final V getValue() {
        return value;
    }

    @Override
    public final V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    private static final long serialVersionUID = 0;
}