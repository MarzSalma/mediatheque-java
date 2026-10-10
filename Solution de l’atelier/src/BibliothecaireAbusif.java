package src;

public class BibliothecaireAbusif extends Catalogue<Document>{
    private final String nom;
    public BibliothecaireAbusif(String nom) {
        this.nom = nom;
    }
    public void accueillir(){
        System.out.println("Bonjour, je suis" + nom);
    }
}
