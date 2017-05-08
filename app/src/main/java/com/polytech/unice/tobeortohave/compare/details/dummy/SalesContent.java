package com.polytech.unice.tobeortohave.compare.details.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static final Map<Integer, List<DummyItem>> LIST_MAP = new HashMap<>();


    private static void addItem(DummyItem item, int shopId) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        if (!LIST_MAP.containsKey(shopId)) {
            LIST_MAP.put(shopId, new ArrayList<DummyItem>());
        }
        LIST_MAP.get(shopId).add(item);
    }


    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String sale;
        public final int number;

        public DummyItem(String id, String sale, int number) {
            this.id = id;
            this.sale = sale;
            this.number = number;
        }

        @Override
        public String toString() {
            return "DummyItem{" +
                    "id='" + id + '\'' +
                    ", sale='" + sale + '\'' +
                    ", number=" + number +
                    '}';
        }
    }
}
