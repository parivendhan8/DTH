package com.example.dth_project;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dth_project.helper.SessionManager;
import com.example.dth_project.PojoPack.Clist;
import com.example.dth_project.helper.SessionManager;

import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter {
    List<Clist> clist;

    public TableViewAdapter(List<Clist> clistList) {
        this.clist = clistList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.table_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SessionManager session = null;

        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.txtName.setBackgroundResource(R.drawable.table_header_cell_bg);
//            rowViewHolder.txtAddrs.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtPhone.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBal.setBackgroundResource(R.drawable.table_header_cell_bg);
//            rowViewHolder.txtCompId.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtCustid.setBackgroundResource(R.drawable.table_header_cell_bg);
//            rowViewHolder.txtGroup.setBackgroundResource(R.drawable.table_header_cell_bg);
//            rowViewHolder.txtArea.setBackgroundResource(R.drawable.table_header_cell_bg);




//            rowViewHolder.txtCompId.setText("CId");
            rowViewHolder.txtCustid.setText("UId");
//            rowViewHolder.txtGroup.setText("Group");
//            rowViewHolder.txtArea.setText("Area");
            rowViewHolder.txtName.setText("Name");
//            rowViewHolder.txtAddrs.setText("Address");
            rowViewHolder.txtPhone.setText("Phone");
            rowViewHolder.txtBal.setText("Balance");

        } else {
            Clist modal = clist.get(rowPos-1);
//          session.getValue(modal.getName()+"");txtAddrs = itemView.findViewById(R.id.addrsid);txtCompId=itemView.findViewById(R.id.vw_comp_id);txtArea=itemView.findViewById(R.id.vw_line);
//          session.saveArrayList(modal.getName());txtGroup=itemView.findViewById(R.id.vw_group);

            // Content Cells. Content appear here
            rowViewHolder.txtName.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtAddrs.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtPhone.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBal.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtCompId.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtCustid.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtGroup.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtArea.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtName.setText(modal.getName()+"");
//            rowViewHolder.txtAddrs.setText(modal.getAddress()+"");
            rowViewHolder.txtPhone.setText(modal.getPhone()+"");
            rowViewHolder.txtBal.setText(modal.getAct()+"");
            rowViewHolder.txtCustid.setText(modal.getId()+"");
//            rowViewHolder.txtCompId.setText(modal.getCid()+"");
//            rowViewHolder.txtArea.setText(modal.getArea()+"");
//            rowViewHolder.txtGroup.setText(modal.getCgroup()+"");





        }
    }

    @Override
    public int getItemCount() {
        return clist.size()+1; // one more to add header row
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtCustid;
        protected TextView txtCompId;
        protected TextView txtName;
        protected TextView txtAddrs;
        protected TextView txtPhone;
        protected TextView txtBal;
        protected TextView txtArea;
        protected TextView txtGroup;


        public RowViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.nameid);

            txtPhone = itemView.findViewById(R.id.phoneid);
            txtBal = itemView.findViewById(R.id.balanceid);
            txtCustid=itemView.findViewById(R.id.vw_cust_id);




        }
    }
}
