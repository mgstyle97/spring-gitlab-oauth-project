package space.migni.gitlab.domain.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
        final AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/{oauth_type}")
    public ResponseEntity<?> oauth(
            @PathVariable("oauth_type") final String oauthType,
            @RequestParam("code") final String authorizationCode
    ) {
        final String result = this.authService.oauth(
            oauthType,
            authorizationCode
        );

        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<?> handleRestClientException(
        final RestClientException exception
    ) {
        return ResponseEntity.badRequest()
            .body(exception.getMessage());
    }

}
