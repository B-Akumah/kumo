package edu.csis.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bakumah
 */
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    String streetLine1;
    String streetLine2;
    String city;
    String state;
    String zipCode;
    String nation;
}
