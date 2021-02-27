package ru.netology.authorizationvalidation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.authorizationvalidation.Users;
import ru.netology.authorizationvalidation.user.User;

import java.util.List;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserParamResolver());
    }

    private class UserParamResolver implements HandlerMethodArgumentResolver {

        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            return methodParameter.getParameterAnnotation(Users.class) != null;
        }

        @Override
        public Object resolveArgument(MethodParameter methodParameter,
                                      ModelAndViewContainer modelAndViewContainer,
                                      NativeWebRequest nativeWebRequest,
                                      WebDataBinderFactory webDataBinderFactory) {

            return new User(nativeWebRequest.getParameter("name"), nativeWebRequest.getParameter("password"));

        }
    }

}
