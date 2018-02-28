package com.example.javier.challengeiguanafix.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by javier on 27/1/18.
 */

public class ContainerContacto {

    @SerializedName("array")
    private List<Contacto> listaDeContactos;

    public ContainerContacto(List<Contacto> contactos) {
        this.listaDeContactos = contactos;
    }

    public List<Contacto> getContactos() {
        return listaDeContactos;
    }
}
