package bebeShare.domain.product;

import bebeShare.web.dto.productDto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {
    Page<ProductInfoResponseDto> findAllProducts(ProductRequest productRequest, Pageable pageable);
}
