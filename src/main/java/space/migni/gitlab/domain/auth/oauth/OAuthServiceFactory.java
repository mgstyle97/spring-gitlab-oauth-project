package space.migni.gitlab.domain.auth.oauth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import space.migni.gitlab.domain.auth.oauth.services.OAuthService;

@Component
public class OAuthServiceFactory {

    private final OAuthService gitlabOAuthService;

    public OAuthServiceFactory(
        @Qualifier("gitlab") final OAuthService gitlabOAuthService
    ) {
        this.gitlabOAuthService = gitlabOAuthService;
    }

    public OAuthService service(final String oauthType) {
        return switch (oauthType) {
            case "gitlab" -> this.gitlabOAuthService;
            default -> null;
        };
    }

}
