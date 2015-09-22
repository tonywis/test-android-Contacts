package tonywis.tests.contacts;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by Tony on 22/09/2015.
 */
public class Contact {

    private String nom = "";
    private String prenom = "";
    private String mail = "";
    private String phone = "";
    private Bitmap img = null;

    public Contact(String _nom, String _prenom, String _mail, String _phone, Bitmap _img) {
        this.nom = _nom;
        this.prenom = _prenom;
        this.mail = _mail;
        this.phone = _phone;
        this.img = _img;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
