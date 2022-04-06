package bebeShare.domain.user;

import bebeShare.web.dto.userDto.*;

import java.util.List;

public interface UserRepositoryCustom {

    List<ShareInfoResponseDto> shareInfo(UserRequest userRequest);

    List<GiveInfoResponseDto> giveInfo(UserRequest conduserRequestition);

    List<LikeInfoResponseDto> likeInfo(UserRequest userRequest );

    List<MemberCommentResponseDto> comments(UserRequest condition );


}
