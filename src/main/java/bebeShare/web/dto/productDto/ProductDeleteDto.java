package bebeShare.web.dto.productDto;

import bebeShare.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDeleteDto {
    private Long productId;
    private String deleteYn;

    public Product toEntity(){
        return Product.builder()
                .deleteYn("Y")
                .build();
    }
}
