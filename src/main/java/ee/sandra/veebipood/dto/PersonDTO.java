package ee.sandra.veebipood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
}
