package telegramversionfive.hendlers.commands;

import telegramversionfive.enums.StateNotes;

import java.util.*;

public class DataNotes {
    private static final Map<Long, Deque<String>> links = new HashMap<>();
    private static final Map<Long, Deque<String>> videos = new HashMap<>();
    private static final Map<Long, Deque<String>> other = new HashMap<>();

    private static StateNotes previousState;
    private static StateNotes state;

    public static StateNotes getPreviousState() {
        return previousState;
    }

    public static void setPreviousState(StateNotes previousState) {
        DataNotes.previousState = previousState;
    }

    public static StateNotes getState() {
        return state;
    }

    public static void setState(StateNotes state) {
        DataNotes.state = state;
    }

    public static Deque<String> getList(Long chatId, String s) {
        switch (s) {
            case "link":
                return links.get(chatId);
            case "video":
                return videos.get(chatId);
            case "other":
                return other.get(chatId);
            default:
                return null;
        }
    }

    public static void newLink(Long chatId) {
        if (!links.containsKey(chatId)) {
            links.put(chatId, new ArrayDeque<>());
            videos.put(chatId, new ArrayDeque<>());
            other.put(chatId, new ArrayDeque<>());
        }
    }

}
