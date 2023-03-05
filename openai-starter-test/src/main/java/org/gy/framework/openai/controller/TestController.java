package org.gy.framework.openai.controller;

import com.theokanning.openai.completion.CompletionChoice;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.gy.framework.core.dto.Response;
import org.gy.framework.core.exception.CommonErrorCode;
import org.gy.framework.openai.utils.OpenAiUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 *
 * @author gy
 * @version 1.0.0
 * @date 2022/1/18 16:07
 */
@Slf4j
@RestController
@RequestMapping("/api/openai")
public class TestController {

    public static final String REGEX = ",";

    @RequestMapping("/{openaiType}")
    public Object getAiResult(@PathVariable String openaiType, String text) {
        if (!StringUtils.hasText(text)) {
            return Response.asError(CommonErrorCode.PARAM_ERROR);
        }
        List<CompletionChoice> aiResult = OpenAiUtils.getAiResult(openaiType, text.split(REGEX));
        return Response.asSuccess(aiResult);
    }

}
