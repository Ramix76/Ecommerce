import { useContext } from "react";
import { CartContext } from "../../context/CartContext";
import { AuthContext } from "../../context/AuthContext";

export default function ProductCard({ product }) {
  const { addToCart } = useContext(CartContext);
  const { token } = useContext(AuthContext);

  const handleAddToCart = () => {
    if (!token) {
      alert("You must be logged in to add products to the cart.");
      return;
    }
    addToCart(product);
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
        border: "1px solid #ccc",
        borderRadius: "8px",
        padding: "10px",
        margin: "10px",
        width: "200px",
        minHeight: "250px",
        boxSizing: "border-box",
      }}
    >
      <div>
        <h3>{product.name || product.title}</h3>
        {product.price && <p>Price: ${product.price.toFixed(2)}</p>}
      </div>
      <button onClick={handleAddToCart}>Add to Order</button>
    </div>
  );
}