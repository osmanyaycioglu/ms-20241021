package org.training.microservice.msorder.integration.models;

import lombok.Data;

@Data
public class PaymentResponse   {
  private String transId = null;
  private String orderId = null;
  private String desc = null;
}
