package com.bot.store;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class SessionStore {

    private static final Map<Long, String> data = new ConcurrentHashMap<>();

    public static void save(Long user, String transcript) {
        data.put(user, transcript);
    }

    public static String get(Long user) {
        return data.get(user);
    }
}