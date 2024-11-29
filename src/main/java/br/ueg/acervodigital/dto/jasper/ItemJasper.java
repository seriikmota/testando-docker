package br.ueg.acervodigital.dto.jasper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ItemJasper {
    private Long id;
    private String description;
    private String numberCode;
    private String name;
    private String colleactionYear;
    private String provenance;
    private String period;
    private String location;
    private String taxonomy;
    private String collection;
    private String heritageDate;
    private String collector;
    private InputStream imageMain;
    private String imagesPath;
    private JRBeanCollectionDataSource images;
}
