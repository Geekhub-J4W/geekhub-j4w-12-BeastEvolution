package edu.geekhub.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceTest {
    Service service;

    @BeforeEach
    void setUp() {
        service = new Service(new TxtConvertor());
    }

    @Test
    void test() {
        service.saveSongsOnDrive();
    }
}
