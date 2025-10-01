import api from "./api";

export const getItems = () => api.get("/products");

export const getItemById = (id) => api.get(`/products/${id}`);

export const createItem = (data) => api.post("/products", data);

export const updateItem = (id, data) => api.put(`/products/${id}`, data);
