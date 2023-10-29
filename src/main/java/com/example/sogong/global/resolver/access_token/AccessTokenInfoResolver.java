package com.example.sogong.global.resolver.access_token;

import com.example.sogong.global.auth.jwt.JwtTokenProvider;
import com.example.sogong.global.common.response.code.AuthErrorCode;
import com.example.sogong.global.common.response.exception.AuthErrorException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Component
public class AccessTokenInfoResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AccessTokenInfo.class) != null
                && parameter.getParameterType().equals(AccessToken.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        final var httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        final String accessToken = JwtTokenProvider.parseJwtFromRequest(httpServletRequest);

        if (!StringUtils.hasText(accessToken)) {
            log.error("헤더에 Access 토큰이 없음");
            throw new AuthErrorException(AuthErrorCode.EMPTY_ACCESS_TOKEN, "빈 토큰입니다");
        }

        final Claims claims = jwtTokenProvider.verifyAndGetClaims(accessToken);
        return new AccessToken(accessToken, Long.parseLong(claims.getSubject()), claims.getExpiration());
    }

}
