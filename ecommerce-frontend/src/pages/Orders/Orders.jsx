import { useContext, useState } from "react";
import { CartContext } from "../../context/CartContext";
import { AuthContext } from "../../context/AuthContext";
import api from "../../api/api";

export default function Orders() {
  const { cart, clearCart, removeFromCart } = useContext(CartContext);
  const { token } = useContext(AuthContext);
  const [loading, setLoading] = useState(false);

  // Calcular total
  const total = cart.reduce((sum, p) => sum + p.price, 0);

  const handleConfirmOrder = async () => {
    if (cart.length === 0) {
      alert("Your order is empty.");
      return;
    }

    setLoading(true);
    try {
      const merchProductIds = cart.map(p => p.id);
      const res = await api.post("/orders", { merchProductIds }, {
        headers: { Authorization: `Bearer ${token}` }
      });
      alert("Order confirmed! ID: " + res.data.id);
      clearCart(); // Vaciar carrito
    } catch (err) {
      console.error(err);
      alert("Failed to confirm order.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Your Order</h1>
      {cart.length === 0 ? (
        <p>Your order is empty. Add products first.</p>
      ) : (
        <>
          <ul>
            {cart.map((p, idx) => (
              <li key={idx}>
                {p.name} - ${p.price.toFixed(2)}
                <button
                  style={{ marginLeft: "10px" }}
                  onClick={() => removeFromCart(p.id)}
                >
                  Remove
                </button>
              </li>
            ))}
          </ul>
          <p><strong>Total: ${total.toFixed(2)}</strong></p>
        </>
      )}
      <button onClick={handleConfirmOrder} disabled={loading || cart.length === 0}>
        {loading ? "Processing..." : "Confirm Order"}
      </button>
      {cart.length > 0 && (
        <button
          onClick={clearCart}
          disabled={loading}
          style={{ marginLeft: "10px" }}
        >
          Clear Cart
        </button>
      )}
    </div>
  );
}