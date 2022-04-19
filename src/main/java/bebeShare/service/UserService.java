package bebeShare.service;

import bebeShare.domain.user.User;
import bebeShare.domain.user.UserRepository;
import bebeShare.web.dto.userDto.*;
import bebeShare.web.dto.userDto.req.CommentRequest;
import bebeShare.web.dto.userDto.req.GiveRequest;
import bebeShare.web.dto.userDto.req.LikeRequest;
import bebeShare.web.dto.userDto.req.ShareRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new UserResponseDto(entity);
    }

    public List<ShareInfoResponseDto> shareInfo(ShareRequest shareRequest) {
        return userRepository.shareInfo(shareRequest);
    }

    public List<GiveInfoResponseDto> giveInfo(GiveRequest giveRequest) {
        return userRepository.giveInfo(giveRequest);
    }

    public List<LikeInfoResponseDto> likeInfo(LikeRequest likeRequest) {
        return userRepository.likeInfo(likeRequest);
    }

    public List<MemberCommentResponseDto> comments(CommentRequest commentRequest) {
        return userRepository.comments(commentRequest);
    }
}
