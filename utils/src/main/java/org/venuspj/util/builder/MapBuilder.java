package org.venuspj.util.builder;

import org.venuspj.util.objects2.Objects2;

import java.util.Map;

import static org.venuspj.util.collect.Maps2.newHashMap;

public class MapBuilder<K, V> extends ObjectBuilder<Map<K, V>, MapBuilder<K, V>> {

    Map<K, V> map = newHashMap();

    public MapBuilder<K, V> put(K key, V value) {
        if (Objects2.isNull(key)) return getThis();
        addConfigurator(builder -> builder.map.put(key, value));
        return getThis();
    }

    public MapBuilder<K, V> putAll(Map<K, V> aMap) {
        if (Objects2.isNull(aMap)) return getThis();
        addConfigurator(builder -> builder.map.putAll(aMap));
        return getThis();
    }

    @Override
    protected void apply(Map<K, V> vo, MapBuilder<K, V> builder) {
        builder.putAll(vo);

    }

    @Override
    protected Map<K, V> createValueObject() {
        return newHashMap(map);
    }

    @Override
    protected MapBuilder<K, V> getThis() {
        return this;
    }

    @Override
    protected MapBuilder<K, V> newInstance() {
        return new MapBuilder<>();
    }
}
