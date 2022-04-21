package bebeShare.web.dto.userDto;

import bebeShare.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long memberId;
    private String name;
    private Integer shareCnt;
    private Integer giveCnt;
    private Integer shareChanceCnt;


    public UserResponseDto(User user) {
        this.memberId = user.getId();
        this.name = user.getName();
        this.shareCnt = user.getShareCnt();
        this.giveCnt = user.getGiveCnt();
        this.shareChanceCnt = user.getShareChanceCnt();
    }

}
