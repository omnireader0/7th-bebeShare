package bebeShare.domain.product;

import bebeShare.web.dto.productDto.*;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductInfoResponseDto> findAllProducts(ProductRequest productRequest);
}
