package com.epam.tasks.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CreatorTest {

    Creator creator;
    Human adam;
    Human eve;

    @BeforeEach
    void createCreator() {
        creator = new Creator();
        adam = new Man();
        eve = new Woman();
    }


    @Test
    public void shouldReturnTwoHumans() {
        //given

        //when
        Human[] humans = creator.create();
        //then
        assertEquals(2, humans.length);
    }


    @Test
    public void shouldReturnAdamAsFirstHuman() {
        //given
        //when
        Human[] humans = creator.create();
        //then
        assertEquals(adam, humans[0]);
    }


    @Test
    public void shouldReturnWomanAsSecondParameter(){
        //when
        Human[] humans = creator.create();
        //then
        assertEquals(eve, humans[1]);
    }

}