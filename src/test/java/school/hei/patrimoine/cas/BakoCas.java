package school.hei.patrimoine.cas;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;
import school.hei.patrimoine.modele.possession.TransfertArgent;

public class BakoCas implements Supplier<Patrimoine> {
    private final LocalDate ajd = LocalDate.of(2025, APRIL, 8);
    private final LocalDate finSimulation = LocalDate.of(2025, DECEMBER, 31);

    private Compte compteBNI() {
        return new Compte("Compte BNI", ajd, ariary(2_000_000));
    }

    private Compte compteBMOI() {
        return new Compte("Compte BMOI", ajd, ariary(625_000));
    }

    private Compte coffreFort() {
        return new Compte("Coffre fort", ajd, ariary(1_750_000));
    }

    private Materiel ordinateur() {
        return new Materiel(
            "Ordinateur portable",
            ajd,
            ajd,
            ariary(3_000_000),
            -0.12);
    }

    private Set<Possession> possessionsBako(Compte compteBNI, Compte compteBMOI, Compte coffreFort, Materiel ordinateur) {
        new FluxArgent(
            "Salaire mensuel",
            compteBNI,
            ajd,
            finSimulation,
            2,
            ariary(2_125_000));

        new TransfertArgent(
            "Épargne mensuelle",
            compteBNI,
            compteBMOI,
            ajd,
            finSimulation,
            3,
            ariary(200_000));

        new FluxArgent(
            "Loyer mensuel",
            compteBNI,
            ajd,
            finSimulation,
            26,
            ariary(-600_000));

        new FluxArgent(
            "Dépenses courantes",
            compteBNI,
            ajd,
            finSimulation,
            1,
            ariary(-700_000));

        return Set.of(compteBNI, compteBMOI, coffreFort, ordinateur);
    }

    @Override
    public Patrimoine get() {
        var bako = new Personne("Bako");
        var compteBNI = compteBNI();
        var compteBMOI = compteBMOI();
        var coffreFort = coffreFort();
        var ordinateur = ordinateur();

        return Patrimoine.of(
            "Patrimoine de Bako",
            MGA,
            ajd,
            bako,
            possessionsBako(compteBNI, compteBMOI, coffreFort, ordinateur));
    }
} 