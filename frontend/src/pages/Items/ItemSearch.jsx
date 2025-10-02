import { useState, useContext } from "react";
import { getItems, deleteItem } from "../../api/items.service";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";

const TYPE_MAP = {
  merch: "merch",
  apparel: "apparel",
  manga: "manga",
  figure: "figure"
};

// Función para mapear correctamente el tipo de producto
function mapItemType(item) {
  if (item.apparel) return "apparel";
  if (item.mangaBook) return "manga";
  if (item.figure) return "figure";
  return "merch"; // fallback para productos genéricos
}

export default function ItemSearch({ setMode }) {
  const [items, setItems] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const { role } = useContext(AuthContext);

  const handleSearch = () => {
    if (!search.trim()) return;
    setLoading(true);
    getItems()
      .then(res => {
        const filtered = res.data
          .map(item => ({ ...item, type: mapItemType(item) }))
          .filter(item => (item.name || item.nombre || item.title || "")
            .toLowerCase().includes(search.toLowerCase()));
        console.log("🔍 Search results:", filtered);
        setItems(filtered);
      })
      .catch(() => setError("Error loading items"))
      .finally(() => setLoading(false));
  };

  const handleDelete = async (item) => {
    if (!window.confirm(`Are you sure you want to delete "${item.name || item.title}"?`)) return;
    try {
      const apiType = TYPE_MAP[item.type];
      if (!apiType) throw new Error("Unknown product type");
      console.log("🗑️ Trying to delete item:", item.id, "with apiType:", apiType);
      await deleteItem(item.id, apiType);
      handleSearch(); // recarga la búsqueda
    } catch (err) {
      console.error("❌ Failed to delete item:", err);
      alert("Failed to delete item");
    }
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", width: "100vw", padding: "80px 20px 20px" }}>
      <div style={{ maxWidth: "600px", width: "100%", textAlign: "center" }}>
        <h1>Search Items</h1>
        <input
          type="text"
          placeholder="Type item name..."
          value={search}
          onChange={e => setSearch(e.target.value)}
          style={{ padding: "10px", width: "100%", marginBottom: "10px" }}
        />
        <button onClick={handleSearch} style={{ padding: "10px 20px" }}>Search</button>

        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}

        {items.length > 0 && (
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