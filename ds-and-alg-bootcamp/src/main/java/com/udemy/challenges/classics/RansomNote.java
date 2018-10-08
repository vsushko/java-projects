package com.udemy.challenges.classics;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vsushko
 */
public class RansomNote {
    //
    // This is basically an exercise in counting and book keeping.
    //
    // If we can count the number of times a letter appears in the ransom note,
    // and then compare it with the number of times it appears in the letters,
    // we can tell if the ransom note can be written.
    //

    public boolean canWrite(String note, String letters) {

        // Assumption: We don't need to count spaces in our note
        String noteNoSpaces = note.replaceAll("\\s+", "");

        Map<String, Integer> noteMap = map(noteNoSpaces);
        Map<String, Integer> letterMap = map(letters);

        return canWriteRansom(noteMap, letterMap);
    }

    private Map<String, Integer> map(String text) {
        Map<String, Integer> map = new HashMap<>();
        char[] characters = text.toCharArray();

        for (int i = 0; i < characters.length; i++) {
            String character = String.valueOf(characters[i]);

            // If we already have this character... increment the count
            if (map.containsKey(character)) {
                Integer currentCount = map.get(character);
                map.put(character, currentCount + 1);
            } else {
                // else add
                map.put(String.valueOf(characters[i]), 1);
            }
        }

        return map;
    }

    private boolean canWriteRansom(Map<String, Integer> noteMap,
                                   Map<String, Integer> letterMap) {
        for (String key : noteMap.keySet()) {

            if (!letterMap.containsKey(key)) {
                return false;
            }

            int noteCount = noteMap.get(key);
            int letterCount = letterMap.get(key);

            if (letterCount < noteCount) {
                return false;
            }
        }
        return true;
    }
}
