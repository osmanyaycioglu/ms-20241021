package org.training.microservice.mscommon.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String         desc;
    private Integer        code;

    @Builder(setterPrefix = "with")
    public ErrorObj(final List<ErrorObj> subErrorsParam,
                    final String descParam,
                    final Integer codeParam) {
        subErrors = subErrorsParam;
        desc      = descParam;
        code      = codeParam;
    }
}
