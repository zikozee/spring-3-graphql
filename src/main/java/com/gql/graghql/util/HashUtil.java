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

    public boolean isBcryptMatch(String original, String hashValue){
        return OpenBSDBCrypt.checkPassword(hashValue, original.getBytes(StandardCharsets.UTF_8));
    }
}
