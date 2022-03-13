package fr.keyce.luc_vs_vador;

import java.util.Scanner;

public class Main {

    static int critical = 0;

    public static double round_double(double valeur) {
        double total = (double) Math.round(valeur * 100) / 100;
        return total;
    }

    public static double substract_jet(double value, double jet) {
        double total = value - jet;
        return round_double(total);
    }

    public static boolean attack(double reussite_attaque, double jet, String fighter_name) {
        double total = reussite_attaque - jet;
        boolean reussite = false;
        if (total > 0) {
            reussite = true;
        } else {
            reussite = false;
        }
        if (reussite == false) {
            System.out.println("----> " + fighter_name + " n'a pas pu attaquer ...");
            return false;
        } else {
            System.out.println("----> " + fighter_name + " peut attquer !");
            return true;
        }
    }

    public static boolean esquive(double fighter_esquive, double jet_esquive, String fighter_name) {
        double total = fighter_esquive - jet_esquive;
        boolean reussite = false;
        if (total > 0) {
            reussite = true;
        } else {
            reussite = false;
        }
        if (reussite == false) {
            System.out.println("----> " + fighter_name + " n'a pas pu esquiver ...");
            return false;
        } else {
            System.out.println("----> " + fighter_name + " esquive l'attaque !");
            return true;
        }
    }

    public static boolean parade(double fighter_parade, double jet_parade, String fighter_name) {
        double total = fighter_parade - jet_parade;
        boolean reussite = false;
        if (total > 0) {
            reussite = true;
        } else {
            reussite = false;
        }
        if (reussite == false) {
            System.out.println("----> " + fighter_name + " n'a pas pu parer ...");
            return false;
        } else {
            System.out.println("----> " + fighter_name + " pare l'attaque !");
            return true;
        }
    }

    public static double attack_points(double attack_point, double jet_ap, String fighter_name) {
        double total_ap = 0.00;
        if (jet_ap < 0.5) {
            total_ap = attack_point / 2;
            System.out.println("       ----> points d'attques diminué de 50% pour " + fighter_name + " il a "
                    + round_double(total_ap));
            critical = 0;
        } else if (jet_ap < 0.7) {
            total_ap = attack_point * 0.66;
            System.out.println("       ----> points d'attques diminué de 33% pour " + fighter_name + " il a "
                    + round_double(total_ap));
            critical = 0;
        } else if (jet_ap < 0.9) {
            total_ap = attack_point;
            System.out.println(
                    "       ----> points d'attques normaux pour " + fighter_name + " il a " + round_double(total_ap));
            critical = 0;
        } else if (jet_ap < 0.95) {
            total_ap = attack_point * 1.3;
            System.out.println("       ----> points d'attques augmentés de 30% pour " + fighter_name + " il a "
                    + round_double(total_ap));
            critical = 0;
        } else if (jet_ap < 0.98) {
            total_ap = attack_point * 1.5;
            System.out.println("       ----> CRITICAL ! points d'attques augmentés de 50% pour " + fighter_name
                    + " il a " + round_double(total_ap));
            critical = 1;
        } else if (jet_ap < 1) {
            total_ap = 100;
            System.out.print("             --- FATALITY --- ");
            System.out.println("l'adversaire est hors d'état de nuire");
        }
        return total_ap;
    }

    public static double encaissement(double encaissement_fighter, double jet_encaissement, double pa_adversaire,
            String fighter_name) {
        double total = encaissement_fighter - jet_encaissement;
        double coup = pa_adversaire;
        if (total > 0) {
            System.out.println("              ----> " + fighter_name + " n'a pas encaissé le coup ... et va perdre : "
                    + round_double(coup * (-1)));
            return (coup * (-1));
        } else {
            System.out.println("              ----> " + fighter_name + " encaisse le coup et diminue les dégâts de "
                    + round_double(1 - (coup - total)));
            return (1 - (coup - total));
        }
    }

    public static double fight(double jet_attack, double jet_esquive, double jet_parade, double jet_point_attack,
            double jet_encaissement, String fighter_name, String adversaire_name, double fighter_reussite_attack,
            double adversaire_esquive, double adversaire_parade, double fighter_point_attaque,
            double adversaire_encaissement, double adversaire_pv) {
        System.out.println(fighter_name + " attque !");
        boolean attack = attack(fighter_reussite_attack, jet_attack, fighter_name);
        if (attack == false) { // si l'attaque ne réussie pas
            return 1000;
        } else { // si l'attque réussie
            boolean esquive = esquive(adversaire_esquive, jet_esquive, adversaire_name);
            if (esquive) { // si l'esquive passe
                return 1000;
            } else { // si l'esquive ne passe pas
                boolean parade = parade(adversaire_parade, jet_parade, adversaire_name);
                if (parade) { // si le coup est parré
                    return 1000;
                } else { // si le coup n'est pas paré
                    double coup = attack_points(fighter_point_attaque, jet_point_attack, fighter_name);
                    if (coup == 100) { // si le coup est fatale c'est la fin du combat
                        return 2000;
                    } else { // si le coup n'est pas fatale, l'adversaire encaisse
                        double pv_lost = encaissement(adversaire_encaissement, jet_encaissement, coup, adversaire_name);
                        adversaire_pv = adversaire_pv + pv_lost;
                        return round_double(adversaire_pv);
                    }
                }
            }
        }
    }

    public static double contre_attaque(double jet_attack, double jet_esquive, double jet_parade,
            double jet_point_attack, double jet_encaissement, String fighter_name, String adversaire_name,
            double fighter_reussite_attack, double adversaire_esquive, double adversaire_parade,
            double fighter_point_attaque, double adversaire_encaissement, double adversaire_pv) {
        System.out.println(fighter_name + " attque !");
        boolean attack = attack(fighter_reussite_attack, jet_attack, fighter_name);
        if (attack == false) { // si l'attaque ne réussie pas
            return 1000;
        } else { // si l'attque réussie
            boolean esquive = esquive(adversaire_esquive, jet_esquive, adversaire_name);
            if (esquive) { // si l'esquive passe
                return 1000;
            } else { // si l'esquive ne passe pas
                boolean parade = parade(adversaire_parade, jet_parade, adversaire_name);
                if (parade) { // si le coup est parré
                    return 1000;
                } else { // si le coup n'est pas paré
                    double coup = ca_attack_points(fighter_point_attaque, jet_point_attack, fighter_name);
                    if (coup == 100) { // si le coup est fatale c'est la fin du combat
                        return 2000;
                    } else { // si le coup n'est pas fatale, l'adversaire encaisse
                        double pv_lost = encaissement(adversaire_encaissement, jet_encaissement, coup, adversaire_name);
                        adversaire_pv = adversaire_pv + pv_lost;
                        return round_double(adversaire_pv);
                    }
                }
            }
        }
    }

    public static double ca_attack_points(double attack_point, double jet_ap, String fighter_name) {
        double total_ap = 0.00;
        if (jet_ap < 0.5) {
            total_ap = attack_point / 2;
            System.out.println("       ----> points d'attques diminué de 50% pour " + fighter_name + " il a "
                    + round_double(total_ap));
        } else if (jet_ap < 0.7) {
            total_ap = attack_point * 0.66;
            System.out.println("       ----> points d'attques diminué de 33% pour " + fighter_name + " il a "
                    + round_double(total_ap));
        } else if (jet_ap < 0.9) {
            total_ap = attack_point;
            System.out.println(
                    "       ----> points d'attques normaux pour " + fighter_name + " il a " + round_double(total_ap));
        } else if (jet_ap < 0.95) {
            total_ap = attack_point * 1.3;
            System.out.println("       ----> points d'attques augmentés de 30% pour " + fighter_name + " il a "
                    + round_double(total_ap));
        } else if (jet_ap < 1) {
            total_ap = 100;
            System.out.print("             --- FATALITY --- ");
            System.out.println("l'adversaire est hors d'état de nuire");
        }
        return total_ap;
    }

    public static boolean jedi(double use_the_force_fighter, double jet_use_the_force, String fighter_name) {
        double total = use_the_force_fighter - jet_use_the_force;
        boolean reussite = false;
        if (total > 0) {
            reussite = true;
        } else {
            reussite = false;
        }
        if (reussite) {
            System.out.print(fighter_name + " utilise la force contre son adversaire !!! : ");
            return true;
        } else {
            System.out.println(fighter_name + " n'a pas pu utiliser la force ...");
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner player_choice = new Scanner(System.in);
        Fighter Fighter_1 = new Fighter();
        Fighter Fighter_2 = new Fighter();
        int round = 0;

        // Création de l'instance d'objet Fighter, ici Luc
        Fighter_1.name = "Luc";
        Fighter_1.initiative = 0.80; // 0.70
        Fighter_1.point_vie = 45;
        Fighter_1.esquive = 0.45; // 0.85
        Fighter_1.parade = 0.20; // 0.70
        Fighter_1.encaissement = 0.60;
        Fighter_1.reussite_attaque = 0.90;
        Fighter_1.point_attaque = 5;
        Fighter_1.reussite_contre_attaque = 0.10;
        Fighter_1.point_contre_attaque = 3;
        Fighter_1.use_the_force = 0.2;

        // Création de l'instance d'objet Fighter, ici Vador
        Fighter_2.name = "Vador";
        Fighter_2.initiative = 0.60; // 0.80
        Fighter_2.point_vie = 55;
        Fighter_2.esquive = 0.35; // 0.60
        Fighter_2.parade = 0.60; // 0.70
        Fighter_2.encaissement = 0.85;
        Fighter_2.reussite_attaque = 0.75; // 0.65
        Fighter_2.point_attaque = 4;
        Fighter_2.reussite_contre_attaque = 0.05;
        Fighter_2.point_contre_attaque = 2;
        Fighter_2.use_the_force = 0.1;

        double life_point_f1 = Fighter_1.point_vie;
        double life_point_f2 = Fighter_2.point_vie;

        System.out.println("----------------------- LUC VS VADOR -----------------------");
        System.out.println();
        System.out.print("Si vous voulez commencer le jeu appuyer sur une touche sauf 'q' : ");

        String choice = player_choice.nextLine();
        System.out.println();

        if (!(choice.equals("q"))) {
            while (life_point_f1 > 0 && life_point_f2 > 0 && !(choice.equals("q"))) {

                double jet_initiative_fighter_1 = Math.random();
                double jet_initiative_fighter_2 = Math.random();
                boolean initiative_fighter_1 = false;
                boolean initiative_fighter_2 = false;
                double substract_fighter_1 = substract_jet(Fighter_1.initiative, jet_initiative_fighter_1);
                double substract_fighter_2 = substract_jet(Fighter_2.initiative, jet_initiative_fighter_2);
                boolean first_player = false;
                boolean second_player = false;

                // paramètres pour la méthode fight du fighter 1
                double jet_attack_fighter1 = round_double(Math.random());
                double jet_esquive_fighter2 = round_double(Math.random());
                double jet_parade_fighter2 = round_double(Math.random());
                double jet_point_attack_fighter1 = round_double(Math.random());
                double jet_encaissement_fighter2 = round_double(Math.random());

                // jet de contre attaque pour le fighter 2 suite au coup qui n'a fait aucun
                // dégâts du fighter 1
                double ca_jet_attack_fighter2 = round_double(Math.random());
                double ca_jet_esquive_fighter1 = round_double(Math.random());
                double ca_jet_parade_fighter1 = round_double(Math.random());
                double ca_jet_point_attack_fighter2 = round_double(Math.random());
                double ca_jet_encaissement_fighter1 = round_double(Math.random());

                // paramètres pour la méthode fight du fighter 2
                double jet_attack_fighter2 = round_double(Math.random());
                double jet_esquive_fighter1 = round_double(Math.random());
                double jet_parade_fighter1 = round_double(Math.random());
                double jet_point_attack_fighter2 = round_double(Math.random());
                double jet_encaissement_fighter1 = round_double(Math.random());

                // jet de contre attaque pour le fighter 1 suite au coup qui n'a fait aucun
                // dégâts du fighter 2
                double ca_jet_attack_fighter1 = round_double(Math.random());
                double ca_jet_esquive_fighter2 = round_double(Math.random());
                double ca_jet_parade_fighter2 = round_double(Math.random());
                double ca_jet_point_attack_fighter1 = round_double(Math.random());
                double ca_jet_encaissement_fighter2 = round_double(Math.random());

                boolean use_the_force_f1 = false;
                boolean use_the_force_f2 = false;
                double jet_force_f1 = round_double(Math.random());
                double jet_force_f2 = round_double(Math.random());
                int min = 0;
                int max = 4;
                int hit_force_f1 = (int) (Math.random() * (max - min + 1) + min);
                int hit_force_f2 = (int) (Math.random() * (max - min + 1) + min);

                // Calcul du nombre de round
                round++;
                System.out.println();
                System.out.println(
                        "-------------------------------- Round " + round + " --------------------------------");
                System.out.println();
                System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                System.out.println();

                // Valeur initiative et jet de Fighter_1
                System.out.print(Fighter_1.name + " à une initative de : " + Fighter_1.initiative + " et à un jet de : "
                        + round_double(jet_initiative_fighter_1) + " ---> ");
                if (substract_jet(Fighter_1.initiative, jet_initiative_fighter_1) <= 0) {
                    System.out.println("jet raté ... Vous ne pouvez pas attaquer");
                    initiative_fighter_1 = false;
                } else {
                    System.out.println("jet réussi ! Vous pouvez attquer");
                    initiative_fighter_1 = true;
                }
                System.out.println();

                // Valeur initiative et jet de Fighter_2
                System.out.print(Fighter_2.name + " à une initative de : " + Fighter_2.initiative + " et à un jet de : "
                        + round_double(jet_initiative_fighter_2) + " ---> ");
                if (substract_jet(Fighter_2.initiative, jet_initiative_fighter_2) <= 0) {
                    System.out.println("jet raté ... Vous ne pouvez pas attaquer");
                    initiative_fighter_2 = false;
                } else {
                    System.out.println("jet réussi ! Vous pouvez attquer");
                    initiative_fighter_2 = true;
                }
                System.out.println();

                // Vérifier si les deux combattants on rater leurs jet, si oui prochain round
                if (initiative_fighter_1 == false && initiative_fighter_2 == false) {
                    System.out.println();
                    System.out.println();
                    System.out
                            .print("APPUYER SUR ENTRÉE POUR LANCER LE PROCHAIN ROUND ou 'q' POUR QUITTER LA PARTIE : ");
                    choice = player_choice.nextLine();
                    System.out.println();
                }

                // Déterminer l'ordre avec la calcul de initiative - jet
                if (substract_fighter_1 != substract_fighter_2) {
                    if (substract_fighter_1 > substract_fighter_2) {
                        System.out.println(Fighter_1.name + " attaque en premier car il a une initiative finale de : "
                                + substract_fighter_1 + " > " + substract_fighter_2);
                        first_player = true;
                    } else {
                        System.out.println(Fighter_2.name + " attaque en premier car il a une initiative finale de : "
                                + substract_fighter_2 + " > " + substract_fighter_1);
                    }
                } else {
                    System.out.println(Fighter_1.name + " et " + Fighter_2.name
                            + " on la même valeur d'initiative finale, le round s'achève ici");
                    System.out.println();
                    System.out.println();
                    System.out
                            .print("APPUYER SUR ENTRÉE POUR LANCER LE PROCHAIN ROUND ou 'q' POUR QUITTER LA PARTIE : ");
                    choice = player_choice.nextLine();
                    System.out.println();
                }

                // Le premier joueur attaque
                System.out.println();
                System.out.println("                 ------- PHASE ATTAQUE 1er joueur -------");
                System.out.println();
                if (first_player) {
                    second_player = false; // n'est pas le deuxième joueur
                    System.out.println(Fighter_1.name + " attque en premier !");
                    double riposte_f1 = fight(jet_attack_fighter1, jet_esquive_fighter2, jet_parade_fighter2,
                            jet_point_attack_fighter1, jet_encaissement_fighter2, Fighter_1.name, Fighter_2.name,
                            Fighter_1.reussite_attaque, Fighter_2.esquive, Fighter_2.parade, Fighter_1.point_attaque,
                            Fighter_2.encaissement, life_point_f2);
                    if (critical == 1) {
                        use_the_force_f1 = false;
                    } else {
                        use_the_force_f1 = true;
                    }
                    if (riposte_f1 == 1000) {
                        System.out.println(
                                Fighter_1.name + " n'a pas fait de dégat, " + Fighter_2.name + " contre-attaque !!! ");
                        contre_attaque(ca_jet_attack_fighter2, ca_jet_esquive_fighter1, ca_jet_parade_fighter1,
                                ca_jet_point_attack_fighter2, ca_jet_encaissement_fighter1, Fighter_2.name,
                                Fighter_1.name, Fighter_2.reussite_contre_attaque, Fighter_1.esquive, Fighter_1.parade,
                                Fighter_2.point_contre_attaque, Fighter_1.encaissement, life_point_f1);
                        if (riposte_f1 == 1000) {
                            System.out.println("impossible de contre-attquer");
                        } else if (riposte_f1 == 2000) {
                            life_point_f2 = 0;
                            System.out.println("combat terminé car FATLITY");
                            System.exit(0);
                        } else {
                            System.out.println();
                            System.out.println();
                            life_point_f1 = riposte_f1;
                            System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                            System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                        }
                    } else if (riposte_f1 == 2000) {
                        life_point_f2 = 0;
                        System.out.println("combat terminé car FATLITY");
                        System.exit(0);
                    } else {
                        System.out.println();
                        System.out.println();
                        life_point_f2 = riposte_f1;
                        System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                        System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                    }
                } else {
                    second_player = true; // n'est pas le deuxième joueur
                    System.out.println(Fighter_2.name + " attque en premier !");
                    double riposte_f2 = fight(jet_attack_fighter2, jet_esquive_fighter1, jet_parade_fighter1,
                            jet_point_attack_fighter2, jet_encaissement_fighter1, Fighter_2.name, Fighter_1.name,
                            Fighter_2.reussite_attaque, Fighter_1.esquive, Fighter_1.parade, Fighter_2.point_attaque,
                            Fighter_1.encaissement, life_point_f1);
                    if (critical == 1) {
                        use_the_force_f1 = false;
                    } else {
                        use_the_force_f1 = true;
                    }
                    if (riposte_f2 == 1000) {
                        System.out.println(
                                Fighter_2.name + " n'a pas fait de dégat, " + Fighter_1.name + " contre-attaque !!! ");
                        contre_attaque(ca_jet_attack_fighter1, ca_jet_esquive_fighter2, ca_jet_parade_fighter2,
                                ca_jet_point_attack_fighter1, ca_jet_encaissement_fighter2, Fighter_1.name,
                                Fighter_2.name, Fighter_1.reussite_contre_attaque, Fighter_2.esquive, Fighter_2.parade,
                                Fighter_1.point_contre_attaque, Fighter_2.encaissement, life_point_f2);
                        if (riposte_f2 == 1000) {
                            System.out.println("impossible de contre-attquer");
                        } else if (riposte_f2 == 2000) {
                            life_point_f2 = 0;
                            System.out.println("combat terminé car FATLITY");
                            System.exit(0);
                        } else {
                            life_point_f2 = riposte_f2;
                            System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                            System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                        }
                    } else if (riposte_f2 == 2000) {
                        life_point_f2 = 0;
                        System.out.println("combat terminé car FATLITY");
                        System.exit(0);
                    } else {
                        System.out.println();
                        System.out.println();
                        life_point_f1 = riposte_f2;
                        System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                        System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                    }
                }

                // remise à 0 du critical pour le prochain round
                critical = 0;

                // Le second joueur attaque, vérification de l'initiative
                System.out.println();
                System.out.println("                 ------- PHASE ATTAQUE 2nd joueur -------");
                System.out.println();
                if (second_player) {
                    if (initiative_fighter_1 == false) {
                        System.out.println(Fighter_1.name
                                + " ne peut pas attaquer ce tour car il n'a pas pu prendre l'initiative");
                        if (critical == 1) {
                            use_the_force_f2 = false;
                        } else {
                            use_the_force_f2 = true;
                        }
                    } else {
                        System.out.println(Fighter_1.name + " attque en second !");
                        double riposte_f1 = fight(jet_attack_fighter1, jet_esquive_fighter2, jet_parade_fighter2,
                                jet_point_attack_fighter1, jet_encaissement_fighter2, Fighter_1.name, Fighter_2.name,
                                Fighter_1.reussite_attaque, Fighter_2.esquive, Fighter_2.parade,
                                Fighter_1.point_attaque, Fighter_2.encaissement, life_point_f2);
                        if (critical == 1) {
                            use_the_force_f2 = false;
                        } else {
                            use_the_force_f2 = true;
                        }
                        if (riposte_f1 == 1000) {
                            System.out.println(Fighter_1.name + " n'a pas fait de dégat, " + Fighter_2.name
                                    + " contre-attaque !!! ");
                            contre_attaque(ca_jet_attack_fighter2, ca_jet_esquive_fighter1, ca_jet_parade_fighter1,
                                    ca_jet_point_attack_fighter2, ca_jet_encaissement_fighter1, Fighter_2.name,
                                    Fighter_1.name, Fighter_2.reussite_contre_attaque, Fighter_1.esquive,
                                    Fighter_1.parade, Fighter_2.point_contre_attaque, Fighter_1.encaissement,
                                    life_point_f1);
                            if (riposte_f1 == 1000) {
                                System.out.println("impossible de contre-attquer");
                            } else if (riposte_f1 == 2000) {
                                life_point_f2 = 0;
                                System.out.println("combat terminé car FATLITY");
                                System.exit(0);
                            } else {
                                System.out.println();
                                System.out.println();
                                life_point_f1 = riposte_f1;
                                System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                                System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                            }
                        } else if (riposte_f1 == 2000) {
                            life_point_f2 = 0;
                            System.out.println("combat terminé car FATLITY");
                            System.exit(0);
                        } else {
                            System.out.println();
                            System.out.println();
                            life_point_f2 = riposte_f1;
                            System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                            System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                        }
                    }
                } else {
                    if (initiative_fighter_2 == false) {
                        System.out.println(Fighter_2.name
                                + " ne peut pas attaquer ce tour car il n'a pas pu prendre l'initiative");
                        if (critical == 1) {
                            use_the_force_f2 = false;
                        } else {
                            use_the_force_f2 = true;
                        }
                    } else {
                        System.out.println(Fighter_2.name + " attque en second !");
                        double riposte_f2 = fight(jet_attack_fighter2, jet_esquive_fighter1, jet_parade_fighter1,
                                jet_point_attack_fighter2, jet_encaissement_fighter1, Fighter_2.name, Fighter_1.name,
                                Fighter_2.reussite_attaque, Fighter_1.esquive, Fighter_1.parade,
                                Fighter_2.point_attaque, Fighter_1.encaissement, life_point_f1);
                        if (critical == 1) {
                            use_the_force_f2 = false;
                        } else {
                            use_the_force_f2 = true;
                        }
                        if (riposte_f2 == 1000) {
                            System.out.println(Fighter_2.name + " n'a pas fait de dégat, " + Fighter_1.name
                                    + " contre-attaque !!! ");
                            contre_attaque(ca_jet_attack_fighter1, ca_jet_esquive_fighter2, ca_jet_parade_fighter2,
                                    ca_jet_point_attack_fighter1, ca_jet_encaissement_fighter2, Fighter_1.name,
                                    Fighter_2.name, Fighter_1.reussite_contre_attaque, Fighter_2.esquive,
                                    Fighter_2.parade, Fighter_1.point_contre_attaque, Fighter_2.encaissement,
                                    life_point_f2);
                            if (riposte_f2 == 1000) {
                                System.out.println("impossible de contre-attquer");
                            } else if (riposte_f2 == 2000) {
                                life_point_f2 = 0;
                                System.out.println("combat terminé car FATLITY");
                                System.exit(0);
                            } else {
                                System.out.println();
                                System.out.println();
                                life_point_f2 = riposte_f2;
                                System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                                System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                            }
                        } else if (riposte_f2 == 2000) {
                            life_point_f2 = 0;
                            System.out.println("combat terminé car FATLITY");
                            System.exit(0);
                        } else {
                            System.out.println();
                            System.out.println();
                            life_point_f1 = riposte_f2;
                            System.out.println("point de vie de " + Fighter_1.name + " : " + life_point_f1);
                            System.out.println("point de vie de " + Fighter_2.name + " : " + life_point_f2);
                        }
                    }
                }

                // remise à 0 du critical pour le prochain round
                critical = 0;

                // Utilisation de la force en fin de tours si le si le fighter a subi un
                // critical ou non
                System.out.println();
                System.out.println();
                if (use_the_force_f1 == false) {
                    System.out
                            .println(Fighter_2.name + " ne peut pas utiliser la force car il a subit un critical ...");
                } else {
                    boolean force_f1 = jedi(Fighter_2.use_the_force, jet_force_f2, Fighter_2.name);
                    if (force_f1) {
                        life_point_f1 = life_point_f1 - hit_force_f2;
                        System.out.println(Fighter_2.name + " fait perdre " + hit_force_f2 + " à " + Fighter_1.name);
                    }
                }

                if (use_the_force_f2 == false) {
                    System.out
                            .println(Fighter_1.name + " ne peut pas utiliser la force car il a subit un critical ...");
                } else {
                    boolean force_f2 = jedi(Fighter_1.use_the_force, jet_force_f1, Fighter_1.name);
                    if (force_f2) {
                        life_point_f2 = life_point_f2 - hit_force_f1;
                        System.out.println(Fighter_1.name + " fait perdre " + hit_force_f1 + " à " + Fighter_2.name);
                    }
                }

                // fin de round
                System.out.println();
                System.out.println();
                System.out.print("APPUYER SUR ENTRÉE POUR LANCER LE PROCHAIN ROUND ou 'q' POUR QUITTER LA PARTIE : ");
                choice = player_choice.nextLine();
                System.out.println();

            }

        } else {
            player_choice.close();
            System.out.println();
            System.out.println("--- Partie quité ---");
            System.exit(0);
        }

        if (life_point_f1 < 0) {
            System.out.println(Fighter_2.name + " WINNER ! ");
        } else {
            System.out.println(Fighter_1.name + " WINNER ! ");
        }

    }

}