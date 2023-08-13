package com.LinkTree.LinktreeClone.Model;

import javax.persistence.*;
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

    @Column(nullable = false ,unique = true)
    private String title;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Long userId;

}
