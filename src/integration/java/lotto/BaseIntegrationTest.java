package lotto;

import com.lotto.domain.LottoSpringBootApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.annotation.ApplicationScope;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = LottoSpringBootApplication.class)
@ActiveProfiles("integration")
@AutoConfigureMockMvc
@Testcontainers
public class BaseIntegrationTest {

    @@Autowired
    public MockMvc mockMvc (){

    }

}
