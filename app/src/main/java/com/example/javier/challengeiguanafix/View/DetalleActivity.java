package com.example.javier.challengeiguanafix.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javier.challengeiguanafix.Controller.ControllerContacto;
import com.example.javier.challengeiguanafix.Model.Addresses;
import com.example.javier.challengeiguanafix.Model.Contacto;
import com.example.javier.challengeiguanafix.Model.Phones;
import com.example.javier.challengeiguanafix.R;
import com.example.javier.challengeiguanafix.Utils.ResultListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetalleActivity extends AppCompatActivity {

    static final String USERID = "userID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Integer userID = bundle.getInt(USERID);

        ControllerContacto controllerContacto = new ControllerContacto();
        controllerContacto.obtenerContactosApi(userID.toString(), new ResultListener<Contacto>() {
            @Override
            public void finish(Contacto contacto) {

                ImageView imageView = (ImageView) findViewById(R.id.imagen_detalle);

                TextView nombre = (TextView) findViewById(R.id.nombre);
                TextView fechaDeNacimiento = (TextView) findViewById(R.id.fechaDeNacimiento);

                TextView direccionWork = (TextView) findViewById(R.id.direccionWork);
                TextView direccionHome = (TextView) findViewById(R.id.direccionHome);

                TextView telefonoHome = (TextView) findViewById(R.id.telefonoHome);
                TextView telefonoCellphone = (TextView) findViewById(R.id.telefonoCellphone);
                TextView telefonoOffice = (TextView) findViewById(R.id.telefonoOffice);

                Picasso.with(getBaseContext())
                        .load(contacto.getPhoto())
                        .placeholder(R.drawable.ic_person)
                        .error(R.drawable.ic_person)
                        .into(imageView);


                nombre.setText(contacto.getFirst_name() + " " + contacto.getLast_name());
                fechaDeNacimiento.setText(contacto.getBirth_date());

                List<Phones> telefonos = contacto.getPhones();

                for (Phones telefono : telefonos) {
                    if (telefono.getType().toLowerCase().equals("home") && telefono.getNumber() != null){
                        telefonoHome.setText(telefono.getNumber());
                    } else {

                    }
                    if (telefono.getType().toLowerCase().equals("cellphone") && telefono.getNumber() != null){
                        telefonoCellphone.setText(telefono.getNumber());
                    } else {

                    }
                    if(telefono.getType().toLowerCase().equals("office") && telefono.getNumber() != null){
                        telefonoOffice.setText(telefono.getNumber());
                    } else {

                    }
                }

                List<Addresses> addresses = contacto.getAddresses();

                for (Addresses addresses1 : addresses){
                    if (addresses1.getHome() != null){
                        direccionHome.setText(addresses1.getHome());
                    } else{

                    }
                    if (addresses1.getWork() != null){
                        direccionWork.setText(addresses1.getWork());
                    } else {

                    }
                }


            }
        });

    }
}
