package bebeShare.domain.common;

import lombok.Getter;

@Getter
public enum ResponeMsgEnum {


    RESPONES_SUCCESS("000"),
    RESPONES_FAIL("999");

    private String value;

    ResponeMsgEnum(String value) {
        this.value = value;
    }
}
