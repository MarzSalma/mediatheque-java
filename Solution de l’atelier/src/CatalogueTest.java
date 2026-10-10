package src;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {

 @Test
 void empruntNominal() throws DocumentIndisponibleException {
  Livre l = new Livre("Clean Code", 2008, "R. Martin", 464);
  l.emprunter();
  assertFalse(l.estDisponible());
  l.rendre();
  assertTrue(l.estDisponible());
 }

 @Test
 void doubleEmpruntLeveException() throws DocumentIndisponibleException {
  Livre l = new Livre("Refactoring", 1999, "M. Fowler", 448);
  l.emprunter();
  DocumentIndisponibleException e = assertThrows(
          DocumentIndisponibleException.class, () -> l.emprunter());
  assertTrue(e.getMessage().contains("Refactoring"));
 }

 @Test
 void doubleEmpruntDvdLeveException() throws DocumentIndisponibleException {
  DVD d = new DVD("Inception", 2010, 148);
  d.emprunter();
  assertThrows(DocumentIndisponibleException.class, () -> d.emprunter());
 }

 @Test
 void rechercheInfructueuse() {
  Catalogue<Document> c = new Catalogue<>();
  c.ajouter(new Revue("Science", 2026, 412));
  assertFalse(c.rechercherParTitre("Nature").isPresent());
 }

 @Test
 void bibliothecaireChercheParTitre() {
  Catalogue<Document> c = new Catalogue<>();
  c.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
  Bibliothecaire b = new Bibliothecaire("Salma", c);

  assertTrue(b.chercher("clean code").isPresent());   // insensible a la casse
  assertFalse(b.chercher("Inconnu").isPresent());
 }

 @Test
 void empruntManagerScenarios() throws MediathequeException {
  Catalogue<Document> c = new Catalogue<>();
  Livre livre = new Livre("Clean Code", 2008, "R. Martin", 464);
  c.ajouter(livre);
  c.ajouter(new Revue("Science", 2026, 412));
  EmpruntManager m = new EmpruntManager(c);

  m.emprunter("clean code");                       // OK, insensible à la casse
  assertFalse(livre.estDisponible());              // le livre est bien emprunté

  DocumentIndisponibleException dejaEmprunte = assertThrows(
          DocumentIndisponibleException.class, () -> m.emprunter("Clean Code"));
  assertTrue(dejaEmprunte.getMessage().contains("Clean Code"));

  assertThrows(DocumentIndisponibleException.class,
          () -> m.emprunter("Science"));      // revue : non empruntable

  DocumentIntrouvableException absent = assertThrows(
          DocumentIntrouvableException.class, () -> m.emprunter("Inconnu"));
  assertTrue(absent.getMessage().contains("Inconnu"));
 }

 @Test
 void empruntManagerDvd() throws MediathequeException {
  Catalogue<Document> c = new Catalogue<>();
  c.ajouter(new DVD("Inception", 2010, 148));
  EmpruntManager m = new EmpruntManager(c);

  m.emprunter("Inception");                        // 1er emprunt OK
  assertThrows(DocumentIndisponibleException.class,
          () -> m.emprunter("Inception"));    // 2e : déjà emprunté
 }
}