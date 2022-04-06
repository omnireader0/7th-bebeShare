package bebeShare.web.dto.commentDto;

import bebeShare.domain.common.entity.ResEntity;
import lombok.Getter;

import static bebeShare.domain.common.CommonContants.COMMENT_SAVE_OK;
import static bebeShare.domain.common.ResponeMsgEnum.RESPONES_SUCCESS;

@Getter
public class CommentResponseDto extends ResEntity {

    private String resultCode;
    private String resultMsg;
    private Long productId;

    public CommentResponseDto(Long productId) {
        this.setResultCode(RESPONES_SUCCESS.getValue());
        this.setResultMsg(COMMENT_SAVE_OK);
        this.productId = productId;
    }
}
