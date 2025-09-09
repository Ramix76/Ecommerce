package store.ecommerce.service.interfaces;

import store.ecommerce.dto.merchProductDTO.MerchProductRequestDTO;
import store.ecommerce.dto.merchProductDTO.MerchProductResponseDTO;
import store.ecommerce.dto.merchProductDTO.MerchProductUpdateDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookRequestDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookResponseDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookUpdateDTO;
import store.ecommerce.dto.figureDTO.FigureRequestDTO;
import store.ecommerce.dto.figureDTO.FigureResponseDTO;
import store.ecommerce.dto.figureDTO.FigureUpdateDTO;
import store.ecommerce.dto.apparelDTO.ApparelRequestDTO;
import store.ecommerce.dto.apparelDTO.ApparelResponseDTO;
import store.ecommerce.dto.apparelDTO.ApparelUpdateDTO;

import java.util.List;

public interface MerchProductService {

    List<MerchProductResponseDTO> findAll();

    MerchProductResponseDTO findById(Long id);

    MerchProductResponseDTO create(MerchProductRequestDTO request);

    MerchProductResponseDTO update(Long id, MerchProductUpdateDTO update);

    void delete(Long id);

    // MangaBook specific
    List<MangaBookResponseDTO> findAllMangaBooks();
    MangaBookResponseDTO findMangaBookById(Long id);
    MangaBookResponseDTO createMangaBook(MangaBookRequestDTO request);
    MangaBookResponseDTO updateMangaBook(Long id, MangaBookUpdateDTO update);
    void deleteMangaBook(Long id);

    // Figure specific
    List<FigureResponseDTO> findAllFigures();
    FigureResponseDTO findFigureById(Long id);
    FigureResponseDTO createFigure(FigureRequestDTO request);
    FigureResponseDTO updateFigure(Long id, FigureUpdateDTO update);
    void deleteFigure(Long id);

    // Apparel specific
    List<ApparelResponseDTO> findAllApparels();
    ApparelResponseDTO findApparelById(Long id);
    ApparelResponseDTO createApparel(ApparelRequestDTO request);
    ApparelResponseDTO updateApparel(Long id, ApparelUpdateDTO update);
    void deleteApparel(Long id);
}
