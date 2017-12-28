package org.venuspj.util.collect;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import static org.venuspj.util.collect.CollectPreconditions.checkEntryNotNull;

@SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")
class ImmutableMapEntry<K, V> extends ImmutableEntry<K, V> {
    @SuppressWarnings("unchecked")
    static <K, V> ImmutableMapEntry<K, V>[] createEntryArray(int size) {
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

    static class NonTerminalImmutableMapEntry<K, V> extends ImmutableMapEntry<K, V> {
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

    static final class NonTerminalImmutableBiMapEntry<K, V>
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