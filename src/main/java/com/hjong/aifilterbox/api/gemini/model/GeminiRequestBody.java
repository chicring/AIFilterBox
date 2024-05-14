package com.hjong.aifilterbox.api.gemini.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/
@Data
public class GeminiRequestBody {
    private List<Content> contents;
    private GenerationConfig generationConfig;
    private List<Tool> tools;

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
    public static class Tool {
        private Function_declarations function_declarations;

        @Data
        public static class Function_declarations {
            private String name;
            private String description;
            private Map<String,Object> parameters;
        }
    }

    public static GeminiRequestBody builder(String content, List<Tool> functions){
        GeminiRequestBody geminiRequestBody = new GeminiRequestBody();

        GeminiRequestBody.Content newContent = new GeminiRequestBody.Content();
        newContent.setRole("user");
        GeminiRequestBody.Content.Part newPart = new Content.Part();
        newPart.setText(content);
        newContent.setParts(Collections.singletonList(newPart));
        geminiRequestBody.setContents(Collections.singletonList(newContent));

        geminiRequestBody.setTools(functions);

        return geminiRequestBody;
    }
}
