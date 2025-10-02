import { useState, useContext } from "react";
import api from "../../api/api";
import { AuthContext } from "../../context/AuthContext";

export default function CreateOrder() {
  const { token } = useContext(AuthContext);
  const [productIds, setProductIds] = useState("");
  const [loading, setLoading] = useState(false);

  const handleCreateOrder = async (e) => {
    e.preventDefault();
    setLoading(true);

    try {
      const ids = productIds
        .split(",")
        .map((id) => parseInt(id.trim(), 10))
        .filter((id) => !isNaN(id));

      if (ids.length === 0) {
        alert("Please enter at least one valid product ID.");
        setLoading(false);
        return;
      }

      const quantities = ids.map(() => 1);

      await api.post(
        "/orders",
        { merchProductIds: ids, quantities },
        { headers: { Authorization: `Bearer ${token}` } }
      );

      alert("Order successfully created!");
      setProductIds("");
    } catch (err) {
      console.error("Failed to create order:", err.response || err);
      alert(
        "Failed to create order. Make sure your user has a Customer associated."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "20px", textAlign: "center", minHeight: "100vh" }}>
      <h2>Create Order</h2>
      <form onSubmit={handleCreateOrder} style={{ marginTop: "20px" }}>
        <input
          type="text"
          placeholder="Product IDs (comma separated)"
          value={productIds}
          onChange={(e) => setProductIds(e.target.value)}
          style={{ padding: "10px", width: "250px", borderRadius: "5px" }}
        />
        <button
          type="submit"
          disabled={loading}
          style={{
            marginLeft: "10px",
            padding: "10px 20px",
            borderRadius: "5px",
            backgroundColor: "#007bff",
            color: "#fff",
            border: "none",
            cursor: "pointer",
          }}
        >
          {loading ? "Creating..." : "Create Order"}
        </button>
      </form>
    </div>
  );
}