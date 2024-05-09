package com.hjong.aifilterbox.api.openai.model;

import lombok.Data;

import java.util.List;


/**
 * @author HJong
 * @version 1.0
 * @date 2024/3/16
 **/
@Data
public class OpenAiRequestBody {
    private List<Message> messages;

    @Data
    public static class Message{
        private String role;
        private String content;
        private String name;
        private Object tool_calls;
        private String tool_call_id;
    }

    private boolean stream;
    private String model;
    private Float temperature;
    private Integer presence_penalty;
    private Integer frequency_penalty;
    private Float top_p;
    private Integer top_k;
    private String stop;
    private Integer max_tokens;

    List<Tools> tools;

    private String tool_choice;

    @Data
    public static class Tools{
        private String type;  //function
        private Function function;

        @Data
        public static class Function{
            private String description;
            private String name;
            private Object parameters;
        }
    }
}
