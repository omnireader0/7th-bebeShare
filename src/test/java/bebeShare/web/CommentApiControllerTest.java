package bebeShare.web;

import bebeShare.domain.comment.Comment;
import bebeShare.domain.comment.CommentRepository;
import bebeShare.domain.product.Product;
import bebeShare.domain.product.ProductRepository;
import bebeShare.domain.user.Role;
import bebeShare.domain.user.User;
import bebeShare.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentApiControllerTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void 댓글작성() throws Exception {
        //given
        String testComment = "This is test content";

        Comment comment = new Comment();
        comment.setId(123L);
        comment.setCommentContent(testComment);

        //when
        Comment newComment = commentRepository.save(comment);

        //then
        Assertions.assertThat(newComment.getCommentContent()).isEqualTo(testComment);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;




    @Test
    public void 댓글수정() throws Exception {
        // given
        Long testId = 123L;
        String testCommentContent = "This is test content";

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

        Comment comment = new Comment();
        comment.setProduct(testProduct);
        comment.setCommentContent(testCommentContent);

        commentRepository.save(comment);


        // when
        String updateCommentContent = "수정된 댓글";

        Map<Object,Object> reqBody = new HashMap<>();
        reqBody.put("productId",testProduct.getId());
        reqBody.put("memberId",testUser.getId());
        reqBody.put("commentContent",updateCommentContent);

        // then
        mockMvc.perform(patch("/api/v1/products/"+testProduct.getId()+"/comments/"+comment.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqBody)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void 댓글삭제() throws Exception {

        //given
        Comment comment = new Comment();
        comment.setId(123L);
        comment.setCommentContent("This is test content");

        commentRepository.save(comment);

        //when
        commentRepository.delete(comment);

        Optional<Comment> deleteComment = commentRepository.findById(123L);

        //then
        Assert.assertFalse(deleteComment.isPresent());
    }
}
