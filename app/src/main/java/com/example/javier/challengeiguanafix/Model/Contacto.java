package com.example.javier.challengeiguanafix.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by javier on 27/1/18.
 */

public class Contacto implements Serializable {

    private Integer user_id;
    private String created_at;
    private String birth_date;
    private String first_name;
    private String last_name;
    private List<Phones> phones;
    private String thumb;
    private String photo;
    private List<Addresses> addresses;

    public Contacto(Integer user_id, String created_at, String birth_date, String first_name, String last_name, List<Phones> phones, String thumb, String photo, List<Addresses> addresses) {
        this.user_id = user_id;
        this.created_at = created_at;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phones = phones;
        this.thumb = thumb;
        this.photo = photo;
        this.addresses = addresses;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public List<Phones> getPhones() {
        return phones;
    }

    public String getThumb() {
        return thumb;
    }

    public String getPhoto() {
        return photo;
    }

    public List<Addresses> getAddresses() {
        return addresses;
    }
}
