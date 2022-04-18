package bebeShare.web.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class HelloResponseDtoTestTest {

    @Test
    void name() {
        HelloResponseDto member = new HelloResponseDto("test", 1000);

        assertThat(member.getName()).isEqualTo("test");

    }
}