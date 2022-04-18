package bebeShare.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //단위 테스트 끝날때 작동 데이터 침범을 막기위해
    public void cleanup() {
        postsRepository.deleteAll();
    }



    @Test
    public void name() {

        String title = "test";
        String content = "test";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("kkj")
                .build()
        );

        List<Posts> posts = postsRepository.findAll();

        assertThat(posts.get(0).getTitle()).isEqualTo("test");

    }
}