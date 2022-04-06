package bebeShare.web.dto.likeDto;

import bebeShare.domain.product.Product;
import bebeShare.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductLikeDto {

    private User user;
    private Long product;

    public ProductLikeDto(User user, Long product) {
        this.user = user;
        this.product = product;
    }
}
