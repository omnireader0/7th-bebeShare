package bebeShare.web;

import bebeShare.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;


    @WithMockUser(roles = "USER")
    @Test
    void 조회() throws Exception {

        mvc.perform(
                        get("/api/v1/members/1")
                )
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "USER")
    public void Posts_수정된다() throws Exception {


        String url = "http://localhost:" + port + "/api/v1/members/" + "1";

//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //when
        mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        )
                .andExpect(status().isOk());
//
//
//        List<Posts> all = postsRepository.findAll();
//        AssertionsForClassTypes.assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        AssertionsForClassTypes.assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }

}