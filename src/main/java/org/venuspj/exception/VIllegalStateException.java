/*
 * Copyright 2004-2012 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.venuspj.exception;


/**
 * {@link IllegalStateException}をラップする例外です。
 * 
 */
public class VIllegalStateException extends IllegalStateException {

    private static final long serialVersionUID = -2154525994315946504L;

    /**
     * {@link VIllegalStateException}を作成します。
     */
    public VIllegalStateException() {
        super();
    }

    /**
     * {@link VIllegalStateException}を作成します。
     * 
     * @param message
     *            メッセージ
     */
    public VIllegalStateException(final String message) {
        super(message);
    }

    /**
     * {@link VIllegalStateException}を作成します。
     * 
     * @param message
     *            メッセージ
     * @param cause
     *            元の例外
     */
    public VIllegalStateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * {@link VIllegalStateException}を作成します。
     * 
     * @param cause
     *            元の例外
     */
    public VIllegalStateException(final Throwable cause) {
        super(cause);
    }

}
