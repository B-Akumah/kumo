package edu.csis.services;

/**
 * UtilityService provides various utility methods for the application.
 * This class contains a method to retrieve a list of US states.
 *
 * @author bakumah
 */
public class UtilityService {

    /**
     * Retrieves a list of US states as an array of strings.
     *
     * @return An array of strings representing the names of US states.
     */
    public String[] getStates() {
        return new String[]{
                "Alabama", "Alaska", "Arizona", "Arkansas", "California",
                "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
                "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
                "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
                "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
                "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
                "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
                "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
        };
    }
}
