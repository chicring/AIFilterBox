package com.hjong.aifilterbox.api.openai.model;

import lombok.Data;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/16
 **/
@Data
public class OpenAiResponseBody {
    String id;
    String object;
    Long created;
    String model;
    List<Choices> choices;
    Usage usage;
    String system_fingerprint;

    @Data
    public static class Usage{
        Integer prompt_tokens;
        Integer completion_tokens;
        Integer total_tokens;
    }
    @Data
    public static class Choices{
        String finish_reason;
        Integer index;
        Message message;
        Message delta;
    }

    @Data
    public static class Message{
        private String role;
        private String content;
        private List<Tool_calls> tool_calls;

        @Data
        public static class Tool_calls{
            private Integer index;
            private String id;
            private String type;
            private Function function;

            @Data
            public static class Function{
                private String name;
                private String arguments;
            }
        }
    }
}