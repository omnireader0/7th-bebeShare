package bebeShare.web.dto.commentDto;

import bebeShare.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDeleteRequestDto {
    private Long commentId;
    private String deleteYn;

    public Comment toEntity(){
        return Comment.builder()
                .deleteYn("Y")
                .build();
    }
}
