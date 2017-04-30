package com.polytech.unice.tobeortohave.list.dummy;

import android.support.annotation.NonNull;

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
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ShopDetail> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, ShopDetail> ITEM_MAP = new HashMap<>();


    public static void addItem(ShopDetail item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    /**
     * A dummy item representing a piece of name.
     */
    public static class ShopDetail  implements Comparable{
        public final int id;
        public final String name;
        public final String adress;
        public final int benefits;
        public final int cost;

        public ShopDetail(int id, String content, String details, int benefits, int cost) {
            this.id = id;
            this.name = content;
            this.adress = details;
            this.benefits = benefits;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(@NonNull Object o) {
            ShopDetail shopDetail = (ShopDetail) o;
            return (this.benefits - this.cost) - (shopDetail.benefits - shopDetail.cost);
        }
    }
}
