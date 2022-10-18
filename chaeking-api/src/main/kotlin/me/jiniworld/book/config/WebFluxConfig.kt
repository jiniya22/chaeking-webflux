package me.jiniworld.book.config

import io.swagger.annotations.ApiModelProperty
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Configuration
class WebFluxConfig(
    private val authUserHandlerArgumentResolver: AuthUserHandlerArgumentResolver,
): WebFluxConfigurer {

//    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
//        registry.addResourceHandler("/resources/**")
//            .addResourceLocations("classpath:/static/")
//            .setCacheControl(CacheControl.maxAge(3, TimeUnit.DAYS));
//    }

    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.apply {
            addCustomResolver(authUserHandlerArgumentResolver)
        }
    }
}


@Component
class AuthUserHandlerArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean =
        AuthUser::class.java.isAssignableFrom(parameter.parameterType)

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<Any> {
        return AuthUser(
            userId = 1,
            username= "지니",
        ).toMono()
    }
}

data class AuthUser(
    @ApiModelProperty(hidden = true) val userId: Long,
    @ApiModelProperty(hidden = true) val username: String,
//    @ApiModelProperty(hidden = true) val profileUrl: String? = null,
)