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
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        width: "100vw",
        paddingTop: "80px",
        paddingLeft: "20px",
        paddingRight: "20px",
        boxSizing: "border-box",
      }}
    >
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          gap: "20px",
          justifyContent: "center",
          maxWidth: "1000px",
        }}
      >
        {figures.map((f) => (
          <ProductCard key={f.id} product={f} />
        ))}
      </div>
    </div>
  );
}