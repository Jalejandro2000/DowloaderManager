package com.example.dowloadermanager.adapter;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dowloadermanager.R;
import com.example.dowloadermanager.model.Ficheros;
import com.example.dowloadermanager.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class FicheroAdapter extends RecyclerView.Adapter<FicheroAdapter.FicheroViewHolder> {
    private List<Ficheros> data = new ArrayList<>();
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Ficheros> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FicheroAdapter.FicheroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new FicheroAdapter.FicheroViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_fichero, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull FicheroAdapter.FicheroViewHolder holder, int position) {
          Ficheros ficheros = data.get(position);
        holder.txtId.setText(((Integer) Utils.coalesce(ficheros.getId(), "N/D")).toString());
        holder.txtNombre.setText(Utils.coalesce(ficheros.getNombre(), "N/D"));
        holder.txtpeso.setText(Utils.coalesce(ficheros.getPeso(), "N/D"));

        holder.btnDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadPdf(holder.txtNombre.getContext(),data.get(holder.getAdapterPosition()).getNombre(),".pdf",DIRECTORY_DOWNLOADS,data.get(holder.getAdapterPosition()).getArchivo());
            }
        });





    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FicheroViewHolder extends RecyclerView.ViewHolder {

        TextView txtId;
        TextView txtNombre;
        TextView txtpeso;
        Button btnDescargar;

        public FicheroViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtpeso = itemView.findViewById(R.id.txtPeso);
            btnDescargar = itemView.findViewById(R.id.btnDescargar);

        }
    }

    public void downloadPdf ( Context context, String file, String fileExten, String destinationDirecto,String url)
    {
        DownloadManager downloadManager =(DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirecto,file+fileExten);

        downloadManager.enqueue(request);
    }
}
