package com.example.javier.challengeiguanafix.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javier.challengeiguanafix.Model.Contacto;
import com.example.javier.challengeiguanafix.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javier on 27/1/18.
 */

public class ContactoRecyclerAdapter extends RecyclerView.Adapter {

    private List<Contacto> contactos = new ArrayList<>();
    private NotificadorActivity notificadorActivity;

    public ContactoRecyclerAdapter(List<Contacto> contactos, NotificadorActivity notificadorActivity) {
        this.contactos = contactos;
        this.notificadorActivity = notificadorActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View celda = inflater.inflate(R.layout.celda_recyclerview, parent, false);

        ContactoViewHolder contactoViewHolder = new ContactoViewHolder(celda);

        return contactoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ContactoViewHolder contactoViewHolder = (ContactoViewHolder) holder;

        contactoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notificadorActivity.recibirMensaje(contactos.get(position).getUser_id());

            }
        });

        contactoViewHolder.cargarContacto(contactos.get(position));
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNombre;
        ImageView imageViewThumb;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.celda_nombre);
            imageViewThumb = itemView.findViewById(R.id.celda_foto);
        }

        public void cargarContacto (Contacto contacto) {
            textViewNombre.setText(contacto.getFirst_name() + " " + contacto.getLast_name());
            Picasso.with(itemView.getContext())
                    .load(contacto.getThumb())
                    .into(imageViewThumb);

        }
    }

    public interface NotificadorActivity {
        void recibirMensaje (Integer userID);
    }

}
