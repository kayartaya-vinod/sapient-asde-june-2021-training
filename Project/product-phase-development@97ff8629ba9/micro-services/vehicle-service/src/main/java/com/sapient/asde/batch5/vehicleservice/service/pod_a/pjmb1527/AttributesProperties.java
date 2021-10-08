package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1527;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "vehicle")
public class AttributesProperties {
  private Map<String, List<String>> attributes;
  
}
