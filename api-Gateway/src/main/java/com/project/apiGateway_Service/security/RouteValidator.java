package com.project.apiGateway_Service.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class RouteValidator {

    private static final List<Pattern> OPEN_PATTERNS = List.of(
            Pattern.compile("^/user/register$"),
            Pattern.compile("^/user/login$"),
            Pattern.compile("^/user/\\d+/exists$")
    );

    public final Predicate<ServerHttpRequest> isSecured =
            request -> OPEN_PATTERNS.stream()
                    .noneMatch(pattern -> pattern.matcher(request.getURI().getPath()).matches());
}
