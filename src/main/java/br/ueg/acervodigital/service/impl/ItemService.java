package br.ueg.acervodigital.service.impl;

import br.ueg.acervodigital.dto.jasper.ImageJasper;
import br.ueg.acervodigital.dto.jasper.ItemJasper;
import br.ueg.acervodigital.dto.list.ItemListDTO;
import br.ueg.acervodigital.dto.request.ItemRequestDTO;
import br.ueg.acervodigital.dto.response.ItemResponseDTO;
import br.ueg.acervodigital.entities.Item;
import br.ueg.acervodigital.entities.ItemImage;
import br.ueg.acervodigital.entities.User;
import br.ueg.acervodigital.mapper.ItemMapper;
import br.ueg.acervodigital.repository.ItemRepository;
import br.ueg.acervodigital.service.IItemService;
import br.ueg.acervodigital.service.IJasperService;
import br.ueg.acervodigital.util.Util;
import br.ueg.acervodigitalarquitetura.dto.CredentialDTO;
import br.ueg.acervodigitalarquitetura.enums.ApiErrorEnum;
import br.ueg.acervodigitalarquitetura.exception.DataException;
import br.ueg.acervodigitalarquitetura.security.impl.CredentialProvider;
import br.ueg.acervodigitalarquitetura.service.impl.AbstractService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.*;

@Service
public class ItemService extends AbstractService<ItemRequestDTO, ItemResponseDTO, ItemListDTO, Item, ItemRepository, ItemMapper, Long>
        implements IItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private IJasperService jasperService;

    @Override
    protected void prepareToCreate(Item data) {
        setUserCreate(data);
        updateItemImages(data);
    }

    @Override
    protected void prepareToUpdate(Item data) {
        updateItemImages(data);
    }

    @Override
    protected void prepareToDelete(Item data) {

    }

    public List<Item> getByDescription(String description) {
        List<Item> temp = repository.findByNameContaining(description);
        if(temp.isEmpty()){
            throw new DataException(ApiErrorEnum.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return temp;
    }

    private void setUserCreate(Item data) {
        User user = new User();
        user.setId(((CredentialDTO) CredentialProvider.newInstance().getCurrentInstance()).getId());
        data.setUser(user);
    }

    public void updateItemImages(Item data) {
        if (data.getImages() != null) {
            for (ItemImage image : data.getImages()) {
                image.setItem(data);
            }
        }
    }

    @Override
    public byte[] exportItemsPdf() throws JRException {
        String file = "/src/main/resources/jasper/AcervoCompleto.jasper";
        List<ItemJasper> itemsJasper = mountObjectsJasper(repository.findAll());
        return exportPdf(file, itemsJasper);
    }

    @Override
    public byte[] exportItemsPdf(Long id) throws JRException {
        String file = "/src/main/resources/jasper/AcervoIndividual.jasper";
        List<ItemJasper> itemsJasper = mountObjectsJasper(List.of(this.validateIdModelExistsAndGet(id)));
        return exportPdf(file, itemsJasper);
    }

    private byte[] exportPdf(String file, List<ItemJasper> itemsJasper) throws JRException {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(itemsJasper);
        return jasperService.generatePdf(file, new HashMap<>(), dataSource);
    }

    private List<ItemJasper> mountObjectsJasper(List<Item> items) {
        List<ItemJasper> itemsJasper = new ArrayList<>();

        for (Item item : items) {
            ItemJasper itemJasper = ItemJasper.builder()
                    .id(item.getId())
                    .description(item.getDescription())
                    .numberCode(item.getNumberCode())
                    .name(item.getName())
                    .colleactionYear(Util.formatDateYear(item.getColleactionYear()))
                    .provenance(item.getProvenance())
                    .period(item.getPeriod())
                    .location(item.getLocation())
                    .taxonomy(item.getTaxonomy())
                    .collection(item.getCollection())
                    .heritageDate(Util.formatDateWithoutHour(item.getHeritageDate()))
                    .collector(item.getCollector())
                    .build();

            if (item.getImages() != null && !item.getImages().isEmpty()) {
                itemJasper.setImageMain(new ByteArrayInputStream(item.getImages().get(0).getImage()));
                if (item.getImages().size() != 1) {
                    List<ImageJasper> imagesJasper = new ArrayList<>();
                    for (ItemImage image : item.getImages()) {
                        if (!Objects.equals(image.getId(), item.getImages().get(0).getId())) {
                            imagesJasper.add(new ImageJasper(new ByteArrayInputStream(image.getImage())));
                        }
                    }
                    itemJasper.setImages(new JRBeanCollectionDataSource(imagesJasper));
                    itemJasper.setImagesPath("/src/main/resources/jasper/ItemImagem.jasper");
                }
            }
            itemsJasper.add(itemJasper);
        }
        return itemsJasper;
    }
}
