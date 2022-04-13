package bebeShare.web.dto.productDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ProductRequest {
    private String productName;
    private String categoryCode;
}
