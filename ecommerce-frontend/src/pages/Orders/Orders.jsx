import { useEffect, useState } from "react";
import api from "../../api/api";

export default function Orders() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const res = await api.get("/orders"); // Protected route
        setOrders(res.data);
      } catch (err) {
        console.error(err);
        alert("Error loading orders. Are you logged in?");
      } finally {
        setLoading(false);
      }
    };
    fetchOrders();
  }, []);

  if (loading) return <p>Loading orders...</p>;

  return (
    <div style={{ padding: "20px" }}>
      <h1>Orders</h1>
      <ul>
        {orders.map(order => (
          <li key={order.id}>
            <strong>Order #{order.id}</strong> - Date: {new Date(order.date).toLocaleString()} - Total: ${order.total.toFixed(2)} - Customer ID: {order.customerId}
            <br />
            Product IDs: {order.merchProductIds.join(", ")}
          </li>
        ))}
      </ul>
    </div>
  );
}