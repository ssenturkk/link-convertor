package com.ssenturk.trendyollinkconvertor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssenturk.trendyollinkconvertor.object.LinkConverter;
import com.ssenturk.trendyollinkconvertor.repository.LinkConverterRepository;
import com.ssenturk.trendyollinkconvertor.service.LinkConverterService;
import com.ssenturk.trendyollinkconvertor.service.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class WebServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private LinkConverterRepository converterRepository;

    @Autowired
    private RedisService cacheService;

    private LinkConverterService linkConverterService = new LinkConverterService(converterRepository, cacheService);

    // TODO Write Tests for Controller

    @BeforeEach
    private void setup(){
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void testConvertFromWeburl_whenWebUrlTodeeplinkPostRequest_shouldSaveConvertedLinkAndReturnConvertedLink() throws Exception {
        LinkConverter request = new LinkConverter("https://www.trendyol.com/casio/saat-p-123456");
        this.mockMvc.perform(post("/api/converter/webUrl_to_deeplink")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void testConvertFromDeeplink_whenWebUrlTodeeplinkPostRequest_shouldSaveConvertedLinkAndReturnConvertedLink() throws Exception {
        LinkConverter request = new LinkConverter("ty://?Page=Product&ContentId=1925865&MerchantId=105064");
        this.mockMvc.perform(post("/api/converter/deeplink_to_webUrl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void testConvertFromWeburl_whenPostedLinkWithRequest_ifPostedLinkIsEmpty_returnException() throws Exception {
        LinkConverter request = new LinkConverter("");
        this.mockMvc.perform(post("/api/converter/webUrl_to_deeplink")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testConvertFromDeeplink_whenPostedLinkWithRequest_ifPostedLinkIsEmpty_returnException() throws Exception {
        LinkConverter request = new LinkConverter("");
        this.mockMvc.perform(post("/api/converter/deeplink_to_webUrl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}