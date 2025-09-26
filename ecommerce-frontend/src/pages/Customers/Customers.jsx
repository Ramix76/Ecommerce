// components/Customers.js
import { useEffect, useState } from "react";
import api from "../../api/api";

export default function Customers() {
  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const res = await api.get("/customers");
        setCustomers(res.data);
      } catch (err) {
        console.error(err);
        alert("Error loading customers. Are you logged in?");
      } finally {
        setLoading(false);
      }
    };
    fetchCustomers();
  }, []);

  if (loading) return <p>Loading customers...</p>;

  return (
    <div style={{ padding: "20px" }}>
      <h1>Customers</h1>
      <ul>
        {customers.map(c => (
          <li key={c.id}>
            <strong>{c.name}</strong> - Email: {c.email}
          </li>
        ))}
      </ul>
    </div>
  );
}