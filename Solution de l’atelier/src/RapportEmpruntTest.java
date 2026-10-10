package src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RapportEmpruntTest {

    private EmpruntManager managerAvecLivre() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
        return new EmpruntManager(c);
    }

    @Test
    void ferme_apres_succes() throws MediathequeException {
        EmpruntManager m = managerAvecLivre();
        RapportEmprunt rapport = new RapportEmprunt();

        try (rapport) {
            m.emprunter("Clean Code");
            rapport.enregistrer("Clean Code");
            assertFalse(rapport.estFerme());      // encore ouvert dans le bloc
        }

        assertTrue(rapport.estFerme());           // ferme apres le bloc
        assertEquals(1, rapport.getTitres().size());
    }

    @Test
    void ferme_meme_si_un_emprunt_echoue() throws MediathequeException {
        EmpruntManager m = managerAvecLivre();
        RapportEmprunt rapport = new RapportEmprunt();

        assertThrows(DocumentIndisponibleException.class, () -> {
            try (rapport) {
                m.emprunter("Clean Code");
                rapport.enregistrer("Clean Code");
                m.emprunter("Clean Code");        // 2e emprunt : leve l'exception
                rapport.enregistrer("jamais atteint");
            }
        });

        assertTrue(rapport.estFerme());           // close() a ete appele malgre l'echec
        assertEquals(1, rapport.getTitres().size());   // seul le 1er titre est memorise
    }

    @Test
    void ecriture_refusee_apres_fermeture() {
        RapportEmprunt rapport = new RapportEmprunt();
        rapport.close();
        assertThrows(IllegalStateException.class, () -> rapport.enregistrer("X"));
    }
}