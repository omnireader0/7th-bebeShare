package bebeShare.domain.user;

import bebeShare.web.dto.userDto.*;
import bebeShare.web.dto.userDto.req.CommentRequest;
import bebeShare.web.dto.userDto.req.GiveRequest;
import bebeShare.web.dto.userDto.req.LikeRequest;
import bebeShare.web.dto.userDto.req.ShareRequest;

import java.util.List;

public interface UserRepositoryCustom {

    List<ShareInfoResponseDto> shareInfo(ShareRequest shareRequest);

    List<GiveInfoResponseDto> giveInfo(GiveRequest giveRequest);

    List<LikeInfoResponseDto> likeInfo(LikeRequest likeRequest );

    List<MemberCommentResponseDto> comments(CommentRequest commentRequest );


}
