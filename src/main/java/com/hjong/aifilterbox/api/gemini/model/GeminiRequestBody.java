package com.hjong.aifilterbox.api.gemini.model;

import lombok.Data;

import java.util.List;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/
@Data
public class GeminiRequestBody {
    private List<Content> contents;
    private GenerationConfig generationConfig;
    private List<Function_declarations> tools;

    @Data
    public static class Content {
        private String role;
        private List<Part> parts;

        @Data
        public static class Part {
            private String text;
        }
    }

    @Data
    public static class GenerationConfig {
        private Float temperature;
        private Integer topK;
        private Float topP;
        private Integer maxOutputTokens;
        private List<String> stopSequences;
    }

    @Data
    public static class Function_declarations {
        private String name;
        private String description;
        private Object parameters;
    }
}
