package com.polytech.unice.tobeortohave.compare.details.dummy;

import android.support.annotation.NonNull;

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
public class SalesContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Sales> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Sales> ITEM_MAP = new HashMap<>();

    public static final Map<Integer, List<Sales>> LIST_MAP = new HashMap<>();


    public static void addItem(Sales item, int shopId) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        if (!LIST_MAP.containsKey(shopId)) {
            LIST_MAP.put(shopId, new ArrayList<Sales>());
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

    public static void clear() {
        ITEMS.clear();
        ITEM_MAP.clear();
        LIST_MAP.clear();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Sales implements Comparable {
        public final String id;
        public final String sale;
        public final int number;

        public Sales(String id, String sale, int number) {
            this.id = id;
            this.sale = sale;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Sales{" +
                    "id='" + id + '\'' +
                    ", sale='" + sale + '\'' +
                    ", number=" + number +
                    '}';
        }

        @Override
        public int compareTo(@NonNull Object o) {
            Sales sales = (Sales) o;
            return sales.number - this.number;

        }
    }
}
