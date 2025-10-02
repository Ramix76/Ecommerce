import api from "./api";

// ----------------- GET -----------------
export const getItems = () => api.get("/products");
export const getItemById = (id) => api.get(`/products/${id}`);

// ----------------- CREATE -----------------
export const createApparel = (data) => api.post("/products/apparel", data);
export const createFigure = (data) => api.post("/products/figures", data);
export const createManga = (data) => api.post("/products/manga", data);
export const createMerch = (data) => api.post("/products", data); // Para productos genéricos

// ----------------- UPDATE -----------------
export const updateItem = (id, data, type) => {
  switch (type) {
    case "apparel":
      return api.put(`/products/apparel/${id}`, data);
    case "figure":
      return api.put(`/products/figures/${id}`, data);
    case "manga":
      return api.put(`/products/manga/${id}`, data);
    case "merch":
      return api.put(`/products/${id}`, data);
    default:
      throw new Error("Unknown product type");
  }
};

// ----------------- DELETE -----------------
export const deleteItem = (id, type) => {
  switch (type) {
    case "apparel":
      return api.delete(`/products/apparel/${id}`);
    case "figure":
      return api.delete(`/products/figures/${id}`);
    case "manga":
      return api.delete(`/products/manga/${id}`);
    case "merch":
      return api.delete(`/products/${id}`);
    default:
      throw new Error("Unknown product type");
  }
};