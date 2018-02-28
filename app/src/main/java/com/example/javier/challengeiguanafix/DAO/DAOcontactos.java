package com.example.javier.challengeiguanafix.DAO;

import android.os.AsyncTask;

import com.example.javier.challengeiguanafix.Model.Contacto;
import com.example.javier.challengeiguanafix.Model.ContainerContacto;
import com.example.javier.challengeiguanafix.Utils.DAOException;
import com.example.javier.challengeiguanafix.Utils.HTTPConnectionManager;
import com.example.javier.challengeiguanafix.Utils.ResultListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by javier on 27/1/18.
 */

public class DAOcontactos {


    public void obtenerContactos (ResultListener<List<Contacto>> telefonoDelController){

        TareaAsincronca tareaAsincronca = new TareaAsincronca(telefonoDelController);
        tareaAsincronca.execute("https://private-d0cc1-iguanafixtest.apiary-mock.com/contacts");
    }

    public void obtenerContactosApi (String userID, ResultListener<Contacto> telefonoDelController){

        TareaAsincroncaApi tareaAsincroncaApi = new TareaAsincroncaApi(telefonoDelController);
        tareaAsincroncaApi.execute("https://private-anon-6266c43cec-iguanafixtest.apiary-mock.com/contacts/" + userID);
    }

    public class TareaAsincronca extends AsyncTask<String, Void, List<Contacto>> {

        private ResultListener<List<Contacto>> telefonoDelController;

        public TareaAsincronca(ResultListener<List<Contacto>> telefonoDelController) {
            this.telefonoDelController = telefonoDelController;
        }


        @Override
        protected List<Contacto> doInBackground(String... params) {
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();

            String url = params [0];

            try {
                String json = httpConnectionManager.getRequestString(url);
                Gson gson = new Gson();
                Contacto[] contacts = gson.fromJson(json, Contacto[].class);

                ArrayList<Contacto> contactArrayList = new ArrayList<Contacto>(Arrays.asList(contacts));

                return contactArrayList;

            } catch (DAOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Contacto> contactos) {
            super.onPostExecute(contactos);
            telefonoDelController.finish(contactos);
        }
    }

    public class TareaAsincroncaApi extends AsyncTask<String, Void, Contacto> {

        private ResultListener<Contacto> telefonoDelController;

        public TareaAsincroncaApi(ResultListener<Contacto> telefonoDelController) {
            this.telefonoDelController = telefonoDelController;
        }


        @Override
        protected Contacto doInBackground(String... params) {
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();

            String url = params [0];

            try {
                String json = httpConnectionManager.getRequestString(url);
                Gson gson = new Gson();

                Contacto contacto = gson.fromJson(json, Contacto.class);

                return contacto;

            } catch (DAOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Contacto contacto) {
            super.onPostExecute(contacto);
            telefonoDelController.finish(contacto);
        }
    }

}
