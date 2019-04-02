package id.trydev.kosanqu.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;

import java.util.ArrayList;
import java.util.List;

import id.trydev.kosanqu.Model.Kos;
import id.trydev.kosanqu.R;
import id.trydev.kosanqu.Utils.KosanquGlideModule;
import id.trydev.kosanqu.Utils.ItemClickSupport;

public class KosAdapter extends RecyclerView.Adapter<KosAdapter.ViewHolder> {

    private List<Kos> listKos = new ArrayList<>();
    public KosAdapter(List<Kos> listKos){
        this.listKos = listKos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kost, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bindItem(listKos.get(i));
    }

    @Override
    public int getItemCount() {
        return listKos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvKos;
        ImageView ivKos;
        TextView judul, alamat, harga, jenisKos;
        LinearLayout llFasilitas;
        ViewHolder(View itemView) {
            super(itemView);

            cvKos = itemView.findViewById(R.id.cardview_kost);
            ivKos = itemView.findViewById(R.id.iv_preview_kost);
            judul = itemView.findViewById(R.id.tv_judul);
            alamat = itemView.findViewById(R.id.tv_alamat_kos);
            harga = itemView.findViewById(R.id.tv_harga_kos);
            jenisKos = itemView.findViewById(R.id.tv_jenis_penghuni_kos);
            llFasilitas = itemView.findViewById(R.id.ll_fasilitas);
        }

        void bindItem(Kos item){
            judul.setText(item.getJudul());
            alamat.setText(item.getAlamat());
            harga.setText(item.getHarga());
            jenisKos.setText(item.getJenisPenghuni());

            Glide.with(itemView)
                    .asBitmap()
                    .centerCrop()
                    .load(item.getUrl())
                    .thumbnail(0.25f)
                    .into(ivKos);
        }
    }
}
