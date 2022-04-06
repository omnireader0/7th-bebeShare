package bebeShare.web.dto.userDto;

import bebeShare.domain.common.entity.PageEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserRequest extends PageEntity {

    private Long memberId;
    private String productStatus;

}
