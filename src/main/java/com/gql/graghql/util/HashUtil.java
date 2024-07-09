package com.gql.graghql.util;

import lombok.experimental.UtilityClass;
import org.bouncycastle.crypto.generators.OpenBSDBCrypt;

import java.nio.charset.StandardCharsets;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@UtilityClass
public class HashUtil {

    private final String BCRYPT_SALT = "dontDoThisOnProd";

    public boolean isBcryptMatch(String original, String hashValue){
        return OpenBSDBCrypt.checkPassword(hashValue, original.getBytes(StandardCharsets.UTF_8));
    }

    public String hashBcrypt(String original){
        return OpenBSDBCrypt.generate(original.getBytes(StandardCharsets.UTF_8),
                BCRYPT_SALT.getBytes(StandardCharsets.UTF_8), 5);
    }
}
