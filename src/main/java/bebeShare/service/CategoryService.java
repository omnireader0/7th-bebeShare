package bebeShare.service;

import bebeShare.domain.code.CodeRepository;
import bebeShare.web.dto.codeDto.CodeResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CodeRepository codeRepository;

    public List<CodeResponseDto> lookup(String code){
        System.out.println("CategoryService.lookup");
        return codeRepository.lookup(code);
    }
}
