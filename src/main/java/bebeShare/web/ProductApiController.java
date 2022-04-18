package bebeShare.web;

import bebeShare.config.auth.LoginUser;
import bebeShare.config.auth.dto.SessionUser;
import bebeShare.exception.CustomException;
import bebeShare.exception.ErrorCode;
import bebeShare.service.ProductService;
import bebeShare.web.dto.productDto.*;
import bebeShare.web.dto.userDto.UserRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    // 상품 게시글 등록
    @PostMapping("/product")
    public Long save(@RequestBody final ProductCreateRequestDto params) {
        return productService.save(params);
    }

    // 상품 게시글 목록 조회
    @PostMapping(value = "/products")
    public List<ProductInfoResponseDto> findAllProducts(@RequestBody ProductRequest productRequest, @LoginUser SessionUser sessionUser) {

        if(statusCheck(productRequest)){
            System.out.println("@@:" +sessionUser.toString());
            productRequest.setMemberId(sessionUser.getId());
        }
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

    private boolean statusCheck(ProductRequest productRequest) {
        return productRequest.getProductStatus() != null && productRequest.getProductStatus().equals("C");
    }

}
