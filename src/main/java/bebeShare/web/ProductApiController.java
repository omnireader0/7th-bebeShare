package bebeShare.web;

import bebeShare.exception.CustomException;
import bebeShare.exception.ErrorCode;
import bebeShare.service.ProductService;
import bebeShare.web.dto.productDto.*;
import bebeShare.web.dto.userDto.UserRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductApiController {

    @Autowired
    private ProductService productService;

    // 상품 게시글 등록
    @PostMapping("/product")
    public Long save(@RequestBody final ProductCreateRequestDto params) {
        System.out.println("params.getProductId() = " + params.getProductId());
        System.out.println("params.getUser = " + params.getUser());
        System.out.println("params.getProductName = " + params.getProductName());
        System.out.println("params.getProductContent = " + params.getProductContent());
        System.out.println("params.getProductImage1 = " + params.getProductImage1());
        System.out.println("params.getProductImage2 = " + params.getProductImage2());
        System.out.println("params.getProductImage3 = " + params.getProductImage3());
        System.out.println("params.getProductCategory = " + params.getProductCategory());
        System.out.println("params.getProductStatus = " + params.getProductStatus());
        System.out.println("params.getDeleteYn = " + params.getDeleteYn());
        return productService.save(params);
    }

    // 상품 게시글 목록 조회
    @PostMapping(value = "/products")
    public List<ProductInfoResponseDto> findAllProducts(@RequestBody ProductRequest productRequest) {
        log.debug("@@@@@@@@@@@@@@");
        log.debug("@@@@@@@@@@@@@@:{}" , productRequest.toString() );
        return productService.findAllProducts(productRequest);
    }


    // 상품 게시글 상세 조회
    @ResponseBody
    @GetMapping("/products/{productId}")
    public ProductResponseDto findById(@PathVariable Long productId) {
        return productService.findById(productId);
    }

    // 상품 게시글 수정
    @PatchMapping("/products")
    public Long update( @RequestBody final ProductCreateRequestDto params) {
        return productService.update(params);
    }

    // 예외 발생
    @GetMapping("/test")
    public String test() {
        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    }

    // 상품 게시글 삭제
    @DeleteMapping("/products")
    public void delete(@RequestBody ProductDeleteDto params){
        productService.delete(params);
    }
}
