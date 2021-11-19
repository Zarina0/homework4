package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 1000;
    public static int bossDamage = 60;
    public static String bossDefence = "";
    public static int evasionLucky;
    public static int[] heroesHealth = {270, 280, 240, 300, 250,250,235, 280};
    public static int[] heroesDamage = {15, 10, 20, 10, 10,15,5, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Golem", "Lucky","Berserk","Thor", "Medic"};


    public static void  medicHeal(){
        for (int i = 0; i < heroesHealth.length ; i++) {
            if (i == 3){
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0){
                heroesHealth[i] += 50;
                System.out.println("Medic heal " + heroesAttackType[i]);
                break;
            }
        }
    }

    public static void golemTanking(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0){
                heroesHealth[4] -= bossDamage * 1 / 5;
                heroesHealth[i] += bossDamage * 1 / 5;
                System.out.println("Golem get: " + bossDamage);
                break;
            }
        }
    }
    public static void luckyEvasionChance() {
        Random random = new Random();
        boolean eve = random.nextBoolean();
        for (int i = 0; i < heroesHealth.length ; i++){
            if (heroesHealth[5] > 0)
                if (eve){
                    heroesHealth[5] = heroesHealth[5] + 90;
                    System.out.println("lucky dodged damage");
                    break;
                }
        }
    }

    public static void berserkRage(){

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[6] > 0){
                heroesHealth[6] -= bossDamage * 1 / 13;
                heroesDamage[6] += heroesDamage[6] + bossDamage * 1 / 13;
                System.out.println("berserk blocking damage and up damage");
                break;
            }

        }
    }

public static void thorStanning(){
        Random random = new Random();
        boolean stun = random.nextBoolean();
    for (int i = 0; i < heroesHealth.length; i++) {
        if (heroesHealth[7] > 0){
            if(stun){
                bossDamage = bossDamage - bossDamage;
                System.out.println("The boss is stunned");
                break;
            }else{
                bossDamage = 60;
            }
        }
    }

}



    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
            medicHeal();
            golemTanking();
            luckyEvasionChance();
            berserkRage();
            thorStanning();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(
                heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
        chooseBossDefence();
        if (bossHealth > 0) { // РЅР° РІСЃСЏРєРёР№ СЃР»СѓС‡Р°Р№
            bossHits();
        }
        heroesHit();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(11); //0,1,2,3,4,5,6,7,8,9
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }


        }

    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }

        }


    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND ______________");
        System.out.println("Boss health: " + bossHealth
                + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i]
                    + " (" + heroesDamage[i] + ")");
        }
        System.out.println("____________________");

















    }
}
