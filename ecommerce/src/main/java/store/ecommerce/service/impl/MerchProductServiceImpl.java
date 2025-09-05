package store.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
import store.ecommerce.exception.ResourceNotFoundException;
import store.ecommerce.model.*;
import store.ecommerce.repository.*;
import store.ecommerce.service.interfaces.MerchProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MerchProductServiceImpl implements MerchProductService {

    private final MerchProductRepository merchProductRepository;
    private final MangaBookRepository mangaBookRepository;
    private final FigureRepository figureRepository;
    private final ApparelRepository apparelRepository;

    // ----------------- GENERAL MERCHPRODUCT -----------------
    @Override
    public List<MerchProductResponseDTO> findAll() {
        return merchProductRepository.findAll()
                .stream()
                .map(this::mapMerchProductToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MerchProductResponseDTO findById(Long id) {
        MerchProduct product = merchProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MerchProduct not found"));
        return mapMerchProductToResponse(product);
    }

    @Override
    public MerchProductResponseDTO create(MerchProductRequestDTO request) {
        MerchProduct product = new MerchProduct() {};
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        MerchProduct saved = merchProductRepository.save(product);
        return mapMerchProductToResponse(saved);
    }

    @Override
    public MerchProductResponseDTO update(Long id, MerchProductUpdateDTO update) {
        MerchProduct product = merchProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MerchProduct not found"));
        if (update.getPrice() != null) product.setPrice(update.getPrice());
        if (update.getDescription() != null) product.setDescription(update.getDescription());
        MerchProduct updated = merchProductRepository.save(product);
        return mapMerchProductToResponse(updated);
    }

    @Override
    public void delete(Long id) {
        MerchProduct product = merchProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MerchProduct not found"));
        merchProductRepository.delete(product);
    }

    private MerchProductResponseDTO mapMerchProductToResponse(MerchProduct product) {
        MerchProductResponseDTO dto = new MerchProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        return dto;
    }

    // ----------------- MANGA BOOK -----------------
    @Override
    public List<MangaBookResponseDTO> findAllMangaBooks() {
        return mangaBookRepository.findAll()
                .stream()
                .map(this::mapMangaBookToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MangaBookResponseDTO findMangaBookById(Long id) {
        MangaBook book = mangaBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MangaBook not found"));
        return mapMangaBookToResponse(book);
    }

    @Override
    public MangaBookResponseDTO createMangaBook(MangaBookRequestDTO request) {
        MangaBook book = new MangaBook();
        book.setName(request.getName());
        book.setPrice(request.getPrice());
        book.setDescription(request.getDescription());
        book.setAuthor(request.getAuthor());
        book.setVolumeNumber(request.getVolumeNumber());
        book.setPublisher(request.getPublisher());
        MangaBook saved = mangaBookRepository.save(book);
        return mapMangaBookToResponse(saved);
    }

    @Override
    public MangaBookResponseDTO updateMangaBook(Long id, MangaBookUpdateDTO update) {
        MangaBook book = mangaBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MangaBook not found"));
        if (update.getPrice() != null) book.setPrice(update.getPrice());
        if (update.getDescription() != null) book.setDescription(update.getDescription());
        MangaBook updated = mangaBookRepository.save(book);
        return mapMangaBookToResponse(updated);
    }

    @Override
    public void deleteMangaBook(Long id) {
        MangaBook book = mangaBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MangaBook not found"));
        mangaBookRepository.delete(book);
    }

    private MangaBookResponseDTO mapMangaBookToResponse(MangaBook book) {
        MangaBookResponseDTO dto = new MangaBookResponseDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setPrice(book.getPrice());
        dto.setDescription(book.getDescription());
        dto.setAuthor(book.getAuthor());
        dto.setVolumeNumber(book.getVolumeNumber());
        dto.setPublisher(book.getPublisher());
        return dto;
    }

    // ----------------- FIGURE -----------------
    @Override
    public List<FigureResponseDTO> findAllFigures() {
        return figureRepository.findAll()
                .stream()
                .map(this::mapFigureToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FigureResponseDTO findFigureById(Long id) {
        Figure figure = figureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Figure not found"));
        return mapFigureToResponse(figure);
    }

    @Override
    public FigureResponseDTO createFigure(FigureRequestDTO request) {
        Figure figure = new Figure();
        figure.setName(request.getName());
        figure.setPrice(request.getPrice());
        figure.setDescription(request.getDescription());
        figure.setBrand(request.getBrand());
        figure.setCharacter(request.getCharacter());
        figure.setScale(request.getScale());
        Figure saved = figureRepository.save(figure);
        return mapFigureToResponse(saved);
    }

    @Override
    public FigureResponseDTO updateFigure(Long id, FigureUpdateDTO update) {
        Figure figure = figureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Figure not found"));
        if (update.getPrice() != null) figure.setPrice(update.getPrice());
        if (update.getDescription() != null) figure.setDescription(update.getDescription());
        Figure updated = figureRepository.save(figure);
        return mapFigureToResponse(updated);
    }

    @Override
    public void deleteFigure(Long id) {
        Figure figure = figureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Figure not found"));
        figureRepository.delete(figure);
    }

    private FigureResponseDTO mapFigureToResponse(Figure figure) {
        FigureResponseDTO dto = new FigureResponseDTO();
        dto.setId(figure.getId());
        dto.setName(figure.getName());
        dto.setPrice(figure.getPrice());
        dto.setDescription(figure.getDescription());
        dto.setBrand(figure.getBrand());
        dto.setCharacter(figure.getCharacter());
        dto.setScale(figure.getScale());
        return dto;
    }

    // ----------------- APPAREL -----------------
    @Override
    public List<ApparelResponseDTO> findAllApparels() {
        return apparelRepository.findAll()
                .stream()
                .map(this::mapApparelToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApparelResponseDTO findApparelById(Long id) {
        Apparel apparel = apparelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apparel not found"));
        return mapApparelToResponse(apparel);
    }

    @Override
    public ApparelResponseDTO createApparel(ApparelRequestDTO request) {
        Apparel apparel = new Apparel();
        apparel.setName(request.getName());
        apparel.setPrice(request.getPrice());
        apparel.setDescription(request.getDescription());
        apparel.setSize(request.getSize());
        apparel.setColor(request.getColor());
        apparel.setType(request.getType());
        Apparel saved = apparelRepository.save(apparel);
        return mapApparelToResponse(saved);
    }

    @Override
    public ApparelResponseDTO updateApparel(Long id, ApparelUpdateDTO update) {
        Apparel apparel = apparelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apparel not found"));
        if (update.getPrice() != null) apparel.setPrice(update.getPrice());
        if (update.getDescription() != null) apparel.setDescription(update.getDescription());
        Apparel updated = apparelRepository.save(apparel);
        return mapApparelToResponse(updated);
    }

    @Override
    public void deleteApparel(Long id) {
        Apparel apparel = apparelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apparel not found"));
        apparelRepository.delete(apparel);
    }

    private ApparelResponseDTO mapApparelToResponse(Apparel apparel) {
        ApparelResponseDTO dto = new ApparelResponseDTO();
        dto.setId(apparel.getId());
        dto.setName(apparel.getName());
        dto.setPrice(apparel.getPrice());
        dto.setDescription(apparel.getDescription());
        dto.setSize(apparel.getSize());
        dto.setColor(apparel.getColor());
        dto.setType(apparel.getType());
        return dto;
    }
}
