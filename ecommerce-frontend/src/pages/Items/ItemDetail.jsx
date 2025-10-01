import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getItemById } from "../../api/items.service";

function ItemDetail() {
  const { id } = useParams();
  const [item, setItem] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getItemById(id)
      .then((res) => setItem(res.data))
      .catch(() => setError("Error loading item"))
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div style={{ paddingTop: "80px" }}>
      <h1>Item Detail: {item.name || item.nombre || item.title}</h1>
      <p>ID: {item.id}</p>
      <p>Price: {item.price || item.precio || "-"}</p>
      <p>Description: {item.description || item.descripcion || "-"}</p>
      <Link to="/items">Back to List</Link>
    </div>
  );
}

export default ItemDetail;