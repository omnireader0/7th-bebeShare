package bebeShare.web.dto.commentDto;


import bebeShare.domain.comment.Comment;
import bebeShare.domain.product.Product;
import bebeShare.domain.user.User;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequestsDto {
    private User user;
    private Product product;
    private Long productId;
    private Long memberId;
    private String commentContent;
    private String deleteYn;
    private String commentStatus;

    @Builder
    public Comment toEntity(){
        return Comment.builder()
                .user(user)
                .product(product)
                .commentContent(commentContent)
                .deleteYn("N")
                .commentStatus("100")
                .build();
    }

}
