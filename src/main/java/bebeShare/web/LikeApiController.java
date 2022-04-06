package bebeShare.web;

import bebeShare.domain.user.User;
import bebeShare.service.LikeService;
import bebeShare.web.dto.productDto.ProductCreateRequestDto;
import bebeShare.web.dto.likeDto.ProductLikeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LikeApiController {

    private final LikeService likeService;

    // 상품 게시글 좋아요 생성
    @PostMapping("/products/{productId}/like")
    public boolean save(@PathVariable Long productId) {
        return likeService.addLike(productId);
    }

    // 상품 게시글 좋아요 해제
    @DeleteMapping("/products/{productId}/like")
    public void delete(@PathVariable Long productId){
        likeService.delete(productId);
    }
}
