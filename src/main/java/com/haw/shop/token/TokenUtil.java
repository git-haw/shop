package com.haw.shop.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.haw.shop.model.UserInfo;

/**
 * Created by aiwei on 2020-3-13.
 */
public class TokenUtil {
    public static String getToken(Integer id) {
        String token = "";
        token = JWT.create().withAudience(id.toString()).sign(Algorithm.HMAC256("screct"));
        return token;
    }
}
