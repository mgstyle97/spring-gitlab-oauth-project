package space.migni.gitlab.domain.auth.oauth.services;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import space.migni.gitlab.domain.auth.oauth.properties.GitlabOAuthProperties;

import java.util.Map;

@Component("gitlab")
public non-sealed class GitlabOAuthService extends OAuthService {

    private final GitlabOAuthProperties oauthProperties;

    public GitlabOAuthService(
        final RestTemplate client,
        final GitlabOAuthProperties oauthProperties
    ) {
        super(client);
        this.oauthProperties = oauthProperties;
    }

    @Override
    public String getAccessToken(String authorizationCode) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final MultiValueMap<String, String> requestBody = tokenRequestBody(authorizationCode);

        final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(
            requestBody,
            headers
        );

        final ResponseEntity<Map> response = this.client.postForEntity(
            oauthProperties.tokenUri(),
            request,
            Map.class
        );

        return (String) response.getBody().get("access_token");
    }

    @Override
    public String getUserInfo(String accessToken) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        final HttpEntity<?> request = new HttpEntity<>(headers);

        final ResponseEntity<Map> response = this.client.exchange(
            oauthProperties.userInfoUri(),
            HttpMethod.GET,
            request,
            Map.class
        );

        return response.getBody().toString();
    }

    private MultiValueMap<String, String> tokenRequestBody(final String authorizationCode) {
        final MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", oauthProperties.clientId());
        requestBody.add("client_secret", oauthProperties.clientSecret());
        requestBody.add("code", authorizationCode);
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("redirect_uri", oauthProperties.redirectUri());

        return requestBody;
    }
}
