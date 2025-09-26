import { useEffect, useState } from "react";
import api from "../../api/api";
import ProductCard from "./ProductCard";

export default function Figures() {
  const [figures, setFigures] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchFigures = async () => {
      try {
        const res = await api.get("/products/figures");
        setFigures(res.data);
      } catch (err) {
        console.error(err);
        alert("Error loading figures");
      } finally {
        setLoading(false);
      }
    };
    fetchFigures();
  }, []);

  if (loading) return <p>Loading figures...</p>;

  return (
    <div style={{ display: "flex", flexWrap: "wrap" }}>
      {figures.map(f => (
        <ProductCard key={f.id} product={f} />
      ))}
    </div>
  );
}