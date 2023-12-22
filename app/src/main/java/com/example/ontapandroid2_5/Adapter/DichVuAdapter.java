package com.example.ontapandroid2_5.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapandroid2_5.DAO.DichVuDAO;
import com.example.ontapandroid2_5.DTO.DichVuDTO;
import com.example.ontapandroid2_5.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DichVuAdapter extends RecyclerView.Adapter<DichVuAdapter.viewHolder> {

    Context context;
    ArrayList<DichVuDTO> listDV;

    public DichVuAdapter(Context context, ArrayList<DichVuDTO> listDV) {
        this.context = context;
        this.listDV = listDV;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dv, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DichVuDTO dichVuDTO = listDV.get(position);
        DichVuDAO dichVuDAO = new DichVuDAO(context);

        holder.txt_madv.setText("Ma DV: " + dichVuDTO.getMadv());
        holder.txt_noidung.setText("Noi Dung: " + dichVuDTO.getNoiDung());
        holder.txt_ngay.setText("Ngay DV: " + dichVuDTO.getNgay());
        holder.txt_thanhtien.setText("Thanh Tien: " + dichVuDTO.getThanhTien());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int result = dichVuDAO.deleteRow(dichVuDTO);
                if (result>0) {
                    listDV.remove(dichVuDTO);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int result = dichVuDAO.deleteRow(dichVuDTO);
                if (result>0) {
                    listDV.remove(dichVuDTO);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_sua, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edt_thanhTien = view.findViewById(R.id.edt_thanhTien);
                EditText edt_noiDung = view.findViewById(R.id.edt_noiDung);
                EditText edt_ngay = view.findViewById(R.id.edt_ngay);
                Button btn_sua = view.findViewById(R.id.btn_sua);
                Button btn_huySua = view.findViewById(R.id.btn_huySua);

                edt_thanhTien.setText(dichVuDTO.getThanhTien()+"");
                edt_noiDung.setText(dichVuDTO.getNoiDung()+"");
                edt_ngay.setText(dichVuDTO.getNgay()+"");


                btn_sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tien = edt_thanhTien.getText().toString().trim();
                        String noidung = edt_noiDung.getText().toString().trim();
                        String ngay = edt_ngay.getText().toString().trim();

                        dichVuDTO.setNgay(ngay);
                        dichVuDTO.setNoiDung(noidung);


                        if (tien.isEmpty()||noidung.isEmpty()||ngay.isEmpty()) {
                            Toast.makeText(context, "Khong bo trong du lieu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            int tien_int = Integer.parseInt(tien);
                            if (tien_int < 0) {
                                Toast.makeText(context, "tien phai lon hon 0", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            dichVuDTO.setThanhTien(tien_int);


                            int result = dichVuDAO.updateRow(dichVuDTO);
                            if (result > 0) {
                                dialog.dismiss();
                                notifyDataSetChanged();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, "tien phai la so", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                btn_huySua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });




            }
        });

    }

    @Override
    public int getItemCount() {
        return listDV.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView txt_madv, txt_ngay, txt_noidung, txt_thanhtien;
        Button btn_sua, btn_xoa;

        DichVuAdapter dichVuAdapter;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            dichVuAdapter = new DichVuAdapter(context, listDV);

            txt_madv = itemView.findViewById(R.id.txt_madv);
            txt_noidung = itemView.findViewById(R.id.txt_noiDung);
            txt_ngay = itemView.findViewById(R.id.txt_ngay);
            txt_thanhtien = itemView.findViewById(R.id.txt_thanhTien);
            btn_sua = itemView.findViewById(R.id.btn_sua);
            btn_xoa = itemView.findViewById(R.id.btn_xoa);
        }
    }
}
