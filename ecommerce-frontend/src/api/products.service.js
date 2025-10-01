import api from "./api";

// ---- Existing functions ----
export const getAllProducts = async () => {
  const res = await api.get("/products");
  return res.data;
};

export const getProductById = async (id) => {
  const res = await api.get(`/products/${id}`);
  return res.data;
};

// ---- New functions for creating products ----
export const createApparel = async (data) => {
  const res = await api.post("/products/apparel", data);
  return res.data;
};

export const createFigure = async (data) => {
  const res = await api.post("/products/figures", data);
  return res.data;
};

export const createManga = async (data) => {
  const res = await api.post("/products/manga", data);
  return res.data;
};