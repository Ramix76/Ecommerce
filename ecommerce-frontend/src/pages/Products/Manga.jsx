import { useEffect, useState } from "react";
import api from "../../api/api";
import ProductCard from "./ProductCard";

export default function Manga() { // cambia el nombre a Figures o Apparel según corresponda
  const [mangaBooks, setMangaBooks] = useState([]); // figures o apparel
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await api.get("/products/manga"); // "/products/figures" o "/products/apparel"
        setMangaBooks(res.data); // figures o apparel
      } catch (err) {
        console.error(err);
        alert("Error loading data");
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  if (loading) return <p>Loading...</p>;

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
        {mangaBooks.map((m) => (
          <ProductCard key={m.id} product={m} />
        ))}
      </div>
    </div>
  );
}
