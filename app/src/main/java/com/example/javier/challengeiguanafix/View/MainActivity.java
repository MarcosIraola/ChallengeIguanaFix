package com.example.javier.challengeiguanafix.View;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.javier.challengeiguanafix.Controller.ControllerContacto;
import com.example.javier.challengeiguanafix.Model.Contacto;
import com.example.javier.challengeiguanafix.R;
import com.example.javier.challengeiguanafix.Utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactoRecyclerAdapter.NotificadorActivity {

    private List<Contacto> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactoRecyclerAdapter contactoRecyclerAdapter;

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {

                List<Contacto> filterList = new ArrayList();

                for (Contacto contacto : list){
                    String text = contacto.getFirst_name().toLowerCase() + contacto.getLast_name().toLowerCase() + " ";
                    if (text.contains(newText.toLowerCase())){
                        filterList.add(contacto);
                    }
                }

                ContactoRecyclerAdapter otroAdapter = new ContactoRecyclerAdapter(filterList, MainActivity.this);
                recyclerView.setAdapter(otroAdapter);


                return true;
            }
        });

        return true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        contactoRecyclerAdapter = new ContactoRecyclerAdapter(list, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(contactoRecyclerAdapter);

        ControllerContacto controllerContacto = new ControllerContacto();
        controllerContacto.obtenerContactos(new ResultListener<List<Contacto>>() {
            @Override
            public void finish(List<Contacto> resultado) {
                list.addAll(resultado);
                contactoRecyclerAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void recibirMensaje(Integer userID) {
        Intent intent = new Intent(this, DetalleActivity.class);
        Bundle bundle = new Bundle();


        bundle.putInt(DetalleActivity.USERID, userID);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
