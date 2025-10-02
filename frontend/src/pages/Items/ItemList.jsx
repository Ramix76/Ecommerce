import { useEffect, useState, useContext } from "react";
import { getItems, deleteItem } from "../../api/items.service";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";

const TYPE_MAP = {
  merch: "merch",
  apparel: "apparel",
  manga: "manga",
  figure: "figure"
};

function mapItemType(item) {
  if (item.apparel) return "apparel";
  if (item.mangaBook) return "manga";
  if (item.figure) return "figure";
  return "merch";
}

export default function ItemList({ setMode }) {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const { role } = useContext(AuthContext);

  const fetchItems = () => {
    setLoading(true);
    getItems()
      .then(res => {
        const itemsWithType = res.data.map((item, index) => {
          const type = mapItemType(item);
          console.log(`📦 Item mapped: ${index + 1} → type: ${type}`);
          return { ...item, type };
        });
        setItems(itemsWithType);
      })
      .catch(() => setError("Error loading items"))
      .finally(() => setLoading(false));
  };

  useEffect(() => { fetchItems(); }, []);

  const handleDelete = async (item) => {
    if (!window.confirm(`Are you sure you want to delete "${item.name || item.title}"?`)) return;
    try {
      const apiType = TYPE_MAP[item.type];
      if (!apiType) throw new Error("Unknown product type");
      await deleteItem(item.id, apiType);
      fetchItems();
    } catch (err) {
      console.error("❌ Failed to delete item:", err);
      alert("Failed to delete item");
    }
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", width: "100vw", padding: "80px 20px 20px" }}>
      <div style={{ maxWidth: "600px", width: "100%", textAlign: "center" }}>
        <h1>All Items</h1>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {!loading && !error && (
          <ul style={{ listStyle: "none", padding: 0, marginTop: "20px" }}>
            {items.map(item => (
              <li key={item.id} style={{ marginBottom: "8px" }}>
                {item.name || item.nombre || item.title}{" "}
                {role === "ROLE_ADMIN" && (
                  <>
                    <button onClick={() => navigate(`/items/edit/${item.id}`)} style={{ marginLeft: "10px" }}>Edit</button>
                    <button onClick={() => handleDelete(item)} style={{ marginLeft: "10px" }}>Delete</button>
                  </>
                )}
              </li>
            ))}
          </ul>
        )}
        <button onClick={() => setMode(null)} style={{ marginTop: "20px" }}>Back</button>
      </div>
    </div>
  );
}