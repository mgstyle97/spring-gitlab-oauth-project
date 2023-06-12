package space.migni.gitlab.domain.auth.oauth.services;

import org.springframework.web.client.RestTemplate;
import space.migni.gitlab.domain.auth.oauth.services.GitlabOAuthService;

public abstract sealed class OAuthService permits GitlabOAuthService {

    protected final RestTemplate client;

    protected OAuthService(
        final RestTemplate client
    ) {
        this.client = client;
    }

    public abstract String getAccessToken(final String authorizationCode);
    public abstract String getUserInfo(final String accessToken);

}
