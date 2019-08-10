package com.example.dth_project;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dth_project.PojoPack.Clist;
import com.example.dth_project.PojoPack.Clistt;
import com.example.dth_project.PojoPack.ViewListdetails;

import java.util.List;

public class ViewListTableAdapter extends RecyclerView.Adapter {
//    List<ViewListModal> viewListModals;
    List<Clistt> viewListdetails;

    public ViewListTableAdapter(List<Clistt> viewListdetails) {
        this.viewListdetails = viewListdetails;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.viewlist_table, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.txtRank.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_header_cell_bg);
//
            rowViewHolder.txtRank.setText("Name");
            rowViewHolder.txtMovieName.setText("Date");
            rowViewHolder.txtYear.setText("Paid Amount");
            rowViewHolder.txtCost.setText("Reference");
        } else {
            Clistt modal = viewListdetails.get(rowPos-1);
//            ViewListModal modal = viewListModals.get(rowPos-1);

            // Content Cells. Content appear here

                rowViewHolder.txtRank.setBackgroundResource(R.drawable.table_content_cell_bg);
                rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_content_cell_bg);
                rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_content_cell_bg);
                rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_content_cell_bg);

                rowViewHolder.txtRank.setText(modal.getName() + "");
                rowViewHolder.txtMovieName.setText(modal.getDate() + "");
                rowViewHolder.txtYear.setText(modal.getTotal() + "");
                rowViewHolder.txtCost.setText(modal.getRef() + "");

        }
    }

    @Override
    public int getItemCount() {
        return viewListdetails.size()+1; // one more to add header row
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtRank;
        protected TextView txtMovieName;
        protected TextView txtYear;
        protected TextView txtCost;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtRank = itemView.findViewById(R.id.vw_nameid);
            txtMovieName = itemView.findViewById(R.id.vw_date);
            txtYear = itemView.findViewById(R.id.vw_paidamnt);
            txtCost = itemView.findViewById(R.id.vw_reference);
        }
    }
}
