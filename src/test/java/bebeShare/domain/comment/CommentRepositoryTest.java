package bebeShare.domain.comment;

import bebeShare.domain.product.Product;
import bebeShare.domain.product.ProductRepository;
import bebeShare.domain.user.Role;
import bebeShare.domain.user.User;
import bebeShare.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void 댓글조회(){
        //given
        String testCommentContent = "테스트 댓글";

        User testUser = userRepository.save(User.builder()
                .name("test1")
                .picture("/fake/path")
                .email("test1@naver.com")
                .role(Role.USER)
                .build()
        );

        Product testProduct = productRepository.save(Product.builder()
                .user(testUser)
                .productName("product0")
                .productCategory("100")
                .productContent("content0")
                .productImage1("1")
                .productImage2("2")
                .productImage3("3")
                .productStatus("S")
                .deleteYn("N")
                .build()
        );

        //when
        Comment testComment = commentRepository.save( Comment.builder()
                .user(testUser)
                .product(testProduct)
                .commentContent(testCommentContent)
                .deleteYn("N")
                .commentStatus("100")
                .build()
        );

        Optional<Comment> comments = commentRepository.findById(testComment.getId());

        //then
        Assertions.assertThat(comments.get().getCommentContent()).isEqualTo(testCommentContent);

    }
}
