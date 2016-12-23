package exam.jmedinilla.offering_jmedinilla;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Javier Medinilla
 * @version 1.0
 *          <p>
 *          Adapter for the main list of the application. It initializes
 *          all the elements for each row and fill them with the item
 *          information of the main list in the Repository class
 */
class ListAdapter extends ArrayAdapter<Offer> {
    static final int ORDER_NAME_ASC = 741;
    static final int ORDER_NAME_DESC = 852;
    static final int ORDER_TYPE = 963;

    private Context context;
    private boolean importance;
    private Repository repository;
    private ArrayList<Offer> offerArrayList;

    ListAdapter(Context context, ArrayList<Offer> offerArrayList, boolean importance) {
        super(context, R.layout.adapter_row, offerArrayList);
        this.context = context;
        this.importance = importance;
        this.repository = Repository.getInstance();
        this.offerArrayList = offerArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        OfferHolder offerHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_row, parent, false);
            offerHolder = new OfferHolder();
            offerHolder.layImportance = (RelativeLayout) view.findViewById(R.id.row_layImportance);
            offerHolder.imgIcon = (ImageView) view.findViewById(R.id.row_imgIcon);
            offerHolder.txtShop = (TextView) view.findViewById(R.id.row_txtShop);
            offerHolder.txtName = (TextView) view.findViewById(R.id.row_txtTitle);
            offerHolder.txtDate = (TextView) view.findViewById(R.id.row_txtDate);

            view.setTag(offerHolder);
        } else {
            offerHolder = (OfferHolder) view.getTag();
        }

        Offer offer = getItem(position);
        if (offer != null) {
            offerHolder.txtShop.setFocusable(false);
            offerHolder.txtName.setFocusable(false);
            offerHolder.txtDate.setFocusable(false);
            offerHolder.imgIcon.setFocusable(false);
            offerHolder.layImportance.setFocusable(false);

            offerHolder.txtShop.setText(offer.getOffer_shop());
            offerHolder.txtName.setText(offer.getOffer_name());
            offerHolder.txtDate.setText(offer.getOffer_date());
            switch (offer.getOffer_type()) {
                case Offer.TYPE_HOME:
                    offerHolder.imgIcon.setBackgroundResource(R.mipmap.ic_home);
                    break;
                case Offer.TYPE_ELECTRONICS:
                    offerHolder.imgIcon.setBackgroundResource(R.mipmap.ic_mobile);
                    break;
                case Offer.TYPE_SPORT:
                    offerHolder.imgIcon.setBackgroundResource(R.mipmap.ic_sports);
                    break;
            }
            if (this.importance) {
                switch (offer.getOffer_importance()) {
                    case Offer.IMP_LOW:
                        offerHolder.layImportance.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
                        break;
                    case Offer.IMP_MEDIUM:
                        offerHolder.layImportance.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_light));
                        break;
                    case Offer.IMP_HIGH:
                        offerHolder.layImportance.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
                        break;
                }
            }
        }

        view.setFocusable(false);
        return view;
    }

    void notifyChanges() {
        notifyDataSetChanged();
    }

    void orderOffer(int order) {
        switch (order) {
            case ListAdapter.ORDER_NAME_ASC:
                Collections.sort(offerArrayList, Offer.COMP_ORDER_NAME_ASC);
                break;
            case ListAdapter.ORDER_NAME_DESC:
                Collections.sort(offerArrayList, Offer.COMP_ORDER_NAME_DESC);
                break;
            case ListAdapter.ORDER_TYPE:
                Collections.sort(offerArrayList, Offer.COMP_RDER_TYPE);
                break;
        }
        notifyDataSetChanged();
    }

    private class OfferHolder {
        RelativeLayout layImportance;
        TextView txtShop;
        TextView txtName;
        TextView txtDate;
        ImageView imgIcon;
    }
}
