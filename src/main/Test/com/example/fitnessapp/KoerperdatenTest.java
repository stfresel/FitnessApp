package com.example.fitnessapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KoerperdatenTest {

    private Koerperdaten koerperdaten = null;

    @BeforeEach
    public void init(){
        koerperdaten = new Koerperdaten();
    }

    @Test
    public void testTagesUmsatzBerechnen_Mann(){
        koerperdaten.setGroesse(2.00);
        koerperdaten.setGewicht(100.0);
        koerperdaten.setAlter(30);
        koerperdaten.setGeschlecht("männlich");
        koerperdaten.tagesUmsatzBerechnen();
        assertEquals(2233, koerperdaten.getTagesUmsatz().getKcal());
    }

    @Test
    public void testTagesUmsatzBerechnen_Frau(){
        koerperdaten.setGroesse(1.5397);
        koerperdaten.setGewicht(53.009);
        koerperdaten.setAlter(18.5);
        koerperdaten.setGeschlecht("weiblich");
        koerperdaten.tagesUmsatzBerechnen();
        assertEquals(1354, koerperdaten.getTagesUmsatz().getKcal());
    }

    @Test
    public void testTagesUmsatzBerechnen_GanzeZahlen(){
        koerperdaten.setGroesse(2);
        koerperdaten.setGewicht(100);
        koerperdaten.setAlter(40);
        koerperdaten.setGeschlecht("weiblich");
        koerperdaten.tagesUmsatzBerechnen();
        assertEquals(1787, koerperdaten.getTagesUmsatz().getKcal());
    }

    @Test
    public void testTagesUmsatzBerechnen_KommaZahlen(){
        koerperdaten.setGroesse(1.7861);
        koerperdaten.setGewicht(55.2375);
        koerperdaten.setAlter(18.1294);
        koerperdaten.setGeschlecht("weiblich");
        koerperdaten.tagesUmsatzBerechnen();
        assertEquals(1422, koerperdaten.getTagesUmsatz().getKcal());
    }


    @Test
    public void testGetBmi_Double2() {
        koerperdaten.setKoerperdaten(1.7543, 54.257, 20, "weiblich");
        assertEquals(17.63, koerperdaten.getBMI());
    }

    @Test
    public void testGetBmi_Integer() {
        koerperdaten.setKoerperdaten(2, 90, 20, "weiblich");
        assertEquals(22.5, koerperdaten.getBMI());
    }

    @Test
    public void testGetBmi_DoubleInteger() {
        koerperdaten.setKoerperdaten(1.7, 54.2, 20.1, "weiblich");
        assertEquals(18.75, koerperdaten.getBMI());
    }

    @Test
    public void testGetBmi_0() {
        koerperdaten.setKoerperdaten(0, 0, 0, "weiblich");
        assertEquals(0, koerperdaten.getBMI());
    }

}
