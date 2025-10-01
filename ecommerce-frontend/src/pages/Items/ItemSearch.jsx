import { useState } from "react";
import { getItems } from "../../api/items.service";
import { useNavigate } from "react-router-dom";

export default function ItemSearch({ setMode }) {
  const [items, setItems] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSearch = () => {
    if (!search.trim()) return;

    setLoading(true);
    getItems()
      .then((res) => {
        const filtered = res.data.filter((item) => {
          const name = item.name || item.nombre || item.title || "";
          return name.toLowerCase().includes(search.toLowerCase());
        });
        setItems(filtered);
      })
      .catch(() => setError("Error loading items"))
      .finally(() => setLoading(false));
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        width: "100vw",
        paddingTop: "80px",
        paddingLeft: "20px",
        paddingRight: "20px",
        boxSizing: "border-box",
      }}
    >
      <div style={{ maxWidth: "600px", width: "100%", textAlign: "center" }}>
        <h1>Search Items</h1>
        <input
          type="text"
          placeholder="Type item name..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          style={{ padding: "10px", width: "100%", marginBottom: "10px" }}
        />
        <button onClick={handleSearch} style={{ padding: "10px 20px" }}>
          Search
        </button>

        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}

        {items.length > 0 && (
          <ul style={{ listStyle: "none", padding: 0, marginTop: "20px" }}>
            {items.map((item) => {
              const name = item.name || item.nombre || item.title;
              return (
                <li key={item.id} style={{ marginBottom: "8px" }}>
                  {name}{" "}
                  <button
                    onClick={() => navigate(`/items/edit/${item.id}`)}
                    style={{ marginLeft: "10px" }}
                  >
                    Edit
                  </button>
                </li>
              );
            })}
          </ul>
        )}

        <button onClick={() => setMode(null)} style={{ marginTop: "20px" }}>
          Back
        </button>
      </div>
    </div>
  );
}