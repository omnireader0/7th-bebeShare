package bebeShare.domain.code;

import bebeShare.web.dto.codeDto.CodeResponseDto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class CodeRepositoryTest {
    @Autowired
    CodeRepository codeRepository;

    @Test
    public void find_code() {
        //given
        String testCode = "100";

        codeRepository.save(Code.builder()
                .id(123L)
                .code(testCode)
                .codeName("전자기기")
                .useYn("Y")
                .build()
        );


        //when
        List<CodeResponseDto> code = codeRepository.lookup(testCode);

        //then
        assertThat(code.get(0).getCode()).isEqualTo("100");
    }

}
