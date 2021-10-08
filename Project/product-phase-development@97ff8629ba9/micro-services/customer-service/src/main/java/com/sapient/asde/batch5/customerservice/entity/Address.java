package com.sapient.asde.batch5.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  @Field(name = "default")
  @JsonProperty("default")
  private AddressFields primary;
  private AddressFields anotherAddress;

}
