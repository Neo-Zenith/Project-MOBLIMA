package src.handler;

import java.util.HashMap;

/**
 * General helper functions for handling database
 * @author Lee Juin
 * @version 1.0
 */
public class DatabaseHandler {
    
    /**
     * Generic function to generate unique UUID for different database
     * @param <K> is the generic form for key
     * @param <V> is the generic form for vlaue
     * @param data is the HashMap database to generate UUID for
     * @return A unique UUID for the database
     */
    public static <K, V> int generateUUID(HashMap <K, V> data) {

        if (data.size() == 0) {
            return 1;
        }

        String currentMaxKey = "";

        for (K key : data.keySet()) {
            if (key instanceof String) {
                String currentKey = (String) key;
                currentKey = currentKey.substring(2);
                if (currentKey.compareTo(currentMaxKey) > 0) {
                    currentMaxKey = currentKey;
                }
            }
        }

        String UUID = currentMaxKey;
        return Integer.parseInt(UUID) + 1;
    }
}
