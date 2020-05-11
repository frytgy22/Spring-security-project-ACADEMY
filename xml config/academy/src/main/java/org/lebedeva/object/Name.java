package org.lebedeva.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    private String name;
    private String surname;

    public static Name splitFullName(String text) {

        List<String> names = Arrays.stream(text.split(" "))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .limit(2)
                .collect(Collectors.toList());

        String name = names.size() > 0 ? names.get(0) : "";
        String surname = names.size() == 2 ? names.get(1) : null;

        return new Name(name, surname);
    }

    public boolean isHasSurname() {
        return this.surname != null;
    }
}
