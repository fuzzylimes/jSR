package com.fuzzylimes.jsr.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Representation of users on the side, when performing embedded deserialization. Users can either be register or
 * unregistered. All registered users will be added to the userList. All unregistered will be added to the guestList
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeedRunUsers {
    /** List of all of the registered users for this run */
    private List<User> userList;
    /** List of all of the guest users for this run */
    private List<Guest> guestList;
}
