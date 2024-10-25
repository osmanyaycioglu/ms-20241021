package org.training.microservice.msorder.integration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse   {
  private String transId = null;
  private String orderId = null;
  private String desc = null;
}
