package org.gy.framework.openai;

import org.gy.framework.openai.utils.OpenAiUtils;
import com.theokanning.openai.completion.CompletionChoice;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OpenAiTest {

    /**
     * 测试问答
     */
    @Test
    public void testQA(){
        List<CompletionChoice> questionAnswer = OpenAiUtils.getQuestionAnswer("重庆今天的天气怎么样？");
        for (CompletionChoice completionChoice : questionAnswer) {
            System.out.println(completionChoice.getText());
        }
    }

    /**
     * 测试面试题生成
     */
    @Test
    public void testInterview(){
        List<CompletionChoice> results = OpenAiUtils.getInterviewQuestion("redis");
        for (CompletionChoice completionChoice : results) {
            System.out.println(completionChoice.getText());
        }
    }

}
