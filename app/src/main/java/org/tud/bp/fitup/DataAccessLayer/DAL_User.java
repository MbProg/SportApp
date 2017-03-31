package org.tud.bp.fitup.DataAccessLayer;


import android.content.Context;


import org.tud.bp.fitup.BusinessLayer.DiaryEntry;
import org.tud.bp.fitup.BusinessLayer.FitnessFragebogen;
import org.tud.bp.fitup.BusinessLayer.Fragebogen;
import org.tud.bp.fitup.BusinessLayer.StimmungAbfrageScore;
import org.tud.bp.fitup.BusinessLayer.StimmungsAngabe;
import org.tud.bp.fitup.BusinessLayer.User;
import org.tud.bp.fitup.BusinessLayer.Challenge;
import org.tud.bp.fitup.BusinessLayer.Exercise;
import com.firebase.client.Firebase;

import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * Created by M.Braei, Y.Aziani, Felix, Basti on 31.01.2017.
 */

public class DAL_User {

    static public void InsertStimmung(User user, StimmungsAngabe stimmungsAngabe, Context context) {
        try {


            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context)+ "users/" + user.getName() + 
                    "/Stimmungsabfrage/"
                    + stimmungsAngabe.Date + "/" + DAL_Utilities.GetTimeAsString());

            String V_N = stimmungsAngabe.Vor ? "/V" : "/N";
            if (stimmungsAngabe.Angespannt >= 0) {
                Firebase childAngespannt = ref.child(V_N).child("Angespannt");
                childAngespannt.setValue(stimmungsAngabe.Angespannt);
            }
            if (stimmungsAngabe.Mitteilsam >= 0) {
                Firebase childMitteilsam = ref.child(V_N).child("Mitteilsam");
                childMitteilsam.setValue(stimmungsAngabe.Mitteilsam);
            }
            if (stimmungsAngabe.Muede >= 0) {
                Firebase childMuede = ref.child(V_N).child("Muede");
                childMuede.setValue(stimmungsAngabe.Muede);
            }
            if (stimmungsAngabe.Selbstsicher >= 0) {
                Firebase childSelbstsicher = ref.child(V_N).child("Selbstsicher");
                childSelbstsicher.setValue(stimmungsAngabe.Selbstsicher);
            }
            if (stimmungsAngabe.Tatkraeftig >= 0) {
                Firebase childTatkraeftig = ref.child(V_N).child("Tatkraeftig");
                childTatkraeftig.setValue(stimmungsAngabe.Tatkraeftig);
            }
            if (stimmungsAngabe.Traurig >= 0) {
                Firebase childTraurig = ref.child(V_N).child("Traurig");
                childTraurig.setValue(stimmungsAngabe.Traurig);
            }
            if (stimmungsAngabe.Wuetend >= 0) {
                Firebase childWuetend = ref.child(V_N).child("Wuetend");
                childWuetend.setValue(stimmungsAngabe.Wuetend);
            }
            if (stimmungsAngabe.Zerstreut >= 0) {
                Firebase childZerstreut = ref.child(V_N).child("Zerstreut");
                childZerstreut.setValue(stimmungsAngabe.Zerstreut);
            }
        } catch (Exception ex) {
            String s = ex.getMessage();
        } finally {

        }


    }

    static public void UpdateStimmung(User user, StimmungsAngabe stimmungsAngabe, Context context) {
        try {


            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() + "/Stimmungsabfrage/"
                    + stimmungsAngabe.Date + "/" + stimmungsAngabe.Time + "/");

            String V_N = stimmungsAngabe.Vor ? "/V" : "/N";
            if (stimmungsAngabe.Angespannt >= 0) {
                Firebase childAngespannt = ref.child(V_N).child("Angespannt");
                childAngespannt.setValue(stimmungsAngabe.Angespannt);
            }
            if (stimmungsAngabe.Mitteilsam >= 0) {
                Firebase childMitteilsam = ref.child(V_N).child("Mitteilsam");
                childMitteilsam.setValue(stimmungsAngabe.Mitteilsam);
            }
            if (stimmungsAngabe.Muede >= 0) {
                Firebase childMuede = ref.child(V_N).child("Muede");
                childMuede.setValue(stimmungsAngabe.Muede);
            }
            if (stimmungsAngabe.Selbstsicher >= 0) {
                Firebase childSelbstsicher = ref.child(V_N).child("Selbstsicher");
                childSelbstsicher.setValue(stimmungsAngabe.Selbstsicher);
            }
            if (stimmungsAngabe.Tatkraeftig >= 0) {
                Firebase childTatkraeftig = ref.child(V_N).child("Tatkraeftig");
                childTatkraeftig.setValue(stimmungsAngabe.Tatkraeftig);
            }
            if (stimmungsAngabe.Traurig >= 0) {
                Firebase childTraurig = ref.child(V_N).child("Traurig");
                childTraurig.setValue(stimmungsAngabe.Traurig);
            }
            if (stimmungsAngabe.Wuetend >= 0) {
                Firebase childWuetend = ref.child(V_N).child("Wuetend");
                childWuetend.setValue(stimmungsAngabe.Wuetend);
            }
            if (stimmungsAngabe.Zerstreut >= 0) {
                Firebase childZerstreut = ref.child(V_N).child("Zerstreut");
                childZerstreut.setValue(stimmungsAngabe.Zerstreut);
            }
        } catch (Exception ex) {
            String s = ex.getMessage();
        } finally {

        }


    }

    static public void InsertStimmungScore(User user, StimmungAbfrageScore stimmungAbfrageScore, Context context) {
        try {
            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() +
                    "/StimmungabfrageScore/" + stimmungAbfrageScore.Date + "/" + DAL_Utilities.GetTimeAsString());
            String V_N = stimmungAbfrageScore.Vor ? "/V" : "/N";

            Firebase childscoreangespannt = ref.child(V_N).child("Score Angespannt");
            childscoreangespannt.setValue(stimmungAbfrageScore.AngespanntScore);

            Firebase childscoretraurig = ref.child(V_N).child("Score Traurig");
            childscoretraurig.setValue(stimmungAbfrageScore.TraurigScore);

            Firebase childscoretatkraeftig = ref.child(V_N).child("Score Tatkräftig");
            childscoretatkraeftig.setValue(stimmungAbfrageScore.TatkraeftigScore);

            Firebase childscorezerstreut = ref.child(V_N).child("Score Zerstreut");
            childscorezerstreut.setValue(stimmungAbfrageScore.ZerstreutScore);

            Firebase childscorewuetend = ref.child(V_N).child("Score Wütend");
            childscorewuetend.setValue(stimmungAbfrageScore.WuetendScore);

            Firebase childscoremuede = ref.child(V_N).child("Score Müde");
            childscoremuede.setValue(stimmungAbfrageScore.MuedeScore);

            Firebase childscoreselbstsicher = ref.child(V_N).child("Score Selbstsicher");
            childscoreselbstsicher.setValue(stimmungAbfrageScore.SelbstsicherScore);

            Firebase childscoremitteilsam = ref.child(V_N).child("Score Mitteilsam");
            childscoremitteilsam.setValue(stimmungAbfrageScore.MitteilsamScore);

            Firebase childscorebarometer = ref.child(V_N).child("Score Stimmungsbarometer");
            childscorebarometer.setValue(stimmungAbfrageScore.StimmungsBarometerScore);

            Firebase childscoreenergieindex = ref.child(V_N).child("Energieindex");
            childscoreenergieindex.setValue(stimmungAbfrageScore.EnergieIndexScore);


        } catch (Exception exception) {
            String s = exception.getMessage();
            System.out.println(s);
        } finally {

        }

    }

    static public void UpdateStimmungScore(User user, StimmungAbfrageScore stimmungAbfrageScore, Context context) {
        try {


            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() +
                    "/StimmungabfrageScore/" + stimmungAbfrageScore.Date + "/" + stimmungAbfrageScore.Time + "/");
            String V_N = stimmungAbfrageScore.Vor ? "/V" : "/N";

            Firebase childscoreangespannt = ref.child(V_N).child("Score Angespannt");
            childscoreangespannt.setValue(stimmungAbfrageScore.AngespanntScore);

            Firebase childscoretraurig = ref.child(V_N).child("Score Traurig");
            childscoretraurig.setValue(stimmungAbfrageScore.TraurigScore);

            Firebase childscoretatkraeftig = ref.child(V_N).child("Score Tatkräftig");
            childscoretatkraeftig.setValue(stimmungAbfrageScore.TatkraeftigScore);

            Firebase childscorezerstreut = ref.child(V_N).child("Score Zerstreut");
            childscorezerstreut.setValue(stimmungAbfrageScore.ZerstreutScore);

            Firebase childscorewuetend = ref.child(V_N).child("Score Wütend");
            childscorewuetend.setValue(stimmungAbfrageScore.WuetendScore);

            Firebase childscoremuede = ref.child(V_N).child("Score Müde");
            childscoremuede.setValue(stimmungAbfrageScore.MuedeScore);

            Firebase childscoreselbstsicher = ref.child(V_N).child("Score Selbstsicher");
            childscoreselbstsicher.setValue(stimmungAbfrageScore.SelbstsicherScore);

            Firebase childscoremitteilsam = ref.child(V_N).child("Score Mitteilsam");
            childscoremitteilsam.setValue(stimmungAbfrageScore.MitteilsamScore);

            Firebase childscorebarometer = ref.child(V_N).child("Score Stimmungsbarometer");
            childscorebarometer.setValue(stimmungAbfrageScore.StimmungsBarometerScore);

            Firebase childscoreenergieindex = ref.child(V_N).child("Energieindex");
            childscoreenergieindex.setValue(stimmungAbfrageScore.EnergieIndexScore);

        } catch (Exception ex) {
            String s = ex.getMessage();
        } finally {

        }


    }



    static public void InsertDiaryEntry(User user, DiaryEntry diaryEntry, Context context) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        try {
            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() + "/Diary/");

            Firebase newChildDate = ref.child(sdfDate.format(diaryEntry.getDate()));

            Firebase childTime = newChildDate.child(sdfTime.format(diaryEntry.getDate()).toString());

            Firebase childPonts = childTime.child("totalPoints");
            childPonts.setValue(diaryEntry.getTotalPoints());


            int i = 0;
            for (Exercise ex : diaryEntry.getExerciseList()) {
                // 1. Ebene
                Firebase exerciseChild = childTime.child("exercise " + String.valueOf(i) + " :");

                // 2. Ebene
                Firebase categoryChild = exerciseChild.child("category");
                categoryChild.setValue(ex.getCategory());
                Firebase childExercise = exerciseChild.child("name");
                childExercise.setValue(ex.getName());
                Firebase childMinutes = exerciseChild.child("timeMinutes");
                childMinutes.setValue(ex.getTimeMunites());
                Firebase childHours = exerciseChild.child("timeHours");
                childHours.setValue(ex.getTimeHours());

                i++;
            }

        } catch (Exception ex) {
            String s = ex.getMessage();
        } finally {

        }

    }

    /**
     * Speichert die Scorings und Indexe der gewählten Listviews des Fitnessfragbogens in der Datenbank.
     *
     * @param user
     * @param finessfragebogen
     */
    static public void InsertFitnessFragebogen(User user, FitnessFragebogen finessfragebogen, Context context) {
        try {
            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() + "/FitnessFragebogen/"
                    + finessfragebogen.Date);


            if (finessfragebogen.vom_Stuhl_aufstehen >= 0) {
                Firebase childstuhlaufstehen = ref.child("vom_Stuhl_aufstehen");
                childstuhlaufstehen.setValue(finessfragebogen.vom_Stuhl_aufstehen);
            }

            if (finessfragebogen.Einkaufskorb_tragen >= 0) {
                Firebase childeinkaufskorb = ref.child("Einkaufskorb_tragen");
                childeinkaufskorb.setValue(finessfragebogen.Einkaufskorb_tragen);
            }

            if (finessfragebogen.Kiste_tragen >= 0) {
                Firebase childkistetragen = ref.child("Kiste_tragen");
                childkistetragen.setValue(finessfragebogen.Kiste_tragen);
            }

            if (finessfragebogen.Situp >= 0) {
                Firebase childsitup = ref.child("Situp");
                childsitup.setValue(finessfragebogen.Situp);
            }

            if (finessfragebogen.Koffer_hoch_heben >= 0) {
                Firebase childkofferheben = ref.child("Koffer_hoch_heben");
                childkofferheben.setValue(finessfragebogen.Koffer_hoch_heben);
            }

            if (finessfragebogen.Koffer_tragen >= 0) {
                Firebase childkoffertragen = ref.child("Koffer_tragen");
                childkoffertragen.setValue(finessfragebogen.Koffer_tragen);
            }

            if (finessfragebogen.Hantel_stemmen >= 0) {
                Firebase childhantelstemmen = ref.child("Hantel_stemmen");
                childhantelstemmen.setValue(finessfragebogen.Hantel_stemmen);
            }

            if (finessfragebogen.flott_gehen >= 0) {
                Firebase childflottgehen = ref.child("flott_gehen");
                childflottgehen.setValue(finessfragebogen.flott_gehen);
            }

            if (finessfragebogen.Treppen_gehen >= 0) {
                Firebase childtreppengehen = ref.child("Treppen_gehen");
                childtreppengehen.setValue(finessfragebogen.Treppen_gehen);
            }

            if (finessfragebogen.Zwei_km_gehen >= 0) {
                Firebase childzweikmgehen = ref.child("Zwei_km_gehen");
                childzweikmgehen.setValue(finessfragebogen.Zwei_km_gehen);
            }

            if (finessfragebogen.Ein_km_joggen >= 0) {
                Firebase childeinkmjoggen = ref.child("Ein_km_joggen");
                childeinkmjoggen.setValue(finessfragebogen.Ein_km_joggen);
            }

            if (finessfragebogen.Dreißig_min_joggen >= 0) {
                Firebase childdreißigminjoggen = ref.child("Dreißig_min_joggen");
                childdreißigminjoggen.setValue(finessfragebogen.Dreißig_min_joggen);
            }

            if (finessfragebogen.Sechzig_min_joggen >= 0) {
                Firebase childsechzigminjoggen = ref.child("Sechzig_min_joggen");
                childsechzigminjoggen.setValue(finessfragebogen.Sechzig_min_joggen);
            }

            if (finessfragebogen.Marathon >= 0) {
                Firebase childmarathon = ref.child("Marathon");
                childmarathon.setValue(finessfragebogen.Marathon);
            }

            if (finessfragebogen.Socken_anziehen >= 0) {
                Firebase childanziehen = ref.child("Socken_anziehen");
                childanziehen.setValue(finessfragebogen.Socken_anziehen);
            }

            if (finessfragebogen.Boden_im_Sitzen_beruehren >= 0) {
                Firebase childsitzendboden = ref.child("Boden_im_Sitzen_beruehren");
                childsitzendboden.setValue(finessfragebogen.Boden_im_Sitzen_beruehren);
            }

            if (finessfragebogen.Schuhe_binden >= 0) {
                Firebase childschuhebinden = ref.child("Schuhe_binden");
                childschuhebinden.setValue(finessfragebogen.Schuhe_binden);
            }

            if (finessfragebogen.Ruecken_beruehren >= 0) {
                Firebase childrueckenberuehren = ref.child("Ruecken_beruehren");
                childrueckenberuehren.setValue(finessfragebogen.Ruecken_beruehren);
            }

            if (finessfragebogen.Im_Stehen_Boden_beruehren >= 0) {
                Firebase childstehendboden = ref.child("Im_Stehen_Boden_beruehren");
                childstehendboden.setValue(finessfragebogen.Im_Stehen_Boden_beruehren);
            }

            if (finessfragebogen.Mit_Kopf_das_Knie_beruehren >= 0) {
                Firebase childkopfknie = ref.child("Mit_Kopf_das_Knie_beruehren");
                childkopfknie.setValue(finessfragebogen.Mit_Kopf_das_Knie_beruehren);
            }

            if (finessfragebogen.Bruecke >= 0) {
                Firebase childbruecke = ref.child("Bruecke");
                childbruecke.setValue(finessfragebogen.Bruecke);
            }

            if (finessfragebogen.Treppe_runter_gehen >= 0) {
                Firebase childtrepperunter = ref.child("Treppe_runter_gehen");
                childtrepperunter.setValue(finessfragebogen.Treppe_runter_gehen);
            }

            if (finessfragebogen.Einbeinstand >= 0) {
                Firebase childeinbeinstand = ref.child("Einbeinstand");
                childeinbeinstand.setValue(finessfragebogen.Einbeinstand);
            }

            if (finessfragebogen.Purzelbaum >= 0) {
                Firebase childpurzelbaum = ref.child("Purzelbaum");
                childpurzelbaum.setValue(finessfragebogen.Purzelbaum);
            }

            if (finessfragebogen.Ball_prellen >= 0) {
                Firebase childballprellen = ref.child("Ball_prellen");
                childballprellen.setValue(finessfragebogen.Ball_prellen);
            }

            if (finessfragebogen.Zaunsprung >= 0) {
                Firebase childzaunsprung = ref.child("Zaunsprung");
                childzaunsprung.setValue(finessfragebogen.Zaunsprung);
            }

            if (finessfragebogen.Kurve_fahren_ohne_Hand >= 0) {
                Firebase childkurveohnehand = ref.child("Kurve_fahren_ohne_Hand");
                childkurveohnehand.setValue(finessfragebogen.Kurve_fahren_ohne_Hand);
            }

            if (finessfragebogen.Rad_schlagen >= 0) {
                Firebase childradschlagen = ref.child("Rad_schlagen");
                childradschlagen.setValue(finessfragebogen.Rad_schlagen);
            }


            Firebase childscorekraft = ref.child("Score_Kraft");
            childscorekraft.setValue(finessfragebogen.Score_Kraft);

            Firebase childscoreausdauer = ref.child("Score_Ausdauer");
            childscoreausdauer.setValue(finessfragebogen.Score_Ausdauer);

            Firebase childscorekoordination = ref.child("Score_Koordination");
            childscorekoordination.setValue(finessfragebogen.Score_Koordination);

            Firebase childscorebewglichkeit = ref.child("Score_Beweglichkeit");
            childscorebewglichkeit.setValue(finessfragebogen.Score_Beweglichkeit);

            Firebase childscoregesamt = ref.child("Score_Gesamt");
            childscoregesamt.setValue(finessfragebogen.Score_Gesamt);

        } catch (Exception exception) {
            String s = exception.getMessage();
            System.out.println(s);
        } finally {

        }

    }

    /**
     * Updatet die Daten aus fitnessfragebogen
     *
     * @param user
     * @param finessfragebogen
     */
    static public void UpdateFitnessFragebogen(User user, FitnessFragebogen finessfragebogen, Context context) {
        try {
            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() + "/FitnessFragebogen/"
                    + finessfragebogen.Date);


            if (finessfragebogen.vom_Stuhl_aufstehen >= 0) {
                Firebase childstuhlaufstehen = ref.child("vom_Stuhl_aufstehen");
                childstuhlaufstehen.setValue(finessfragebogen.vom_Stuhl_aufstehen);
            }

            if (finessfragebogen.Einkaufskorb_tragen >= 0) {
                Firebase childeinkaufskorb = ref.child("Einkaufskorb_tragen");
                childeinkaufskorb.setValue(finessfragebogen.Einkaufskorb_tragen);
            }

            if (finessfragebogen.Kiste_tragen >= 0) {
                Firebase childkistetragen = ref.child("Kiste_tragen");
                childkistetragen.setValue(finessfragebogen.Kiste_tragen);
            }

            if (finessfragebogen.Situp >= 0) {
                Firebase childsitup = ref.child("Situp");
                childsitup.setValue(finessfragebogen.Situp);
            }

            if (finessfragebogen.Koffer_hoch_heben >= 0) {
                Firebase childkofferheben = ref.child("Koffer_hoch_heben");
                childkofferheben.setValue(finessfragebogen.Koffer_hoch_heben);
            }

            if (finessfragebogen.Koffer_tragen >= 0) {
                Firebase childkoffertragen = ref.child("Koffer_tragen");
                childkoffertragen.setValue(finessfragebogen.Koffer_tragen);
            }

            if (finessfragebogen.Hantel_stemmen >= 0) {
                Firebase childhantelstemmen = ref.child("Hantel_stemmen");
                childhantelstemmen.setValue(finessfragebogen.Hantel_stemmen);
            }

            if (finessfragebogen.flott_gehen >= 0) {
                Firebase childflottgehen = ref.child("flott_gehen");
                childflottgehen.setValue(finessfragebogen.flott_gehen);
            }

            if (finessfragebogen.Treppen_gehen >= 0) {
                Firebase childtreppengehen = ref.child("Treppen_gehen");
                childtreppengehen.setValue(finessfragebogen.Treppen_gehen);
            }

            if (finessfragebogen.Zwei_km_gehen >= 0) {
                Firebase childzweikmgehen = ref.child("Zwei_km_gehen");
                childzweikmgehen.setValue(finessfragebogen.Zwei_km_gehen);
            }

            if (finessfragebogen.Ein_km_joggen >= 0) {
                Firebase childeinkmjoggen = ref.child("Ein_km_joggen");
                childeinkmjoggen.setValue(finessfragebogen.Ein_km_joggen);
            }

            if (finessfragebogen.Dreißig_min_joggen >= 0) {
                Firebase childdreißigminjoggen = ref.child("Dreißig_min_joggen");
                childdreißigminjoggen.setValue(finessfragebogen.Dreißig_min_joggen);
            }

            if (finessfragebogen.Sechzig_min_joggen >= 0) {
                Firebase childsechzigminjoggen = ref.child("Sechzig_min_joggen");
                childsechzigminjoggen.setValue(finessfragebogen.Sechzig_min_joggen);
            }

            if (finessfragebogen.Marathon >= 0) {
                Firebase childmarathon = ref.child("Marathon");
                childmarathon.setValue(finessfragebogen.Marathon);
            }

            if (finessfragebogen.Socken_anziehen >= 0) {
                Firebase childanziehen = ref.child("Socken_anziehen");
                childanziehen.setValue(finessfragebogen.Socken_anziehen);
            }

            if (finessfragebogen.Boden_im_Sitzen_beruehren >= 0) {
                Firebase childsitzendboden = ref.child("Boden_im_Sitzen_beruehren");
                childsitzendboden.setValue(finessfragebogen.Boden_im_Sitzen_beruehren);
            }

            if (finessfragebogen.Schuhe_binden >= 0) {
                Firebase childschuhebinden = ref.child("Schuhe_binden");
                childschuhebinden.setValue(finessfragebogen.Schuhe_binden);
            }

            if (finessfragebogen.Ruecken_beruehren >= 0) {
                Firebase childrueckenberuehren = ref.child("Ruecken_beruehren");
                childrueckenberuehren.setValue(finessfragebogen.Ruecken_beruehren);
            }

            if (finessfragebogen.Im_Stehen_Boden_beruehren >= 0) {
                Firebase childstehendboden = ref.child("Im_Stehen_Boden_beruehren");
                childstehendboden.setValue(finessfragebogen.Im_Stehen_Boden_beruehren);
            }

            if (finessfragebogen.Mit_Kopf_das_Knie_beruehren >= 0) {
                Firebase childkopfknie = ref.child("Mit_Kopf_das_Knie_beruehren");
                childkopfknie.setValue(finessfragebogen.Mit_Kopf_das_Knie_beruehren);
            }

            if (finessfragebogen.Bruecke >= 0) {
                Firebase childbruecke = ref.child("Bruecke");
                childbruecke.setValue(finessfragebogen.Bruecke);
            }

            if (finessfragebogen.Treppe_runter_gehen >= 0) {
                Firebase childtrepperunter = ref.child("Treppe_runter_gehen");
                childtrepperunter.setValue(finessfragebogen.Treppe_runter_gehen);
            }

            if (finessfragebogen.Einbeinstand >= 0) {
                Firebase childeinbeinstand = ref.child("Einbeinstand");
                childeinbeinstand.setValue(finessfragebogen.Einbeinstand);
            }

            if (finessfragebogen.Purzelbaum >= 0) {
                Firebase childpurzelbaum = ref.child("Purzelbaum");
                childpurzelbaum.setValue(finessfragebogen.Purzelbaum);
            }

            if (finessfragebogen.Ball_prellen >= 0) {
                Firebase childballprellen = ref.child("Ball_prellen");
                childballprellen.setValue(finessfragebogen.Ball_prellen);
            }

            if (finessfragebogen.Zaunsprung >= 0) {
                Firebase childzaunsprung = ref.child("Zaunsprung");
                childzaunsprung.setValue(finessfragebogen.Zaunsprung);
            }

            if (finessfragebogen.Kurve_fahren_ohne_Hand >= 0) {
                Firebase childkurveohnehand = ref.child("Kurve_fahren_ohne_Hand");
                childkurveohnehand.setValue(finessfragebogen.Kurve_fahren_ohne_Hand);
            }

            if (finessfragebogen.Rad_schlagen >= 0) {
                Firebase childradschlagen = ref.child("Rad_schlagen");
                childradschlagen.setValue(finessfragebogen.Rad_schlagen);
            }


            Firebase childscorekraft = ref.child("Score_Kraft");
            childscorekraft.setValue(finessfragebogen.Score_Kraft);

            Firebase childscoreausdauer = ref.child("Score_Ausdauer");
            childscoreausdauer.setValue(finessfragebogen.Score_Ausdauer);

            Firebase childscorekoordination = ref.child("Score_Koordination");
            childscorekoordination.setValue(finessfragebogen.Score_Koordination);

            Firebase childscorebewglichkeit = ref.child("Score_Beweglichkeit");
            childscorebewglichkeit.setValue(finessfragebogen.Score_Beweglichkeit);

            Firebase childscoregesamt = ref.child("Score_Gesamt");
            childscoregesamt.setValue(finessfragebogen.Score_Gesamt);

        } catch (Exception exception) {
            String s = exception.getMessage();
            System.out.println(s);
        } finally {

        }

    }

    /**
     * Speichert Antworten und Scoring des BSA Fragebogens.
     *
     * @param user
     * @param fragebogen
     */
    static public void InsertFragebogen(User user, Fragebogen fragebogen, Context context) {
        try {
            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() + "/BSAFragebogen/" +
                    fragebogen.Date);

            if (fragebogen.Berufstaetig >= 0 && fragebogen.Berufstaetig<=1) {
                Firebase childberufstätig = ref.child("Berufstaetig");
                childberufstätig.setValue(fragebogen.Berufstaetig);
            }

            if (fragebogen.Berufstaetig == 0 && fragebogen.sitzende_Taetigkeiten >= 0) {
                Firebase childsitzendetätigkeiten = ref.child("sitzende_Taetigkeiten");
                childsitzendetätigkeiten.setValue(fragebogen.sitzende_Taetigkeiten);
            }

            if (fragebogen.Berufstaetig == 0 && fragebogen.maeßige_Bewegung >= 0) {
                Firebase childmäßigebewegung = ref.child("maeßige_Bewegung");
                childmäßigebewegung.setValue(fragebogen.maeßige_Bewegung);
            }

            if (fragebogen.Berufstaetig == 0 && fragebogen.intensive_Bewegung >= 0) {
                Firebase childintensivebewegung = ref.child("intensive_Bewegung");
                childintensivebewegung.setValue(fragebogen.intensive_Bewegung);
            }

            if (fragebogen.sportlich_aktiv >= 0) {
                Firebase childsportlichaktiv = ref.child("sportlich_aktiv");
                childsportlichaktiv.setValue(fragebogen.sportlich_aktiv);
            }

            if (fragebogen.Zu_Fuß_zur_Arbeit > 0) {
                Firebase childzufußzurarbeit = ref.child("Zu_Fuß_zur_Arbeit");
                childzufußzurarbeit.setValue(fragebogen.Zu_Fuß_zur_Arbeit);
            }

            if (fragebogen.Zu_Fuß_zur_Arbeit_Tag > 0) {
                Firebase childzufußzurarbeittag = ref.child("Zu_Fuß_zur_Arbeit_Tag");
                childzufußzurarbeittag.setValue(fragebogen.Zu_Fuß_zur_Arbeit_Tag);
            }
            if (fragebogen.Zu_Fuß_zur_Arbeit_Minuten > 0) {
                Firebase childzufußzurarbeitminuten = ref.child("Zu_Fuß_zur_Arbeit_Minuten");
                childzufußzurarbeitminuten.setValue(fragebogen.Zu_Fuß_zur_Arbeit_Minuten);
            }

            if (fragebogen.Zu_Fuß_einkaufen > 0) {
                Firebase childzufußeinkaufen = ref.child("Zu_Fuß_einkaufen");
                childzufußeinkaufen.setValue(fragebogen.Zu_Fuß_einkaufen);
            }
            if (fragebogen.Zu_Fuß_einkaufen_Tag > 0) {
                Firebase childzufußeinkaufen = ref.child("Zu_Fuß_einkaufen_Tag");
                childzufußeinkaufen.setValue(fragebogen.Zu_Fuß_einkaufen_Tag);
            }
            if (fragebogen.Zu_Fuß_einkaufen_Minuten > 0) {
                Firebase childzufußeinkaufen = ref.child("Zu_Fuß_einkaufen_Minuten");
                childzufußeinkaufen.setValue(fragebogen.Zu_Fuß_einkaufen_Minuten);
            }

            if (fragebogen.Rad_zur_Arbeit > 0) {
                Firebase childradzurarbeit = ref.child("Rad_zur_Arbeit");
                childradzurarbeit.setValue(fragebogen.Rad_zur_Arbeit);
            }
            if (fragebogen.Rad_zur_Arbeit_Tag > 0) {
                Firebase childradzurarbeit = ref.child("Rad_zur_Arbeit_Tag");
                childradzurarbeit.setValue(fragebogen.Rad_zur_Arbeit_Tag);
            }
            if (fragebogen.Rad_zur_Arbeit_Minuten > 0) {
                Firebase childradzurarbeit = ref.child("Rad_zur_Arbeit_Minuten");
                childradzurarbeit.setValue(fragebogen.Rad_zur_Arbeit_Minuten);
            }

            if (fragebogen.Radfahren > 0) {
                Firebase childradfahren = ref.child("Radfahren");
                childradfahren.setValue(fragebogen.Radfahren);
            }
            if (fragebogen.Radfahren_Tag > 0) {
                Firebase childradfahren = ref.child("Radfahren_Tag");
                childradfahren.setValue(fragebogen.Radfahren_Tag);
            }
            if (fragebogen.Radfahren_Minuten > 0) {
                Firebase childradfahren = ref.child("Radfahren_Minuten");
                childradfahren.setValue(fragebogen.Radfahren_Minuten);
            }

            if (fragebogen.Spazieren > 0) {
                Firebase childspazieren = ref.child("Spazieren");
                childspazieren.setValue(fragebogen.Spazieren);
            }
            if (fragebogen.Spazieren_Tag > 0) {
                Firebase childspazieren = ref.child("Spazieren_Tag");
                childspazieren.setValue(fragebogen.Spazieren_Tag);
            }
            if (fragebogen.Spazieren_Minuten > 0) {
                Firebase childspazieren = ref.child("Spazieren_Minuten");
                childspazieren.setValue(fragebogen.Spazieren_Minuten);
            }

            if (fragebogen.Gartenarbeit > 0) {
                Firebase childgartenarbeit = ref.child("Gartenarbeit");
                childgartenarbeit.setValue(fragebogen.Gartenarbeit);
            }
            if (fragebogen.Gartenarbeit_Tag > 0) {
                Firebase childgartenarbeit = ref.child("Gartenarbeit_Tag");
                childgartenarbeit.setValue(fragebogen.Gartenarbeit_Tag);
            }
            if (fragebogen.Gartenarbeit_Minuten > 0) {
                Firebase childgartenarbeit = ref.child("Gartenarbeit_Minuten");
                childgartenarbeit.setValue(fragebogen.Gartenarbeit_Minuten);
            }

            if (fragebogen.Hausarbeit > 0) {
                Firebase childhausarbeit = ref.child("Hausarbeit");
                childhausarbeit.setValue(fragebogen.Hausarbeit);
            }
            if (fragebogen.Hausarbeit_Tag > 0) {
                Firebase childhausarbeit = ref.child("Hausarbeit_Tag");
                childhausarbeit.setValue(fragebogen.Hausarbeit_Tag);
            }
            if (fragebogen.Hausarbeit_Minuten > 0) {
                Firebase childhausarbeit = ref.child("Hausarbeit_Minuten");
                childhausarbeit.setValue(fragebogen.Hausarbeit_Minuten);
            }

            if (fragebogen.Pflegearbeit > 0) {
                Firebase childpflegearbeit = ref.child("Pflegearbeit");
                childpflegearbeit.setValue(fragebogen.Pflegearbeit);
            }
            if (fragebogen.Pflegearbeit_Tag > 0) {
                Firebase childpflegearbeit = ref.child("Pflegearbeit_Tag");
                childpflegearbeit.setValue(fragebogen.Pflegearbeit_Tag);
            }
            if (fragebogen.Pflegearbeit_Minuten > 0) {
                Firebase childpflegearbeit = ref.child("Pflegearbeit_Minuten");
                childpflegearbeit.setValue(fragebogen.Pflegearbeit_Minuten);
            }

            if (fragebogen.Treppensteigen > 0) {
                Firebase childtreppensteigen = ref.child("Treppensteigen");
                childtreppensteigen.setValue(fragebogen.Treppensteigen);
            }
            if (fragebogen.Treppensteigen_Tag > 0) {
                Firebase childtreppensteigen = ref.child("Treppensteigen_Tag");
                childtreppensteigen.setValue(fragebogen.Treppensteigen_Tag);
            }
            if (fragebogen.Treppensteigen_Stockwerke > 0) {
                Firebase childtreppensteigen = ref.child("Treppensteigen_Stockwerke");
                childtreppensteigen.setValue(fragebogen.Treppensteigen_Stockwerke);
            }

            if (fragebogen.Aktivitaet_A_Name.isEmpty() == false && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktaname = ref.child("Aktivitaet_A_Name");
                childaktaname.setValue(fragebogen.Aktivitaet_A_Name);
            }

            if (fragebogen.Aktivitaet_A_Name.isEmpty() == false && fragebogen.Aktivitaet_A_Zeit > 0 && fragebogen
                    .sportlich_aktiv == 0) {
                Firebase childaktaanzahl = ref.child("Aktivitaet_A_Zeit");
                childaktaanzahl.setValue(fragebogen.Aktivitaet_A_Zeit);
            }

            if (fragebogen.Aktivitaet_A_Name.isEmpty() == false && fragebogen.Aktivitaet_A_Einheiten > 0 &&
                    fragebogen.sportlich_aktiv == 0) {
                Firebase childaktaeinheiten = ref.child("Aktivitaet_A_Einheiten");
                childaktaeinheiten.setValue(fragebogen.Aktivitaet_A_Einheiten);
            }

            if (fragebogen.Aktivitaet_A_Name.isEmpty() == false && fragebogen.Aktivitaet_A_Minuten > 0 && fragebogen
                    .sportlich_aktiv == 0) {
                Firebase childaktaminuten = ref.child("Aktivitaet_A_Minuten");
                childaktaminuten.setValue(fragebogen.Aktivitaet_A_Minuten);
            }

            if (fragebogen.Aktivitaet_B_Name.isEmpty() == false && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktbname = ref.child("Aktivitaet_B_Name");
                childaktbname.setValue(fragebogen.Aktivitaet_B_Name);
            }

            if (fragebogen.Aktivitaet_B_Name.isEmpty() == false && fragebogen.Aktivitaet_B_Zeit > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktbanzahl = ref.child("Aktivitaet_B_Zeit");
                childaktbanzahl.setValue(fragebogen.Aktivitaet_B_Zeit);
            }

            if (fragebogen.Aktivitaet_B_Name.isEmpty() == false && fragebogen.Aktivitaet_B_Einheiten > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktbeinheiten = ref.child("Aktivitaet_B_Einheiten");
                childaktbeinheiten.setValue(fragebogen.Aktivitaet_B_Einheiten);
            }

            if (fragebogen.Aktivitaet_B_Name.isEmpty() == false && fragebogen.Aktivitaet_B_Minuten > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktbminuten = ref.child("Aktivitaet_B_Minuten");
                childaktbminuten.setValue(fragebogen.Aktivitaet_B_Minuten);
            }

            if (fragebogen.Aktivitaet_C_Name.isEmpty() == false && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktcname = ref.child("Aktivitaet_C_Name");
                childaktcname.setValue(fragebogen.Aktivitaet_C_Name);
            }

            if (fragebogen.Aktivitaet_C_Name.isEmpty() == false && fragebogen.Aktivitaet_C_Zeit > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktcanzahl = ref.child("Aktivitaet_C_Zeit");
                childaktcanzahl.setValue(fragebogen.Aktivitaet_C_Zeit);
            }

            if (fragebogen.Aktivitaet_C_Name.isEmpty() == false && fragebogen.Aktivitaet_C_Zeit > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktceinheiten = ref.child("Aktivitaet_C_Einheiten");
                childaktceinheiten.setValue(fragebogen.Aktivitaet_C_Einheiten);
            }

            if (fragebogen.Aktivitaet_C_Name.isEmpty() == false && fragebogen.Aktivitaet_C_Minuten > 0 && fragebogen.sportlich_aktiv ==0) {
                Firebase childaktcanzahl = ref.child("Aktivitaet_C_Minuten");
                childaktcanzahl.setValue(fragebogen.Aktivitaet_C_Minuten);
            }

            Firebase childbewegungscore = ref.child("Bewegungsscoring");
            childbewegungscore.setValue(fragebogen.Bewegungsscoring);

            Firebase childsportscore = ref.child("Sportscoring");
            childsportscore.setValue(fragebogen.Sportscoring);

            Firebase childscore = ref.child("Gesamtscoring");
            childscore.setValue(fragebogen.Gesamtscoring);

        } catch (Exception exception) {
            String s = exception.getMessage();
            System.out.println(s);
        } finally {

        }


    }

    /**
     * Speichert Antworten und Scoring des BSA Fragebogens.
     *
     * @param user
     * @param fragebogen
     */
    public static void UpdateFragebogen(User user, Fragebogen fragebogen, Context context) {
        try {
            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() + "/BSAFragebogen/" +
                    fragebogen.Date);


            if (fragebogen.Berufstaetig >= 0) {
                Firebase childberufstätig = ref.child("Berufstaetig");
                childberufstätig.setValue(fragebogen.Berufstaetig);
            }

            if (fragebogen.Berufstaetig == 0 && fragebogen.sitzende_Taetigkeiten >= 0) {
                Firebase childsitzendetätigkeiten = ref.child("sitzende_Taetigkeiten");
                childsitzendetätigkeiten.setValue(fragebogen.sitzende_Taetigkeiten);
            }

            if (fragebogen.Berufstaetig == 0 && fragebogen.maeßige_Bewegung >= 0) {
                Firebase childmäßigebewegung = ref.child("maeßige_Bewegung");
                childmäßigebewegung.setValue(fragebogen.maeßige_Bewegung);
            }

            if (fragebogen.Berufstaetig == 0 && fragebogen.intensive_Bewegung >= 0) {
                Firebase childintensivebewegung = ref.child("intensive_Bewegung");
                childintensivebewegung.setValue(fragebogen.intensive_Bewegung);
            }

            if (fragebogen.sportlich_aktiv >= 0 && fragebogen.sportlich_aktiv<=1) {
                Firebase childsportlichaktiv = ref.child("sportlich_aktiv");
                childsportlichaktiv.setValue(fragebogen.sportlich_aktiv);
            }

            if (fragebogen.Zu_Fuß_zur_Arbeit > 0) {
                Firebase childzufußzurarbeit = ref.child("Zu_Fuß_zur_Arbeit");
                childzufußzurarbeit.setValue(fragebogen.Zu_Fuß_zur_Arbeit);
            }

            if (fragebogen.Zu_Fuß_zur_Arbeit_Tag > 0) {
                Firebase childzufußzurarbeittag = ref.child("Zu_Fuß_zur_Arbeit_Tag");
                childzufußzurarbeittag.setValue(fragebogen.Zu_Fuß_zur_Arbeit_Tag);
            }
            if (fragebogen.Zu_Fuß_zur_Arbeit_Minuten > 0) {
                Firebase childzufußzurarbeitminuten = ref.child("Zu_Fuß_zur_Arbeit_Minuten");
                childzufußzurarbeitminuten.setValue(fragebogen.Zu_Fuß_zur_Arbeit_Minuten);
            }

            if (fragebogen.Zu_Fuß_einkaufen > 0) {
                Firebase childzufußeinkaufen = ref.child("Zu_Fuß_einkaufen");
                childzufußeinkaufen.setValue(fragebogen.Zu_Fuß_einkaufen);
            }
            if (fragebogen.Zu_Fuß_einkaufen_Tag > 0) {
                Firebase childzufußeinkaufen = ref.child("Zu_Fuß_einkaufen_Tag");
                childzufußeinkaufen.setValue(fragebogen.Zu_Fuß_einkaufen_Tag);
            }
            if (fragebogen.Zu_Fuß_einkaufen_Minuten > 0) {
                Firebase childzufußeinkaufen = ref.child("Zu_Fuß_einkaufen_Minuten");
                childzufußeinkaufen.setValue(fragebogen.Zu_Fuß_einkaufen_Minuten);
            }

            if (fragebogen.Rad_zur_Arbeit > 0) {
                Firebase childradzurarbeit = ref.child("Rad_zur_Arbeit");
                childradzurarbeit.setValue(fragebogen.Rad_zur_Arbeit);
            }
            if (fragebogen.Rad_zur_Arbeit_Tag > 0) {
                Firebase childradzurarbeit = ref.child("Rad_zur_Arbeit_Tag");
                childradzurarbeit.setValue(fragebogen.Rad_zur_Arbeit_Tag);
            }
            if (fragebogen.Rad_zur_Arbeit_Minuten > 0) {
                Firebase childradzurarbeit = ref.child("Rad_zur_Arbeit_Minuten");
                childradzurarbeit.setValue(fragebogen.Rad_zur_Arbeit_Minuten);
            }

            if (fragebogen.Radfahren > 0) {
                Firebase childradfahren = ref.child("Radfahren");
                childradfahren.setValue(fragebogen.Radfahren);
            }
            if (fragebogen.Radfahren_Tag > 0) {
                Firebase childradfahren = ref.child("Radfahren_Tag");
                childradfahren.setValue(fragebogen.Radfahren_Tag);
            }
            if (fragebogen.Radfahren_Minuten > 0) {
                Firebase childradfahren = ref.child("Radfahren_Minuten");
                childradfahren.setValue(fragebogen.Radfahren_Minuten);
            }

            if (fragebogen.Spazieren > 0) {
                Firebase childspazieren = ref.child("Spazieren");
                childspazieren.setValue(fragebogen.Spazieren);
            }
            if (fragebogen.Spazieren_Tag > 0) {
                Firebase childspazieren = ref.child("Spazieren_Tag");
                childspazieren.setValue(fragebogen.Spazieren_Tag);
            }
            if (fragebogen.Spazieren_Minuten > 0) {
                Firebase childspazieren = ref.child("Spazieren_Minuten");
                childspazieren.setValue(fragebogen.Spazieren_Minuten);
            }

            if (fragebogen.Gartenarbeit > 0) {
                Firebase childgartenarbeit = ref.child("Gartenarbeit");
                childgartenarbeit.setValue(fragebogen.Gartenarbeit);
            }
            if (fragebogen.Gartenarbeit_Tag > 0) {
                Firebase childgartenarbeit = ref.child("Gartenarbeit_Tag");
                childgartenarbeit.setValue(fragebogen.Gartenarbeit_Tag);
            }
            if (fragebogen.Gartenarbeit_Minuten > 0) {
                Firebase childgartenarbeit = ref.child("Gartenarbeit_Minuten");
                childgartenarbeit.setValue(fragebogen.Gartenarbeit_Minuten);
            }

            if (fragebogen.Hausarbeit > 0) {
                Firebase childhausarbeit = ref.child("Hausarbeit");
                childhausarbeit.setValue(fragebogen.Hausarbeit);
            }
            if (fragebogen.Hausarbeit_Tag > 0) {
                Firebase childhausarbeit = ref.child("Hausarbeit_Tag");
                childhausarbeit.setValue(fragebogen.Hausarbeit_Tag);
            }
            if (fragebogen.Hausarbeit_Minuten > 0) {
                Firebase childhausarbeit = ref.child("Hausarbeit_Minuten");
                childhausarbeit.setValue(fragebogen.Hausarbeit_Minuten);
            }

            if (fragebogen.Pflegearbeit > 0) {
                Firebase childpflegearbeit = ref.child("Pflegearbeit");
                childpflegearbeit.setValue(fragebogen.Pflegearbeit);
            }
            if (fragebogen.Pflegearbeit_Tag > 0) {
                Firebase childpflegearbeit = ref.child("Pflegearbeit_Tag");
                childpflegearbeit.setValue(fragebogen.Pflegearbeit_Tag);
            }
            if (fragebogen.Pflegearbeit_Minuten > 0) {
                Firebase childpflegearbeit = ref.child("Pflegearbeit_Minuten");
                childpflegearbeit.setValue(fragebogen.Pflegearbeit_Minuten);
            }

            if (fragebogen.Treppensteigen > 0) {
                Firebase childtreppensteigen = ref.child("Treppensteigen");
                childtreppensteigen.setValue(fragebogen.Treppensteigen);
            }
            if (fragebogen.Treppensteigen_Tag > 0) {
                Firebase childtreppensteigen = ref.child("Treppensteigen_Tag");
                childtreppensteigen.setValue(fragebogen.Treppensteigen_Tag);
            }
            if (fragebogen.Treppensteigen_Stockwerke > 0) {
                Firebase childtreppensteigen = ref.child("Treppensteigen_Stockwerke");
                childtreppensteigen.setValue(fragebogen.Treppensteigen_Stockwerke);
            }

            if (fragebogen.Aktivitaet_A_Name.isEmpty() == false && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktaname = ref.child("Aktivitaet_A_Name");
                childaktaname.setValue(fragebogen.Aktivitaet_A_Name);
            }

            if (fragebogen.Aktivitaet_A_Name.isEmpty() == false && fragebogen.Aktivitaet_A_Zeit > 0 && fragebogen
                    .sportlich_aktiv == 0) {
                Firebase childaktaanzahl = ref.child("Aktivitaet_A_Zeit");
                childaktaanzahl.setValue(fragebogen.Aktivitaet_A_Zeit);
            }

            if (fragebogen.Aktivitaet_A_Name.isEmpty() == false && fragebogen.Aktivitaet_A_Einheiten > 0 &&
                    fragebogen.sportlich_aktiv == 0) {
                Firebase childaktaeinheiten = ref.child("Aktivitaet_A_Einheiten");
                childaktaeinheiten.setValue(fragebogen.Aktivitaet_A_Einheiten);
            }

            if (fragebogen.Aktivitaet_A_Name.isEmpty() == false && fragebogen.Aktivitaet_A_Minuten > 0 && fragebogen
                    .sportlich_aktiv == 0) {
                Firebase childaktaminuten = ref.child("Aktivitaet_A_Minuten");
                childaktaminuten.setValue(fragebogen.Aktivitaet_A_Minuten);
            }

            if (fragebogen.Aktivitaet_B_Name.isEmpty() == false && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktbname = ref.child("Aktivitaet_B_Name");
                childaktbname.setValue(fragebogen.Aktivitaet_B_Name);
            }

            if (fragebogen.Aktivitaet_B_Name.isEmpty() == false && fragebogen.Aktivitaet_B_Zeit > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktbanzahl = ref.child("Aktivitaet_B_Zeit");
                childaktbanzahl.setValue(fragebogen.Aktivitaet_B_Zeit);
            }

            if (fragebogen.Aktivitaet_B_Name.isEmpty() == false && fragebogen.Aktivitaet_B_Einheiten > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktbeinheiten = ref.child("Aktivitaet_B_Einheiten");
                childaktbeinheiten.setValue(fragebogen.Aktivitaet_B_Einheiten);
            }

            if (fragebogen.Aktivitaet_B_Name.isEmpty() == false && fragebogen.Aktivitaet_B_Minuten > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktbminuten = ref.child("Aktivitaet_B_Minuten");
                childaktbminuten.setValue(fragebogen.Aktivitaet_B_Minuten);
            }

            if (fragebogen.Aktivitaet_C_Name.isEmpty() == false && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktcname = ref.child("Aktivitaet_C_Name");
                childaktcname.setValue(fragebogen.Aktivitaet_C_Name);
            }

            if (fragebogen.Aktivitaet_C_Name.isEmpty() == false && fragebogen.Aktivitaet_C_Zeit > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktcanzahl = ref.child("Aktivitaet_C_Zeit");
                childaktcanzahl.setValue(fragebogen.Aktivitaet_C_Zeit);
            }

            if (fragebogen.Aktivitaet_C_Name.isEmpty() == false && fragebogen.Aktivitaet_C_Zeit > 0 && fragebogen.sportlich_aktiv == 0) {
                Firebase childaktceinheiten = ref.child("Aktivitaet_C_Einheiten");
                childaktceinheiten.setValue(fragebogen.Aktivitaet_C_Einheiten);
            }

            if (fragebogen.Aktivitaet_C_Name.isEmpty() == false && fragebogen.Aktivitaet_C_Minuten > 0 && fragebogen.sportlich_aktiv ==0) {
                Firebase childaktcanzahl = ref.child("Aktivitaet_C_Minuten");
                childaktcanzahl.setValue(fragebogen.Aktivitaet_C_Minuten);
            }

            Firebase childbewegungscore = ref.child("Bewegungsscoring");
            childbewegungscore.setValue(fragebogen.Bewegungsscoring);

            Firebase childsportscore = ref.child("Sportscoring");
            childsportscore.setValue(fragebogen.Sportscoring);

            Firebase childscore = ref.child("Gesamtscoring");
            childscore.setValue(fragebogen.Gesamtscoring);

        } catch (Exception exception) {
            String s = exception.getMessage();
            System.out.println(s);
        } finally {

        }



    }

    /**
     * update groups of alternating group assignment
     *
     * @param currentActiveGroup the currently active group
     * @param nextActiveGroup    the next group to be active
     * @param alternGroup        the set of groups currently used
     */
    static public void insertAlternGroupUpdate(String currentActiveGroup, String nextActiveGroup, String alternGroup, Context context) {
        try {
            // setting up url for the database
            URL url = new URL(DAL_Utilities.getDatabaseURL(context) + "Administration/assignment/altern/" + alternGroup +
                    "/groups/");
            Firebase root = new Firebase(url.toString());
            // update group values
            root.child(currentActiveGroup).child("groupactive").setValue(false);
            root.child(nextActiveGroup).child("groupactive").setValue(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts challenge to the database
     *
     * @param user      the current user
     * @param challenge the challenge to add
     */
    public static void InsertChallenge(User user, Challenge challenge, Context context) {

        Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() + "/Challenges/");

        //Build a string with start and end date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        StringBuilder sb = new StringBuilder();
        sb.append(sdf.format(challenge.getStartDate())).append("_").
                append(sdf.format(challenge.getEndDate()));

        //add challenge to user challenges
        Firebase name = ref.child(challenge.getName());

        Firebase nameChildStart = name.child("startDate");
        nameChildStart.setValue(sdf.format(challenge.getStartDate()));

        Firebase nameChildEnd = name.child("endDate");
        nameChildEnd.setValue(sdf.format(challenge.getEndDate()));
    }

    /**
     * Removes the challenge in user in the database
     *
     * @param user      user that has to leave the challenge
     * @param challenge to leave
     */
    public static void RemoveChallenge(User user, Challenge challenge, Context context) {

        Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + user.getName() + "/Challenges/");
        ref.child(challenge.getName()).removeValue();
    }


}


