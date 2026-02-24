package com.bot.bot;

import com.bot.service.TranscriptService;
import com.bot.service.SummaryService;
import com.bot.store.SessionStore;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class YoutubeBot extends TelegramLongPollingBot {

    private final String TOKEN = "8047655242:AAEL1GxuisuV2rXiH_91b_m5khXluC4t1OA";
    private final String NAME = "bunny4539F_bot";

    @Override
    public void onUpdateReceived(Update update) {

        if (!update.hasMessage()) return;

        String text = update.getMessage().getText();
        if (text != null) text = text.trim();

        Long user = update.getMessage().getChatId();

        try {

            if (text != null && text.matches(".*(youtube\\.com|youtu\\.be).*")) {

                String transcript = TranscriptService.getTranscript(text);

                if (transcript == null) {
                    send(user, "No transcript available for this video.");
                    return;
                }

                SessionStore.save(user, transcript);
                send(user, SummaryService.summarize(transcript));

            } else {

                String transcript = SessionStore.get(user);

                if (transcript == null) {
                    send(user, "Send YouTube link first.");
                    return;
                }

                send(user, SummaryService.answer(transcript, text));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send(Long chat, String msg) throws Exception {
        SendMessage m = new SendMessage();
        m.setChatId(chat.toString());
        m.setText(msg);
        execute(m);
    }

    @Override
    public String getBotUsername() { return NAME; }

    @Override
    public String getBotToken() { return TOKEN; }
}