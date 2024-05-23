package org.venuspj.util.builder;

import static org.venuspj.util.collect.Lists2.newArrayList;

import java.util.List;

/**
 * ValueObjectのインスタンスを生成するビルダー。
 *
 * @param <T> ビルド対象のインスタンスの型
 * @param <S> このビルダークラスの型
 */
public abstract class ObjectBuilder<T, S extends ObjectBuilder<T, S>> {

  protected List<BuilderConfigurator<S>> configurators = newArrayList();

  /**
   * ビルダの設定に基づき、引数のValueObjectの内容を変更した新しいインスタンスを生成する。
   *
   * @param vo 状態を引用するValueObject
   * @return vo の内容に対して、このビルダの設定を上書きしたValueObjectの新しいインスタンス
   */
  public T apply(T vo) {
    S builder = newInstance();
    apply(vo, builder);
    for (BuilderConfigurator<S> configurator : configurators) {
      builder.addConfigurator(configurator);
    }
    return builder.build();
  }

  /**
   * ビルダの設定に基づいてValueObjectの新しいインスタンスを生成する。
   *
   * @return ValueObjectの新しいインスタンス
   */
  public T build() {
    for (BuilderConfigurator<S> configurator : configurators) {
      configurator.configure(getThis());
    }

    return createValueObject();
  }

  /**
   * {@link BuilderConfigurator}を追加する。
   *
   * @param configurator {@link BuilderConfigurator}
   */
  protected void addConfigurator(BuilderConfigurator<S> configurator) {
    configurators.add(configurator);
  }

  /**
   * 引数のビルダに対して、引数のValueObjectの内容を適用する。
   *
   * @param vo 状態を引用するValueObject
   * @param builder ビルダ
   */
  protected abstract void apply(T vo, S builder);

  /**
   * ビルダの設定に基づいてValueObjectの新しいインスタンスを生成する。
   * <p>
   * <p>
   * {@link ObjectBuilder#build}内でこのビルダに追加された{@link BuilderConfigurator}を全て実行した後に、このメソッドが呼ばれる。<br>
   * その為、このビルダに対する変更を行うロジックはこのメソッド内に記述せず、目的となるValueObjectを生成し返すロジックを記述することが望まれる。
   * </p>
   *
   * @return ValueObjectの新しいインスタンス
   */
  protected abstract T createValueObject();

  /**
   * このビルダークラスのインスタンスを返す。
   *
   * @return このビルダークラスのインスタンス。
   */
  protected abstract S getThis();

  /**
   * このビルダークラスの新しいインスタンスを返す。
   *
   * @return このビルダークラスの新しいインスタンス。
   */
  protected abstract S newInstance();


  /**
   * {@link ObjectBuilder#build()}内で順次実行されるビルダの設定を定義するインタフェース。
   *
   * @param <S> 設定対象ビルダーの型
   */
  public static interface BuilderConfigurator<S> {

    /**
     * {@link ObjectBuilder#build()}内で呼ばれる際に実行するビルドアクションを定義する。
     *
     * @param builder ビルダーインスタンス
     */
    void configure(S builder);

  }
}