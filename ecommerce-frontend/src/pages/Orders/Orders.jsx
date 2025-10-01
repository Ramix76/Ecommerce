import { useContext, useState } from "react";
import { CartContext } from "../../context/CartContext";
import { AuthContext } from "../../context/AuthContext";
import api from "../../api/api";

export default function Orders() {
  const { cart, clearCart, removeFromCart } = useContext(CartContext);
  const { token, username } = useContext(AuthContext);
  const [loading, setLoading] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [orderId, setOrderId] = useState(null);

  const total = cart.reduce((sum, p) => sum + p.price, 0);

  const handleConfirmOrder = async () => {
    if (cart.length === 0) {
      alert("Your order is empty.");
      return;
    }

    setLoading(true);
    try {
      const merchProductIds = cart.map((p) => p.id);
      const quantities = cart.map(() => 1); // 1 por producto

      const res = await api.post(
        "/orders",
        { merchProductIds, quantities },
        { headers: { Authorization: `Bearer ${token}` } }
      );

      setOrderId(res.data.id);
      setShowModal(true);
      clearCart();
    } catch (err) {
      console.error("Failed to confirm order:", err.response || err);
      alert("Failed to confirm order. Make sure your user has a Customer associated.");
    } finally {
      setLoading(false);
    }
  };

  const closeModal = () => setShowModal(false);

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        width: "100vw",
        paddingTop: "80px",
        padding: "20px",
        boxSizing: "border-box",
      }}
    >
      <div style={{ maxWidth: "600px", width: "100%", textAlign: "center" }}>
        <h1>Your Order</h1>
        {cart.length === 0 ? (
          <p>Your order is empty. Add products first.</p>
        ) : (
          <>
            <ul style={{ listStyle: "none", padding: 0 }}>
              {cart.map((p, idx) => (
                <li key={idx} style={{ marginBottom: "8px" }}>
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
            <p>
              <strong>Total: ${total.toFixed(2)}</strong>
            </p>
          </>
        )}
        <button
          onClick={handleConfirmOrder}
          disabled={loading || cart.length === 0}
          style={{ marginTop: "10px" }}
        >
          {loading ? "Processing..." : "Confirm Order"}
        </button>
        {cart.length > 0 && (
          <button
            onClick={clearCart}
            disabled={loading}
            style={{ marginLeft: "10px", marginTop: "10px" }}
          >
            Clear Cart
          </button>
        )}
      </div>

      {/* Modal */}
      {showModal && (
        <div
          style={{
            position: "fixed",
            top: 0,
            left: 0,
            width: "100vw",
            height: "100vh",
            backgroundColor: "rgba(0,0,0,0.5)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            zIndex: 1000,
          }}
          onClick={closeModal}
        >
          <div
            style={{
              backgroundColor: "#fff",
              color: "#333",
              padding: "30px 20px",
              borderRadius: "10px",
              maxWidth: "400px",
              width: "90%",
              textAlign: "center",
              boxShadow: "0 4px 15px rgba(0,0,0,0.3)",
            }}
            onClick={(e) => e.stopPropagation()}
          >
            <h2 style={{ marginBottom: "15px" }}>
              Thank you for your purchase, {username}!
            </h2>
            <p style={{ marginBottom: "10px" }}>
              Your order has been successfully placed.
            </p>
            <p style={{ marginBottom: "15px" }}>Order ID: {orderId}</p>
            <button
              onClick={closeModal}
              style={{
                padding: "10px 20px",
                backgroundColor: "#007bff",
                color: "#fff",
                border: "none",
                borderRadius: "5px",
                cursor: "pointer",
              }}
            >
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
}