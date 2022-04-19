package bebeShare.web;

import bebeShare.config.auth.LoginUser;
import bebeShare.config.auth.dto.SessionUser;
import bebeShare.service.ProductService;
import bebeShare.service.UserService;
import bebeShare.web.dto.userDto.*;
import bebeShare.web.dto.userDto.req.CommentRequest;
import bebeShare.web.dto.userDto.req.GiveRequest;
import bebeShare.web.dto.userDto.req.LikeRequest;
import bebeShare.web.dto.userDto.req.ShareRequest;
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
    private final ProductService productService;



    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/shareInfo")
    public List<ShareInfoResponseDto> shareInfo(@RequestBody ShareRequest shareRequest , @LoginUser SessionUser user) {
        System.out.println("@@@:" + shareRequest.toString());
        shareRequest.setMemberId(user.getId());
        return userService.shareInfo(shareRequest);
    }

    @PostMapping("/giveInfo")
    public List<GiveInfoResponseDto> giveInfo(@RequestBody GiveRequest giveRequest , @LoginUser SessionUser user) {
        giveRequest.setShareId(user.getId());
        return userService.giveInfo(giveRequest);
    }


    @PostMapping("/likeInfo")
    public List<LikeInfoResponseDto> likeInfo(@RequestBody LikeRequest likeRequest , @LoginUser SessionUser user) {
        likeRequest.setMemberId(user.getId());
        return userService.likeInfo(likeRequest);
    }


    @PostMapping("/comments")
    public List<MemberCommentResponseDto> comments(@RequestBody CommentRequest commentRequest , @LoginUser SessionUser user) {
        commentRequest.setMemberId(user.getId());
//        PageRequest pageRequest = PageRequest.of(commentRequest.getPage(), commentRequest.getSize());
        return userService.comments(commentRequest);
    }

}
