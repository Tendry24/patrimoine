package school.hei.patrimoine.cas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import school.hei.patrimoine.modele.Patrimoine;

class BakoCasTest {

    @Test
    void patrimoine_de_bako_evolue_correctement() {
        var bakoCas = new BakoCas();
        var patrimoine = bakoCas.get();

        assertEquals(
            ariary(7_375_000),
            patrimoine.getValeurComptable(),
            "La valeur initiale du patrimoine devrait être de 7_375_000 Ar");

        var patrimoineFinAnnee = patrimoine.projectionFuture(LocalDate.of(2025, Month.DECEMBER, 31));
        
        assertEquals(
            ariary(13_111_657),
            patrimoineFinAnnee.getValeurComptable(),
            "La valeur du patrimoine au 31 décembre 2025 devrait être de 13_111_657 Ar");
    }
} 