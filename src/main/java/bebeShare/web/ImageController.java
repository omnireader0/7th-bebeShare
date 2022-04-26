package bebeShare.web;

import bebeShare.domain.user.User;
import bebeShare.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public @ResponseBody String imageUpload(@RequestParam("image") MultipartFile image, HttpSession session) throws IOException {
        log.info("image={}",image);
        log.info("imageService={}",imageService);
        if(image.isEmpty()) return null;
        return imageService.createImage(image.getBytes(), (User)session.getAttribute("user"));
    }


    @GetMapping("/products/images/{userId}/{fileName}")
    public byte[] imageDownload(@PathVariable("userId") String userid, @PathVariable("fileName") String filename) {
        System.out.println("ImageController.imageDownload");
        log.info("userId={}",userid);
        return imageService.getImage(userid,filename);
    }
}
