import api from "./api";

// Get all products (merch products)
export const getAllProducts = async () => {
  const res = await api.get("/products");
  return res.data;
};

// Optional: get product details by ID
export const getProductById = async (id) => {
  const res = await api.get(`/products/${id}`);
  return res.data;
};