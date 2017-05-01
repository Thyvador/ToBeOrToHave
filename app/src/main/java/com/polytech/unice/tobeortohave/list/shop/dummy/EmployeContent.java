package com.polytech.unice.tobeortohave.list.shop.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample name for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class EmployeContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<EmployerDetails> ITEMS = new ArrayList<EmployerDetails>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, EmployerDetails> ITEM_MAP = new HashMap<String, EmployerDetails>();

    private static final int COUNT = 25;

    public static void addItem(EmployerDetails item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static EmployerDetails createDummyItem(int position) {
        return new EmployerDetails(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore role information here.");
        }
        return builder.toString();
    }

    public static void clear() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    /**
     * A dummy item representing a piece of name.
     */
    public static class EmployerDetails {
        public final String id;
        public final String name;
        public final String role;

        public EmployerDetails(String id, String content, String role) {
            this.id = id;
            this.name = content;
            this.role = role;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
