package org.venuspj.util.collect;

import java.util.Map.Entry;
import org.venuspj.util.objects2.Objects2;

abstract class AbstractMapEntry<K, V> implements Entry<K, V> {

  @Override
  public abstract K getKey();

  @Override
  public abstract V getValue();

  @Override
  public V setValue(V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Entry) {
      Entry<?, ?> that = (Entry<?, ?>) object;
      return Objects2.equal(this.getKey(), that.getKey())
          && Objects2.equal(this.getValue(), that.getValue());
    }
    return false;
  }

  @Override
  public int hashCode() {
    K k = getKey();
    V v = getValue();
    return ((k == null) ? 0 : k.hashCode()) ^ ((v == null) ? 0 : v.hashCode());
  }

  @Override
  public String toString() {
    return getKey() + "=" + getValue();
  }
}