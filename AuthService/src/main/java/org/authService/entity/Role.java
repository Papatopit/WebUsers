package org.authService.entity;

import jakarta.persistence.*;
import lombok.*;
import org.authService.response.UserResponse;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;

    private UserResponse userResponse;
}
