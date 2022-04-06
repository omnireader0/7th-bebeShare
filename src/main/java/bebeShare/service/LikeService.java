package bebeShare.service;

import bebeShare.domain.like.Dibs;
import bebeShare.domain.like.DibsRepository;
import bebeShare.domain.product.Product;
import bebeShare.domain.product.ProductRepository;
import bebeShare.domain.user.User;
import bebeShare.exception.CustomException;
import bebeShare.exception.ErrorCode;
import bebeShare.web.dto.ProductLikeDto;
import com.sun.el.parser.AstFalse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final DibsRepository dibsRepository;
    private final ProductRepository productRepository;

    // 상품 게시글 좋아요 생성
    @Transactional
    public boolean addLike(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()
                -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        return true;
    }

    // 상품 게시글 좋아요 해제
    @Transactional
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
}

