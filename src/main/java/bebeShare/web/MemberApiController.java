package bebeShare.web;

import bebeShare.service.UserService;
import bebeShare.web.dto.userDto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/members")
@RestController
public class MemberApiController {

    private final UserService userService;


    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/shareInfo")
    public List<ShareInfoResponseDto> shareInfo(@RequestBody UserRequest userRequest) {
        return userService.shareInfo(userRequest);
    }

    @GetMapping("/giveInfo")
    public List<GiveInfoResponseDto> giveInfo(@RequestBody UserRequest userRequest) {
        return userService.giveInfo(userRequest);
    }


    @GetMapping("/likeInfo")
    public List<LikeInfoResponseDto> likeInfo(@RequestBody UserRequest userRequest) {
        PageRequest pageRequest = PageRequest.of(userRequest.getPage(), userRequest.getSize());
        return userService.likeInfo(userRequest);
    }


    @GetMapping("/comments")
    public List<MemberCommentResponseDto> comments(@RequestBody UserRequest userRequest) {
        PageRequest pageRequest = PageRequest.of(userRequest.getPage(), userRequest.getSize());
        return userService.comments(userRequest);
    }

}
