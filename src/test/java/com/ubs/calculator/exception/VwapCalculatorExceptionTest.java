package com.ubs.calculator.exception;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VwapCalculatorExceptionTest {

    @Test
    public void shouldCreateCalculatorExceptionWithCause() {
        Exception innerException = new Exception("Inner exception");
        VwapCalculatorException exception
                = new VwapCalculatorException("Calculator exception", innerException);

        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("Calculator exception");

        assertThat(exception.getCause()).isEqualTo(innerException);
        assertThat(exception.getCause().getMessage()).isEqualTo("Inner exception");
    }
}