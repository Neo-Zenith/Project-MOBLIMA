package handler;

import java.util.HashMap;

public class DatabaseHandler {
    
    public static <K, V> int generateUUID(HashMap <K, V> data) {

        if (data.size() == 0) {
            return 1;
        }

        String currentMaxKey = "";

        for (K key : data.keySet()) {
            if (key instanceof String) {
                String currentKey = (String) key;
                if (currentKey.compareTo(currentMaxKey) > 0) {
                    currentMaxKey = currentKey;
                }
            }
        }

        String UUID = currentMaxKey.substring(2);
        return Integer.parseInt(UUID) + 1;
    }
}
