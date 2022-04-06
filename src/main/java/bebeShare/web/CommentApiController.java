package bebeShare.web;

import bebeShare.config.auth.LoginUser;
import bebeShare.config.auth.dto.SessionUser;
import bebeShare.service.CommentsService;
import bebeShare.web.dto.commentDto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class CommentApiController {

    private final CommentsService commentsService;

    // 상품 댓글 등록
    @PostMapping("/{productId}/comments")
    public CommentResponseDto save(@PathVariable Long productId, @RequestBody CommentSaveRequestsDto requestsDto,
                                   @LoginUser SessionUser sessionUser) {
        requestsDto.setProductId(productId);
        requestsDto.setMemberId(sessionUser.getId());
        return commentsService.save(requestsDto);
    }

    //상품 댓글 조회
    @GetMapping("/products")
    public List<CommentFindAllResponseDto> findAll(){
        return commentsService.findAll();
    }

    // 상품 댓글 수정
    @PatchMapping("/{productId}/comments/{commentId}")
    public CommentUpdateResponseDto update(@PathVariable Long productId, @PathVariable Long commentId, @RequestBody CommentUpdateRequestsDto updateRequestsDto) {
        return commentsService.update(productId, commentId, updateRequestsDto);
    }

    // 상품 댓글 삭제
    @DeleteMapping("/{productId}/comments/{commentId}")
    public CommentDeleteResponseDto delete(@PathVariable Long productId, @PathVariable Long commentId,
                                           @RequestBody CommentDeleteRequestDto params) {

        return commentsService.delete(productId, commentId,params);
    }
}
