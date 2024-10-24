package org.training.microservice.msorder.integration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest   {
  private String orderId = null;
  private BigDecimal amount = null;
  private String customerId = null;
}
