package bebeShare.web;

import bebeShare.domain.posts.Posts;
import bebeShare.domain.posts.PostsRepository;
import bebeShare.web.dto.PostSaveRequestsDto;
import bebeShare.web.dto.PostUpdateRequestsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }



    @WithMockUser(roles = "USER")
    @Test
    public void 등록() throws Exception {

        String title = "title";
        String content = "content";

        PostSaveRequestsDto requestsDto = PostSaveRequestsDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";


//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestsDto, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestsDto)))
                .andExpect(status().isOk());

        List<Posts> all = postsRepository.findAll();
        AssertionsForClassTypes.assertThat(all.get(0).getTitle()).isEqualTo(title);
        AssertionsForClassTypes.assertThat(all.get(0).getContent()).isEqualTo(content);
    }


    @Test
    @WithMockUser(roles = "USER")
    public void Posts_수정된다() throws Exception {

        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";


        PostUpdateRequestsDto requestDto = PostUpdateRequestsDto.builder().title(expectedTitle)
                .content(expectedContent).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

//        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
//
//        //when
//
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        //then
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //when
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());


        List<Posts> all = postsRepository.findAll();
        AssertionsForClassTypes.assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        AssertionsForClassTypes.assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}