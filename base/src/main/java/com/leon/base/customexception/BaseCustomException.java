/**
 * FileName: BaseCustomException
 * Author: shiwenliang
 * Date: 2021/10/22 11:15
 * Description:
 */
package com.leon.base.customexception;

public abstract class BaseCustomException extends Throwable {

    public BaseCustomException(final String exceptionMessage) {
        super(exceptionMessage);
    }
}
