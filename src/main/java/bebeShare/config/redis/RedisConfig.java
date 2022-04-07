package bebeShare.config.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;


@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 6000) /* 세션 만료 시간 : 60초 */
@RequiredArgsConstructor
public class RedisConfig extends AbstractHttpSessionApplicationInitializer {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;


//    @Bean
//    public LettuceConnectionFactory connectionFactory() {
//        return new LettuceConnectionFactory(host, port);
//
//
//    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer lettuceClientConfigurationBuilderCustomizer() {
        return clientConfigurationBuilder -> {
            if (clientConfigurationBuilder.build().isUseSsl()) {
                clientConfigurationBuilder.useSsl().disablePeerVerification();
            }
        };
    }

    public static StatefulRedisConnection<String, String> connect() {
        System.out.println("redis!!");
        RedisURI redisURI = RedisURI.create(System.getenv("redis: // : pae39b99f39db2c0e45ff88155be2bfd0445aa604c574ce084b84baf3b12e436d @ ec2-18-205-76-248.compute-1.amazonaws.com :"));
        redisURI.setVerifyPeer(false);

        RedisClient redisClient = RedisClient.create(redisURI);
        return redisClient.connect();
    }





}