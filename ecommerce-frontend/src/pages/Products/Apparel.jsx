import { useEffect, useState } from "react";
import api from "../../api/api";
import ProductCard from "./ProductCard";

export default function Apparel() {
  const [apparel, setApparel] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchApparel = async () => {
      try {
        const res = await api.get("/products/apparel");
        setApparel(res.data);
      } catch (err) {
        console.error(err);
        alert("Error al cargar apparel");
      } finally {
        setLoading(false);
      }
    };
    fetchApparel();
  }, []);

  if (loading) return <p>Cargando apparel...</p>;

  return (
    <div style={{ display: "flex", flexWrap: "wrap" }}>
      {apparel.map(a => (
        <ProductCard key={a.id} product={a} />
      ))}
    </div>
  );
}