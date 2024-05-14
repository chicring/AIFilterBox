package com.hjong.aifilterbox.api.gemini.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/13
 **/
@Data
public class GeminiResponseBody {

    private List<Candidate> candidates;

    @Data
    public static class Candidate {
        private Content content;
        private String finishReason;
        private Integer index;
        @Data
        public static class Content {
            private List<Part> parts;
            private String role;

            @Data
            public static class Part {
                private String text;
                private FunctionCAll functionCall;

                @Data
                public static class FunctionCAll{
                    private String name;
                    private Map<String,Object> args;
                }
            }
        }
    }

}
