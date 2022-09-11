package com.muhammedtopgul.introduction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author muhammed-topgul
 * @since 11/09/2022 01:44
 */
@DisplayName("Customized display name tests.")
public class DisplayNameTest {
    @Test
    @DisplayName("This is first test in this class.")
    void test001() {
        System.out.println("inside test001()");
    }

    @Test
    @DisplayName("This is second test in this class.")
    void test002() {
        System.out.println("inside test002()");
    }
}
