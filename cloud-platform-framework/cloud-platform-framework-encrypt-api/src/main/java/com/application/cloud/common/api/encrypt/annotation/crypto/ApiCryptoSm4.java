package com.application.cloud.common.api.encrypt.annotation.crypto;

import com.application.cloud.common.api.encrypt.annotation.decrypt.ApiDecrypt;
import com.application.cloud.common.api.encrypt.annotation.encrypt.ApiEncrypt;
import com.application.cloud.common.api.encrypt.enums.EncryptType;

import java.lang.annotation.*;

/**
 * Sm4加密解密注解
 *
 * @author cloud
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ApiEncrypt(EncryptType.SM4)
@ApiDecrypt(EncryptType.SM4)
public @interface ApiCryptoSm4 {

}
