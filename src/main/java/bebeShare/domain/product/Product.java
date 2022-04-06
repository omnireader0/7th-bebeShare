package bebeShare.domain.product;


import bebeShare.BaseEntity;
import bebeShare.domain.user.User;
import bebeShare.web.dto.productDto.ProductCreateRequestDto;
import bebeShare.web.dto.productDto.ProductDeleteDto;
import lombok.*;

import javax.persistence.*;

@ToString(of = {"id", "productName", "productContent", "productImage1", "productImage2", "productImage3", "productStatus", "productCategory", "deleteYn"})
@Getter
@NoArgsConstructor
@Table(name = "Product")
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private User user;

    private String productName;

  
    private String productContent;

    private String productImage1;

    private String productImage2;

    private String productImage3;

    private String productStatus;

    private String productCategory;

    private String deleteYn;

    private Long shareId;


    @Builder
    public Product(User user, String productName, String productContent,String productImage1, String productImage2, String productImage3, String productStatus, String productCategory ,String deleteYn
    ,Long shareId) {

        this.user = user;
        this.productName = productName;
        this.productContent = productContent;
        this.productImage2 = productImage2;
        this.productImage3 = productImage3;
        this.productImage1 = productImage1;
        this.productStatus = productStatus;
        this.productCategory = productCategory;
        this.deleteYn = deleteYn;
        this.shareId = shareId;

    }

    public void addUser(User user){
        this.user = user;
        user.getProducts().add(this);
    }

    public void update(ProductCreateRequestDto pro) {
        this.id = pro.getProductId();
        this.user = pro.getUser();
        this.productName = pro.getProductName();
        this.productContent = pro.getProductContent();
        this.productImage2 = pro.getProductImage2();
        this.productImage3 = pro.getProductImage3();
        this.productImage1 = pro.getProductImage1();
        this.productStatus = pro.getProductStatus();
        this.productCategory = pro.getProductCategory();
    }

    public void delete(ProductDeleteDto pro){
        this.deleteYn = pro.getDeleteYn();
    }

}


