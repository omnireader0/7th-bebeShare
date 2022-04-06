package bebeShare.service;

import bebeShare.domain.product.Product;
import bebeShare.domain.product.ProductRepository;
import bebeShare.exception.CustomException;
import bebeShare.exception.ErrorCode;
import bebeShare.web.dto.productDto.ProductCreateRequestDto;
import bebeShare.web.dto.productDto.ProductDeleteDto;
import bebeShare.web.dto.productDto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 롬북 초기화 되지 않은 필드 생성 -> 의존성 주입 가능
public class ProductService {

    private final ProductRepository productRepository;


    //상품 게시글 생성
    @Transactional
    public Long save(final ProductCreateRequestDto params) {

        Product entity = productRepository.save(params.toEntity());
        return entity.getId();
    }

    // 게시글 목록 조회
    public List<ProductResponseDto> findAll() {

        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Product> list = productRepository.findAll(sort);
        return list.stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

    // 상품 게시글 상세 조회
    public ProductResponseDto findById(Long productId) {
        Product entity = productRepository.findById(productId).orElseThrow(()
                -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        return new ProductResponseDto(entity);
    }

    // 상품 게시글 수정
    @Transactional
    public Long update(final ProductCreateRequestDto params) {

        Product entity = productRepository.findById(params.getProductId()).orElseThrow(()
                -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        entity.update(params);
        return params.getProductId();
    }


    // 상품 게시글 삭제
    @Transactional
    public void delete(ProductDeleteDto params) {
        Product entity = productRepository.findById(params.getProductId()).orElseThrow(()
                -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete(params);
    }
}
