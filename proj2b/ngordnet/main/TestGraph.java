package ngordnet.main;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static com.google.common.truth.Truth.assertThat;

public class TestGraph {
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String MEDIUM_SYNSET_FILE = "data/wordnet/synsets1000-subgraph.txt";
    public static final String MEDIUM_HYPONYM_FILE = "data/wordnet/hyponyms1000-subgraph.txt";
    public static final String BIG_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String BIG_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    @Test
    public void testSynset() {
        Graph dag = new Graph(MEDIUM_SYNSET_FILE, MEDIUM_HYPONYM_FILE);
        dag.printSynset();
        dag.printHyponym();
    }

    @Test
    public void testWordHyponymsSmall1() {
        Graph dag = new Graph(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);

        HashSet<String> hyponyms = dag.hyponyms("change");
        String actual = dag.sortHyponyms(hyponyms).toString();
        String expected = "[alteration, change, demotion, increase, jump, leap, modification, saltation, transition, variation]";
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    public void testWordHyponymsSmall2() {
        Graph dag = new Graph(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);

        HashSet<String> hyponyms = dag.hyponyms("act");
        String actual = dag.sortHyponyms(hyponyms).toString();
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    public void testWordHyponymsBig1() {
        Graph dag = new Graph(BIG_SYNSET_FILE, BIG_HYPONYM_FILE);

        HashSet<String> hyponyms = dag.hyponyms("virus");
        String actual = dag.sortHyponyms(hyponyms).toString();
        String expected = "[Arenaviridae, Bunyaviridae, CMV, Coxsackie_virus, EBV, Ebola_virus, Epstein-Barr_virus, Flaviviridae, HIV, HS1, HS2, HSV-1, HSV-2, HSV-I, HSV-II, HTLV-1, Junin_virus, Lassa_virus, Machupo_virus, Marburg_virus, Reoviridae, Rhabdoviridae, TMV, Togaviridae, WTV, West_Nile_encephalitis_virus, West_Nile_virus, adenovirus, alphavirus, animal_virus, arborvirus, arbovirus, arenavirus, bacteriophage, bunyavirus, coliphage, computer_virus, cosmid, coxsackievirus, cytomegalovirus, echovirus, enterovirus, filovirus, flavivirus, hepadnavirus, hepatitis_A_virus, herpes, herpes_simplex, herpes_simplex_1, herpes_simplex_2, herpes_simplex_virus, herpes_varicella_zoster, herpes_varicella_zoster_virus, herpes_virus, herpes_zoster, herpes_zoster_virus, human_T-cell_leukemia_virus-1, human_immunodeficiency_virus, human_papilloma_virus, lymphocytic_choriomeningitis_virus, lyssavirus, myxoma_virus, myxovirus, onion_yellow-dwarf_virus, orthomyxovirus, papovavirus, parainfluenza_virus, paramyxovirus, parvo, parvovirus, phage, picornavirus, plant_virus, poliovirus, polyoma, polyoma_virus, potato_yellow-dwarf_virus, poxvirus, reovirus, respiratory_syncytial_virus, retrovirus, rhabdovirus, rhinovirus, rotavirus, slow_virus, smallpox_virus, tobacco_mosaic_virus, tumor_virus, typhoid_bacteriophage, varicella_zoster_virus, variola_major, variola_major_virus, variola_minor, variola_minor_virus, variola_virus, vector, vesiculovirus, viroid, virus, virusoid, wound_tumor_virus]";
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    public void testWordHyponymsBig2() {
        Graph dag = new Graph(BIG_SYNSET_FILE, BIG_HYPONYM_FILE);

        HashSet<String> hyponyms = dag.hyponyms("philosophy");
        String actual = dag.sortHyponyms(hyponyms).toString();
        String expected = "[Arianism, Aristotelianism, Athanasian_Creed, Athanasianism, Augsburg_Confession, Behmenism, Boehmenism, British_empiricism, Byzantinism, Cabalism, Caesaropapism, Christology, Comtism, Confucianism, Daoism, Docetism, Episcopalianism, Erastianism, Girondism, Gnosticism, Golden_Rule, Hinayanism, Immaculate_Conception, Immaculate_Conception_of_the_Virgin_Mary, Incarnation, Jansenism, Kabbalism, Mahayanism, Mandaeanism, Mandeanism, Marcionism, Monophysitism, Monothelitism, Nativity, Neoplatonism, Nestorianism, Nicene_Creed, Pelagianism, Platonism, Quakerism, Rosicrucianism, Scholasticism, Stoicism, Taoism, Thomism, Virgin_Birth, Zurvanism, abolitionism, absolutism, aesthetic, aesthetics, aetiology, ahimsa, amoralism, animalism, animism, antiestablishmentarianism, antiestablishmentism, antinomianism, article_of_faith, asceticism, axiology, bioethics, casuistry, chiliasm, church_doctrine, commandment, conceptualism, confession, consubstantiation, contextualism, contract_law, corporation_law, cosmology, creation_science, creationism, credendum, credo, creed, deconstruction, deconstructionism, deism, democracy, descriptivism, determinism, dialectic, dialectical_materialism, divine_right, divine_right_of_kings, doctrine, dogma, dualism, dynamism, ecumenicalism, ecumenicism, ecumenism, egalitarianism, election, empiricism, empiricist_philosophy, endaemonism, environmentalism, epicureanism, epistemology, equalitarianism, establishmentarianism, establishmentism, esthetic, esthetics, ethicism, ethics, etiology, eudemonism, existential_philosophy, existentialism, existentialist_philosophy, expansionism, experimentalism, fatalism, feminism, foreordination, formalism, free_thought, freethinking, functionalism, gospel, gymnosophy, hedonism, hereditarianism, humanism, humanitarianism, idealism, imitation, individualism, instrumentalism, internationalism, intuitionism, irredentism, irridentism, ism, jurisprudence, laissez_faire, law, legal_philosophy, literalism, logic, logical_positivism, logicism, majority_rule, materialism, matrimonial_law, mechanism, mentalism, metaphysics, methodological_analysis, methodology, millenarianism, millenarism, millennium, millenniumism, mimesis, mitsvah, mitzvah, modal_logic, monism, moral_philosophy, multiculturalism, naive_realism, nationalism, nativism, naturalism, neuroethics, nihilism, nominalism, nuclear_deterrence, nullification, one-way_street, ontology, operationalism, original_sin, pacificism, pacifism, passivism, patent_law, peripateticism, phenomenology, philosophical_doctrine, philosophical_system, philosophical_theory, philosophy, physicalism, pluralism, populism, positivism, pragmatism, precept, predestination, predetermination, preordination, prescriptivism, presentism, probabilism, rationalism, real_presence, realism, reformism, reincarnation, reincarnationism, relativism, religious_doctrine, school_of_thought, secessionism, secular_humanism, secularism, semiology, semiotics, sensationalism, sensualism, solipsism, spiritualism, states'_rights, subjectivism, synergism, teaching, teleology, tenet, testament, theanthropism, theological_doctrine, total_depravity, traditionalism, transcendental_philosophy, transcendentalism, transubstantiation, unilateralism, universalism, utilitarianism, vitalism]";
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    public void testMultiWordHyponymsSmall1() {
        Graph dag = new Graph(BIG_SYNSET_FILE, BIG_HYPONYM_FILE);
        String[] inputWords = {"video", "recording"};

        HashSet<String> hyponyms = dag.hyponyms(inputWords);
        String actual = dag.sortHyponyms(hyponyms).toString();
        String expected = "[video, video_recording, videocassette, videotape]";
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    public void testMultiWordHyponymsSmall2() {
        Graph dag = new Graph(BIG_SYNSET_FILE, BIG_HYPONYM_FILE);
        String[] inputWords = {"pastry", "tart"};

        HashSet<String> hyponyms = dag.hyponyms(inputWords);
        String actual = dag.sortHyponyms(hyponyms).toString();
        String expected = "[apple_tart, lobster_tart, quiche, quiche_Lorraine, tart, tartlet]";
        assertThat(actual.equals(expected)).isTrue();
    }

    @Test
    public void testMultiWordHyponymsBig() {
        Graph dag = new Graph(BIG_SYNSET_FILE, BIG_HYPONYM_FILE);
        String[] inputWords = {"drug", "medicine", "remedy"};

        HashSet<String> hyponyms = dag.hyponyms(inputWords);
        String actual = dag.sortHyponyms(hyponyms).toString();
        String expected = "[acoustic, alleviant, alleviator, antidote, application, arnica, atropine, baby_oil, balm, balsam, belladonna, black_lotion, blackwash, calamine_lotion, camphor_ice, carron_oil, catholicon, cerate, chrism, chrisom, collyrium, counterpoison, curative, cure, cure-all, dry_mustard, elixir, elixir_of_life, embrocation, emetic, eye-lotion, eyewash, holy_oil, ipecac, lenitive, liniment, lip_balm, lotion, magic_bullet, menthol, mentholated_salve, mercurial_ointment, nauseant, nostrum, obidoxime_chloride, ointment, palliative, panacea, powdered_mustard, preventative, preventive, prophylactic, remedy, rubbing_alcohol, sacramental_oil, salve, therapeutic, unction, unguent, vomit, vomitive, witch_hazel, wych_hazel, zinc_ointment]";
        assertThat(actual.equals(expected)).isTrue();
    }
}
