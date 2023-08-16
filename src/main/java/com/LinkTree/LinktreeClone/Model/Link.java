package com.LinkTree.LinktreeClone.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title can't be blank")
    @NotNull
    @Column(nullable = false)
    private String title;


    @NotBlank(message = "Url can't be blank")
    @Column(nullable = false)
    @NotNull
    private String url;

    @NotNull
    private Long userId;

}
