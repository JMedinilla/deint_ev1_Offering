package exam.jmedinilla.offering_jmedinilla;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Medinilla
 * @version 1.0
 *          <p>
 *          Reppository class for the application. It contains
 *          the list the ListActivity is going to show to the
 *          application user
 */
class Repository extends ArrayList<Offer> {
    private static Repository instance;

    static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {
        this.add(new Offer("Silla madera con respaldo", "Carrefour", "05/07/2016", Offer.CategoryValues.TYPE_HOME, Offer.ImportanceValues.IMP_HIGH));
        this.add(new Offer("LG3", "Carrefour", "29/12/2016", Offer.CategoryValues.TYPE_ELECTRONICS, Offer.ImportanceValues.IMP_LOW));
        this.add(new Offer("Moto G 2015", "PCComponentes", "30/03/2016", Offer.CategoryValues.TYPE_ELECTRONICS, Offer.ImportanceValues.IMP_MEDIUM));
        this.add(new Offer("Espejo baño redondo", "Ikea", "12/07/2016", Offer.CategoryValues.TYPE_HOME, Offer.ImportanceValues.IMP_MEDIUM));
        this.add(new Offer("Bicicleta de paseo ciudad", "Decathlon", "13/07/2016", Offer.CategoryValues.TYPE_SPORT, Offer.ImportanceValues.IMP_LOW));
        this.add(new Offer("Geforce GTX970", "Amazon", "09/12/2016", Offer.CategoryValues.TYPE_ELECTRONICS, Offer.ImportanceValues.IMP_HIGH));
        this.add(new Offer("Juego de pesas amateur", "Decathlon", "06/04/2016", Offer.CategoryValues.TYPE_SPORT, Offer.ImportanceValues.IMP_HIGH));
        this.add(new Offer("Muñequera para tenis", "Decathlon", "16/09/2016", Offer.CategoryValues.TYPE_SPORT, Offer.ImportanceValues.IMP_MEDIUM));
        this.add(new Offer("Estantería Medinillen", "Ikea", "23/06/2016", Offer.CategoryValues.TYPE_HOME, Offer.ImportanceValues.IMP_HIGH));
    }

    void addOffer(Offer newOffer) {
        this.add(newOffer);
    }

    List<Offer> getHomeOffers() {
        ArrayList<Offer> tmp = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getOffer_type() == Offer.CategoryValues.TYPE_HOME) {
                tmp.add(this.get(i));
            }
        }
        return tmp;
    }

    List<Offer> getElectronicsOffers() {
        ArrayList<Offer> tmp = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getOffer_type() == Offer.CategoryValues.TYPE_ELECTRONICS) {
                tmp.add(this.get(i));
            }
        }
        return tmp;
    }

    List<Offer> getSportOffers() {
        ArrayList<Offer> tmp = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getOffer_type() == Offer.CategoryValues.TYPE_SPORT) {
                tmp.add(this.get(i));
            }
        }
        return tmp;
    }
}
