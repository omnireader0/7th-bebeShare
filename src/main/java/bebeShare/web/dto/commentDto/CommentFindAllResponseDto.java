package bebeShare.web.dto.commentDto;

import bebeShare.domain.comment.Comment;
import bebeShare.domain.user.User;

import java.time.LocalDateTime;

public class CommentFindAllResponseDto {
    private Long commentId;
    private Long memberId;
    private String commentContent;
    private String deleteYn;
    private LocalDateTime insertDt;
    private String commentStatus;

    public CommentFindAllResponseDto(Comment entity) {
        this.commentId = entity.getId();
        this.memberId = entity.getUser().getId();
        this.commentContent = entity.getCommentContent();
        this.deleteYn = entity.getDeleteYn();
        this.insertDt = entity.getCreatedDate();
        this.commentStatus = entity.getCommentStatus();
    }
}
