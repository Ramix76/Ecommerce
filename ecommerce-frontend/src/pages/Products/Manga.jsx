import { useEffect, useState } from "react";
import api from "../../api/api";
import ProductCard from "./ProductCard";

export default function Manga() {
  const [mangaBooks, setMangaBooks] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchManga = async () => {
      try {
        const res = await api.get("/products/manga");
        setMangaBooks(res.data);
      } catch (err) {
        console.error(err);
        alert("Error loading manga books");
      } finally {
        setLoading(false);
      }
    };
    fetchManga();
  }, []);

  if (loading) return <p>Loading manga books...</p>;

  return (
    <div style={{ display: "flex", flexWrap: "wrap" }}>
      {mangaBooks.map(m => (
        <ProductCard key={m.id} product={m} />
      ))}
    </div>
  );
}