package com.ssenturk.trendyollinkconvertor;

import com.ssenturk.trendyollinkconvertor.controller.WebServiceController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions.*;

@SpringBootTest
class TrendyolLinkConvertorApplicationTests {

    @Autowired
    private WebServiceController webServiceController;

    @Test
    void contextLoads() {
        Assertions.assertThat(webServiceController).isNotNull();
    }


}
