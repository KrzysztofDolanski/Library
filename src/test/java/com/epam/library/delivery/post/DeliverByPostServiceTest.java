package com.epam.library.delivery.post;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeliverByPostServiceTest {


    @Test
    public void shouldReturnExpectedBooleanDependingOfGivenAddress(){
        //given
        DeliverByPostService deliverByPostService = new DeliverByPostService();
        DeliverByPost deliverByPost = new DeliverByPost();
        deliverByPost.setCity("Gdansk");
        deliverByPost.setStreet_name("WIECKOWSKIEGO");
        deliverByPost.setHome_number(1);
        deliverByPost.setFlat_number(2);
        //when
        boolean result = deliverByPostService.addressAccessibility("Gdansk", "WIECKOWSKIEGO", 1, 1);
        //then
        assertTrue(result);
    }
}