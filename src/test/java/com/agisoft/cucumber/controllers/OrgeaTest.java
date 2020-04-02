package com.agisoft.cucumber.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrgeaTest {

    @Test
    public void test() {

        Orgea orgea = new Orgea(4);
        assertEquals(4, orgea.getI());

    }

}