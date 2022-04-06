package bebeShare.web.dto.codeDto;

import bebeShare.domain.code.Code;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CodeResponseDto {
    private Long codeId;
    private String code;
    private String codeName;
    private String useYn;
    private LocalDateTime insertDt;

    public CodeResponseDto(Code code) {
        this.codeId = code.getId();
        this.code = code.getCode();
        this.codeName = code.getCodeName();
        this.useYn = code.getUseYn();
        this.insertDt = code.getCreatedDate();
    }
}
