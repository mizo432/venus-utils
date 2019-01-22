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


import java.util.Arrays;

/**
 * 引数がnullだった場合にthrowする例外です。
 * 
 * {@link NullPointerException}をthrowする代わりに使うことを想定しています。
 * 
 * @author wyukawa
 */
public class NullArgumentException extends VIllegalArgumentException {

    /**
     * {@link NullArgumentException}を作成します。
     * 
     * @param argName
     *            {@code null} である引数の名前
     */
    public NullArgumentException(final String argName) {
        super(argName, "EUTL0008", Arrays.asList(argName).toArray());
    }

}
