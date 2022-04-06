package bebeShare.web.dto.productDto;

import bebeShare.domain.product.Product;
import bebeShare.domain.user.User;
import lombok.Data;
import lombok.Getter;

@Getter
public class ProductCreateRequestDto {
    private Long productId;
    private User user;
    private String productName;
    private String productContent;
    private String productImage1;
    private String productImage2;
    private String productImage3;
    private String productCategory;
    private String productStatus;
    private String deleteYn;

    public ProductCreateRequestDto() {
        this.productId = productId;
        this.user = user;
        this.productName = productName;
        this.productContent = productContent;
        this.productImage1 = productImage1;
        this.productImage2 = productImage2;
        this.productImage3 = productImage3;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
        this.deleteYn = deleteYn;
    }


    public Product toEntity() {
        return Product.builder()
                .user(user)
                .productName(productName)
                .productCategory(productCategory)
                .productContent(productContent)
                .productImage1(productImage1)
                .productImage2(productImage2)
                .productImage3(productImage3)
                .productStatus(productStatus)
                .deleteYn(deleteYn)
                .build();
    }
}
