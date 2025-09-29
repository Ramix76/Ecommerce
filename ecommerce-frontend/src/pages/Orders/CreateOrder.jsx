import { useState } from "react";
import api from "../../api/api";

export default function CreateOrder() {
  const [productIds, setProductIds] = useState("");
  const [loading, setLoading] = useState(false);

  const handleCreateOrder = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const ids = productIds.split(",").map(id => parseInt(id.trim(), 10));
      const res = await api.post("/orders", { merchProductIds: ids });
      alert("Order created! ID: " + res.data.id);
    } catch (err) {
      console.error(err);
      alert("Failed to create order");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Create Order</h2>
      <form onSubmit={handleCreateOrder}>
        <input
          type="text"
          placeholder="Product IDs (comma separated)"
          value={productIds}
          onChange={(e) => setProductIds(e.target.value)}
        />
        <button type="submit" disabled={loading}>
          {loading ? "Creating..." : "Create Order"}
        </button>
      </form>
    </div>
  );
}