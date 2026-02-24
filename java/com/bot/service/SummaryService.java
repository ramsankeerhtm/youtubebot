package com.bot.service;

public class SummaryService {

    public static String summarize(String title) {

        return "🎥 Video Title: " + title + "\n\n"
             + "📌 5 Key Points:\n"
             + "1. Main topic explained: " + title + "\n"
             + "2. Key concepts and overview\n"
             + "3. Practical insights and examples\n"
             + "4. Importance of the discussed topic\n"
             + "5. Real-world relevance\n\n"
             + "⏱ Important Timestamps:\n"
             + "0:00 Introduction\n"
             + "1:30 Key Explanation\n"
             + "3:45 Examples\n"
             + "5:30 Summary\n\n"
             + "🧠 Core Takeaway:\n"
             + "This video explains " + title.toLowerCase() 
             + " and its practical importance.";
    }

    public static String answer(String title, String question) {

        return "Based on the video \"" + title + "\":\n\n"
             + "The speaker discusses aspects related to "
             + title.toLowerCase() + ".";
    }
}