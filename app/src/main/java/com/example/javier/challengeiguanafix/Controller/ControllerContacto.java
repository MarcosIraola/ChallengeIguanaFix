package com.example.javier.challengeiguanafix.Controller;

import com.example.javier.challengeiguanafix.DAO.DAOcontactos;
import com.example.javier.challengeiguanafix.Model.Contacto;
import com.example.javier.challengeiguanafix.Utils.ResultListener;

import java.util.List;

/**
 * Created by javier on 27/1/18.
 */

public class ControllerContacto {

    public void obtenerContactos (final ResultListener<List<Contacto>> telefonoDeLaView){

        DAOcontactos daOcontactos = new DAOcontactos();
        daOcontactos.obtenerContactos(new ResultListener<List<Contacto>>() {
            @Override
            public void finish(List<Contacto> resultado) {
                telefonoDeLaView.finish(resultado);
            }
        });

    }

    public void obtenerContactosApi (String userID, final ResultListener<Contacto> telefonoDeLaView){

        DAOcontactos daOcontactos = new DAOcontactos();
        daOcontactos.obtenerContactosApi(userID, new ResultListener<Contacto>() {
            @Override
            public void finish(Contacto resultado) {
                telefonoDeLaView.finish(resultado);
            }
        });
    }
}
