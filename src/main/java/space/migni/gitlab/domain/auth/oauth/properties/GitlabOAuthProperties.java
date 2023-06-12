package space.migni.gitlab.domain.auth.oauth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties("gitlab.properties")
public record GitlabOAuthProperties(
        String clientId,
        String clientSecret,
        String redirectUri,
        String authorizationUri,
        String tokenUri,
        String userInfoUri
) {
    @ConstructorBinding
    public GitlabOAuthProperties(
            final String clientId,
            final String clientSecret,
            final String redirectUri,
            final String authorizationUri,
            final String tokenUri,
            final String userInfoUri
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.authorizationUri = authorizationUri;
        this.tokenUri = tokenUri;
        this.userInfoUri = userInfoUri;
    }
}
