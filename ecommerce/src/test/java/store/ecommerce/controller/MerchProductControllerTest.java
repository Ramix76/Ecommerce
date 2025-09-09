package store.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import store.ecommerce.dto.apparelDTO.ApparelRequestDTO;
import store.ecommerce.dto.apparelDTO.ApparelResponseDTO;
import store.ecommerce.dto.apparelDTO.ApparelUpdateDTO;
import store.ecommerce.dto.figureDTO.FigureRequestDTO;
import store.ecommerce.dto.figureDTO.FigureResponseDTO;
import store.ecommerce.dto.figureDTO.FigureUpdateDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookRequestDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookResponseDTO;
import store.ecommerce.dto.mangaBookDTO.MangaBookUpdateDTO;
import store.ecommerce.dto.merchProductDTO.MerchProductResponseDTO;
import store.ecommerce.service.interfaces.MerchProductService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MerchProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MerchProductService merchProductService;

    @InjectMocks
    private MerchProductController merchProductController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(merchProductController).build();
    }

    // ----------------- GENERAL MERCHPRODUCT -----------------
    @Test
    void testGetAllProducts() throws Exception {
        MerchProductResponseDTO dto = new MerchProductResponseDTO();
        dto.setId(1L);
        dto.setName("Product1");
        dto.setPrice(100.0);
        dto.setDescription("A sample product");

        when(merchProductService.findAll()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Product1"));

        verify(merchProductService, times(1)).findAll();
    }

    @Test
    void testGetProductById() throws Exception {
        MerchProductResponseDTO dto = new MerchProductResponseDTO();
        dto.setId(1L);
        dto.setName("Product1");
        dto.setPrice(100.0);
        dto.setDescription("A sample product");

        when(merchProductService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Product1"));

        verify(merchProductService, times(1)).findById(1L);
    }

    // ----------------- MANGA BOOK -----------------
    @Test
    void testGetAllMangaBooks() throws Exception {
        MangaBookResponseDTO dto = new MangaBookResponseDTO();
        dto.setId(1L);
        dto.setName("Naruto");
        dto.setPrice(9.99);
        dto.setDescription("Popular manga");
        dto.setAuthor("Masashi Kishimoto");
        dto.setVolumeNumber(1);
        dto.setPublisher("Shueisha");

        when(merchProductService.findAllMangaBooks()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/products/manga"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Naruto"));

        verify(merchProductService, times(1)).findAllMangaBooks();
    }

    @Test
    void testCreateMangaBook() throws Exception {
        MangaBookRequestDTO request = new MangaBookRequestDTO();
        request.setName("Naruto");
        request.setPrice(9.99);
        request.setDescription("Popular manga");
        request.setAuthor("Masashi Kishimoto");
        request.setVolumeNumber(1);
        request.setPublisher("Shueisha");

        MangaBookResponseDTO response = new MangaBookResponseDTO();
        response.setId(1L);
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setDescription(request.getDescription());
        response.setAuthor(request.getAuthor());
        response.setVolumeNumber(request.getVolumeNumber());
        response.setPublisher(request.getPublisher());

        when(merchProductService.createMangaBook(any(MangaBookRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/products/manga")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Naruto"))
                .andExpect(jsonPath("$.author").value("Masashi Kishimoto"));

        verify(merchProductService, times(1)).createMangaBook(any(MangaBookRequestDTO.class));
    }

    @Test
    void testUpdateMangaBook() throws Exception {
        MangaBookUpdateDTO update = new MangaBookUpdateDTO();
        update.setPrice(12.0);
        update.setDescription("Updated description");

        MangaBookResponseDTO response = new MangaBookResponseDTO();
        response.setId(1L);
        response.setName("Naruto");
        response.setPrice(update.getPrice());
        response.setDescription(update.getDescription());
        response.setAuthor("Masashi Kishimoto");
        response.setVolumeNumber(1);
        response.setPublisher("Shueisha");

        when(merchProductService.updateMangaBook(eq(1L), any(MangaBookUpdateDTO.class))).thenReturn(response);

        mockMvc.perform(put("/api/products/manga/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(12.0));

        verify(merchProductService, times(1)).updateMangaBook(eq(1L), any(MangaBookUpdateDTO.class));
    }

    @Test
    void testDeleteMangaBook() throws Exception {
        doNothing().when(merchProductService).deleteMangaBook(1L);

        mockMvc.perform(delete("/api/products/manga/1"))
                .andExpect(status().isNoContent());

        verify(merchProductService, times(1)).deleteMangaBook(1L);
    }

    // ----------------- FIGURE -----------------
    @Test
    void testGetAllFigures() throws Exception {
        FigureResponseDTO dto = new FigureResponseDTO();
        dto.setId(1L);
        dto.setName("Goku Figure");
        dto.setPrice(29.99);
        dto.setDescription("Action figure");
        dto.setBrand("Bandai");
        dto.setCharacter("Goku");
        dto.setScale("1/12");

        when(merchProductService.findAllFigures()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/products/figures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Goku Figure"));

        verify(merchProductService, times(1)).findAllFigures();
    }

    @Test
    void testCreateFigure() throws Exception {
        FigureRequestDTO request = new FigureRequestDTO();
        request.setName("Goku Figure");
        request.setPrice(29.99);
        request.setDescription("Action figure");
        request.setBrand("Bandai");
        request.setCharacter("Goku");
        request.setScale("1/12");

        FigureResponseDTO response = new FigureResponseDTO();
        response.setId(1L);
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setDescription(request.getDescription());
        response.setBrand(request.getBrand());
        response.setCharacter(request.getCharacter());
        response.setScale(request.getScale());

        when(merchProductService.createFigure(any(FigureRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/products/figures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Goku Figure"));

        verify(merchProductService, times(1)).createFigure(any(FigureRequestDTO.class));
    }

    @Test
    void testUpdateFigure() throws Exception {
        FigureUpdateDTO update = new FigureUpdateDTO();
        update.setPrice(35.0);
        update.setDescription("Updated description");

        FigureResponseDTO response = new FigureResponseDTO();
        response.setId(1L);
        response.setName("Goku Figure");
        response.setPrice(update.getPrice());
        response.setDescription(update.getDescription());
        response.setBrand("Bandai");
        response.setCharacter("Goku");
        response.setScale("1/12");

        when(merchProductService.updateFigure(eq(1L), any(FigureUpdateDTO.class))).thenReturn(response);

        mockMvc.perform(put("/api/products/figures/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.0));

        verify(merchProductService, times(1)).updateFigure(eq(1L), any(FigureUpdateDTO.class));
    }

    @Test
    void testDeleteFigure() throws Exception {
        doNothing().when(merchProductService).deleteFigure(1L);

        mockMvc.perform(delete("/api/products/figures/1"))
                .andExpect(status().isNoContent());

        verify(merchProductService, times(1)).deleteFigure(1L);
    }

    // ----------------- APPAREL -----------------
    @Test
    void testGetAllApparels() throws Exception {
        ApparelResponseDTO dto = new ApparelResponseDTO();
        dto.setId(1L);
        dto.setName("T-Shirt");
        dto.setPrice(19.99);
        dto.setDescription("Cotton T-Shirt");
        dto.setSize("M");
        dto.setColor("Red");
        dto.setType("Shirt");

        when(merchProductService.findAllApparels()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/products/apparel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("T-Shirt"));

        verify(merchProductService, times(1)).findAllApparels();
    }

    @Test
    void testCreateApparel() throws Exception {
        ApparelRequestDTO request = new ApparelRequestDTO();
        request.setName("T-Shirt");
        request.setPrice(19.99);
        request.setDescription("Cotton T-Shirt");
        request.setSize("M");
        request.setColor("Red");
        request.setType("Shirt");

        ApparelResponseDTO response = new ApparelResponseDTO();
        response.setId(1L);
        response.setName(request.getName());
        response.setPrice(request.getPrice());
        response.setDescription(request.getDescription());
        response.setSize(request.getSize());
        response.setColor(request.getColor());
        response.setType(request.getType());

        when(merchProductService.createApparel(any(ApparelRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/products/apparel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("T-Shirt"));

        verify(merchProductService, times(1)).createApparel(any(ApparelRequestDTO.class));
    }

    @Test
    void testUpdateApparel() throws Exception {
        ApparelUpdateDTO update = new ApparelUpdateDTO();
        update.setPrice(22.0);
        update.setDescription("Updated description");

        ApparelResponseDTO response = new ApparelResponseDTO();
        response.setId(1L);
        response.setName("T-Shirt");
        response.setPrice(update.getPrice());
        response.setDescription(update.getDescription());
        response.setSize("M");
        response.setColor("Red");
        response.setType("Shirt");

        when(merchProductService.updateApparel(eq(1L), any(ApparelUpdateDTO.class))).thenReturn(response);

        mockMvc.perform(put("/api/products/apparel/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(22.0));

        verify(merchProductService, times(1)).updateApparel(eq(1L), any(ApparelUpdateDTO.class));
    }

    @Test
    void testDeleteApparel() throws Exception {
        doNothing().when(merchProductService).deleteApparel(1L);

        mockMvc.perform(delete("/api/products/apparel/1"))
                .andExpect(status().isNoContent());

        verify(merchProductService, times(1)).deleteApparel(1L);
    }
}