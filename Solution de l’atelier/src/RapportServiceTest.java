package src;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RapportServiceTest {

    @Test
    void genereEtNotifie() throws IOException {
        StringBuilder disque = new StringBuilder();
        List<String> notifs = new ArrayList<>();
        RapportService service = new RapportService(
                docs -> "contenu-teste",              // fake formatter (lambda)
                contenu -> disque.append(contenu),    // fake output
                message -> notifs.add(message));      // fake notifier
        service.generer(List.of());
        assertEquals("contenu-teste", disque.toString());
        assertEquals(1, notifs.size());
    }

    @Test
    void pasDeNotificationSiEcritureEchoue() {
        List<String> notifs = new ArrayList<>();
        RapportService service = new RapportService(
                docs -> "x",
                contenu -> { throw new IOException("disque plein"); },
                message -> notifs.add(message));
        assertThrows(IOException.class, () -> service.generer(List.of()));
        assertTrue(notifs.isEmpty());   // no notification when the write fails
    }
}