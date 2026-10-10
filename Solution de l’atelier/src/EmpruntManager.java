package src;

public class EmpruntManager {
    private final Catalogue<Document> catalogue;   // composition

    public EmpruntManager(Catalogue<Document> catalogue) {
        this.catalogue = catalogue;
    }

    public void emprunter(String titre)
            throws DocumentIntrouvableException, DocumentIndisponibleException {
        // Recherche
        Document d = catalogue.rechercherParTitre(titre)
                .orElseThrow(() -> new DocumentIntrouvableException(titre));
        if (!(d instanceof Empruntable e)) {
            throw new DocumentIndisponibleException(titre);
        }

        //  Emprunt
        e.emprunter();
    }
}