package com.fuzzylimes.jsr.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeedRunUsers {
    private List<User> userList;
    private List<Guest> guestList;
}
