package com.pes.chaplincinemabackend.entities;

import com.pes.chaplincinemabackend.auth.entities.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Document("user")
@Data
public class Customer extends User {
    public Customer() {
        Bookings = new ArrayList<>();
    }

    private String firstName;
    private String lastName;
    private ArrayList<UUID> Bookings;

}
