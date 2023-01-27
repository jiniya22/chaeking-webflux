package me.jiniworld.book.web

import com.ninjasquad.springmockk.MockkBean
import me.jiniworld.book.service.BestSellerService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print


@ExtendWith(RestDocumentationExtension::class)
@ExtendWith(SpringExtension::class)
//@ContextConfiguration(classes = [R2bcConfiguration::class])
@WebMvcTest(controllers = [BestSellerController::class])
//@AutoConfigureMockMvc
internal class BestSellerControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var bestSellerService: BestSellerService

    @BeforeEach
    fun setUp(restDocumentation: RestDocumentationContextProvider?) {
        mockMvc = MockMvcBuilders
            .standaloneSetup(BestSellerController(bestSellerService))
            .apply<StandaloneMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
            .build()
    }

    @Test
    @Throws(Exception::class)
    fun test() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/best-sellers/test")
                .header("Content-Type", "application/json")
        )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}