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

//  마이페이지 내역 파라미터값 .
    private Long memberId;
    private String productStatus;
    private Long shareId;
}
