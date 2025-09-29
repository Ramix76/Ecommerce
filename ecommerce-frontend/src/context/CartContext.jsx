import { createContext, useState } from "react";

export const CartContext = createContext();

export function CartProvider({ children }) {
  const [cart, setCart] = useState([]);

  // Añadir producto
  const addToCart = (product) => {
    setCart(prev => [...prev, product]);
  };

  // Eliminar producto por ID
  const removeFromCart = (productId) => {
    setCart(prev => prev.filter(p => p.id !== productId));
  };

  // Vaciar carrito
  const clearCart = () => {
    setCart([]);
  };

  const value = { cart, addToCart, removeFromCart, clearCart };
  return <CartContext.Provider value={value}>{children}</CartContext.Provider>;
}