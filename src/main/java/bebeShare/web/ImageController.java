package bebeShare.web;

import bebeShare.domain.user.User;
import bebeShare.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
public class ImageController {
    @PostMapping("/upload")
    public @ResponseBody String imageUpload(@RequestParam("image") MultipartFile image, HttpSession session) throws IOException {
        log.info("image={}",image);

        if(image.isEmpty()) return null;
        return ImageService.createImage(image.getBytes(), (User)session.getAttribute("user"));
    }
}
