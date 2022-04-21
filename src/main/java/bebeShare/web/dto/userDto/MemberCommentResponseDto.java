package bebeShare.web.dto.userDto;

import bebeShare.domain.comment.Comment;
import bebeShare.domain.product.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class MemberCommentResponseDto {

    private Long commentId;
    private String productName;
    private String commentContent;
    private String productImage;
    private LocalDateTime insertDt;



    @QueryProjection //의존적이게 되 단점
    public MemberCommentResponseDto(Long commentId, String productName, String commentContent,String productImage, LocalDateTime insertDt) {
        this.commentId = commentId;
        this.productName   = productName;
        this.commentContent = commentContent;
        this.productImage = productImage;
        this.insertDt = insertDt;
    }

}
