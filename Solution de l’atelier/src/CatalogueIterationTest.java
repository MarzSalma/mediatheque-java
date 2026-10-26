package src;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogueIterationTest {
    @Test void deux_parcours_sont_independants() {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
        catalogue.ajouter(new Revue("Pour la Science", 2024, 12));

        Iterator<Document> a = catalogue.iterator();
        Iterator<Document> b = catalogue.iterator();
        a.next();
      //  a.next();

        assertEquals("Pour la Science", a.next().getTitre());
    }

    @Test void remove_est_interdit() {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));

        Iterator<Document> it = catalogue.iterator();
        it.next();

        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test void catalogue_vide_n_a_pas_de_suivant() {
        assertFalse(new Catalogue<Livre>().iterator().hasNext());
    }
}
