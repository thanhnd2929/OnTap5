package com.example.ontapandroid2_5.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapandroid2_5.Adapter.DichVuAdapter;
import com.example.ontapandroid2_5.DAO.DichVuDAO;
import com.example.ontapandroid2_5.DTO.DichVuDTO;
import com.example.ontapandroid2_5.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_dv extends Fragment {

    RecyclerView rv_dv;

    DichVuDAO dichVuDAO;

    DichVuAdapter dichVuAdapter;
    FloatingActionButton fb_add;

    LinearLayoutManager linearLayoutManager;
    ArrayList<DichVuDTO> listDV;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dv_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_dv = view.findViewById(R.id.rv_dv);
        fb_add = view.findViewById(R.id.fb_add);

        linearLayoutManager = new LinearLayoutManager(getContext());
        rv_dv.setLayoutManager(linearLayoutManager);

        dichVuDAO = new DichVuDAO(getContext());
        listDV = dichVuDAO.getList();

        dichVuAdapter = new DichVuAdapter(getContext(), listDV);
        rv_dv.setAdapter(dichVuAdapter);

        fb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = getLayoutInflater().inflate(R.layout.dialog_them, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edt_ngay = view1.findViewById(R.id.edt_ngay);
                EditText edt_noiDung = view1.findViewById(R.id.edt_noiDung);
                EditText edt_thanhTien = view1.findViewById(R.id.edt_thanhTien);
                Button btn_them = view1.findViewById(R.id.btn_them);
                Button btn_huyThem = view1.findViewById(R.id.btn_huyThem);

                btn_huyThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ngay = edt_ngay.getText().toString().trim();
                        String noidung = edt_noiDung.getText().toString().trim();
                        String tien = edt_thanhTien.getText().toString().trim();

                        if (ngay.isEmpty()||noidung.isEmpty()||tien.isEmpty()) {
                            Toast.makeText(getContext(), "Khong bo trong du lieu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            int tien_int = Integer.parseInt(tien);
                            if (tien_int < 0) {
                                Toast.makeText(getContext(), "Tien phai lon hon khong", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            int result = dichVuDAO.addRow(new DichVuDTO(noidung, ngay, tien_int));
                            if (result > 0) {
                                dialog.dismiss();
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                                builder1.setTitle("Thong Bao");
                                builder1.setMessage("Ban da them thanh cong");

                                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        listDV.clear();
                                        listDV.addAll(dichVuDAO.getList());
                                        dichVuAdapter.notifyDataSetChanged();
                                    }
                                });
                                Dialog dialog1 = builder1.create();
                                dialog1.show();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(getContext(), "Tien phai la so", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}
