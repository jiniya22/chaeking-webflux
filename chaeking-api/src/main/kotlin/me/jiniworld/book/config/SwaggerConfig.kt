package me.jiniworld.book.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig(
    @Value("\${chaeking.version}") val version: String,
) {

    @Bean
    fun docket(): Docket =
        Docket(DocumentationType.OAS_30)
            .groupName("jiniworld")
            .apiInfo(
                ApiInfoBuilder()
                    .title("Chaeking API")
                    .description("책킹 API(with Kotlin)")
                    .contact(Contact("jini", "https://blog.jiniworld.me", "jini@jiniworld.me"))
                    .license("GNU General Public License v3.0")
                    .licenseUrl("https://github.com/jiniya22/chaeking-kotlin/blob/master/LICENSE")
                    .version(version)
                    .build()
            )
            .select()
            .paths(PathSelectors.regex("^\\/v1(\\/?)[a-zA-Z/{}\\-_]*$"))
            .build()

}