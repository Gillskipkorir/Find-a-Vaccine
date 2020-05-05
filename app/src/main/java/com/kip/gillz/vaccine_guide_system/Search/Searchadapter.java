package com.kip.gillz.vaccine_guide_system.Search;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kip.gillz.vaccine_guide_system.R;

import java.util.List;


public class Searchadapter extends RecyclerView.Adapter<Searchadapter.ProductViewHolder> {
    FragmentManager fragmentManager;
    private Context mCtx;
    private List<SearchModel> CList;
    private FragmentManager supportFragmentManager;
    private Dialog dialog;
    public ImageButton bt_expand;
    public View lyt_expand;
    public View lyt_parent;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;

    PreferenceManager prefManager;

    public Searchadapter(Context mCtx, List<SearchModel> CList) {
        this.mCtx = mCtx;
        this.CList = CList;
        dialog = new Dialog(mCtx);
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_vaccines, null);
        return new ProductViewHolder(view);

    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


        String xyz="";
        final SearchModel client = CList.get(position);
        holder.tvrecno.setText(client.getReceipt());
        holder.tvtdate.setText(client.getTDate());
        holder.tvquan.setText(String.valueOf(client.getProduct_Count()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
    @Override
    public int getItemCount() {
        return CList.size();
    }
    public FragmentManager getSupportFragmentManager() {
        return supportFragmentManager;
    }
    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvrecno,tvtdate,tvquan;
        CardView cardView;
        public ProductViewHolder(View itemView) {
            super(itemView);
            tvrecno = itemView.findViewById(R.id.recno);
            tvtdate = itemView.findViewById(R.id.tdate);
            tvquan = itemView.findViewById(R.id.quan);
            cardView= itemView.findViewById(R.id.card);
        }
    }
/*
    private void showCustomDialog() {


                final Dialog dialog = new Dialog(mCtx);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                dialog.setContentView(R.layout.dialog_receipt);
                dialog.setCancelable(true);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                ((TextView) dialog.findViewById(R.id.receipt)).setText("Receipt No:"+(client.getReceipt()));
                ((TextView) dialog.findViewById(R.id.storename)).setText(" Store:"+(client.getStore()));
                ((TextView) dialog.findViewById(R.id.datee)).setText((client.getTDate()));
                ((TextView) dialog.findViewById(R.id.agentt)).setText((client.getAgent()));
                ((TextView) dialog.findViewById(R.id.modee)).setText((client.getPmode()));
                ((TextView) dialog.findViewById(R.id.pr)).setText((client.getItem()));
                ((TextView) dialog.findViewById(R.id.qn)).setText(String.valueOf(client.getQuantity()));
                ((TextView) dialog.findViewById(R.id.am)).setText(String.valueOf(client.getPrice()));



                (dialog.findViewById(R.id.fabb)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                (dialog.findViewById(R.id.print)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mCtx, "Print Receipt Coming Soon...", Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.show();
                dialog.getWindow().setAttributes(lp);


    }
*/
}
