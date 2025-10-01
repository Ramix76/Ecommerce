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
        alert("Error loading apparel");
      } finally {
        setLoading(false);
      }
    };
    fetchApparel();
  }, []);

  if (loading) return <p>Loading apparel...</p>;

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
        {apparel.map((a) => (
          <ProductCard key={a.id} product={a} />
        ))}
      </div>
    </div>
  );
}