package com.polytech.unice.tobeortohave.dummy;

import android.os.Parcel;
import android.os.Parcelable;
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
public class ShopContent {

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
    public static class ShopDetail  implements Comparable, Parcelable{
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

        protected ShopDetail(Parcel in) {
            id = in.readInt();
            name = in.readString();
            adress = in.readString();
            benefits = in.readInt();
            cost = in.readInt();
        }

        public static final Creator<ShopDetail> CREATOR = new Creator<ShopDetail>() {
            @Override
            public ShopDetail createFromParcel(Parcel in) {
                return new ShopDetail(in);
            }

            @Override
            public ShopDetail[] newArray(int size) {
                return new ShopDetail[size];
            }
        };

        @Override
        public String toString() {
            return "ShopDetail{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", adress='" + adress + '\'' +
                    ", benefits=" + benefits +
                    ", cost=" + cost +
                    '}';
        }

        @Override
        public int compareTo(@NonNull Object o) {
            ShopDetail shopDetail = (ShopDetail) o;
            return this.id  - shopDetail.id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ShopDetail that = (ShopDetail) o;

            if (id != that.id) return false;
            if (benefits != that.benefits) return false;
            if (cost != that.cost) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return adress != null ? adress.equals(that.adress) : that.adress == null;

        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (adress != null ? adress.hashCode() : 0);
            result = 31 * result + benefits;
            result = 31 * result + cost;
            return result;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(adress);
            dest.writeInt(benefits);
            dest.writeInt(cost);
        }
    }
}
