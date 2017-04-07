package sddtc.oauth2.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import sddtc.oauth2.authserver.config.CustomUserDetailsService;

import javax.annotation.Resource;

/**
 * Created by hchang on 2017/4/8.
 */
@Configuration
@EnableAuthorizationServer
@ComponentScan(basePackages = {"sddtc.oauth2.authserver"})
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    Environment env;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String[] grantTypes = env.getProperty("client.authorized-grant-types").split(",");
        String[] scrops = env.getProperty("client.scope").split(",");

        clients.inMemory()
                .withClient(env.getProperty("clientId"))
                .secret(env.getProperty("clientSecret"))
                .authorizedGrantTypes(grantTypes)
                .scopes(scrops)
                .accessTokenValiditySeconds(Integer.parseInt(env.getProperty("client.access-token-validity-seconds")));
    }
}
