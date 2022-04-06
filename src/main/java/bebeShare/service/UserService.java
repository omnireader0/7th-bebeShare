package bebeShare.service;

import bebeShare.domain.user.User;
import bebeShare.domain.user.UserRepository;
import bebeShare.web.dto.userDto.*;
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

    public List<ShareInfoResponseDto> shareInfo(UserRequest userRequest) {
        return userRepository.shareInfo(userRequest);
    }

    public List<GiveInfoResponseDto> giveInfo(UserRequest userRequest) {
        return userRepository.giveInfo(userRequest);
    }

    public List<LikeInfoResponseDto> likeInfo(UserRequest userRequest) {
        return userRepository.likeInfo(userRequest);
    }

    public List<MemberCommentResponseDto> comments(UserRequest userRequest) {
        return userRepository.comments(userRequest);
    }
}
