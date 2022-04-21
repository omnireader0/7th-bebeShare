package bebeShare.web;

import bebeShare.domain.like.DibsRepository;
import bebeShare.service.ProductService;
import bebeShare.web.dto.productDto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;
    private final DibsRepository repository;

    // 상품 게시글 등록
    @PostMapping("/product")
    public Long save(@RequestBody final ProductCreateRequestDto params) {
        return productService.save(params);
    }

    // 상품 게시글 목록 조회
    @PostMapping(value = "/products")
    public Page<ProductInfoResponseDto> findAllProducts(@RequestBody ProductRequest productRequest) {
        Pageable pageable = PageRequest.of(productRequest.getPage(), productRequest.getSize());
        return productService.findAllProducts(productRequest, pageable);
    }


    // 상품 게시글 상세 조회
    @ResponseBody
    @GetMapping("/products/{productId}")
    public ProductResponseDto findById(@PathVariable Long productId) {
        return productService.findById(productId);
    }

    // 상품 게시글 수정
    @PatchMapping("/products")
    public Long update(@RequestBody final ProductCreateRequestDto params) {
        return productService.update(params);
    }

    // 상품 게시글 삭제
    @DeleteMapping("/products")
    public void delete(@RequestBody ProductDeleteDto params) {
        productService.delete(params);
    }

}
