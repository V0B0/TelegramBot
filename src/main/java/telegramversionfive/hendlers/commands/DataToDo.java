package telegramversionfive.hendlers.commands;

import telegramversionfive.enums.StateToDo;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class DataToDo {
    private static final Map<Long, Queue<String>> queueMap = new HashMap<>();
    private static StateToDo state;

    public static StateToDo getState() {
        return state;
    }

    public static void setState(StateToDo state) {
        DataToDo.state = state;
    }

    public static Queue<String> getQueue(Long chatId) {
        return queueMap.get(chatId);
    }

    public static void newQueue(Long chatId) {
        if (!queueMap.containsKey(chatId))
            queueMap.put(chatId, new ArrayDeque<>());
    }
}
