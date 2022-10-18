package me.jiniworld.book.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(
    @Value("\${chaeking.version}") val version: String,
    @Value("\${spring.profiles.active}") val profile: String,
    @Value("\${chaeking.url}") val url: String,
) {

    @Bean
    fun openAPI(): OpenAPI =
        OpenAPI()
//            .components(
//                Components()
//                .addSecuritySchemes("access_token",
//                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
//                        .`in`(SecurityScheme.In.HEADER).name("Authorization")))
//            .addSecurityItem(SecurityRequirement().addList("access_token"))
            .info(
                Info().title("Chaeking API - $profile")
                .description("책킹 API(with Kotlin)")
                .contact(Contact().name("jini").url("https://blog.jiniworld.me/").email("jini@chaeking.com"))
                .license(License().name("GNU General Public License v3.0").url("https://github.com/jiniya22/chaeking-kotlin/blob/master/LICENSE")))
            .servers(
                listOf(
                    Server().url(url).description(
                        "chaeking-api ($profile)"
                    )
                )
            )

}