package edu.csis.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Address class represents a physical address with various properties such as street lines, city, state, zip code, and nation (country).
 * This class is used as a component within User.
 *
 * @author bakumah
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    /**
     * The first line of the street address.
     */
    String streetLine1;

    /**
     * The second line of the street address (optional).
     */
    String streetLine2;

    /**
     * The city where the address is located.
     */
    String city;

    /**
     * The state where the address is located.
     */
    String state;

    /**
     * The ZIP code of the address.
     */
    String zipCode;

    /**
     * The nation (country) where the address is located.
     */
    String nation;
}
