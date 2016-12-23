package exam.jmedinilla.offering_jmedinilla;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;

/**
 * @author Javier Medinilla
 * @version 1.0
 *          <p>
 *          Model class for this offers application
 */
class Offer implements Parcelable, Comparable<Offer> {
    private Offer(Parcel in) {
        offer_name = in.readString();
        offer_shop = in.readString();
        offer_date = in.readString();
        offer_type = in.readInt();
        offer_importance = in.readInt();
    }

    public static final Creator<Offer> CREATOR = new Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(offer_name);
        parcel.writeString(offer_shop);
        parcel.writeString(offer_date);
        parcel.writeInt(offer_type);
        parcel.writeInt(offer_importance);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef()
    @interface CategoryValues{
        int TYPE_HOME = 0;
        int TYPE_ELECTRONICS = 1;
        int TYPE_SPORT = 2;
    }
    @Retention(RetentionPolicy.SOURCE)
    @IntDef()
    @interface ImportanceValues{
        int IMP_LOW = 0;
        int IMP_MEDIUM = 1;
        int IMP_HIGH = 2;
    }

    private String offer_name;
    private String offer_shop;
    private String offer_date;
    private @CategoryValues int offer_type;
    private @ImportanceValues int offer_importance;

    Offer(String offer_name, String offer_shop, String offer_date, @CategoryValues int offer_type, @ImportanceValues int offer_importance) {

        this.offer_name = offer_name;
        this.offer_shop = offer_shop;
        this.offer_date = offer_date;
        this.offer_type = offer_type;
        this.offer_importance = offer_importance;
    }

    String getOffer_name() {
        return offer_name;
    }

    void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    String getOffer_shop() {
        return offer_shop;
    }

    void setOffer_shop(String offer_shop) {
        this.offer_shop = offer_shop;
    }

    String getOffer_date() {
        return offer_date;
    }

    void setOffer_date(String offer_date) {
        this.offer_date = offer_date;
    }

    int getOffer_type() {
        return offer_type;
    }

    void setOffer_type(int offer_type) {
        this.offer_type = offer_type;
    }

    int getOffer_importance() {
        return offer_importance;
    }

    void setOffer_importance(int offer_importance) {
        this.offer_importance = offer_importance;
    }

    @Override
    public String toString() {
        return this.offer_name;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o != null) {
            if (o instanceof Offer) {
                if (this.offer_name.equalsIgnoreCase(((Offer) o).offer_name)) {
                    result = true;
                }
            }
        }
        return result;
    }

    static final Comparator<Offer> COMP_ORDER_NAME_ASC = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            return offer.offer_name.compareToIgnoreCase(t1.offer_name);
        }
    };

    static final Comparator<Offer> COMP_ORDER_NAME_DESC = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            return t1.offer_name.compareToIgnoreCase(offer.offer_name);
        }
    };

    static final Comparator<Offer> COMP_RDER_TYPE = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            return offer.offer_type - t1.offer_type;
        }
    };

    @Override
    public int compareTo(@NonNull Offer offer) {
        return this.offer_name.compareTo(offer.offer_name);
    }
}
