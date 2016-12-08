package com.example.estebancastro.proyectofinal.appmodules.citieslist.adapter;

/**
 * Created by ESTEBAN CASTRO on 7/12/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.estebancastro.proyectofinal.R;
import com.example.estebancastro.proyectofinal.model.City;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by danielsanchez on 11/22/16.
 */
public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.ContactRowViewHolder>{

    private ArrayList<City> mCitiesList;
    private final OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(City contact, ImageView profilePicture, TextView contactName);
    }

    public static class ContactRowViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_view_contact) ImageView mImageViewContact;
        @BindView(R.id.text_view_contact_name_value) TextView mTextViewContactName;

        public ContactRowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final City item, final ImageView imageViewContact, final TextView textViewName,final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item, imageViewContact, textViewName);
                }
            });
        }

    }

    public CitiesListAdapter(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
        mCitiesList = new ArrayList<>();
    }

    @Override
    public ContactRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row_item, parent, false);
        ContactRowViewHolder viewHolder = new ContactRowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactRowViewHolder holder, int position) {
        City city = mCitiesList.get(position);

        if(city != null)
            buildContactCell(city, holder);
    }

    private void buildContactCell(City contact, ContactRowViewHolder holder) {
        holder.bind(contact, holder.mImageViewContact, holder.mTextViewContactName, mOnItemClickListener);
        holder.mTextViewContactName.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mCitiesList.size();
    }

    public void setCitiesList(ArrayList<City> mCitiesList) {
        this.mCitiesList = mCitiesList;
        notifyDataSetChanged();
    }
}
