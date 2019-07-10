package com.it.demosso;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTTest {

    private String APP_ID = "123";
    private String APP_SECRET = "gwt";
    byte[] apiKeySecretBytes = APP_SECRET.getBytes();
    @Test
    public void test1() {
        //JWT.create()
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(APP_ID + APP_SECRET);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", UUID.randomUUID().toString());
        claims.put("username", UUID.randomUUID().toString());
        claims.put("password", UUID.randomUUID().toString());
        claims.put("role", "aaa");
        System.out.println(claims);
        //生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        String key = claims.get("password").toString();
        //jwt所面向的用户
        String subject = claims.get("username").toString();
        //签证者
        String issuer = "admin";

        Date expirDate = Date.from(LocalDateTime.now().plusMinutes(2).atZone(ZoneId.systemDefault()).toInstant());
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(new Date())
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
//               //jwt签发者
                .setIssuer(issuer)
                .setExpiration(expirDate)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, signingKey);


        //Builds the JWT and serializes it to a compact, URL-safe string
        String compact = builder.compact();
        System.out.println(compact);
    }

    @Test
    public void test2() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Mzg4NGVlZi0yYTM4LTQxYjUtOTEzMS1lODY4ZTc1NTdkYjUiLCJwYXNzd29yZCI6IjA2ODhhOTNkLTU0ZDgtNGRmYi05MjY1LWZmM2M2YzZiY2QyNCIsInJvbGUiOiJhYWEiLCJpc3MiOiJhZG1pbiIsImlkIjoiNmNhM2IzNjItMTg0Zi00YTc5LTgwMmYtZjA0MWFjZmFiODJkIiwiZXhwIjoxNTYxMzU3ODk1LCJpYXQiOjE1NjEzNTc3NzUsImp0aSI6ImQyYjc4MGI3LWY2MGQtNDMxMy1hMzBiLWYxN2I1ZjRiMzk5NSIsInVzZXJuYW1lIjoiNjM4ODRlZWYtMmEzOC00MWI1LTkxMzEtZTg2OGU3NTU3ZGI1In0.Rt8-vqzwHPDGMvj0zDClcw6xljPJI9lAkIHY40v6aro";
        Claims claims = Jwts.parser()
//                .setSigningKey(DatatypeConverter.parseBase64Binary(APP_ID + APP_SECRET))
                .setSigningKey(apiKeySecretBytes)
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("登录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色：" + claims.get("role"));
        System.out.println("用户角色：" + claims.get("id"));
        System.out.println("用户角色：" + claims.get("username"));
        System.out.println("用户角色：" + claims.get("password"));

    }

}
