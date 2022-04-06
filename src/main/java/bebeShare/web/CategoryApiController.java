package bebeShare.web;


import bebeShare.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryApiController {
    private final CategoryService categoryService;

    // 카테고리 조회
    @GetMapping("/categories")
    public void lookupCategory(@RequestParam String code){
        categoryService.lookup(code);
    }
}