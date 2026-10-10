package src;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ChargeurCatalogue {

    public static List<String> chargerTitres(Path chemin)
            throws CatalogueCorrompuException {
        try (var lecteur = Files.newBufferedReader(chemin)) {
            return lecteur.lines().toList();
        } catch (IOException e) {
            throw new CatalogueCorrompuException(
                    "Echec du chargement : " + chemin, e);
        }
    }
}