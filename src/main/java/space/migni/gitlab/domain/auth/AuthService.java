package space.migni.gitlab.domain.auth;

import org.springframework.stereotype.Service;
import space.migni.gitlab.domain.auth.oauth.OAuthServiceFactory;
import space.migni.gitlab.domain.auth.oauth.services.OAuthService;

@Service
public class AuthService {

    private final OAuthServiceFactory oauthServiceFactory;

    public AuthService(
        final OAuthServiceFactory oauthServiceFactory
    ) {
        this.oauthServiceFactory = oauthServiceFactory;
    }

    public String oauth(final String oauthType, final String authorizationCode) {
        final OAuthService oauthService = this.oauthServiceFactory.service(oauthType);

        final String accessToken = oauthService.getAccessToken(authorizationCode);
        final String userInfo = oauthService.getUserInfo(accessToken);

        return userInfo;
    }

}
