package com.alevel;

import com.alevel.model.DriverLicence;
import com.alevel.model.Person;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        final DriverLicence driverLicence = DriverLicence.builder()
                .categories(new String[]{"A", "B", "C"})
                .expireDate(Instant.parse("2021-11-30T18:35:24.00Z"))
                .build();
        final Person person = Person.builder()
                .firstName("John")
                .lastName("Smith")
                .driverLicence(Optional.ofNullable(driverLicence))
                .build();

        Instant currentDate = Instant.now();
        Optional.ofNullable(person)
                .flatMap(Person::getDriverLicence)
                .filter(driverLicence1 -> currentDate.isBefore(driverLicence1.getExpireDate()))
                .map(DriverLicence::getCategories)
                .ifPresentOrElse(categories -> System.out.println(Arrays.toString(categories)),
                        () -> {
                            throw new RuntimeException("Driver license is not valid");
                        });
    }
}
