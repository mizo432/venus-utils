package org.venuspj.util.collect;

import java.io.Serializable;

import static org.venuspj.util.collect.CollectPreconditions.checkEntryNotNull;

class ImmutableMapEntry<K extends Serializable, V extends Serializable> extends ImmutableEntry<K, V> {
    @SuppressWarnings("unchecked")
    static <K extends Serializable, V extends Serializable> ImmutableMapEntry<K, V>[] createEntryArray(int size) {
        return new ImmutableMapEntry[size];
    }

    ImmutableMapEntry(K key, V value) {
        super(key, value);
        checkEntryNotNull(key, value);
    }

    ImmutableMapEntry(ImmutableMapEntry<K, V> contents) {
        super(contents.getKey(), contents.getValue());
        // null check would be redundant
    }

    ImmutableMapEntry<K, V> getNextInKeyBucket() {
        return null;
    }

    ImmutableMapEntry<K, V> getNextInValueBucket() {
        return null;
    }

    boolean isReusable() {
        return true;
    }

    static class NonTerminalImmutableMapEntry<K extends Serializable, V extends Serializable> extends ImmutableMapEntry<K, V> {
        private final transient ImmutableMapEntry<K, V> nextInKeyBucket;

        NonTerminalImmutableMapEntry(K key, V value, ImmutableMapEntry<K, V> nextInKeyBucket) {
            super(key, value);
            this.nextInKeyBucket = nextInKeyBucket;
        }

        @Override
        final ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return nextInKeyBucket;
        }

        @Override
        final boolean isReusable() {
            return false;
        }

        private static final long serialVersionUID = 0;


    }

    static final class NonTerminalImmutableBiMapEntry<K extends Serializable, V extends Serializable>
            extends NonTerminalImmutableMapEntry<K, V> {
        private final transient ImmutableMapEntry<K, V> nextInValueBucket;

        NonTerminalImmutableBiMapEntry(
                K key,
                V value,
                ImmutableMapEntry<K, V> nextInKeyBucket,
                ImmutableMapEntry<K, V> nextInValueBucket) {
            super(key, value, nextInKeyBucket);
            this.nextInValueBucket = nextInValueBucket;
        }

        @Override
        ImmutableMapEntry<K, V> getNextInValueBucket() {
            return nextInValueBucket;
        }

        private static final long serialVersionUID = 0;

    }

}