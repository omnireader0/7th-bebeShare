package bebeShare.web.dto.commentDto;

import bebeShare.domain.comment.Comment;
import bebeShare.domain.product.Product;
import bebeShare.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveRequestsDto {
    private Long productId;
    private Long memberId;
    private String commentContent;

    
    public Comment toEntity(User user, Product product) {
        return Comment.builder()
                .user(user)
                .product(product)
                .commentContent(commentContent)
                .deleteYn("N")
                .commentStatus("100")
                .build();
    }
}
