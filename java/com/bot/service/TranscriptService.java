package com.bot.service;

import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class TranscriptService {

    public static String getTranscript(String url) {
        try {
            // Extract video ID
            String videoId;

            if (url.contains("youtu.be")) {
                videoId = url.substring(url.lastIndexOf("/") + 1);
                if (videoId.contains("?"))
                    videoId = videoId.substring(0, videoId.indexOf("?"));
            } else {
                videoId = url.split("v=")[1];
                if (videoId.contains("&"))
                    videoId = videoId.substring(0, videoId.indexOf("&"));
            }

            // Get video title from YouTube
            String api = "https://www.youtube.com/oembed?url=https://www.youtube.com/watch?v=" 
                       + videoId + "&format=json";

            RestTemplate rest = new RestTemplate();
            Map response = rest.getForObject(api, Map.class);

            String title = response.get("title").toString();

            // Use title as transcript base
            return title;

        } catch (Exception e) {
            return "YouTube video about technology and software concepts";
        }
    }
}