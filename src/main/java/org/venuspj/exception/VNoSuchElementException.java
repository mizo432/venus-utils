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

import java.util.NoSuchElementException;

/**
 * {@link NoSuchElementException}をラップする例外です。
 */
public class VNoSuchElementException extends NoSuchElementException {

    private static final long serialVersionUID = 1632854460852262479L;

    /**
     * {@link VNoSuchElementException}を作成します。
     */
    public VNoSuchElementException() {
        super();
    }

    /**
     * {@link VNoSuchElementException}を作成します。
     * 
     * @param message
     *            メッセージ
     */
    public VNoSuchElementException(final String message) {
        super(message);
    }

}