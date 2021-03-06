package com.ican.ican;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.cardview, viewGroup, false);

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.name.setText(" " + Recyclable.values()[i].name);
        viewHolder.count.setText("" + (MainActivity.dataRange ? Recyclable.values()[i].alltime : Recyclable.values()[i].daily) + " ");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.context);
                dialog.setTitle(Recyclable.values()[i].name);
                dialog.setContentView(R.layout.infodialog);
                ((TextView) dialog.findViewById(R.id.intro)).setText("By recycling " + (MainActivity.dataRange ? Recyclable.values()[i].alltime : Recyclable.values()[i].daily) + " items you have conserved an incredible:");
                double total = (MainActivity.dataRange ? Recyclable.values()[i].alltime : Recyclable.values()[i].daily) * Recyclable.values()[i].nrg;
                BigDecimal bd = new BigDecimal(total);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                ((TextView) dialog.findViewById(R.id.intro2)).setText("" + bd + " kWh!");
                ((TextView) dialog.findViewById(R.id.facts)).setText("" + Recyclable.values()[i].getFacts());
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Recyclable.values().length;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView count;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView
                    .findViewById(R.id.name);
            count = (TextView) itemLayoutView
                    .findViewById(R.id.count);

        }
    }
}
