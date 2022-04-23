package bebeShare.domain.comment;

import bebeShare.web.dto.commentDto.CommentInfoResponseDto;
import bebeShare.web.dto.commentDto.CommentsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {

    Page<CommentInfoResponseDto> findByProductId(CommentsRequest commentsRequest, Pageable pageable);
}
