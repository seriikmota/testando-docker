package br.ueg.acervodigital.entities;

import br.ueg.acervodigitalarquitetura.domain.GenericModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_images")
public class ItemImage implements GenericModel<Long> {
    public final static String SEQUENCE_NAME = "itemimages_sequence";

    public static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpeg", "image/png");

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
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_id"))
    private Item item;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    @Column(name = "image")
    private byte[] image;
}
