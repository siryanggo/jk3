package com.yang.jk.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @auther yhjStart
 * @create 2022-04-03 21:37
 */
@Component
@ConfigurationProperties("jk")
@Data
public class Yproperties {
    private Upload upload;
    @Data
    public static class Upload{
        private String basicPath;
        private String uploadPath;
        private String imgPath;
        private String videoPath;
        public String getImageRelativePath() {
            return uploadPath+imgPath;
        }
        public String getVideoRelativePath() {
            return uploadPath+videoPath;
        }
    }
}
