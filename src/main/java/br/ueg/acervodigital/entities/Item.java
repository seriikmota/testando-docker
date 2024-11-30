package br.ueg.acervodigital.entities;

import br.ueg.genericarchitecture.domain.GenericModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item implements GenericModel<Long> {
    public final static String SEQUENCE_NAME = "item_sequence";

    @Id
    @SequenceGenerator(
            name=SEQUENCE_NAME,
            sequenceName = SEQUENCE_NAME + "_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = SEQUENCE_NAME
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @Column(name = "number_code")
    private String numberCode;

    @Column(name = "collector")
    private String collector;

    @Column(name = "colleaction_year")
    private LocalDate colleactionYear;

    @Column(name = "collection")
    private String collection;

    @Column(name = "provenance")
    private String provenance; // Procedência

    @Column(name = "location")
    private String location;

    @Column(name = "period")
    private String period;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "approval")
    private Boolean approval;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "taxonomy")
    private String taxonomy;

    @Column(name = "heritage_date")
    private LocalDate heritageDate; // Data do Tombamento

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "item")
    private List<ItemImage> images;
}