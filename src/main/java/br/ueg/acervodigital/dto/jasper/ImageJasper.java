package br.ueg.acervodigital.dto.jasper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ImageJasper {
    private InputStream image;
}
